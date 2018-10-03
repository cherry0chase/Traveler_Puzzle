import java.util.*;

public class Solution {
	// better to use another collection class
	// stack represents the solution.
	private Stack<Vertex> stack;

	// saves the solution by copying s	
	public Solution(Stack<Vertex> s){
		stack = new Stack<Vertex>();
		for(Vertex v : s)
			stack.push(v);
	}
	
	public void show(Grid grid)	{
		// System.out.println(toString());
		System.out.println(toStringShow(grid));
	}
	
	public String toString()	{
		StringBuilder buf = new StringBuilder();
		for(Vertex v : stack)
			buf.append(v);
		return buf.toString();
	}
	
	/*
	 * returns a string to visualize the solution.
	 */ 
	public String toStringShow(Grid grid)	{
		// first, gets a string to visualize the empty grid
		StringBuilder g = new StringBuilder(grid.toString());
		int size = grid.size();
		
		for(int i=1; i<stack.size(); i++){
			Vertex v1 = stack.get(i-1);
			Vertex v2 = stack.get(i);
			int x1 = v1.getX(), y1 = v1.getY();
			int x2 = v2.getX(), y2 = v2.getY();
			// either x1==x2 or y1==y2: replace ' ' with '|' or '-'
			if(x1 == x2)	{ // x1==x2: 1 step down or up from (x1,y1) 
				// locate (x1,y1)
				int index = 4 * size * y1 + 2 * x1;
				// locate where to set '|'
				if(y1 < y2) index += 2*size; 
				else index -= 2*size;
				g.setCharAt(index, '|');				
			}
			else {	// y1==y2: 1 step right or left from (x1,y1) 
				// locate (x1,y1)
				int index = 4 * size * y1 + 2 * x1;
				// locate where to set '-'
				if(x1 < x2) index++; 
				else index--;
				g.setCharAt(index, '-');
			}
		}
		
		return g.toString();		
	}
}
