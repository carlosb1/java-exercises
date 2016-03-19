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
	}

	public static class BinaryTree extends Node {
		public BinaryTree(Integer value) {
			super(value);
		}

		public static void insert(Node node) {

		}

		public Node left() {
			// FIXME create null object
			// TODO Add test for null object
			if (this.adjacent.size() == 0)
				return null;
			return this.adjacent.get(0);
		}

		public Node right() {
			if (this.adjacent.size() < 2) {
				return null;
			}
			return this.adjacent.get(1);
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
