package aisd8_rep;

import java.util.Random;

public class Test
{
	public static void main(String args[])
	{
		BST bst = new BST();
		int size = 10;
		Random rand = new Random();
		
		//build tree
		for(int i = 0; i < size; i++)
		{
			int temp = rand.nextInt(size);
			if(bst.find(temp) == null)
				bst.insert(new Vehicle(temp, "audi"));
			else
				i--;
		}
		
		System.out.println("pre DSW: ");
		System.out.println(bst.toString());
		
		int sum1 = 0;
		for(int i = 0; i < 10; i++)
		{
			bst.counter = 0;
			bst.find(rand.nextInt(size));
			sum1 += bst.counter;
		}
		
		System.out.println("Average number of comparisons: "+(sum1 / 10));
		
		System.out.println("pre-order: ");
		bst.preOrder(bst.root);
		System.out.println("\nin-order: ");
		bst.inOrder(bst.root);
		System.out.println("\npost-order: ");
		bst.postOrder(bst.root);
		
		int temp = rand.nextInt(size);
		System.out.println("\nsearched number: " + temp);
		System.out.println("pre-order search: "+bst.findPreOrder(temp));
		System.out.println("in-order search: "+bst.findInOrder(temp));
		System.out.println("post-order search: "+bst.findPostOrder(temp));
		
		bst.DSW();
		
		System.out.println("\npost DSW: ");
		System.out.println(bst.toString());
		
		int sum2 = 0;
		for(int i = 0; i < 10; i++)
		{
			bst.counter = 0;
			bst.find(rand.nextInt(size));
			sum2 += bst.counter;
		}
		
		System.out.println("Average number of comparisons: "+(sum2 / 10));
		
		System.out.println("pre-order: ");
		bst.preOrder(bst.root);
		System.out.println("\nin-order: ");
		bst.inOrder(bst.root);
		System.out.println("\npost-order: ");
		bst.postOrder(bst.root);
		
		System.out.println("\nsearched number: " + temp);
		System.out.println("pre-order search: "+bst.findPreOrder(temp));
		System.out.println("in-order search: "+bst.findInOrder(temp));
		System.out.println("post-order search: "+bst.findPostOrder(temp));
	}
}
