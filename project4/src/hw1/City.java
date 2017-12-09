package hw1;
/**
 * 
 * @author nkthota
 *
 */
public class City 
{
	private String name;
	private int lcost;
	private int ccost;
	
	/*
	 * The cost to mail a postcard from a city is this fraction times the city's lodging cost
(rounded to the nearest integer).
	 */
	
	public static final double RELATIVE_COST_OF_POSTCARD = 0.05;
	
	/*
	 * Constructs a new City with the given name and lodging cost per night
	 */
	/**
	 * 
	 * @param givenName
	 * the name of the city
	 * @param givenLodgingCost
	 * the cost of staying a night in the city
	 */
	public City(String givenName, int givenLodgingCost)
	{
		name = givenName;
		lcost = givenLodgingCost;
		ccost = (int) Math.round(RELATIVE_COST_OF_POSTCARD*lcost);
	}
	
	/*
	 * Returns this city's name
	 * !accessor!
	 */
	/**
	 * 
	 * @return
	 * the name of the city
	 */
	public String getName()
	{
		return name;
	}
	
	/*
	 * Returns this city's lodging cost per night.
	 * !accessor!
	 */
	/**
	 * 
	 * @return
	 * the cost of staying a night in the city
	 */
	public int lodgingCost()
	{
		return lcost;
	}
	
	/*
	 * Returns the cost to send a postcard from this city. The value is a percentage of the
	 *lodging cost specified by the constant RELATIVE_COST_OF_POSTCARD,
	 *rounded to the nearest integer.
	 *!accessor!
	 */
	/**
	 * 
	 * @return
	 * the cost of sending a postcard from the city
	 */
	public int costToSendPostcard()
	{
		
		return ccost;
	}
	
	/*
	 * Returns the number of nights of lodging in this city that a traveler could afford
	 *with the given amount of money.
	 *!accessor!
	 */
	/**
	 * 
	 * @param funds
	 * how much money the traveler currently has
	 * @return
	 * how many nights the traveler can stay in that city
	 */
	public int maxLengthOfStay(int funds)
	{
		int nights = funds/lcost;
		
		return nights;
	}
	
	/*
	 * Returns the number of postcards that a traveler could afford to send from this city
	 *with the given amount of money
	 *!accessor!
	 */
	/**
	 * 
	 * @param funds
	 * the amount of the money the traveler currently has
	 * @return
	 * the maximum number of postcards the traveler can send
	 */
	public int maxNumberOfPostcards(int funds)
	{
		int cards = funds/ccost;
		return cards;
	}
}
