package com.rabbitmq.demo.model.view;

public class LogVm {
	String[] logNode ;
private int id;
	
    private String name;
	
    private String routingKey;
    private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String[] getLogNode() {
		return logNode;
	}

	public void setLogNode(String[] logNode) {
		this.logNode = logNode;
	}

	
	

}
