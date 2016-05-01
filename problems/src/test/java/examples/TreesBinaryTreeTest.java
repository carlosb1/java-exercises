package examples;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import examples.Trees.BinaryTree;
import examples.Trees.NullBinaryTree;
import examples.Trees.Pair;

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
	}

	@Test
	public void isBalanced() {
		Integer values[] = { 5, 3, 7, 1, 4, 6, 8 };
		BinaryTree node = createBinaryTree(values);
		Pair<Boolean, Integer> result = Trees.IsBalanced(node);
		assertTrue(result.first);
	}

	@Test
	public void isNotBalanced() {
		Integer values[] = { 2, 1, 3, 5, 7, 10 };
		BinaryTree node = createBinaryTree(values);
		Pair<Boolean, Integer> result = Trees.IsBalanced(node);
		assertFalse(result.first);
	}

	@Test
	public void createMinimumHeightFine() {
		Integer values[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		BinaryTree node = Trees.BinaryTree.CreateBinaryMinimTree(java.util.Arrays.asList(values));
		assertTrue(node.value == 6);
		assertTrue(node.adjacent.get(0).value == 3);
		assertTrue(node.adjacent.get(1).value == 9);

		assertTrue(node.adjacent.get(0).adjacent.get(0).value == 2);
		assertTrue(node.adjacent.get(0).adjacent.get(1).value == 5);
		assertTrue(node.adjacent.get(0).adjacent.get(0).adjacent.get(0).value == 1);

		assertTrue(node.adjacent.get(0).adjacent.get(1).adjacent.get(0).value == 4);
		assertTrue(node.adjacent.get(1).adjacent.get(0).value == 8);
		assertTrue(node.adjacent.get(1).adjacent.get(1).value == 10);
		assertTrue(node.adjacent.get(1).adjacent.get(0).adjacent.get(0).value == 7);
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
