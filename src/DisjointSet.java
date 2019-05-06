
import java.util.HashMap;

public class DisjointSet
{
	HashMap<Integer, Set> setMap = new HashMap<>();
	
	int nextInteger = 0;
	
	
	
	int makeSet()
	{
		while(setMap.containsKey(nextInteger))
		{
			nextInteger++;
		}
		
		makeSet(nextInteger);
		nextInteger++;
		return nextInteger - 1;
	}
	
	
	boolean makeSet(int key)
	{
		if(setMap.containsKey(key))
			return false;
		
		Set newSet = new Set(key);
		setMap.put(key, newSet);
		return true;
	}
	
	Set findSet(int key)
	{
		Set currentSet = setMap.get(key);
		return currentSet.findRoot();
	}
	
	boolean union(int first, int second)
	{
		Set firstRoot = setMap.get(first).findRoot();
		Set secondRoot = setMap.get(second).findRoot();
		
		return firstRoot.union(secondRoot);
	}
	
	boolean areConnected(int first, int second)
	{
		return findSet(first) == findSet(second);
	}
	
	
}


class Set
{
	private int key;
	private Set parent = null;
	private int rank = 0;
	
	Set(int key)
	{
		this.key = key;
	}
	
	Set getParent()
	{
		return parent;
	}
	
	void setParent(Set parent)
	{
		this.parent = parent;
	}
	
	Set findRoot()
	{
		if(parent == null)
			return this;
		
		parent = parent.findRoot();
		return parent;
	}
	
	boolean union(Set set)
	{
		if (set == this)
			return false;
		
		if(this.rank > set.rank)
		{
			set.parent = this;
		}
		else
		{
			parent = set;
			if(this.rank == set.rank)
				set.rank++;
		}
		return true;
	}
	
	
	
}