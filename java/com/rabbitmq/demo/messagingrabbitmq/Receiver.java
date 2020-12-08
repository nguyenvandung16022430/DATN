package com.rabbitmq.demo.messagingrabbitmq;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;
import com.rabbitmq.demo.extension.CustomMessage;


@Component
public class Receiver {
	@Autowired
	HashMap<String, String> creathMap;
  public void receiveMessage(byte[] messageBody) {
	  String message = new String(messageBody);
	  ObjectMapper objectMapper = new ObjectMapper();
	  CustomMessage customMessage;
		try {
			customMessage = objectMapper.readValue(message, CustomMessage.class);
			creathMap.put(customMessage.getNodeName(), customMessage.getMessage());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
 
