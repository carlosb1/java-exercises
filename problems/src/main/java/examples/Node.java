package examples;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
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

	public abstract Node search(Node node);

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

		@Override
		public Node search(Node node) {
			return new NullNode();
		}

	}

}
