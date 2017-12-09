package lab5;

import static org.junit.Assert.assertEquals;
import balloon4.Balloon;
import org.junit.Test;

public class BalloonTests {

	
	   
	    @Test
	    public void testInitialRadius()
	    {
	      Balloon b = new Balloon(5);
	      assertEquals(0, b.getRadius());
	    }

	    @Test
	    public void testIntitalPop()
	    {
	    	Balloon b = new Balloon(5);
	    	assertEquals(false, b.isPopped());
	    }
	    @Test
	    public void testIntitalNeg()
	    {
	    	Balloon b = new Balloon(-5);
	    	b.blow(7);
	    	assertEquals(0, b.getRadius());
	    	assertEquals(true, b.isPopped());
	    }

	    @Test
	    public void testIntitalNeg2()
	    {
	    	Balloon b = new Balloon(-5);
	    	b.blow(0);
	    	assertEquals(0, b.getRadius());
	    	assertEquals(false, b.isPopped());
	    	b.blow(1);
	    	assertEquals(true, b.isPopped());
	    }
	    @Test
	    public void testBlowPop()
	    {
	    	Balloon b = new Balloon(5);
	    	b.blow(6);
	    	assertEquals(0, b.getRadius());
	    	assertEquals(true, b.isPopped());
	    }
	    @Test
	    public void testBlowMax()
	    {
	    	Balloon b = new Balloon(5);
	    	b.blow(5);
	    	assertEquals(5, b.getRadius());
	    	assertEquals(false, b.isPopped());
	    }

	    @Test
	    public void testBlow()
	    {
	    	Balloon b = new Balloon(5);
	    	b.blow(4);
	    	assertEquals(4, b.getRadius());
	    	assertEquals(false, b.isPopped());
	    }

	    @Test
	    public void testBlowNeg()
	    {
	    	Balloon b = new Balloon(5);
	    	b.blow(5);
	    	b.blow(-5);
	    	assertEquals(5, b.getRadius());
	    	assertEquals(false, b.isPopped());
	    }
	    
	    @Test
	    public void testBlowTwice()
	    {
	    	Balloon b = new Balloon(8);
	    	b.blow(5);
	    	b.blow(2);
	    	assertEquals(7, b.getRadius());
	    	assertEquals(false, b.isPopped());
	    }
	    @Test
	    public void testBlowAfterPop()
	    {
	    	Balloon b = new Balloon(5);
	    	b.blow(5);
	    	b.pop();
	    	b.blow(10);
	    	assertEquals(0, b.getRadius());
	    	assertEquals(true, b.isPopped());
	    }
	    
	    @Test
	    public void testDeflate()
	    {
	    	Balloon b = new Balloon(5);
	    	b.blow(5);
	    	b.deflate();
	    	assertEquals(0, b.getRadius());
	    	assertEquals(false, b.isPopped());
	    	b.blow(3);
	    	assertEquals(false, b.isPopped());
	    	assertEquals(3, b.getRadius());
	    }
	    @Test
	    public void testPop()
	    {
	    	Balloon b = new Balloon(5);
	    	b.pop();
	    	assertEquals(true, b.isPopped());
	    	assertEquals(0, b.getRadius());
	    	b.blow(5);
	    	assertEquals(0, b.getRadius());
	    	assertEquals(true, b.isPopped());
	    }
	  }
/**
 * Every test works for the correct version
 * Balloon 1 does not work because it does not pop the balloon when it is blown over the max radius
 * Balloon 2 does not work because it can still be inflated after it pops.
 * Balloon 3 does not work because it cannot blow twice in a row
 * Balloon 4 pops when it is deflated.
 */

