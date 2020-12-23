package com.rabbitmq.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.demo.data.dto.NodeDTO;
import com.rabbitmq.demo.data.model.BaseApiResult;
import com.rabbitmq.demo.data.model.Node;
import com.rabbitmq.demo.data.service.NodeService;
import com.rabbitmq.demo.messagingrabbitmq.MessagingRabbitmqApplication;
import com.rabbitmq.demo.service.RabbitMQSender;

@RestController
@RequestMapping(path = "/api/node")
public class CommandController {
	@Autowired
	HashMap<String, String> creathMap;
	@Autowired
	NodeService nodeService;
	@Autowired
	RabbitMQSender rabbitMQSender;
	@GetMapping("/toggle/{nodeId}")
	public BaseApiResult deleteCartProduct(@PathVariable int nodeId) {
        BaseApiResult result = new BaseApiResult();
        try{
        	String command;
            Node node = nodeService.findOne(nodeId);
            String routingkey = node.getName()+ ".input";
            if(node.getStatus()==1){
            	System.out.println(routingkey);
            	rabbitMQSender.send(MessagingRabbitmqApplication.topicExchangeName,routingkey, "stop");
            	command = "stop";
            }else{
            	System.out.println(routingkey);
            	rabbitMQSender.send(MessagingRabbitmqApplication.topicExchangeName,routingkey, "start");
            	command = "start";
            }
            while(true){
            	if(creathMap.get(node.getName())!= null) {
            		break;
            	}
            }
            String message = creathMap.get(node.getName());
            if(command.equals("start")) {
            if(message.contains("engine started")) {
            	node.setStatus(1);
            	result.setSuccess(true);
            }else{
            	result.setSuccess(false);
            }
            }else {
            	node.setStatus(0);
            	result.setSuccess(true);
            }
            nodeService.updateProduct(node);
            result.setMessage(message);
            creathMap.put(node.getName(),null);
            return result;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        result.setMessage("fail!");
        result.setSuccess(false);
        return  result;
    }
	@PostMapping("/addRule")
	public BaseApiResult addToCart(@RequestBody NodeDTO nodedto) {
		BaseApiResult result = new BaseApiResult();
		try {
		 Node node = nodeService.findOne(nodedto.getId());
		 String routingkeyRule = node.getName()+ ".addrule";
		 rabbitMQSender.send(MessagingRabbitmqApplication.topicExchangeName,routingkeyRule, nodedto.getRuleS());
		 result.setMessage("Add rule Success");
         result.setSuccess(true);
         return result;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		result.setMessage("fail!");
        result.setSuccess(false);
		return result;
	}
	@PostMapping("/create/{nodeName}")
	public BaseApiResult createNode(@PathVariable String nodeName) {
		BaseApiResult result = new BaseApiResult();
		try {
			Node node = new Node();
			node.setName(nodeName);
			String routing_key_input = nodeName + ".input";
			String routing_key_addrule = nodeName + ".addrule";
			String queue_input = nodeName + "_input";
			String queue_rule = nodeName + "_rule";
			node.setQueue_input(queue_input);
			node.setQueue_rule(queue_rule);
			node.setRouting_key_addrule(routing_key_addrule);
			node.setRouting_key_input(routing_key_input);
			nodeService.addNewNode(node);
			result.setMessage("Add node Success");
	         result.setSuccess(true);
	         return result;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			result.setMessage(e.getMessage());
	        result.setSuccess(false);
		}
		return result;
	}
}
