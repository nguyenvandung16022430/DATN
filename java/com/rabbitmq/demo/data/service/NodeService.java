package com.rabbitmq.demo.data.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rabbitmq.demo.data.model.Node;
import com.rabbitmq.demo.data.repository.NodeRepository;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NodeService {
	@Autowired
	private NodeRepository nodeRepository;
	
	public void addNewNode (Node node){
        nodeRepository.save(node);
    }
    @Transactional
    public void addNewListNode(List<Node> listNode){
        nodeRepository.saveAll(listNode);
    }
    public Node findOne(int nodeId){
        return nodeRepository.getOne(nodeId);
    }
    public boolean updateProduct(Node node){
        try{
            nodeRepository.save(node);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int nodeId){
        try{
            nodeRepository.deleteById(nodeId);;
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public List<Node> getListAllNode() {
        try {
            return nodeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public long getTotalNodes(){
        return nodeRepository.getTotalNodes();
    }
    public Page<Node> getPage(Pageable pageable) {
        return nodeRepository.findAll(pageable);
    }
}
