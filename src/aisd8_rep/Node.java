package aisd8_rep;

public class Node
{
	Vehicle value;
	Node left;
	Node right;
	
	public Node(Vehicle x)
	{
		value = x;
		left = right = null;
	}
	
	public String toString()
	{
		return value.toString();
	}
}