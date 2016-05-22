package examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Trees {

	public static class BinaryTree extends Node {

		public static List<List<BinaryTree>> iterate(BinaryTree binaryTree) {
			LinkedBlockingQueue<List<BinaryTree>> valuesToAnalise = new LinkedBlockingQueue<List<BinaryTree>>();
			List<List<BinaryTree>> result = new java.util.LinkedList<List<BinaryTree>>();
			/* add graph to result */

			if (!binaryTree.isNull()) {
				List<BinaryTree> newList = new java.util.LinkedList<BinaryTree>();
				newList.add(binaryTree);
				valuesToAnalise.add(newList);
				result.add(newList);
			}

			while (!valuesToAnalise.isEmpty()) {
				List<BinaryTree> nodes = valuesToAnalise.poll();
				List<BinaryTree> allChildren = new ArrayList<BinaryTree>();
				for (BinaryTree node : nodes) {
					List<BinaryTree> partOfChildren = listOfNodesToListOfBinaryTree(node.adjacent);
					allChildren.addAll(partOfChildren);
				}
				if (!allChildren.isEmpty()) {
					valuesToAnalise.add(allChildren);
					result.add(allChildren);
				}
			}
			return result;
		}

		private static List<BinaryTree> listOfNodesToListOfBinaryTree(List<Node> nodesChildren) {
			List<BinaryTree> result = new java.util.LinkedList<BinaryTree>();
			for (Node node : nodesChildren) {
				if (!node.isNull()) {
					result.add((BinaryTree) node);
				}
			}
			return result;
		}

		public BinaryTree() {
			super(new Integer(-1));

		}

		public static BinaryTree CreateBinaryMinimTree(List<Integer> values) {
			Collections.sort(values);
			if (values.size() == 1) {
				return new BinaryTree(values.get(0));
			} else if (values.size() == 2) {
				BinaryTree tree = new BinaryTree(values.get(1));
				tree.insert(new BinaryTree(values.get(0)));
				return tree;
			} else if (values.size() == 3) {
				BinaryTree tree = new BinaryTree(values.get(1));
				tree.insert(new BinaryTree(values.get(0)));
				tree.insert(new BinaryTree(values.get(2)));
				return tree;
			} else {
				int countHalf = (int) (values.size() / 2);
				BinaryTree treeLeft = CreateBinaryMinimTree(values.subList(0, countHalf));
				BinaryTree treeRight = CreateBinaryMinimTree(values.subList(countHalf + 1, values.size()));
				BinaryTree tree = new BinaryTree(values.get(countHalf));
				tree.insert(treeLeft);
				tree.insert(treeRight);
				return tree;
			}

		}

		public BinaryTree(Integer value) {
			super(value);
			this.adjacent.add(new NullBinaryTree());
			this.adjacent.add(new NullBinaryTree());
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

		public BinaryTree left() {
			if (this.adjacent.size() == 0)
				return new NullBinaryTree();
			return (BinaryTree) this.adjacent.get(0);
		}

		public void setLeft(Node node) {
			this.adjacent.set(0, node);
		}

		public BinaryTree right() {
			if (this.adjacent.size() < 2) {
				return new NullBinaryTree();
			}
			return (BinaryTree) this.adjacent.get(1);
		}

		public void setRight(Node node) {
			this.adjacent.set(1, node);
		}

		@Override
		public Node search(Node node) {
			if (isSameNode(node)) {
				// TODO add test for this case
				return this;
			}
			Node result = new NullNode();
			if (node.value < this.value) {
				if (this.left().isNull()) {
					result = new NullNode();
				} else {
					result = this.left().search(node);
				}
			} else if (node.value > this.value) {
				if (this.right().isNull()) {
					result = new NullNode();
				} else {
					result = this.right().search(node);
				}
			}
			return result;
		}

		@Override
		public String toString() {
			return "Node [visited=" + visited + ", left=" + adjacent.get(0) + ", right=" + adjacent.get(1) + ", value=" + value + "]";
		}

		public java.util.List<java.util.List<Integer>> createLists() {
			java.util.LinkedList<java.util.List<Integer>> result = new java.util.LinkedList<java.util.List<Integer>>();
			if (this.isNull()) {
				return result;
			}
			java.util.List<Integer> list = new java.util.LinkedList<Integer>();
			list.add(this.value);
			result.add(list);
			nativeCreateLists(this, result);
			return result;
		}

		private void nativeCreateLists(BinaryTree binaryTree, java.util.List<java.util.List<Integer>> lists) {
			if (binaryTree.adjacent.size() == 0) {

			}
		}

	}

	public static class NullBinaryTree extends BinaryTree {
		public NullBinaryTree() {
			super();
		}

		public NullBinaryTree(Integer value) {
			super(value);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isNull() {
			return true;
		}

		@Override
		public String toString() {
			return "Node [null]";
		}

	}

	public static class Pair<T1, T2> {
		T1 first;
		T2 second;

		private Pair(T1 first, T2 second) {
			this.first = first;
			this.second = second;
		}

		public static <T1, T2> Pair<T1, T2> makePair(T1 first, T2 second) {
			return new Pair<T1, T2>(first, second);
		}
	}

	public static Pair<Boolean, Integer> IsBalanced(BinaryTree node) {
		Pair<Boolean, Integer> resultRight = Pair.makePair(true, 0);
		Pair<Boolean, Integer> resultLeft = Pair.makePair(true, 0);
		if (!node.right().isNull()) {
			resultRight = IsBalanced(node.right());
		}
		if (!node.left().isNull()) {
			resultLeft = IsBalanced(node.left());
		}
		int height = Math.max(resultRight.second, resultLeft.second) + 1;

		if (resultRight.first && resultLeft.first && Math.abs(resultRight.second - resultLeft.second) <= 1) {
			return Pair.makePair(true, height);
		}

		return Pair.makePair(false, height);
	}

}
