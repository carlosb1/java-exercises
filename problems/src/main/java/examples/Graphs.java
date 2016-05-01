package examples;

import java.util.Stack;

import examples.Node.NullNode;

public class Graphs {
	public static class DFS implements Searcher {
		private Stack<Graph> stack;

		public DFS() {
			stack = new Stack<Graph>();
		}

		@Override
		public Node run(Graph directedGraph, Graph node) {

			directedGraph.visited = true;
			if (isFound(node, directedGraph)) {
				return node;
			}
			stack.push(directedGraph);
			while (!stack.isEmpty()) {
				Graph newNode = stack.pop();
				for (Node child : newNode.adjacent) {
					if (child.visited == false) {
						child.visited = true;
						if (isFound(node, (Graph) child)) {
							return child;
						}
						stack.push((Graph) child);
					}
				}
			}
			return new NullNode();
		}

		private boolean isFound(Node child, Graph directedGraph) {
			return (child.value == directedGraph.value);
		}

	}

	public interface Searcher {

		Node run(Graph directedGraph, Graph node);

	}

	public static abstract class Graph extends Node {
		private boolean visited;

		public Graph(Integer value) {
			super(value);
			visited = false;
		}

	}

	public static class DirectedGraph extends Graph {

		private Searcher searcher;

		public DirectedGraph(Integer value) {
			super(value);

			searcher = new Graphs.DFS();
			// TODO Auto-generated constructor stub
		}

		@Override
		public void insert(Node node) {
			super.adjacent.add(node);
		}

		public void setSearcher(Searcher searcher) {
			this.searcher = searcher;
		}

		@Override
		public Node search(Node node) {
			// TODO this casting is horrible
			Node result = searcher.run(this, (Graph) node);
			return result;
		}
	}

}
