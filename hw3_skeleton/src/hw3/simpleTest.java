package hw3;
import api.*;
import java.io.*;
import java.util.*;

public class simpleTest 
{
	public static void main(String[] args)
	{
	/*
	// descriptor for initial grid of a 3 x 4 game
	String[] desc =
	{
	"2 0 1 3",
	"1 0 1 3",
	"2 1 2 1"
	};
	
	JewelFactory generator = new JewelFactory(4);
	Game g = new Game(desc, generator);
	System.out.println(g.toString());
	System.out.println(g.getJewel(2, 1)); // expected 1
	g.setJewel(2, 1, new Jewel(3));
	System.out.println(g.getJewel(2, 1)); // expected 3
	g.setJewel(2, 1, null);
	System.out.println(g.toString());
	
	Jewel[] arr = g.getCol(2);
	System.out.println(Arrays.toString(arr));
	System.out.println();
	Jewel[] testCol = Util.createFromString("5 6 7");
	g.setCol(1, testCol);
	System.out.println(g);
	
	Jewel[] test = Util.createFromString("4 2 2 2 4 4 3 3 3 3 2");
	ArrayList<Integer> result = Util.findRuns(test);
	System.out.println(result); // expected [1, 2, 3, 6, 7, 8, 9]
	
	
	String[] desc =
		{
		"4 2 2 2 4 4 3 3 3 3 2",
		"1 2 3 4 5 1 2 3 4 5 1"
		};
		JewelFactory generator = new JewelFactory(6);
		Game g = new Game(desc, generator);
		ArrayList<GridPosition> result = g.findHorizontalRuns(0);
		System.out.println(result);
		*/
	/*String[] desc =
	{
		"3 3 2 1 0",
		"3 3 0 0 1",
		"2 2 3 2 1",
		"0 2 3 1 0"
	};
	JewelFactory generator = new JewelFactory(6);
	Game g = new Game(desc, generator);
	 // ok to use null here since the jewel will be ignored
	GridPosition c0 = new GridPosition(1, 1, new Jewel(3));
	GridPosition c1 = new GridPosition(1, 2, new Jewel(0));
	boolean ret = g.select(c0, c1);
	System.out.println(ret);
	System.out.println(g);
	*/
		/*String[] desc =
			{
			"2 4 1 3",
			"1 4 1 3",
			"2 1 2 1"
			};

			Game g = new Game(desc, new JewelFactory(5));
			g.setJewel(0, 1, null);
			g.setJewel(1, 1, null);
			g.setJewel(0, 3, null);
			g.setJewel(2, 0, null);
			System.out.println(g);
			ArrayList<GridPosition> result = g.fillAll();
			System.out.println(g);
			System.out.println(result);
			
		Jewel[] test = Util.createFromString("0 1 2 3 4 5 6 7");
		test[2] = null;
		test[5] = null;
		System.out.println(Arrays.toString(test));
		ArrayList<Integer> result = Util.shiftNonNullElements(test);
		System.out.println(Arrays.toString(test));
		System.out.println(result);
		*/
		String[] desc =
			{
			"0 0 0",
			"0 1 0",
			"0 2 0",
			"0 3 0",
			"0 4 0",
			"0 5 0",
			"0 6 0",
			"0 7 0"
			};
		Game g = new Game(desc, new JewelFactory(8));
		g.setJewel(2, 1, null);
		g.setJewel(5, 1, null);
		System.out.println(g);
		ArrayList<GridPosition> result = g.shiftColumnDown(1);
		System.out.println(g);
		System.out.println(result);
	} 
}
