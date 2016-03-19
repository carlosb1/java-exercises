package examples;

import java.util.ArrayList;
import java.util.List;

public class Trees {
	public static abstract class Node {
		public boolean visited;
		public final List<Node> adjacent;
		public final Integer value;

		public Node(Integer value) {
			this.value = value;
			visited = false;
			adjacent = new ArrayList<Node>();
		}

		public boolean isNull() {
			return false;
		}

		public abstract void insert(Node node);

	}

	public static class NullNode extends Node {

		public NullNode() {
			super(new Integer(-1));
		}

		@Override
		public boolean isNull() {
			return true;
		}

		@Override
		public void insert(Node node) {
		}

	}

	public static class BinaryTree extends Node {
		public BinaryTree(Integer value) {
			super(value);
			this.adjacent.add(new NullNode());
			this.adjacent.add(new NullNode());
		}

		public void insert(Node node) {
			if (isSameNode(node)) {
				// TODO add test for this case
				return;
			}
			if (node.value < this.value) {
				if (this.left().isNull()) {
					this.setLeft(node);
				} else {
					// TODO it could be necessary to add all possible tests for
					// insert method..., it is only to test isBalanced library
					this.left().insert(node);
				}
			} else if (node.value > this.value) {
				if (this.right().isNull()) {
					this.setRight(node);
				} else {
					this.right().insert(node);
				}
			}

		}

		private boolean isSameNode(Node node) {
			return node.value == this.value;
		}

		public Node left() {
			if (this.adjacent.size() == 0)
				return new NullNode();
			return this.adjacent.get(0);
		}

		public void setLeft(Node node) {
			this.adjacent.set(0, node);
		}

		public Node right() {
			if (this.adjacent.size() < 2) {
				return new NullNode();
			}
			return this.adjacent.get(1);
		}

		public void setRight(Node node) {
			this.adjacent.set(1, node);
		}

	}

	public static boolean IsBalanced(Node node) {

		/*
		 * if (node.adjacent.size() > 2) { return false; }
		 * 
		 * IsBalanced(node.adjacent.get(0));
		 */
		return false;
	}

}
