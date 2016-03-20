package examples;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import examples.Trees.BinaryTree;
import examples.Trees.Node;
import examples.Trees.NullBinaryTree;

public class TreesBinaryTreeTest {

	@Test
	public void insertNodeLeftCorrectly() {
		Integer values[] = { 2, 1 };
		BinaryTree node = createBinaryTree(values);
		assertTrue(node.left().value == 1);
	}

	@Test
	public void insertNodeRightCorrectly() {
		Integer values[] = { 2, 3 };
		BinaryTree node = createBinaryTree(values);
		assertTrue(node.right().value == 3);
	}

	@Test
	public void insertNodeLeftRightCorrectly() {
		Integer values[] = { 2, 1, 3 };
		BinaryTree node = createBinaryTree(values);
		assertTrue(node.right().value == 3);
		assertTrue(node.left().value == 1);
	}

	@Test
	public void searchNodeCorrectly() {
		Integer values[] = { 2, 1, 3, 5, 7, 10 };
		BinaryTree node = createBinaryTree(values);
		Node result = node.search(new BinaryTree(7));
		System.out.println(result);
	}

	private BinaryTree createBinaryTree(Integer... values) {
		if (values.length == 0) {
			return new NullBinaryTree();
		}
		BinaryTree root = new BinaryTree(values[0]);
		values = java.util.Arrays.copyOfRange(values, 1, values.length);
		for (Integer value : values) {
			root.insert(new BinaryTree(value));
		}
		return root;

	}

}
