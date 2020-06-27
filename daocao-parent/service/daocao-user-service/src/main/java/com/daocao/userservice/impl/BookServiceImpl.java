package com.daocao.userservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.daocao.dao.AccountMapper;
import com.daocao.dao.BookMapper;
import com.daocao.dao.OrderMapper;
import com.daocao.entity.*;
import com.daocao.exception.DataOperateException;
import com.daocao.exception.SolrOperateException;
import com.daocao.myentity.PageResult;
import com.daocao.user.service.BookService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    SolrClient solrClient;

    /**
     * 查询全部
     */
    @Override
    public List<Book> findAll() {
        return bookMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Book> page = (Page<Book>) bookMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 书籍发布
     * @param book 书籍信息
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void add(Book book) {
        initBook(book);
        // 存储到数据库
        int insert = bookMapper.insert(book);
        checkResult(insert,"书籍发布失败");
        // 生成静态页面
        rabbitTemplate.convertAndSend("bookTopicExchange", "topic.bookGene",
                new Integer[]{book.getId()});
        // 加入到Solr索引库
        try {
            solrClient.add(initSolrBook(book));
            solrClient.commit();
        } catch (Exception e) {
            throw new SolrOperateException("加入Solr索引库失败:" + e.getMessage());
        }
    }

    /**
     * 设置书籍基本信息
     *
     * @param book
     */
    public void initBook(Book book) {
        // 先按照默认审核通过实现
        book.setIsaudit("1");
        book.setIssell("1");
    }

    /**
     * 修改
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void update(Book book) {
        int update = bookMapper.updateByPrimaryKey(book);
        checkResult(update,"书籍更新失败");
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Book findOne(Integer id, String username) {
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andSellerEqualTo(username);
        List<Book> books = bookMapper.selectByExample(example);
        if (books.isEmpty()) {
            return null;
        }
        return books.get(0);
    }

    @Override
    public Book findOne(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer[] ids) {

        // 数据库中删除该书籍
        for (Integer id : ids) {
            int delete = bookMapper.deleteByPrimaryKey(id);
            checkResult(delete,"书籍删除失败");

        }
        // 删除书籍的静态页
        rabbitTemplate.convertAndSend("bookTopicExchange", "topic.bookDelete", ids);

        // 从Solr索引库中删除书籍
        try {
            for (Integer id : ids) {
                solrClient.deleteByQuery("id:" + id);
            }
            solrClient.commit();
        } catch (Exception e) {
            throw new RuntimeException("从索引库删除失败:" + e.getMessage());
        }
    }

    @Override
    public PageResult findPage(Book book, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();

        if (book != null) {
            if (book.getName() != null && book.getName().length() > 0) {
                criteria.andNameLike("%" + book.getName() + "%");
            }
            if (book.getAuthor() != null && book.getAuthor().length() > 0) {
                criteria.andAuthorLike("%" + book.getAuthor() + "%");
            }
            if (book.getPublisher() != null && book.getPublisher().length() > 0) {
                criteria.andPublisherLike("%" + book.getPublisher() + "%");
            }
            if (book.getVersion() != null && book.getVersion().length() > 0) {
                criteria.andVersionLike("%" + book.getVersion() + "%");
            }
            if (book.getIsbn() != null && book.getIsbn().length() > 0) {
                criteria.andIsbnLike("%" + book.getIsbn() + "%");
            }
            if (book.getBindtype() != null && book.getBindtype().length() > 0) {
                criteria.andBindtypeLike("%" + book.getBindtype() + "%");
            }
            if (book.getPapersize() != null && book.getPapersize().length() > 0) {
                criteria.andPapersizeLike("%" + book.getPapersize() + "%");
            }
            if (book.getSeller() != null && book.getSeller().length() > 0) {
                criteria.andSellerLike("%" + book.getSeller() + "%");
            }
            if (book.getIssell() != null && book.getIssell().length() > 0) {
                criteria.andIssellLike("%" + book.getIssell() + "%");
            }
            if (book.getBookimages() != null && book.getBookimages().length() > 0) {
                criteria.andBookimagesLike("%" + book.getBookimages() + "%");
            }
            if (book.getDescription() != null && book.getDescription().length() > 0) {
                criteria.andDescriptionLike("%" + book.getDescription() + "%");
            }
            if (book.getIsaudit() != null && book.getIsaudit().length() > 0) {
                criteria.andIsauditLike("%" + book.getIsaudit() + "%");
            }

        }

        Page<Book> page = (Page<Book>) bookMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult findSellingBook(String username, boolean isSellOut, int pageNum, int pageSize) {

        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();

        criteria.andSellerEqualTo(username);
        // 查询售罄图书
        if (isSellOut) {
            criteria.andNumberEqualTo(0);
        } else {
            // 查询在售图书
            criteria.andNumberGreaterThan(0);
        }
        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        Page<Book> books = (Page<Book>) bookMapper.selectByExample(example);
        return new PageResult(books.getTotal(), books.getResult(), (long) books.getPages());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int refillBook(int id, int number, String seller) {
        Book book = new Book();
        book.setNumber(number);
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andSellerEqualTo(seller);
        criteria.andIdEqualTo(id);
        int update = bookMapper.updateByExampleSelective(book, example);
        checkResult(update,"库存补充失败");
        return update;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateBaseInfo(Book book) {
        int updateBaseInfo = bookMapper.updateBaseInfo(book);
        checkResult(updateBaseInfo,"书籍信息更新失败");
        return updateBaseInfo;
    }

    @Override
    public PageResult getBookEvaluation(int id, int pageNum, int pageSize) {
        List<BookEvaluation> bookEvaluations = new ArrayList<>();

        PageHelper.startPage(pageNum, pageSize);
        Page<Order> orders = (Page<Order>) orderMapper.getOrderEval(id);
        for (Order order : orders) {
            Account account = accountMapper.selectByPrimaryKey(order.getUserid());
            bookEvaluations.add(initBookEval(order, account));
        }
        return new PageResult(orders.getTotal(), bookEvaluations, (long) orders.getPages());
    }

    @Override
    public PageResult getRecommendBooks(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        BookExample example = new BookExample();
        example.setOrderByClause("id desc");
        Page<Book> books = (Page<Book>) bookMapper.selectByExample(example);
        return new PageResult(books.getTotal(), books.getResult(), (long) books.getPages());
    }

    /**
     * 初始化评价信息
     *
     * @param order   与评价书籍相关的订单
     * @param account 评价用户
     * @return
     */
    private BookEvaluation initBookEval(Order order, Account account) {
        BookEvaluation bookEvaluation = new BookEvaluation();
        bookEvaluation.setEvalType(order.getEvltype());
        bookEvaluation.setEvaluation(order.getUserevaluation());
        bookEvaluation.setEvaluationDate(order.getOverdate());
        bookEvaluation.setNickName(account.getNickname());
        bookEvaluation.setHeadPic(account.getHeadpic());
        bookEvaluation.setUserLevel(account.getUserlevel());
        return bookEvaluation;
    }

    /**
     * 初始化将要加入solr中的书籍信息
     *
     * @param book 原书籍信息
     * @return
     */
    public SolrInputDocument initSolrBook(Book book) {
        SolrInputDocument solrBook = new SolrInputDocument();
        solrBook.addField("id", book.getId());
        solrBook.addField("book_name", book.getName());
        solrBook.addField("book_author", book.getAuthor());
        solrBook.addField("book_publisher", book.getPublisher());
        solrBook.addField("book_isbn", book.getIsbn());
        solrBook.addField("book_sellprice", book.getSellprice().setScale(2, RoundingMode.HALF_UP).doubleValue());
        solrBook.addField("book_bookimages", book.getBookimages());
        solrBook.addField("book_description", book.getDescription());
        return solrBook;
    }

    /**
     * 检查数据库的增删改操作是否完成
     * @param result 增删改操作影响记录数
     * @param message 操作失败的提示信息
     */
    private void checkResult(int result,String message){
        if(result == 0){
            throw new DataOperateException(message);
        }
    }
}
