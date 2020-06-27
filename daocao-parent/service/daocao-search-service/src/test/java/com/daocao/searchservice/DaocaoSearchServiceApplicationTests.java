package com.daocao.searchservice;

import com.daocao.dao.BookMapper;
import com.daocao.entity.Book;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class DaocaoSearchServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    BookMapper mapper;

    @Autowired
    SolrClient solrClient;

    @Test
    public void initSolrTest() throws IOException, SolrServerException {
        List<Book> books = mapper.selectByExample(null);
        List<SolrInputDocument> documents = new ArrayList<>();
        for (Book book : books) {

            System.out.println(book);
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", book.getId());
            document.addField("book_name", book.getName());
            document.addField("book_author", book.getAuthor());
            document.addField("book_publisher", book.getPublisher());
            document.addField("book_isbn", book.getIsbn());
            document.addField("book_sellprice", book.getSellprice().setScale(2, RoundingMode.HALF_UP).doubleValue());
            document.addField("book_bookimages", book.getBookimages());
            document.addField("book_description", book.getDescription());

            documents.add(document);
        }
        solrClient.add(documents);
        solrClient.commit();
    }

    @Test
    public void solrAddTest() throws IOException, SolrServerException {

        List<SolrInputDocument> documents = new ArrayList<>();

        for (int i = 11; i < 31; i++) {
            Book book = new Book();
            book.setId(i);
            book.setName("啦啦啦");
            book.setAuthor("陈雨琪粉丝会");
            book.setIsbn("20394092384");
            book.setSellprice(new BigDecimal("102.4"));
            book.setDescription("陈雨琪真好看");

            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", book.getId());
            document.addField("book_name", book.getName());
            document.addField("book_author", book.getAuthor());
            document.addField("book_publisher", book.getPublisher());
            document.addField("book_isbn", book.getIsbn());
            document.addField("book_sellprice", book.getSellprice().setScale(2, RoundingMode.HALF_UP).doubleValue());
            document.addField("book_bookimages", book.getBookimages());
            document.addField("book_description", book.getDescription());

            documents.add(document);
        }


        //SolrInputDocument document = new SolrInputDocument();
        //document.ad
        //solrClient.addBean(book);
        solrClient.add(documents);
        solrClient.commit();
    }

    @Test
    public void solrQueryTest() throws IOException, SolrServerException {

        SolrQuery query = new SolrQuery("*:*");

        // 设置查询条件 以关键字搜索
//        query.set("df", "book_keywords");
//        query.setQuery("book_keywords:韩孝周");
        //query.setFilterQueries("id:[1 TO 5]");

        //query.setQuery("book_isbn:20394092384");
//        query.setStart(0);
//        query.setRows(3);


        // 高亮查询
        query.setHighlight(true);
        query.addHighlightField("book_name");
        query.addHighlightField("book_author");
        query.addHighlightField("book_publisher");
        query.addHighlightField("book_description");
        query.addHighlightField("book_isbn");
        query.setHighlightSimplePre("<font style='color:red'>");
        query.setHighlightSimplePost("</font>");

        QueryResponse response = solrClient.query(query);

        SolrDocumentList results = response.getResults();
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        List<Book> books = new ArrayList<>();

//        for (SolrDocument result : results) {
//            String id = (String) result.get("id");
//            Map<String,List<String>> map = highlighting.get(id);
//            for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
//                System.out.println(stringListEntry.getValue()+":"+ stringListEntry.getValue().get(0));
//                System.out.println();
//            }
//        }

        for (SolrDocument result : results) {
            String id = (String) result.get("id");
            // 获取该ID对应的高亮结果集
            Map<String, List<String>> highlightMap = highlighting.get(id);
            if (highlightMap != null) {
                // 获取结果集中高亮的属性值
                Set<String> keySet = highlightMap.keySet();
                for (String key : keySet) {
                    result.setField(key, highlightMap.get(key));
                }
            }
            System.out.println(result);
        }

        long numFound = 0L;
        System.out.println("个数:" + results.getNumFound());
        if ((query.getBool("group", false))) {
            Object facets = response.getResponse().get("facets");
            if (facets instanceof SimpleOrderedMap) {
                Object distinctCount = ((SimpleOrderedMap) facets).get("distinctCount");
                if (distinctCount != null) {
                    numFound = (Long) distinctCount;
                }
            }
        } else {
            numFound = response.getResults().getNumFound();
        }
        System.out.println(numFound);
//        String name = "";
//        for (SolrDocument result : results) {
//            String id = (String) result.getFieldValue("id");
//            List<String> list = highlighting.get(id).get("book_name");
//            if(list != null && list.size() > 0){
//                name = list.get(0);
//            }else {
//                name = (String) result.get("book_name");
//            }
//            System.out.println(id+":"+name);
//        }

        // 处理结果集
//        for (SolrDocument result : results) {
//
//            Book book = new Book();
//            String id = (String) (result.getFieldValue("id"));
//            book.setId(Integer.parseInt(id));
//            String name = (String) (result.getFieldValue("book_name"));
//            book.setName(name);
//            String author = (String) (result.getFieldValue("book_author"));
//            book.setAuthor(author);
//            String publisher = (String) (result.getFieldValue("book_publisher"));
//            book.setPublisher(publisher);
//
//            String isbn = (String) (result.getFieldValue("book_isbn"));
//            book.setIsbn(isbn);
//
//            Double sellprice = (Double) (result.getFieldValue("book_sellprice"));
//            book.setSellprice(new BigDecimal(sellprice));
//
//            String bookimages = (String) (result.getFieldValue("book_bookimages"));
//            book.setBookimages(bookimages);
//
//            String book_description = (String) (result.getFieldValue("book_description"));
//            book.setDescription(book_description);
//
//            books.add(book);
//        }
//        for (Book book : books) {
//            System.out.println(book);
//        }
    }

    @Test
    public void solrDeleteTest() {
        SolrQuery query = new SolrQuery("*:*");
        try {
            solrClient.deleteByQuery("*:*");
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
