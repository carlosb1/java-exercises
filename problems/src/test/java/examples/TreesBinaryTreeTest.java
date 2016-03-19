package examples;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import examples.Trees.BinaryTree;

public class TreesBinaryTreeTest {

	@Test
	public void insertNodeLeftCorrectly() {
		BinaryTree node = new BinaryTree(2);
		node.insert(new BinaryTree(1));
		assertTrue(node.left().value == 1);
		// node.insert(new Node(3));
	}

}
