package lab6;
import java.util.Scanner;
public class NameInititals {
	
	public static void main (String args[])
	{
	
		System.out.println(initials("Edna del Humboldt von der Schooch"));
	}
	
	public static String initials(String s)
	  {
		Scanner scan = new Scanner(s);
		
		String first = "";
	    while (scan.hasNext())
	    {
	     String b = scan.next();
	     first += b.charAt(0);
	    }
	    
	    
	    return first;
	  }
}
