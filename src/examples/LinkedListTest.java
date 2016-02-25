package examples;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import examples.LinkedList.Node;

/**
 * Unit tests for LinkedList implementation
 * 
 * @author carlosb
 *
 */
public class LinkedListTest {
	private static final LinkedList<Integer> CLEANED_LIST = new LinkedList<Integer>().add(new Node(1)).add(new Node(3)).add(new Node(5));;
	private static final LinkedList<Integer> LAST_LIST = new LinkedList<Integer>().add(new Node(5)).add(new Node(5));
	private static final LinkedList<Integer> DELETED_LIST = new LinkedList<Integer>().add(new Node(1)).add(new Node(5)).add(new Node(5));;

	private Node<Integer> list = null;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		list = null;
	}

	@Test
	public void testsEquals() {
		final Node<Integer> newNode = new Node<Integer>(3);
		final Node<Integer> newNodeToCompare = new Node<Integer>(3);
		Assert.assertTrue(newNode.getInfo() == newNodeToCompare.getInfo());
	}

	@Test
	public void testsEqualsDifsListSizes() {
		final Node<Integer> newNode = new Node<Integer>(3);
		final Node<Integer> newNodeToCompare = new Node<Integer>(3).append(new Node<Integer>(5));
		Assert.assertNotEquals(newNode, newNodeToCompare);
	}

	@Test
	public void testsEqualsList() {
		final Node<Integer> newNode = new Node<Integer>(3).append(new Node<Integer>(5));
		final Node<Integer> newNodeToCompare = new Node<Integer>(3).append(new Node<Integer>(5));
		Assert.assertEquals(newNode, newNodeToCompare);
	}

	@Test
	public void testAppend() {
		list = new Node<Integer>(1);
		final Node<Integer> newNode = new Node<Integer>(3);
		list.append(newNode);
		Assert.assertEquals(list, new Node<Integer>(1).append(new Node<Integer>(3)));
	}

	@Test
	public void testAppendWithDifsSizes() {
		list = new Node<Integer>(1);
		final Node<Integer> newNode = new Node<Integer>(3);
		list.append(newNode);

		final Node<Integer> newNodeToCompare = new Node<Integer>(5);
		list.append(newNodeToCompare);

		Assert.assertTrue(list.next().getInfo() == new Node<Integer>(5).getInfo());
	}

	@Test
	public void testRemoveElement() {
		Node<Integer> listElems = new Node<Integer>(1);
		listElems.append(new Node<Integer>(2));
		listElems.next().append(new Node<Integer>(3));

		final Node<Integer> listElems2 = new Node<Integer>(1).append(new Node<Integer>(3));
		listElems.remove(1);
		Assert.assertEquals(listElems, listElems2);

	}

	@Test
	public void testRemoveElementWithTwoValues() {
		Node<Integer> listElems = new Node<Integer>(1);
		listElems.append(new Node<Integer>(3));
		final Node<Integer> listElems2 = new Node<Integer>(1);
		listElems.remove(1);
		Assert.assertEquals(listElems, listElems2);
	}

	@Test
	public void testRemoveElementOneValue() {
		Node<Integer> listElems = new Node<Integer>(1);
		final Node<Integer> listElems2 = new Node<Integer>(1);
		listElems.remove(0);
		Assert.assertEquals(listElems, listElems2);
	}

	@Test
	public void testRemoveElementIncorrectIndex() {
		Node<Integer> listElems = new Node<Integer>(1);
		final Node<Integer> listElems2 = new Node<Integer>(1);
		listElems.remove(1);
		Assert.assertEquals(listElems, listElems2);
	}

	/**
	 * Clean Duplicate test
	 */
	@Test
	public void testCleanDuplicate() {
		LinkedList<Integer> listofValues = new LinkedList<Integer>();
		// TODO refactor list work without nodes
		listofValues.add(new Node(1)).add(new Node(1)).add(new Node(3)).add(new Node(3)).add(new Node(5)).add(new Node(5));
		listofValues = LinkedList.CleanDuplicates(listofValues);
		Assert.assertEquals(CLEANED_LIST, listofValues);
	}

	/**
	 * Clean duplicates without space complexity
	 */
	@Test
	public void testCleanDuplicateWithoutBuffer() {
		LinkedList<Integer> listofValues = new LinkedList<Integer>();
		listofValues.add(new Node(1)).add(new Node(1)).add(new Node(3)).add(new Node(3)).add(new Node(5)).add(new Node(5));
		LinkedList.CleanDuplicatesWithoutBuffer(listofValues);
		Assert.assertEquals(CLEANED_LIST, listofValues);
	}

	/**
	 * Get last kth elements
	 */
	@Test
	public void testLast() {
		LinkedList<Integer> listofValues = new LinkedList<Integer>();
		listofValues.add(new Node(1)).add(new Node(1)).add(new Node(3)).add(new Node(3)).add(new Node(5)).add(new Node(5));
		LinkedList<Integer> lastValues = listofValues.last(4);
		Assert.assertEquals(LAST_LIST, lastValues);
	}

	@Test
	public void testDeleteNode() {
		LinkedList<Integer> listofValues = new LinkedList<Integer>();
		listofValues.add(new Node(1)).add(new Node(3)).add(new Node(5)).add(new Node(5));
		LinkedList.Delete(3, listofValues);
		Assert.assertEquals(DELETED_LIST, listofValues);
	}

	@Test
	public void testPartitionList() {
		LinkedList<Integer> listofValues = new LinkedList<Integer>();
		listofValues.add(new Node(1)).add(new Node(2)).add(new Node(1)).add(new Node(8)).add(new Node(4)).add(new Node(3)).add(new Node(5)).add(new Node(5));
		LinkedList<Integer> less = new LinkedList<Integer>();
		LinkedList<Integer> greater = new LinkedList<Integer>();

		LinkedList.Split(listofValues, 5, less, greater);
		Assert.assertEquals(less, new LinkedList<Integer>()
				.add(new Node<Integer>(1).add(new Node<Integer>(2)).add(new Node<Integer>(1)).add(new Node<Integer>(4)).add(new Node<Integer>(3))));
		;
		Assert.assertEquals(greater, new LinkedList<Integer>().add(new Node<Integer>(8)).add(new Node<Integer>(5)).add(new Node<Integer>(5)));
	}

	@Test
	public void testListToDigits() {
		LinkedList<Integer> listOfValues = new LinkedList<Integer>();
		listOfValues.add(new Node(1)).add(new Node(2)).add(new Node(1)).add(new Node(8)).add(new Node(4)).add(new Node(3)).add(new Node(5)).add(new Node(5));
		int value = LinkedList.ToDigits(listOfValues);
		Assert.assertEquals(55348121, value);
	}

	@Test
	public void testSum() {
		LinkedList<Integer> values1 = new LinkedList<Integer>().add(new Node(1)).add(new Node(2)).add(new Node(1));
		LinkedList<Integer> values2 = new LinkedList<Integer>().add(new Node(1)).add(new Node(1)).add(new Node(1));
		LinkedList<Integer> resultExpected = new LinkedList<Integer>().add(new Node(2)).add(new Node(3)).add(new Node(2));

		LinkedList<Integer> result = LinkedList.Sum(values1, values2);
		Assert.assertEquals(resultExpected, result);
	}

	@Test
	public void testListIsPalindrom() {
		LinkedList<Integer> listOfValues = new LinkedList<Integer>();
		listOfValues.add(new Node(1)).add(new Node(2)).add(new Node(1)).add(new Node(1)).add(new Node(2)).add(new Node(1));
		Assert.assertTrue(LinkedList.IsPalindrom(listOfValues));
	}

	@Test
	public void testListIsNotPalindrom() {
		LinkedList<Integer> listOfValues = new LinkedList<Integer>();
		listOfValues.add(new Node(1)).add(new Node(2)).add(new Node(2)).add(new Node(1)).add(new Node(2)).add(new Node(1));
		Assert.assertFalse(LinkedList.IsPalindrom(listOfValues));
	}

}
