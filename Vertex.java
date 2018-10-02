import java.util.*;

public class Vertex {
	private int _x;
	private int _y;
	private LinkedList<Vertex> _neighbors;
	
	public Vertex()	{
		_neighbors = new LinkedList<Vertex>();
	}
	
	public void setX(int x)	{
		_x = x;
	}
	public void setY(int y)	{
		_y = y;
	}
	public int getX()	{
		return _x;
	}
	public int getY() 	{
		return _y;
	}
	
	public String toString()	{
		return "" + getX() + getY() + " ";
	}
	
	public void setNeighbors(LinkedList<Vertex> neighbors){
		_neighbors = neighbors;
	}
	
	public LinkedList<Vertex> getNeighbors()	{
		return _neighbors;
	}
	
	public LinkedList<Vertex> getUnvisitedNeighbors(Stack<Vertex> s){
		LinkedList<Vertex> list = new LinkedList<Vertex>();
		for(Vertex v : _neighbors)	{
			if(s.search(v) < 0)	{
				list.add(v);
			}
		}
		return list;
	}
}
