package mini1;

/**
 * Simple model of a strand of DNA.  An instance of this class encapsulates
 * a string of characters.  A character is called <em>valid</em> if it
 * is one of the the letters 'A', 'C', 'G', 'T' (uppercase) and a DNASequence
 * object is called <em>valid</em> if all its characters are valid.
 * The characters 'A' and 'T' are said to be <em>complements</em> of each 
 * other and likewise the characters 'C' and 'G' are complements.  
 * Complementary characters are said to <em>bond</em> with each other.
 * The main operations on this class are for the purpose of determining the 
 * number and locations of bonds that one sequence can form with another
 * depending how they are aligned (shifted) with each other.
 * <p>
 * However, it is entirely possible to construct a DNASequence object
 * containing invalid characters, and all operations should work
 * correctly for arbitrary characters.  Note that a character other than
 * 'A', 'C', 'G', or 'T' is never considered to bond with another character.
 */
public class DNASequence
{
  /**
   * String of data for this sequence. 
   */
  private String data;
  private boolean isValid;
  
  /**
   * Constructs a DNASequence object with the given string of data;
   * this constructor does not check whether the given string
   * is valid (see the method allValid).
   * @param givenData
   *   string of characters for this DNASequence
   */
  public DNASequence(String givenData)
  {
    data = givenData;
  }
  
  /**
   * Returns a String representing the data for this
   * DNASequence.
   * @return
   *   the characters in this DNASequence
   */
  public String toString()
  {
    return data;
  }
  
  /**
   * Determines whether all characters in this sequence
   * are valid ('A', 'G', 'C', or 'T' in uppercase only).
   * @return
   *   true if all characters are valid, false otherwise
   */
  public boolean allValid()
  {
    
    for (int i = 0; i < data.length(); i++)
    {
    	char ch = data.charAt(i);
    	if("AGCT".indexOf(ch)<0)
    	{
    		return false;
    	}
    }
    return true;
   
  }
  
  /**
   * Removes all invalid characters from this DNASequence.  For example, 
   * if this object's data is the string <code>"TaGxy*!  Cz"</code>, 
   * after calling this method, the data is <code>"TGC"</code>.
   */
  public void fix()
  {
	  String a = "";
	  
	  for (int i = 0; i < data.length(); i++)
	    {
		  char ch = data.charAt(i);
		  if("AGCT".indexOf(ch)>=0)
		  {
			  a+=ch;
		  }
	    }
	  data = a;
  }
  
  /**
   * Determines whether the given sequence is a subsequence
   * of this one.  A string t is a subsequence of another
   * string s if all characters of t can be found in s in the
   * same order.  Equivalently, string t is a subsequence of s
   * if t can be obtained by deleting some of the characters of s.
   * Invalid characters in the given string are ignored.
   * <p>
   * For example "TxxAA" is a subsequence of "CTyyGCACA" but 
   * not of "CAAT" nor of "TA".
   * @param other
   *   the given DNASequence
   * @return
   *   true if the given sequence is a subsequence of this one, 
   *   false otherwise
   */
  public boolean isSubsequence(DNASequence other)
  {
	  	other.fix();
	  	fix();
	  	String another = other.toString();
	  	int count = 0;
	  	//maybe look here to see if needs to be data instead of another
	  	if(another.length() <= 0)
	  	{
	  		return true;
	  	}
	  	
	  	for(int i = 0; i < data.length(); i++)
	  	{
	  		if(data.charAt(i)==another.charAt(count))
	  		{
	  			count ++;
	  		}
	  		if(count >= another.length())
	  		{
	  			return true;
	  		}
	  	}
	    return false;
	    
  }
  
  /**
   * Returns true if the two characters are complementary
   * ('A' with 'T' or 'C' with 'G').
   * @param c1
   *   potential character for a base pair
   * @param c2
   *   potential character for a base pair
   * @return
   *   true if the two characters are 'A' and 'T' or 'C' and 'G';
   *   false otherwise
   */
  public boolean willBond(char c1, char c2)
  {
    if (c1 == 'A'&& c2 == 'T')
    {
    	return true;
    
    }
    else if (c1 == 'T'&& c2 == 'A')
    {
    	return true;
    }
    else if (c1 == 'C'&& c2 == 'G')
    {
    	return true;
    }
    else if (c1 == 'G'&& c2 == 'C')
    {
    	return true;
    }
    else
    {
    return false;
    }
  }
    
  /**
   * Replaces this object's data with its complement;
   * that is, 'A' is replaced with 'T' and so on.
   * Invalid characters are not modified.
   * For example, if the data for this sequence is "AGTT", after
   * this method is called the data would be "TCAA".  
   */
  public void complement()
  {
	String comp = "";
	boolean replace=false;
    for (int i = 0; i < data.length(); i++)
    {
    	char a = data.charAt(i);
		if(a=='A'){
			comp+= 'T';
			replace=true;
		}
		if(a=='T'){
			comp+= 'A';
			replace=true;
		}
		if(a=='G'){
			comp+= 'C';
			replace=true;
		}
		if(a=='C'){
			comp+= 'G';
			replace=true;
		}
		if(!replace){
			comp+=a;
		}
		replace=false;
    }
    data = comp;
  }
  
  /**
   * Returns the maximum possible number of bonds that can be formed
   * with this sequence when the given sequence is shifted left or 
   * right by any amount.
   * @param other
   *   the DNASequence to align with this one
   * @return
   *   maximum possible number of bonds 
   */
  public int findMaxPossibleBonds(DNASequence other)
  {
	  String another = other.toString();
	  int max = 0;
	  
	
	  
	  for(int i = 0; i < another.length(); i++)
	  {
	  	max = Math.max(max, countBondsWithShift(other,i));
	  }
	  for(int i = 0; -1*i < another.length(); i -= 1)
	  {
	  	max = Math.max(max, countBondsWithShift(other,i));
	  }
    return max;
  }
  
  /**
   * Returns the number of bonds that are formed with this sequence
   * when the given sequence is shifted right by the given number
   * of spaces (where a negative number represents a left shift).
   * Neither this sequence nor the given sequence is modified.
   * @param other
   *   the DNASequence to align with this one
   * @param shift
   *   number of spaces to the right that the other sequence is shifted
   * @return
   *   number of bonds formed when the given sequence is 
   *   aligned with this one, with the given shift
   */
  public int countBondsWithShift(DNASequence other, int shift)
  {
	  String another = other.toString();
	  int count = 0;
	  for(int i = 0; i < another.length(); i++)
	  	{
	  		if (shift+i < 0 || shift + i >= data.length())
	  		{
	  			;
	  		}
	  		else
	  		{
	  			if(other.willBond(another.charAt(i), data.charAt(shift+i)))
	  			{
	  				count += 1;
	  			}
	  		}
	  	}
    return count;
  }
  
  /**
   * Returns a string showing which characters in this sequence
   * are bonded when the given sequence is shifted right by the given number
   * of spaces (where a negative number represents a left shift).
   * Non-matching characters are shown as dashes.  For example,
   * if this sequence is "ATATGC" and the given sequence is "TCC",
   * shifted right by 2, then this method returns "--A-G-".
   * Neither this sequence nor the given sequence is modified.
   * @param other
   *   the sequence to which this one is being matched
   * @param shift
   *   the number of spaces the other sequence is shifted to the right
   * @return
   *   string indicating where matches occur
   */
  public String findBondsWithShift(DNASequence other, int shift)
  {
    String a = "";
    String another = other.toString();
    for (int i = 0; i < data.length(); i++)
    {
    	if (i >= 0 && i< shift)
    	{
    		a = a+"-";
    	}
    	if(i + shift < 0 || (i+shift >= data.length() || i >= another.length()))
    	{
    		
    	//do nothing
    	}
    	else
    	{
    		if (other.willBond(another.charAt(i), data.charAt(shift+i)))
    		{
    			a = a + data.charAt(i+shift);
    		}
    		else
    		{
    			a = a +"-";
    		}
    	}
    }
    return a;
  }
}