package lab2;
/**
 * Model of an atom.
 */
public class Atom 
{
	
	
	/**
	 * Number of protons in this atom.
	 */
	private int protons;
	
	/**
	 * Number of neutrons in this atom.
	 */
	private int neutrons;
	
	/**
	 * Number of electrons in this atom.
	 */
	private int electrons;
	
	/**
	 * Constructs an atom with the given number of protons, neutrons, and electrons. This also gives it its atomic mass and charge.
	 * @param givenProtons 
	 * 		the number of protons for this atom
	 * @param givenNeutrons 
	 * 		the number of neutrons for this atom
	 * @param givenElectrons 
	 * 		the number of electrons for this atom
	 */
	
	public Atom(int givenProtons, int givenNeutrons, int givenElectrons)
	{
		protons = givenProtons;
		neutrons = givenNeutrons;
		electrons = givenElectrons;
	
	}
	/**
	 * Returns the atomic mass of this atom.
	 * @return
	 * 		atomic mass of this atom
	 */
	public int getAtomicMass()
	{
		return protons + neutrons;
	}
	/**
	 * Returns the atomic charge of this atom.
	 * @return
	 * 		atomic charge of this atom
	 */
	public int getAtomicCharge()
	{
		return protons - electrons;
	}
	/**
	 * Decreases the quantity of protons and neutrons by 2 each.
	 */
	public void decay()
	{
		protons = protons - 2;
		neutrons = neutrons - 2;
	}
}
