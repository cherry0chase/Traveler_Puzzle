import java.util.*;

public class Grid {
	private Vertex[][] vertices;
	private int size;
	// Hole is assumed to always be (1,0)
	private int hole_x = 1;
	private int hole_y = 0;
	// Alaways starts from (0,0)
	private int starting_x = 0;
	private int starting_y = 0;
	private SolutionSet solutions;
	private int numAttempts = 0;

	public Grid(int n)	{
		size = n;
		vertices = new Vertex[n][n];
		for(int i=0; i<n; i++)	{
			for(int j=0; j<n; j++)	{
				Vertex v = new Vertex();
				v.setX(i);
				v.setY(j);
				vertices[i][j] = v;
			}
		}
		buildGraph();
		solutions = new SolutionSet(this);
	}

	/*
	 * Build the graph by populating neighbor list for each vertex, excluding the hole and heeding the boundaries.
	 */
	private void buildGraph()	{
		for(int i=0; i<size; i++)	{
			for(int j=0; j<size; j++)	{
				Vertex v = vertices[i][j];
				int x = v.getX();
				int y = v.getY();
				if(x == hole_x && y == hole_y)
					continue;
				LinkedList<Vertex> neighbors = new LinkedList<Vertex>();
				// down: x, y+1
				if(!(x==hole_x && y+1==hole_y) && y+1<size)
					neighbors.add(vertices[x][y+1]);
				// right: x+1, y
				if(!(x+1==hole_x && y==hole_y) && x+1<size)
					neighbors.add(vertices[x+1][y]);
				// up: x, y-1
				if(!(x==hole_x && y-1==hole_y) && y-1>=0)
					neighbors.add(vertices[x][y-1]);
				// left: x-1, y
				if(!(x-1==hole_x && y==hole_y) && x-1>=0)
					neighbors.add(vertices[x-1][y]);
				v.setNeighbors(neighbors);
			}
		}
	}
	
	/*
	 * returns a string visualizing the grid
	 */
	public String toString()	{
		StringBuilder buf = new StringBuilder();
		
		for(int row=0; row<size; row++)	{
			// first line: "+ X + +\n"
			for(int col=0; col<size; col++)	{
				// + or X
				if(row == hole_y && col == hole_x)
					buf.append('X');
				else
					buf.append('+');
				// ' ' or '\n'
				if(col<size-1)
					buf.append(' ');
				else // last one
					buf.append('\n');
			}
			// 2nd   line: "       \n"
			for(int col=0; col<size; col++)	{
				buf.append(' ');
				// ' ' or '\n'
				if(col<size-1)
					buf.append(' ');
				else // last one
					buf.append('\n');
			}
		}

		return buf.toString();
	}
	
	/*
	 * We have reached the dead end. Check to see if stack includes all the vertices. If yes, print it out.
	 */
	public void processSolution(Stack<Vertex> stack){
		// System.out.print("processSolution: " + printStack(stack));
		numAttempts++;
		if(allVisited(stack)){
			Solution solution = new Solution(stack);
			solutions.add(solution);
		} 
	}
	
	public SolutionSet getSolutions()	{
		return solutions;
	}
	
	public int numAttempts()	{
		return numAttempts;
	}
	
	public int numSolutions()	{
		return solutions.num();
	}
	
	private boolean allVisited(Stack<Vertex> stack){
		for(int i=0; i<size; i++)	{
			for(int j=0; j<size; j++)	{
				Vertex v = vertices[i][j];
				if(v.getX() == hole_x && v.getY() == hole_y)
					continue;
				if(stack.search(v) == -1)
					return false;
			}
		}
		return true;
	}
	
	// for debugging
	public String printStack(Stack<Vertex> stack)	{
		StringBuilder buf = new StringBuilder();
		for(Vertex v : stack)
			buf.append(v);
		return buf.toString();
	}
	
	public Vertex getStartingVertex()	{
		return vertices[starting_x][starting_y];
	}
	
	public Vertex getHole()	{
		return vertices[hole_x][hole_y];
	}
	
	public int size()	{
		return size;
	}

	/* 
	 * tests the class with the example of 4x4 grid
	 */ 
	public static void main(String[] args) {
		Grid grid = new Grid(4);
		System.out.println("Grid " + grid.size() + " created: starting: " + grid.getStartingVertex() + ", hole: " + grid.getHole());
		System.out.println(grid);
	}

}
