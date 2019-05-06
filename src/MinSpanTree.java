import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class MinSpanTree
{
	
	ArrayList<Edge> edges = new ArrayList<>();
	HashSet<Integer> vertices = new HashSet<>();
	
	public MinSpanTree addEdge(int first, int second, int weight)
	{
		vertices.add(first);
		vertices.add(second);
		edges.add(new Edge(first, second, weight));
		return this;
	}
	
	
	public ArrayList<Edge> findMinSpan()
	{
		ArrayList<Edge> results = new ArrayList<>();
		Collections.sort(edges);
		
		DisjointSet disjointSet = new DisjointSet();
		
		for(int vertex: vertices)
		{
			disjointSet.makeSet(vertex);
		}
		Edge currentEdge;
		while(edges.size() > 0)
		{
			currentEdge = edges.remove(0);
			if(disjointSet.union(currentEdge.getFirstVertex(), currentEdge.getSecondVertex()))
			{
				results.add(currentEdge);
			}
		}
		
		return results;
	}
	
	
	public static void main(String[] args)
	{
		MinSpanTree minSpanTree = new MinSpanTree();
		minSpanTree
				.addEdge(1,2,3 ).addEdge(2, 3, 1).addEdge(3, 2, 3)
				.addEdge(5, 4, 10).addEdge(4, 1, 1);
		
		ArrayList<Edge> result = minSpanTree.findMinSpan();
		
		for(Edge edge:result)
		{
			System.out.println(edge.getFirstVertex() + " " + edge.getSecondVertex());
		}
	}
	
	
}




class Edge implements Comparable<Edge>
{
	private int firstVertex;
	private int secondVertex;
	private int weight;
	
	Edge(int firstVertex, int secondVertex, int weight)
	{
		this.firstVertex = firstVertex;
		this.secondVertex = secondVertex;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge o)
	{
		return this.getWeight() - o.getWeight();
	}
	
	
	public int getFirstVertex()
	{
		return firstVertex;
	}
	
	public int getSecondVertex()
	{
		return secondVertex;
	}
	
	public int getWeight()
	{
		return weight;
	}
}