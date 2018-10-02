import java.util.*;

public class SolutionSet {
	private ArrayList<Solution> solutions;
	private Grid grid;
	
	public SolutionSet(Grid grid)	{
		solutions = new ArrayList<Solution>();
		this.grid = grid;
	}
	
	public void add(Solution solution)	{
		solutions.add(solution);
	}
	
	// i=1...num
	public void show(int i)	{
		if(i<=0 || i>solutions.size())
			throw new IllegalArgumentException("solution index invalid");
		solutions.get(i-1).show(grid);
	}
	
	public int num()	{
		return solutions.size();
	}
}
