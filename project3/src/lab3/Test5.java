package lab3;
import java.util.Random;
public class Test5 
{
	public static void main(String[] args)
	{
		Random rand = new Random();
		//if there is an argument in the object, then the numbers will be a certain set
		System.out.println(rand.nextInt(6));
		System.out.println(rand.nextInt(6));
		System.out.println(rand.nextInt(6));
		System.out.println(rand.nextInt(6));
		//prints out random numbers through 5
	}

}
