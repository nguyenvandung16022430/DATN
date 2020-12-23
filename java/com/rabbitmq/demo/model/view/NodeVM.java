package com.rabbitmq.demo.model.view;

import javax.persistence.Column;

public class NodeVM {
	
    private int id;
	
    private String name;
	
   
	private String routing_key_input;
	
	
	private String routing_key_addrule;
	
	
	private String queue_input;
	
	
	public String getRouting_key_input() {
		return routing_key_input;
	}
	public void setRouting_key_input(String routing_key_input) {
		this.routing_key_input = routing_key_input;
	}
	public String getRouting_key_addrule() {
		return routing_key_addrule;
	}
	public void setRouting_key_addrule(String routing_key_addrule) {
		this.routing_key_addrule = routing_key_addrule;
	}
	public String getQueue_input() {
		return queue_input;
	}
	public void setQueue_input(String queue_input) {
		this.queue_input = queue_input;
	}
	public String getQueue_rule() {
		return queue_rule;
	}
	public void setQueue_rule(String queue_rule) {
		this.queue_rule = queue_rule;
	}

	private String queue_rule;
    
	
	public void setId(int id) {
		this.id = id;
	}

	private int status;
	
    public int getId() {
		return id;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
