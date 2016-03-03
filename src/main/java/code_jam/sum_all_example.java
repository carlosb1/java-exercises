package code_jam;// This is a sample solution to the "Sum all integers" problem. Each node sums// the elements that belong to it (that is, the ones with position equal to// MyNodeId() modulo NumberOfNodes()).//// To showcase the communication a bit better, instead of sending all the// results to a "master" node, each node sends its result to the next one,// accumulating the result from the previous node. The last node prints the// final result.//// Note: In Java solutions, you must name the main class "Main". Otherwise,// you will get a compilation error.public class sum_all_example {	public static void main(String[] args) {		long sum = 0;		for (long pos = mock_message.MyNodeId(); pos < sum_all.GetN(); pos += mock_message.NumberOfNodes()) {			sum += sum_all.GetNumber(pos);		}		if (mock_message.MyNodeId() > 0) {			mock_message.Receive(mock_message.MyNodeId() - 1);			sum += mock_message.GetLL(mock_message.MyNodeId() - 1);		}		if (mock_message.MyNodeId() < mock_message.NumberOfNodes() - 1) {			mock_message.PutLL(mock_message.MyNodeId() + 1, sum);			mock_message.Send(mock_message.MyNodeId() + 1);		} else {			System.out.println(sum);		}	}}