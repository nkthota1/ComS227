package lab6;

public class vowelIndex {
	
		
		public static void main (String args[])
		{
			System.out.println(vowel("dna del Humboldt von der Schooch"));
		}
		
		public static int vowel(String s)
		  {
			
			
		    for (int i = 0; i < s.length(); i += 1)
		    {
		      if (s.charAt(i) == 'a'||s.charAt(i) == 'e'||s.charAt(i) == 'i'||s.charAt(i) == 'o'||s.charAt(i) == 'u'||s.charAt(i) == 'A'||s.charAt(i) == 'E'||s.charAt(i) == 'I'||s.charAt(i) == 'O'||s.charAt(i) == 'U')
		      {
		    	  return i;
		      }
		      
		    }
		    return -1;
		 
		  }
	

}
