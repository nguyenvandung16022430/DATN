package com.rabbitmq.demo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rabbitmq.demo.data.model.Node;
import com.rabbitmq.demo.data.service.NodeService;
import com.rabbitmq.demo.messagingrabbitmq.MessagingRabbitmqApplication;
import com.rabbitmq.demo.model.view.HomeVM;
import com.rabbitmq.demo.model.view.NodeVM;
import com.rabbitmq.demo.service.RabbitMQSender;

@Controller
public class HomeController {
	@Autowired
	SimpleMessageListenerContainer container;
	@Autowired
	RabbitMQSender rabbitMQSender;
	@Autowired
	NodeService nodeService;
	@GetMapping(value = "/")
	public String index(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "13") Integer size) {
		HomeVM vm = new HomeVM();
		List<Node> listNode = nodeService.getListAllNode();
		List<NodeVM> listNodeVM = new ArrayList<>();
		for(Node node : listNode){
            NodeVM nodeVM = new NodeVM();
            nodeVM.setId(node.getId());
            nodeVM.setName(node.getName());
            nodeVM.setStatus(node.getStatus());
            nodeVM.setQueue_input(node.getQueue_input());
            nodeVM.setQueue_rule(node.getQueue_rule());
            nodeVM.setRouting_key_addrule(node.getRouting_key_addrule());
            nodeVM.setRouting_key_input(node.getRouting_key_input());
            listNodeVM.add(nodeVM);
        }
		vm.setProductVMList(listNodeVM);
		model.addAttribute("vm", vm);
		return "home";
	}

}
