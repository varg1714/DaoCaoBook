package com.daocao.userservice;

import com.daocao.dao.AccountMapper;
import com.daocao.dao.BookMapper;
import com.daocao.entity.Account;
import com.daocao.entity.Address;
import com.daocao.entity.Book;
import com.daocao.entity.BookEvaluation;
import com.daocao.myentity.PageResult;
import com.daocao.user.service.AccountService;
import com.daocao.user.service.AddressService;
import com.daocao.user.service.BookService;
import com.daocao.userservice.impl.AccountServiceImpl;
import com.daocao.userservice.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DaocaoUserServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    AccountService accountService = new AccountServiceImpl();

    @Resource
    AddressService addressService;

    @Resource
    AccountMapper accountMapper;

    @Resource
    BookService bookService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private BookMapper bookMapper;
    
    @Test
    public void accountServiceTest(){

        // 增加用户
        Account  account = new Account();
        account.setUsername("1234567");
        account.setPassword("123456789");
        account.setBalance(new BigDecimal(999));
        account.setEmail("varg247@qq.com");
//        Date date = new Date();
//        account.setCreatedate(date);
//        account.setUpdatedate(date);
        account.setNickname("更新啦");
        account.setTel("1313602");
        // accountService.add(account);
        try {
            int i = accountMapper.updateBaseInfo(account);
            System.out.println("更新数量:"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 查询用户
/*        List<Account> accounts = accountService.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }*/

        // 按条件查询
        /*        System.out.println("----------------------");
                Account account2 = new Account();
                account2.setUsername("1714254511");
                List<Account> rows = accountService.findPage(account2, 1, 10).getRows();
                for (Account row : rows) {
                    System.out.println(row);
                }*/
    }

    @Test
    public void sendMessageTest(){
        Integer[] ids  = new Integer[26];
        for(int i = 0;i < 15; i++){
            ids[i] = i+1;
        }
        for(int i = 15;i < 26; i++){
            ids[i] = i+6;
        }
        rabbitTemplate.convertAndSend("bookTopicExchange","topic.bookGene", ids);
    }

    @Test
    public void addAddressTest(){
        Address address = new Address();
        address.setOwner("123456");
        address.setIsdefault("1");
        address.setAddress("无可奉告");
        address.setProvince("海南");
        address.setCity("海口");
        address.setTown("秀英");
        address.setPhone("458715621");
        address.setContact("韩孝周");
        address.setId(2);
        //addressService.add(address);
        addressService.update(address);
    }

    @Test
    public void deleteAddressTest(){
        addressService.delete(new Integer[]{3},"123456");
    }

    @Test
    public void findAddressTest(){
        List<Address> byUser = addressService.findByUser("123456");
        for (Address address : byUser) {
            System.out.println(address);
        }
    }

    @Test
    public void setDefaultAddressTest(){
        addressService.setDefaultAddress(1,"123456");
    }

    @Test
    public void getBookEvalTest(){
        PageResult bookEvaluation = bookService.getBookEvaluation(8, 2, 1);
        System.out.println(bookEvaluation);
    }
    
    @Test
    public void addBookTest(){
        Book book = new Book();
        book.setSeller("varg1998");
        book.setNumber(4);
        book.setBooktype1(3);
        book.setBooktype2(6);
        book.setAuthor("查尔斯·菲利普斯");
        book.setIssell("1");
        book.setIsaudit("1");
        book.setDescription("在生活中，数字、图形元素以及其他有趣的符号组合，总是能优雅地呈现出某些规律，而人类似乎天生就会注意和欣赏这些规律。了解事物如何彼此联系、运作是人类的本能，这对于我们自科学和思想方面取得突破性进展也有很大帮助。我们生存于多元化的世界，发现事物的规律并善用逻辑思考，将影响你生活的方方面面！本书提供了50道精心设计的逻辑游戏题，分为两个难度不同的阶段，帮你测试自己的智力水平，提升逻辑思维能力！");
        book.setName("如何培养逻辑脑：聪明人都在玩的逻辑游戏");
        book.setVersion("1");
        book.setSellprice(new BigDecimal("38.0"));
        book.setBookimages("[\"http://192.168.25.133/group1/M00/00/00/wKgZhV65QziABAu9AADcyehjlQ0482.jpg\"]");
        book.setPostage(new BigDecimal("0"));

        int insert = bookMapper.insert(book);
        System.out.println(book.getId());
//        mber: "4", name: "如何培养逻辑脑：聪明人都在玩的逻辑游戏", booktype1: 3, booktype2: 6, author: "查尔斯·菲利普斯",…}
//        number: "4"
//    name: "如何培养逻辑脑：聪明人都在玩的逻辑游戏"
//    booktype1: 3
//    booktype2: 6
//    author: "查尔斯·菲利普斯"
//    publisher: "九州出版社"
//    version: "1"
//    description: "在生活中，数字、图形元素以及其他有趣的符号组合，总是能优雅地呈现出某些规律，而人类似乎天生就会注意和欣赏这些规律。了解事物如何彼此联系、运作是人类的本能，这对于我们自科学和思想方面取得突破性进展也有很大帮助。我们生存于多元化的世界，发现事物的规律并善用逻辑思考，将影响你生活的方方面面！本书提供了50道精心设计的逻辑游戏题，分为两个难度不同的阶段，帮你测试自己的智力水平，提升逻辑思维能力！"
//    postage: "0"
//    sellprice: "38"
//    bookimages: "["http://192.168.25.133/group1/M00/00/00/wKgZhV65QziABAu9AADcyehjlQ0482.jpg"]"
//    publishdate: "2014-05-10"
    }

    @Test
    public void getRecomBooksTest(){
        PageResult books = bookService.getRecommendBooks(1, 15);
        List<Book> rows = books.getRows();
        for (Book row : rows) {
            System.out.println(row);
        }
    }
}
