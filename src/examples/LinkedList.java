package examples;

//TODO ADD null object
public class LinkedList<T> {

	// TODO Add tests for equals
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LinkedList<?>)) {
			return false;
		}
		LinkedList<?> linkedListToCompare = (LinkedList<?>) obj;
		if (this.head == null && linkedListToCompare.head != null) {
			return false;
		}
		if (this.head != null && linkedListToCompare.head == null) {
			return false;
		}
		return (this.head.equals(linkedListToCompare.head));
	}

	public static class Node<T> {
		private T info;
		Node<T> next;

		public Node(T info) {
			this.info = info;
		}

		public T getInfo() {
			return info;
		}

		public Node<T> next() {
			return next;
		}

		public Node<T> append(Node<T> nextNode) {
			/* Discard simple cases */
			if (this.next == null) {
				this.next = nextNode;
				return this;
			}
			nextNode.next = this.next;
			this.next = nextNode;
			return this;
		}

		public Node<T> add(Node<T> nextNode) {
			Node<T> node = this;
			while (node.next != null) {
				node = node.next();
			}
			node.next = nextNode;
			return this;
		}

		// TODO pending delete duplication
		// TODO pending creation null object and its implementation
		public Node<T> get(int indexToFind) {
			Node<T> node = this;
			for (int index = 0; index < indexToFind; index++) {
				if (node.next == null) {
					return null;
				}
				node = node.next();
			}
			return node;
		}

		public Node<T> remove(int pos) {
			if (pos == 0) {
				if (this.next != null) {
					return this.next;
				} else {
					return this;
				}
			}

			Node<T> node = this.next;
			Node<T> previous = this;
			for (int index = 1; index <= pos; ++pos) {
				if (node == null) {
					return this;
				}
				if (index == pos) {
					previous.next = node.next;
					node = null;
					return this;
				}
				previous = node;
				node = node.next;

			}
			return this;

		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Node<?>)) {
				return false;
			}
			if (!((Node<?>) obj).info.equals(this.info)) {
				return false;
			}
			Node<?> valueToCompare = (Node<?>) obj;
			if (this.next == null && valueToCompare.next != null) {
				return false;
			}
			if (this.next != null && valueToCompare.next == null) {
				return false;
			}

			if (this.next == null && valueToCompare.next == null) {
				return true;
			}

			return this.next.equals(valueToCompare.next);
		}

		@Override
		public String toString() {
			return "Node [info=" + (info != null ? info : "null") + ", next=" + next + "]";
		}

	}

	private Node<T> head = null;
	private int size;

	public LinkedList() {
		this.head = null;
		this.size = 0;
	}

	public LinkedList<T> add(Node<T> newNode) {
		if (this.head == null) {
			this.head = newNode;
			size++;
			return this;
		}
		Node<T> node = this.head;
		while (node.next != null) {
			node = node.next;
		}
		node.next = newNode;
		size++;
		return this;
	}

	// TODO Apply refactor for iter from list
	public Node<T> get(int pos) throws ArrayIndexOutOfBoundsException {
		if (this.head == null) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int index = 0;
		Node<T> node = this.head;
		while (index < pos) {
			node = node.next();
			if (node == null) {
				throw new ArrayIndexOutOfBoundsException();
			}
			index++;
		}
		return new Node<T>(node.info);
	}

	public LinkedList<T> remove(int pos) {
		if (this.head == null) {
			throw new ArrayIndexOutOfBoundsException();
		}

		if (pos == 0) {
			Node<T> newHead = this.head.next;
			Node<T> oldHead = this.head;
			oldHead.next = null;
			this.head = newHead;
			size--;
			return this;
		}

		int index = 1;
		Node<T> previous = this.head;
		Node<T> node = this.head.next;
		while (index < pos) {
			previous = node;
			node = node.next();
			if (node == null) {
				throw new ArrayIndexOutOfBoundsException();
			}
			index++;
		}
		previous.next = node.next;
		node.next = null;
		size--;
		return this;

	}

	public int size() {
		return size;
	}

	public LinkedList<T> last(int pos) {
		if (this.head == null) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int index = 0;
		Node<T> node = this.head;
		while (index < pos) {
			node = node.next();
			if (node == null) {
				throw new ArrayIndexOutOfBoundsException();
			}
			index++;
		}

		LinkedList<T> lastNodes = new LinkedList<T>();
		while (node != null) {
			lastNodes.add(new Node(node.info));
			node = node.next();
		}
		return lastNodes;
	}

	public static LinkedList<Integer> CleanDuplicates(LinkedList<Integer> list) {
		LinkedList<Integer> notDuplicatedList = new LinkedList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			boolean found = false;
			int indexNotDuplicated = 0;
			Node<Integer> node = list.get(i);
			while (indexNotDuplicated < notDuplicatedList.size() && !found) {
				Node<Integer> noDuplicatedNode = notDuplicatedList.get(indexNotDuplicated);
				if (noDuplicatedNode.info.compareTo(node.info) == 0) {
					found = true;
				}
				indexNotDuplicated++;
			}
			if (!found) {
				notDuplicatedList.add(node);
			}
		}
		return notDuplicatedList;
	}

	public static void CleanDuplicatesWithoutBuffer(LinkedList<Integer> list) {
		Node<Integer> node = list.head;
		while (node != null) {
			Node<Integer> pPrevious = node;
			Node<Integer> pNode = node.next();
			while (pNode != null) {
				if (pNode.info.equals(node.info)) {
					pPrevious.next = pNode.next;
					pNode.next = null;
					pNode = pPrevious.next;
					list.size--;
				} else {
					pPrevious = pNode;
					pNode = pNode.next;
				}

			}

			node = node.next();
		}
	}

	public static void Delete(Integer deleteNumber, LinkedList<Integer> list) {
		if (list.head != null && list.head.info.equals(deleteNumber)) {
			list.head = list.head.next();
			return;
		}
		Node<Integer> pPrevious = list.head;
		Node<Integer> pNode = list.head.next();

		while (pNode != null) {
			if (pNode.info.equals(deleteNumber)) {
				pPrevious.next = pNode.next;
				pNode.next = null;
				pNode = pPrevious.next;
				list.size--;
				return;
			}

			pPrevious = pNode;
			pNode = pNode.next();
		}

	}

	public static void Split(LinkedList<Integer> inputList, Integer i, LinkedList<Integer> less, LinkedList<Integer> greater) {
		for (int index = 0; index < inputList.size; index++) {
			Node<Integer> node = inputList.get(index);
			if (node.info < i) {
				less.add(new Node(node.info));
			} else {
				greater.add(new Node(node.info));
			}
		}

	}

	public static int ToDigits(LinkedList<Integer> listOfValues) {
		int digitCount = 1;
		int result = 0;
		Node<Integer> node = listOfValues.head;

		while (node != null) {
			result += digitCount * node.info;
			digitCount *= 10;
			node = node.next;
		}

		return result;
	}

	public static LinkedList<Integer> Sum(LinkedList<Integer> num1, LinkedList<Integer> num2) {
		int value1 = ToDigits(num1);
		int value2 = ToDigits(num2);
		return ToLinkedList(value1 + value2);

	}

	public static LinkedList<Integer> ToLinkedList(Integer number) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		while (number > 0) {
			int digit = number % 10;
			list.add(new Node(digit));
			number = (number + 1) / 10;
		}
		return list;

	}

	public static boolean IsPalindrom(LinkedList<Integer> listOfValues) {
		int lastIndex = listOfValues.size - 1;
		int startIndex = 0;
		int halfSize = 0;
		if (lastIndex % 2 == 0) {
			halfSize = lastIndex / 2;
		} else {
			halfSize = (lastIndex + 1) / 2;
		}
		while (startIndex < halfSize) {
			if (listOfValues.get(startIndex).info != listOfValues.get(lastIndex).info) {
				return false;
			}
			startIndex++;
			lastIndex--;
		}
		return true;
	}

}
