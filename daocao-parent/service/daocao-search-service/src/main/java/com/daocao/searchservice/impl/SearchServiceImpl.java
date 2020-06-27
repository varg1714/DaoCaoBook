package com.daocao.searchservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.daocao.entity.Book;
import com.daocao.myentity.PageResult;
import com.daocao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author varg
 * @date 2020/4/16 20:05
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SolrClient solrClient;

    @Override
    public PageResult search(Map map) {
        SolrQuery query = new SolrQuery("*:*");
        PageResult pageResult;
        try {

            // 设置查询条件 以关键字搜索
            query.set("df", "book_keywords");
            String keywords = (String) map.get("keywords");
            if (keywords != null) {
                query.setQuery(keywords);
            }
            // 查询页数
            String page = (String) map.get("page");
            query.setStart(0);
            query.setRows(6);
            if (page != null) {
                int begin = Integer.parseInt(page);
                query.setStart((begin - 1) * 6);
            }

            // 排序方式
            String sortRule = (String) map.get("sortRule");
            if(sortRule != null){
                if("priceUp".equals(sortRule)){
                    query.setSort("book_sellprice", SolrQuery.ORDER.asc);
                }else {
                    query.setSort("book_sellprice", SolrQuery.ORDER.desc);
                }
            }

            // 设置高亮域
            query.setHighlight(true);
            query.addHighlightField("book_name");
            query.addHighlightField("book_author");
            query.addHighlightField("book_publisher");
            query.addHighlightField("book_description");
            query.addHighlightField("book_isbn");
            query.setHighlightSimplePre("<font style='color:#d88049'>");
            query.setHighlightSimplePost("</font>");

            QueryResponse response = solrClient.query(query);

            SolrDocumentList results = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            // 高亮结果设置
            for (SolrDocument result : results) {
                String id = (String)result.get("id");
                // 获取该ID对应的高亮结果集
                Map<String, List<String>> highlightMap = highlighting.get(id);
                if(highlightMap != null){
                    // 获取结果集中高亮的属性值
                    Set<String> keySet = highlightMap.keySet();
                    for (String key : keySet) {
                        List<String> values = highlightMap.get(key);
                        if(values != null && values.size() > 0){
                            result.setField(key, values.get(0));
                        }
                    }
                }
            }

            pageResult = new PageResult(results.getNumFound(),handleResult(results));
            pageResult.setTotalPage((pageResult.getTotal()-1)/6+1);

            return pageResult;
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        PageResult result = new PageResult(0L, null);
        result.setTotalPage(0L);
        return result;
    }

    /**
     * 处理solr查询结果
     *
     * @param results
     * @return
     */
    public List<Book> handleResult(SolrDocumentList results) {
        List<Book> books = new ArrayList<>();
        // 处理结果集
        for (SolrDocument result : results) {

            Book book = new Book();
            String id = (String) (result.getFieldValue("id"));
            book.setId(Integer.parseInt(id));
            String name = (String) (result.getFieldValue("book_name"));
            book.setName(name);
            String author = (String) (result.getFieldValue("book_author"));
            book.setAuthor(author);
            String publisher = (String) (result.getFieldValue("book_publisher"));
            book.setPublisher(publisher);

            String isbn = (String) (result.getFieldValue("book_isbn"));
            book.setIsbn(isbn);

            Double sellprice = (Double) (result.getFieldValue("book_sellprice"));
            book.setSellprice(new BigDecimal(sellprice));

            String bookimages = (String) (result.getFieldValue("book_bookimages"));
            book.setBookimages(bookimages);

            String book_description = (String) (result.getFieldValue("book_description"));
            book.setDescription(book_description);

            books.add(book);
            // System.out.println(book);
        }

        return books;
    }

}
