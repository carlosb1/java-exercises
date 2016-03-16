package examples;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	public static class Node {
		public boolean visited;
		public List<Node> adjacent;

		public Node() {
			visited = false;
			adjacent = new ArrayList<Node>();
		}

	}

}
