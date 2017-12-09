package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor
{
  public static void main(String[] args) throws FileNotFoundException
  {
   /* Scanner in = new Scanner(System.in);
    File outFile = new File("mydocument.txt");
    PrintWriter out = new PrintWriter(outFile);

    // Echo keyboard input out to the file.
   while (in.hasNextLine())
    {
      String line = in.nextLine();
      out.println(line);
    }
    
    System.out.println("Done");
    out.close(); // Important: don't forget to close!*/
    wordCount();
  }
  public static void wordCount() throws FileNotFoundException{
	  File file = new File("story.txt");
	  Scanner scanner = new Scanner(file);
	  int lineCount = 1;
	  
	  while (scanner.hasNextLine())
	    {
	      String line = scanner.nextLine();
	      System.out.print(lineCount + " ");
	      Scanner scan = new Scanner(line);
	      int wordCount=0;
	      while(scan.hasNext())
	      {
	    	  wordCount+=1;
	    	  scan.next();
	      }
	      System.out.println(wordCount);
	      lineCount += 1;
	     
	    }
  }
}
