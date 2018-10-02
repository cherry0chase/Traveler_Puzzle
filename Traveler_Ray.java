import java.util.*;

public class Traveler_Ray {
	
	private void DFS(Grid grid, Stack<Vertex> stack, Vertex v)	{
		// System.out.println("DFS: " + v + "with stack " + grid.printStack(stack));
		stack.push(v);
		List<Vertex> unvisitedNeighbors = v.getUnvisitedNeighbors(stack);
		if (unvisitedNeighbors.isEmpty())
			grid.processSolution(stack);
		else	{
			for (Vertex n : unvisitedNeighbors){
				DFS(grid, stack, n);
			}
		}
		stack.pop();	// shouldn't have to do this
	}
	
	public static void main(String[] args) {
		int n;
		
		Scanner console = new Scanner(System.in);
		System.out.print("Welcome to Grid. Which number (2-9)? ");
		n = console.nextInt();
		if(n<2 || n>9){
			System.out.println("Good bye!");
			System.exit(1);
		}			

		Traveler_Ray traveler = new Traveler_Ray();
		Grid grid = new Grid(n);
		Stack<Vertex> stack = new Stack<Vertex>();
		traveler.DFS(grid, stack, grid.getStartingVertex());
		
		System.out.println("All done. Attempts: " + grid.numAttempts() + "; solutions: " + grid.numSolutions());
		System.out.println();
		
		if(grid.numSolutions() > 0)	{
			while(true)	{
				System.out.print("Which solution can I show you? ");
				int index = console.nextInt();
				if(index<=0 || index>grid.numSolutions()){
					System.out.println("Good bye!");
					break;
				}			
				else
					grid.getSolutions().show(index);
			}
		}	
	}
}
