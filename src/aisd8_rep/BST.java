package aisd8_rep;

import java.util.ArrayDeque;
import java.util.Queue;

public class BST implements Tree
{
	Node root;
	int counter = 0;
	int searched = -1;
	
	
	private Node search(int value)
	{
		Node node = root;
		int cmp = 0;
		while(node != null && (cmp = value - node.value.age) != 0)
		{
			node = cmp < 0 ? node.left : node.right;
			counter++;
		}
		return node;
	}
	
	public Object find(int x)
	{
		Node t = search(x);
		return t != null ? t.value : null;
	}
	
	public Node insert(Object x, Node t)
	{
		if(t == null)
		{
			t = new Node((Vehicle) x);
		}
		else
		{
			int cmp = ((Vehicle) x).compareTo((Vehicle)t.value);
			if(cmp < 0)
				t.left = insert(x, t.left);
			else if(cmp > 0)
			{
				t.right = insert(x, t.right);
			}
			else
			{
				t.value = (Vehicle) x;
			}
		}
		return t;
	}
	
	public void insert(Object x)
	{
		root = insert(x, root);
	}
	
	private Node detachMin(Node t, Node del)
	{
		if(t.left != null)
		{
			t.left = detachMin(t.left, del);
		}
		else
		{
			del.value = t.value;
			t = t.right;
		}
		return t;
	}
	
	private Node delete(int x, Node t)
	{
		if(t == null)
			;
		else
		{
			int cmp = x - t.value.age;
			if(cmp < 0)
			{
				t.left = delete(x, t.left);
			}
			else if(cmp > 0)
			{
				t.right = delete(x, t.right);
			}
			else if(t.left != null && t.right != null)
			{
				t.right = detachMin(t.right, t);
			}
			else
			{
				t = (t.left != null) ? t.left : t.right;
			}
		}
		return t;
	}
	
	public void delete(int x)
	{
		root = delete(x, root);
	}
	
	//dsw
	public void DSW()
	{
		if (null != root)
		{
			createBackbone();
		    createPerfectBST();
		}
	}
		 
	private void createBackbone()
	{
		Node grandParent = null;
		Node parent = root;
		Node leftChild;
		 
		while (parent != null)
		{
			leftChild = parent.left;
		    if (leftChild != null)
		    {
		    	grandParent = rotateRight(grandParent, parent, leftChild);
		    	parent = leftChild;
		    }
		    else
		    {
		    	grandParent = parent;
		    	parent = parent.right;
		    }
		}
	}
	
	private Node rotateRight(Node grandParent, Node parent, Node leftChild)
	{
		if (grandParent != null)
		{
			grandParent.right = leftChild;
		}
		else
		{
			root = leftChild;
		}
		parent.left = leftChild.right;
		leftChild.right = parent;
		return grandParent;
	}
	
	private void rotateLeft(Node grandParent, Node parent, Node rightChild)
	{
		if (grandParent != null)
		{
			grandParent.right = rightChild;
		}
		else
		{
			root = rightChild;
		}
		parent.right = rightChild.left;
		rightChild.left = parent;
	}
		 
	private void makeRotations(int bound)
	{
		Node grandParent = null;
		Node parent = root;
		Node child = root.right;
		for (; bound > 0; bound--)
		{
			try
			{
				if (child != null)
				{
					rotateLeft(grandParent, parent, child);
					grandParent = child;
					parent = grandParent.right;
					child = parent.right;
				}
				else
				{
					break;
				}
			} catch (NullPointerException convenient)
			{
		      break;
		    }
		}
	}
	
	private int greatestPowerOf2LessThanN(int n)
	{
		int result = 2;
		int power = 0;
		while(power < n)
		{
			result = 2^power;
			power++;
		}
		return result;
	}
		 
	private void createPerfectBST()
	{
		int n = 0;
		for (Node temp = root; temp != null; temp = temp.right)
		{
			n++;
		}
		int m = greatestPowerOf2LessThanN(n + 1) - 1;
		makeRotations(n - m);
		 
		while (m > 1)
		{
			makeRotations(m /= 2);
		}
	}
	

	//pre-order
	public void preOrderSearch(int value, Node node)
	{
		if(value == node.value.age)
			searched = value;
		if(node.left != null)
			preOrderSearch(value, node.left);
		if(node.right != null)
			preOrderSearch(value, node.right);
	}
	
	public Object findPreOrder(int value)
	{
		searched = -1;
		preOrderSearch(value, root);
		return searched == -1 ? null : searched;
	}
	
	public void preOrderPrint(Node node)
	{
		System.out.print(node.value);
		if(node.left != null)
			preOrderPrint(node.left);
		if(node.right != null)
			preOrderPrint(node.right);
	}
	
	//in-order
	public void inOrderSearch(int value, Node node)
	{
		if(node.left != null)
			inOrderSearch(value, node.left);
		if(value == node.value.age)
			searched = value;
		if(node.right != null)
			inOrderSearch(value, node.right);
	}
	
	public Object findInOrder(int value)
	{
		searched = -1;
		inOrderSearch(value, root);
		return searched == -1 ? null : searched;
	}
	
	public void inOrderPrint(Node node)
	{
		if(node.left != null)
			inOrderPrint(node.left);
		System.out.print(node.value);
		if(node.right != null)
			inOrderPrint(node.right);
	}
	
	//post-order
	public void postOrderSearch(int value, Node node)
	{
		if(node.left != null)
			postOrderSearch(value, node.left);
		if(node.right != null)
			postOrderSearch(value, node.right);
		if(value == node.value.age)
			searched = value;
	}
	
	public Object findPostOrder(int value)
	{
		searched = -1;
		postOrderSearch(value, root);
		return searched == -1 ? null : searched;
	}
	
	public void postOrderPrint(Node node)
	{
		if(node.left != null)
			postOrderPrint(node.left);
		if(node.right != null)
			postOrderPrint(node.right);
		System.out.print(node.value);
	}
	
	//level-order
	public void levelOrderSearch(int value, Node node)
	{
        if(node == null)
        	;
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(node);
        while(!queue.isEmpty())
        {
            Node current = queue.peek();
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
            if(value ==  queue.poll().value.age)
            {
            	searched = value;
            }
        }
    }
	
	public Object findLevelOrder(int value)
	{
		searched = -1;
		levelOrderSearch(value, root);
		return searched == -1 ? null : searched;
	}
	
	public void levelOrderPrint(Node root)
	{
        if(root == null)
        	;
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            Node current = queue.peek();
            System.out.print(current.value);
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
            queue.poll();
        }
    }
	
	private String indent(int s)
	{
		String result = "";
	    for (int i = 0; i < s; i++)
	    	result += "  ";
	    return result;
	  }

	private String print(Node node, int depth)
	{
		if (node == null)
			return "";
		else
			return print(node.right, depth + 1) + indent(depth) + node.toString() + "\n"
		+ print(node.left, depth + 1);
	  }
	
	public String toString()
	{
	    if (root == null)
	    	return "";
	    else
	    	return print(root, 0);
	  }
}
