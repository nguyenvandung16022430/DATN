package com.rabbitmq.demo.model.view;

import java.util.List;

public class HomeVM {
	 private List<NodeVM> NodeVMList;
	 public List<NodeVM> getNodeVMList() {
	        return NodeVMList;
	    }
	 public void setProductVMList(List<NodeVM> NodeVMList) {
	        this.NodeVMList = NodeVMList;
	    }
}
