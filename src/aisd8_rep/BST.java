package aisd8_rep;

public class BST implements Tree
{
	Node root;
	int counter = 0;
	
	int searched = -1;
	
	//pre-order search
	public void preOrderSearch(int value, Node node)
	{
		if(value == node.value.age)
			searched = value;
		if(node.left != null)
			preOrderSearch(value, node.left);
		if(node.right != null)
			preOrderSearch(value, node.right);
	}
	
	public Object findPreOrder(int x)
	{
		searched = -1;
		preOrderSearch(x, root);
		return searched == -1 ? null : searched;
	}
	
	//in-order search
	public void inOrderSearch(int value, Node node)
	{
		if(node.left != null)
			inOrderSearch(value, node.left);
		if(value == node.value.age)
			searched = value;
		if(node.right != null)
			inOrderSearch(value, node.right);
	}
	
	public Object findInOrder(int x)
	{
		searched = -1;
		inOrderSearch(x, root);
		return searched == -1 ? null : searched;
	}
	
	//post-order search
	public void postOrderSearch(int value, Node node)
	{
		if(node.left != null)
			postOrderSearch(value, node.left);
		if(node.right != null)
			postOrderSearch(value, node.right);
		if(value == node.value.age)
			searched = value;
	}
	
	public Object findPostOrder(int x)
	{
		searched = -1;
		postOrderSearch(x, root);
		return searched == -1 ? null : searched;
	}
	
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
		 
		while (null != parent)
		{
			leftChild = parent.left;
		    if (null != leftChild)
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
		if (null != grandParent)
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
		 
	private void createPerfectBST()
	{
		int n = 0;
		for (Node tmp = root; null != tmp; tmp = tmp.right)
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
		 
	private int greatestPowerOf2LessThanN(int n)
	{
		int x = MSB(n);//MSB
		return (1 << x);//2^x
	}

	public int MSB(int n)
	{
		int ndx = 0;
		while (1 < n)
		{
			n = (n >> 1);
		    ndx++;
		}
		return ndx;
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
				if (null != child)
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
		 
	private void rotateLeft(Node grandParent, Node parent, Node rightChild)
	{
		if (null != grandParent)
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
	
	
	public void preOrder(Node root)
	{
		System.out.print(root.value);
		if(root.left != null)
			preOrder(root.left);
		if(root.right != null)
			preOrder(root.right);
	}
	
	public void inOrder(Node root)
	{
		if(root.left != null)
			preOrder(root.left);
		System.out.print(root.value);
		if(root.right != null)
			preOrder(root.right);
	}
	
	public void postOrder(Node root)
	{
		if(root.left != null)
			preOrder(root.left);
		if(root.right != null)
			preOrder(root.right);
		System.out.print(root.value);
	}
	
	
	private String indent(int s)
	{
		String result = "";
	    for (int i = 0; i < s; i++)
	    	result += "  ";
	    return result;
	  }

	private String print(Node x, int depth)
	{
		if (x == null)
			return "";
		else
			return print(x.right, depth + 1) + indent(depth) + x.toString() + "\n"
		+ print(x.left, depth + 1);
	  }
	
	public String toString()
	{
	    if (root == null)
	    	return "";
	    else
	    	return print(root, 0);
	  }
}
