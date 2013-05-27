package techgig;

import java.util.Stack;

class XNode<T> {
	private T data;
	private XNode<T> left;
	private XNode<T> right;
	private XNode<T> parent;

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public boolean ifLeftExists() {
		return (left != null);
	}

	public XNode<T> getLeft() {
		return left;
	}

	public XNode<T> getRight() {
		return right;
	}

	public boolean ifRightExists() {
		return (right != null);
	}

	public XNode<T> addLeft(T data) {
		left = new XNode<T>();
		left.setData(data);
		left.parent = this;
		return left;
	}

	public XNode<T> addRight(T data) {
		right = new XNode<T>();
		right.setData(data);
		right.parent = this;
		return right;
	}

	public XNode<T> getParent() {
		return this.parent;
	}

	public void setParent(XNode<T> node) {
		this.parent = node;
	}
}

class BinaryTree<T> {
	private XNode<T> root;
	private XNode<T> focusXNode;

	BinaryTree(T data) {
		root = new XNode<T>();
		root.setData(data);
		root.setParent(null);
		focusXNode = root;
	}

	public XNode<T> getRoot() {
		return root;
	}

	public XNode<T> getFocusXNode() {
		return focusXNode;
	}

	public void setFocusXNode(XNode<T> node) {
		focusXNode = node;
	}

	public void traverse(XNode<T> node) {
		if (node.ifLeftExists()) {
			traverse(node.getLeft());
		}
		if (node.ifRightExists()) {
			traverse(node.getRight());
		}
	}

}

public class Contest3 {

	private static StringBuilder infixToPostfix(String infix) {

		infix = infix.trim();
		Stack<Character> stack = new Stack<Character>();
		StringBuilder strb = new StringBuilder("");

		for (int i = 0; i < infix.length(); i++) {
			Character ch = infix.charAt(i);
			if (Character.isDigit(ch)) {
				strb.append(ch);
			} else if (ch == '+' || ch == '-' || ch == '*') {
				stack.push(ch);
			} else if (ch == ')') {
				strb.append(stack.pop());
			}
		}

		if (!stack.isEmpty()) {
			while (!stack.isEmpty()) {
				strb.append(stack.pop());
			}
		}

		return strb;
	}

	private static int calculate(XNode<Character> node) {
		if (Character.isDigit(node.getData())) {
			return Integer.parseInt(node.getData() + "");
		} else {
			int left = calculate(node.getLeft());
			int right = calculate(node.getRight());
			if (node.getData() == '+') {
				return left + right;
			} else if (node.getData() == '-') {
				return left - right;
			} else if (node.getData() == '*') {
				return left * right;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		StringBuilder postfix = infixToPostfix("((4+3)*9)+1");
		postfix = postfix.reverse();

		String reverse = postfix.toString();

		BinaryTree<Character> bt = new BinaryTree<Character>(reverse.charAt(0));

		// make Tree
		for (int i = 1; i < reverse.length(); i++) {
			Character ch = reverse.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*') {
				if (!bt.getFocusXNode().ifRightExists()) {
					bt.setFocusXNode(bt.getFocusXNode().addRight(ch));
				} else {
					bt.setFocusXNode(bt.getFocusXNode().addLeft(ch));
				}
			} else if (Character.isDigit(ch)) {
				if (!bt.getFocusXNode().ifRightExists()) {
					bt.getFocusXNode().addRight(ch);
				} else {
					bt.getFocusXNode().addLeft(ch);
					bt.setFocusXNode(bt.getFocusXNode().getParent());
				}
			}
		}

		// display tree
		//bt.traverse(bt.getRoot());

		// calculate expression using recursion
		System.out.println(calculate(bt.getRoot()));

	}

}
