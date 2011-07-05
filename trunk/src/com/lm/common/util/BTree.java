package com.lm.common.util;

import com.lm.common.btree.Node;

public class BTree {
	protected Node root;

	public BTree(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public static Node init() {
			Node a = new Node('A');
			Node b = new Node('B', null, a);
			Node c = new Node('C');
			Node d = new Node('D', b, c);
			Node e = new Node('E');
			Node f = new Node('F', e, null);
			Node g = new Node('G', null, f);
			Node h = new Node('H', d, g);
			return h;// root
	}

	public static void main(String[] args) {
		BTree bt = new BTree(init());
		
		System.out.print(" Pre-Order:");
		preorder(bt.getRoot());
	}

	private static void preorder(Node root) {
		if(root != null){
			System.out.println(""+root.getKey());
			preorder(root.getLeft());
			preorder(root.getRight());
		}
		
	}
}
