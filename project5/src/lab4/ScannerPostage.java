package lab4;
import java.util.Scanner;
/**
 * Edit this import to test a different version
 */
import postage3.PostageUtil;;

public class ScannerPostage
{
  /**
   * Tests the postage calculation utility.
   * @param args
   */
  public static void main(String[] args)
  {
	  String input = "Huey Louie Dewey";
	  System.out.println("enter one double number:");
	  Scanner scan = new Scanner(System.in);
	  double weightnum = scan.nextDouble();
    System.out.println(weightnum + " ounces:" + PostageUtil.computePostage(weightnum));
 
  }

}