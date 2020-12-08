package com.rabbitmq.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rabbitmq.demo.data.model.Node;
import com.rabbitmq.demo.data.service.NodeService;
import com.rabbitmq.demo.messagingrabbitmq.MessagingRabbitmqApplication;
import com.rabbitmq.demo.model.view.LogVm;
import com.rabbitmq.demo.model.view.NodeVM;
import com.rabbitmq.demo.service.RabbitMQSender;

@Controller
@RequestMapping(path = "/log")
public class LogController {
	@Autowired
	HashMap<String, String> creathMap;
	@Autowired
	NodeService nodeService;
	@Autowired
	RabbitMQSender rabbitMQSender;
	@GetMapping("/{nodeId}")
    public String ProductDetail(@PathVariable Integer nodeId, Model model){
		try{
			LogVm vm = new LogVm();
            Node node = nodeService.findOne(nodeId);
            vm.setId(node.getId());
            vm.setName(node.getName());
            vm.setStatus(node.getStatus());
            String routingkey = node.getName()+ ".input";
            rabbitMQSender.send(MessagingRabbitmqApplication.topicExchangeName,routingkey, "log");
            while(true){
            	if(creathMap.get(node.getName()) != null) {
            		break;
            	}
            }
            String logNode = creathMap.get(node.getName());
            creathMap.put(node.getName(),null);
            System.out.println(logNode);
            String[] logStrings = logNode.split("[#]");
            vm.setLogNode(logStrings);
            model.addAttribute("vm", vm);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "log";
	}

}
