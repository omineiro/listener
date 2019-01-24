package com.example.camunda.cainao.demo.domain;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ReceiveMessage implements MessageListener, JavaDelegate {


//    RuntimeService runtimeService;
    public Message myMessage;

    @Override
    public void onMessage(Message message) {
        try {
            myMessage = message;

//            Map<String,Object> variables = new HashMap<>();
//            variables.put("value", new String(message.getBody(), "UTF-8"));

            System.out.println("this is my Receive Messages of my RabbitMQ: "+ new String(message.getBody(), "UTF-8"));

//            runtimeService.createMessageCorrelation("ReceiveMessage")
//                    .setVariable("value", variables)
//                    .correlateWithResult();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.startProcessInstanceByMessage("ReceiveMessage");
    }
}
