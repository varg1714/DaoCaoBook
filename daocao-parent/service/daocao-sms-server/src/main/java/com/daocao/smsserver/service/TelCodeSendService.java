package com.daocao.smsserver.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author varg
 * @date 2020/5/5 18:29
 */
@RabbitListener(queues = "smsTelCheckQueue")
@Component
public class TelCodeSendService {

    @Value("${accessId}")
    String accessId;

    @Value("${secret}")
    String secret;

    @RabbitHandler
    public void sendTelCode(Map<String, String> message) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", message.get("PhoneNumbers"));
        request.putQueryParameter("SignName", message.get("SignName"));
        request.putQueryParameter("TemplateCode", message.get("TemplateCode"));
        request.putQueryParameter("TemplateParam", message.get("TemplateParam"));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
