import java.util.*;

public class Solution {
	// better to use another collection class
	private Stack<Vertex> stack;
	
	public Solution(Stack<Vertex> s){
		stack = new Stack<Vertex>();
		for(Vertex v : s)
			stack.push(v);
	}
	
	public void show(Grid grid)	{
		System.out.println(toString());
		// System.out.println(toStringShow(grid));
	}
	
	public String toString()	{
		StringBuilder buf = new StringBuilder();
		for(Vertex v : stack)
			buf.append(v);
		return buf.toString();
	}
	
	public String toStringShow(Grid grid)	{
		StringBuilder g = new StringBuilder(grid.toString());
		int size = grid.size();
		
		for(int i=1; i<stack.size(); i++){
			Vertex v1 = stack.get(i-1);
			Vertex v2 = stack.get(i);
			int x1 = v1.getX(), y1 = v1.getY();
			int x2 = v2.getX(), y2 = v2.getY();
			// either x1==x2 or y1==y2
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
		// return toString() + "\n" + g.toString();
	}
}
