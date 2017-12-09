package lab7;

import java.util.Arrays;
import java.util.Random;

/**
 * Class containing some utilities for manipulating arrays.
 */
public class ReverseArray
{
  public static void main(String[] args)
  {
    int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println("Before: " + Arrays.toString(test));
    reverse(test);
    System.out.println("After:  " + Arrays.toString(test));
   System.out.println(Arrays.toString(randomExperiment(10, 1000)));
   
    
  }
  
  /**
   * Reverses the contents of the given array.
   * @param arr
   */
  public static void reverse(int[] arr)
  {
    int front = 0;
    int rear = arr.length - 1;
    while (front < rear)
    {
      swap(arr, front, rear);
      
      // move indices towards the center
      front += 1;
      rear -= 1;
    }
  }
 
  public static void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;  
  }
  public static int[] getPositiveNumbers(int[] numbers)
  {
	  int total = 0;
	    for (int i = 0; i < numbers.length; i += 1)
	    {
	      if (numbers[i] > 0)
	      {
	        total += 1;
	      }
	    }
	    int[] pos= new int[total];
	    for (int i = 0; i < numbers.length; i += 1)
	    {
	      if (numbers[i] > 0)
	      {
	        pos[i]=numbers[i];
	      }
	    }  
	    return pos;
  }
  
  public static int[] randomExperiment(int max, int iters)
  {
	  Random rand = new Random();
	  int sth;
	  int[] count = new int[max];
	 
	  for (int i=0;i<=iters;i++)
	  {
		  sth = rand.nextInt(max);
		  
		 count[sth]=count[sth]+1;
	  }
	  return count;
  }
}