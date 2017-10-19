package com.yzx.rest.mq;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * 发送mq扣费消息
 * @author chenxj
 *
 */
@Service
public class MqProducer {

	@Resource(name = "queueTemplate")
	private RabbitTemplate queueTemplate;
	
	public void sendQueueMessage(String message){
		queueTemplate.convertAndSend(message);
	}
}
