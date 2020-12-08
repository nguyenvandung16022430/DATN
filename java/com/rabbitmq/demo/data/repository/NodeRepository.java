package com.rabbitmq.demo.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.rabbitmq.demo.data.model.Node;
public interface NodeRepository extends JpaRepository<Node, Integer>{
	@Query("select count(n.id) from node n")
    long getTotalNodes();
}
