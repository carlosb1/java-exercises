package examples;

import examples.Node.NullNode;

public class Graphs {
	public static class DFS implements Searcher {

		@Override
		public Node run(Graph directedGraph, Graph node) {
			// TODO Auto-generated method stub
			return new NullNode();
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
