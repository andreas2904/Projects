package de.unistuttgart.dsass2014.ex08.p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;


public class ShortestPath implements IShortestPath {

	private final IWeightedGraph graph;
	private final int startVertex;
	private ArrayList<Node> ar = new ArrayList<Node>()

	/**
	 * Initializes the shortest path for weighted graph <tt>graph</tt> from
	 * starting vertex <tt>startVertex</tt>. Calls the bellmanFord(graph,
	 * startVertex) method to execute the Bellman Ford algorithm.
	 * 
	 * @param graph
	 *            the weighted graph
	 * @param startVertex
	 *            the starting vertex
	 */
	public ShortestPath(IWeightedGraph graph, int startVertex) {
		this.graph = graph;
		this.startVertex = startVertex;
		bellmanFord(this.graph, this.startVertex);
	}


	@Override
	public void bellmanFord(IWeightedGraph graph, int startVertex) {
		Iterator<IEdge> it = graph.edgeIterator();
		PriorityQueue<Integer> qu = new PriorityQueue<Integer>();
		//ArrayList<IEdge> adlist = new ArrayList<IEdge>();
		Node start = new Node();
		start.id = 0;
		start.source = 0;
		start.weight = 0;
		ar.add(0,start);
		int actual = startVertex;
		do{
			if(it.next().getSource() == actual){
				if(!qu.contains(it.next().getSource()) || !(ar.size()> it.next().getSource() && ar.get(it.next().getSource()) != null)){
					
					qu.add(it.next().getDestination());
					
				}
				Node node = new Node();
				node.id = it.next().getDestination();
				node.weight = ar.get(node.source).weight + it.next().getWeight();
				node.source = actual;
				
				if(ar.size()> node.id && ar.get(node.id) != null){
					if(ar.get(node.id).weight > node.weight){
						ar.set(node.id, node);
					}
					
				}
				
				ar.add(it.next().getDestination(), node);
				
				
			}
			if(!it.hasNext()){
				it = graph.edgeIterator();
				actual = qu.poll();
			}
			else{
				it.remove();
			}
		}while(!qu.isEmpty());
		
		


	}
	@Override
	public boolean hasNegativeCycle() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public double distanceTo(int destination) {
		Iterator<IEdge> it = graph.edgeIterator();
		
		while(it.hasNext()){
			int source = it.next().getSource();
			if( source == startVertex && destination == it.next().getDestination()){
				if(it.next().getWeight() >= 0){
					return it.next().getWeight();
				}
				else{
					throw new IllegalStateException();
				}
			}
			it.remove();
		}
		return -1;
	}


	@Override
	public boolean existsPathTo(int destination) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Iterator<IEdge> pathTo(int destination) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class Node{
		int source;
		double weight;
		int id;
		
		public Node(){
			weight = Double.POSITIVE_INFINITY;
		}
		
	}

}
