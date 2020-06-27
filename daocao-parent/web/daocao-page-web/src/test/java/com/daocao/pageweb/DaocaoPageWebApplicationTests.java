package com.daocao.pageweb;

import com.daocao.pageweb.service.BookHtmlService;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest
class DaocaoPageWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    BookHtmlService bookHtmlService;

    @Test
    public void testGeneHtml(){
        try {
            bookHtmlService.geneHtml(new Integer[]{8});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPath(){
        String directPath = null;
        try {
            directPath = this.getClass().getResource("/").toURI().getPath() + "/static";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(directPath);
    }

    @Test
    public void testDeleHtml(){
        bookHtmlService.deleHtme(new Integer[]{8});
    }
}
