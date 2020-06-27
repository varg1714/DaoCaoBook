package com.daocao.userweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@SpringBootTest
class DaocaoUserWebApplicationTests {

    @Test
    void contextLoads() {
    }
    
    @Test
    public void passwordEncoderTest(){
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String encode1 = bCrypt.encode("123456");
        String encode2 = bCrypt.encode("123456");

        System.out.println(encode1 + ":" +encode2);
        System.out.println("encode1.equals(encode2):"+encode1.equals(encode2));
        System.out.println("encode1.matches(\"12345\"):"+bCrypt.matches("12345",encode1));
        System.out.println("encode1.matches(\"123456\"):"+bCrypt.matches("123456",encode1));
        System.out.println("encode2.matches(\"123456\"):"+bCrypt.matches("123456",encode2));
        // System.out.println("encode:"+encode+" length: "+encode.length());
        // System.out.println(bCrypt.matches("123456",encode));

    }

}
