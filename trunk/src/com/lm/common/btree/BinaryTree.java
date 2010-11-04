package com.lm.common.btree;



import java.util.Stack;

public class BinaryTree {
	
	protected Node root;

	public BinaryTree(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	/** 鏋勯�犳爲 */
	
/*
 * 	            H
	        /       \   
	  D(d)              G(g)
	  /  \              /  \
    B(b)  C(C)       NULL  F(f)
	/ \                     /\
 NULL  A(A)                E  NULL
 
 */
	            
	            
	            
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

	/** 璁块棶鑺傜偣 */
	public static void visit(Node p) {
		System.out.print(p.getKey() + " ");
	}

	/** 閫掑綊瀹炵幇鍓嶅簭閬嶅巻 */
	protected static void preorder(Node p) {
		if (p != null) {
			visit(p);
			preorder(p.getLeft());
			preorder(p.getRight());
		}
	}

	/** 閫掑綊瀹炵幇涓簭閬嶅巻 */
	protected static void inorder(Node p) {
		if (p != null) {
			inorder(p.getLeft());
			visit(p);
			inorder(p.getRight());
		}
	}

	/** 閫掑綊瀹炵幇鍚庡簭閬嶅巻 */
	protected static void postorder(Node p) {
		if (p != null) {
			postorder(p.getLeft());
			postorder(p.getRight());
			visit(p);
		}
	}

	/** 闈為�掑綊瀹炵幇鍓嶅簭閬嶅巻 */
	protected static void iterativePreorder(Node p) {
		Stack<Node> stack = new Stack<Node>();
		if (p != null) {
			stack.push(p);
			while (!stack.empty()) {
				p = stack.pop();
				visit(p);
				if (p.getRight() != null)
					stack.push(p.getRight());
				if (p.getLeft() != null)
					stack.push(p.getLeft());
			}
		}
	}

	/** 闈為�掑綊瀹炵幇鍓嶅簭閬嶅巻2 */
	protected static void iterativePreorder2(Node p) {
		Stack<Node> stack = new Stack<Node>();
		Node node = p;
		while (node != null || stack.size() > 0) {
			while (node != null) {//鍘嬪叆鎵�鏈夌殑宸﹁妭鐐癸紝鍘嬪叆鍓嶈闂畠
				visit(node);
				stack.push(node);
				node = node.getLeft();
			}
			if (stack.size() > 0) {//
				node = stack.pop();
				node = node.getRight();
			}
		}
	}

	/** 闈為�掑綊瀹炵幇鍚庡簭閬嶅巻 */
	protected static void iterativePostorder(Node p) {
		Node q = p;
		Stack<Node> stack = new Stack<Node>();
		while (p != null) {
			// 宸﹀瓙鏍戝叆鏍�
			for (; p.getLeft() != null; p = p.getLeft())
				stack.push(p);
			// 褰撳墠鑺傜偣鏃犲彸瀛愭垨鍙冲瓙宸茬粡杈撳嚭
			while (p != null && (p.getRight() == null || p.getRight() == q)) {
				visit(p);
				q = p;// 璁板綍涓婁竴涓凡杈撳嚭鑺傜偣
				if (stack.empty())
					return;
				p = stack.pop();
			}
			// 澶勭悊鍙冲瓙
			stack.push(p);
			p = p.getRight();
		}
	}

	/** 闈為�掑綊瀹炵幇鍚庡簭閬嶅巻 鍙屾爤娉� */
	protected static void iterativePostorder2(Node p) {
		Stack<Node> lstack = new Stack<Node>();
		Stack<Node> rstack = new Stack<Node>();
		Node node = p, right;
		do {
			while (node != null) {
				right = node.getRight();
				lstack.push(node);
				rstack.push(right);
				node = node.getLeft();
			}
			node = lstack.pop();
			right = rstack.pop();
			if (right == null) {
				visit(node);
			} else {
				lstack.push(node);
				rstack.push(null);
			}
			node = right;
		} while (lstack.size() > 0 || rstack.size() > 0);
	}

	/** 闈為�掑綊瀹炵幇鍚庡簭閬嶅巻 鍗曟爤娉�*/
	protected static void iterativePostorder3(Node p) {
		Stack<Node> stack = new Stack<Node>();
		Node node = p, prev = p;
		while (node != null || stack.size() > 0) {
			while (node != null) {
				stack.push(node);
				node = node.getLeft();
			}
			if (stack.size() > 0) {
				Node temp = stack.peek().getRight();
				if (temp == null || temp == prev) {
					node = stack.pop();
					visit(node);
					prev = node;
					node = null;
				} else {
					node = temp;
				}
			}

		}
	}

	/** 闈為�掑綊瀹炵幇鍚庡簭閬嶅巻4 鍙屾爤娉�*/
	protected static void iterativePostorder4(Node p) {
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> temp = new Stack<Node>();
		Node node = p;
		while (node != null || stack.size() > 0) {
			while (node != null) {
				temp.push(node);
				stack.push(node);
				node = node.getRight();
			}
			if (stack.size() > 0) {
				node = stack.pop();
				node = node.getLeft();
			}
		}
		while (temp.size() > 0) {
			node = temp.pop();
			visit(node);
		}
	}

	/** 闈為�掑綊瀹炵幇涓簭閬嶅巻 */
	protected static void iterativeInorder(Node p) {
		Stack<Node> stack = new Stack<Node>();
		while (p != null) {
			while (p != null) {
				if (p.getRight() != null)
					stack.push(p.getRight());// 褰撳墠鑺傜偣鍙冲瓙鍏ユ爤
				stack.push(p);// 褰撳墠鑺傜偣鍏ユ爤
				p = p.getLeft();
			}
			p = stack.pop();
			while (!stack.empty() && p.getRight() == null) {
				visit(p);
				p = stack.pop();
			}
			visit(p);
			if (!stack.empty())
				p = stack.pop();
			else
				p = null;
		}
	}

	/** 闈為�掑綊瀹炵幇涓簭閬嶅巻2 */
	protected static void iterativeInorder2(Node p) {
		Stack<Node> stack = new Stack<Node>();
		Node node = p;
		while (node != null || stack.size() > 0) {
			while (node != null) {
				stack.push(node);
				node = node.getLeft();
			}
			if (stack.size() > 0) {
				node = stack.pop();
				visit(node);
				node = node.getRight();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(init());
		System.out.print(" Pre-Order:");
		preorder(tree.getRoot());
		System.out.println();
		System.out.print("  In-Order:");
		inorder(tree.getRoot());
		System.out.println();
		System.out.print("Post-Order:");
		postorder(tree.getRoot());
		System.out.println();
		System.out.print(" Pre-Order:");
		iterativePreorder(tree.getRoot());
		System.out.println();
		System.out.print("Pre-Order2:");
		iterativePreorder2(tree.getRoot());
		System.out.println();
		System.out.print("  In-Order:");
		iterativeInorder(tree.getRoot());
		System.out.println();
		System.out.print(" In-Order2:");
		iterativeInorder2(tree.getRoot());
		System.out.println();
		System.out.print(" Post-Order:");
		iterativePostorder(tree.getRoot());
		System.out.println();
		System.out.print("Post-Order2:");
		iterativePostorder2(tree.getRoot());
		System.out.println();
		System.out.print("Post-Order3:");
		iterativePostorder3(tree.getRoot());
		System.out.println();
		System.out.print("Post-Order4:");
		iterativePostorder4(tree.getRoot());
		System.out.println();

	}

}
