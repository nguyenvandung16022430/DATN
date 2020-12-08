package com.rabbitmq.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rabbitmq.demo.data.model.Node;
import com.rabbitmq.demo.data.service.NodeService;
import com.rabbitmq.demo.model.view.NodeVM;

@Controller
@RequestMapping(path = "/node")
public class NodeController {
	@Autowired
	NodeService nodeService;
	@GetMapping("/{nodeId}")
    public String ProductDetail(@PathVariable Integer nodeId, Model model){
		Node node = new Node();
		NodeVM nodeVM = new NodeVM();
		node = nodeService.findOne(nodeId);
		nodeVM.setId(node.getId());
		nodeVM.setName(node.getName());
		nodeVM.setStatus(node.getStatus());
		model.addAttribute("vm", nodeVM);
		return "node_detail";
	}

}
