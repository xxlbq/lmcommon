package com.lm.common.util;



public class Node{
	String key;
	Node left,right;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	public Node(String key, Node left, Node right) {
		super();
		this.key = key;
		this.left = left;
		this.right = right;
	}
	
	
}