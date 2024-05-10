package top.luoqiz.im.nettysocketio.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.luoqiz.im.nettysocketio.model.MessageTemplate;

@Slf4j
 @Service
 public class SingleMessageServiceImpl implements SingleMessageService {

   @Override public void saveSingleMessage(MessageTemplate template) {
     try {
       log.info("接收消息：{} {}", template.toString());
     } catch (Exception exception) {
       exception.printStackTrace();
       log.info("连接错误 [{}]", exception.getMessage());
     }
   }
 }