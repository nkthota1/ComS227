package lab8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListExample3
{
  public static void main(String[] args)
  {
    ArrayList<String> list = new ArrayList<String>();
    list.add("A");
    list.add("B");
    list.add("A");
    String[] result = removeDuplicates(list);
    System.out.println(Arrays.toString(result));
  }
  
  public static int[] getPositiveNumbers(int[] arr)
  {
    ArrayList<Integer> positiveNums = new ArrayList<Integer>();
    for (int num : arr)
    {
      if (num > 0)
      {
        positiveNums.add(num);
      }
    }
    
    int[] ret = new int[positiveNums.size()];
    for (int i = 0; i < positiveNums.size(); i += 1)
    {
      ret[i] = positiveNums.get(i);
    }
    return ret;
  }
  
  public static String[] removeDuplicates(ArrayList words)
  {
	  ArrayList<String> noDuplicates = new ArrayList<String>();
	  for (String word : (ArrayList<String>)words)
	    {
	      if (!noDuplicates.contains(word))
	      {
	        noDuplicates.add(word);
	      }
	    }
	  String[] ret = new String[noDuplicates.size()];
	    for (int i = 0; i < noDuplicates.size(); i += 1)
	    {
	      ret[i] = noDuplicates.get(i);
	    }
	  
	  return ret;
  }
}