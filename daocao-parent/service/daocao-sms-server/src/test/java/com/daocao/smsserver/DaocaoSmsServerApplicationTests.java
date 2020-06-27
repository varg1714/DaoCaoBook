package com.daocao.smsserver;

import com.daocao.smsserver.service.TelCodeSendService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DaocaoSmsServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testSmsCodeSend(){
        TelCodeSendService telCodeSendService = new TelCodeSendService();
//        telCodeSendService.sendTelCode();
    }
}
