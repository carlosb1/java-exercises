package examples.datastructures;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import examples.datastructures.Graphs.DirectedGraph;

public class GraphsTest {

	@Test
	public void EasyNodeFind() {
		// TODO add

		Graphs.DirectedGraph graph = new Graphs.DirectedGraph(10);
		graph.insert(new DirectedGraph(20));
		Graphs.DirectedGraph graph2 = new DirectedGraph(2);
		graph2.insert(new DirectedGraph(10));
		graph2.insert(new DirectedGraph(1));
		graph.insert(graph2);

		Node findGraph = graph.search(new DirectedGraph(20));
		assertTrue(!findGraph.isNull());
	}

	@Test
	public void difficultNodeFind() {
		Graphs.DirectedGraph graph = new Graphs.DirectedGraph(10);
		graph.insert(new DirectedGraph(20));
		Graphs.DirectedGraph graph2 = new DirectedGraph(2);
		graph2.insert(new DirectedGraph(10));
		graph2.insert(new DirectedGraph(1));
		graph.insert(graph2);

		Node findGraph = graph.search(new DirectedGraph(10));
		assertTrue(!findGraph.isNull());
	}

	@Test
	public void findNodeNotExist() {
		Graphs.DirectedGraph graph = new Graphs.DirectedGraph(10);
		graph.insert(new DirectedGraph(20));
		Graphs.DirectedGraph graph2 = new DirectedGraph(2);
		graph2.insert(new DirectedGraph(10));
		graph2.insert(new DirectedGraph(1));
		graph.insert(graph2);

		Node findGraph = graph.search(new DirectedGraph(7));
		assertTrue(findGraph.isNull());
	}

}
