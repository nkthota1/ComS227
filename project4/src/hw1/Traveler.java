package hw1;
/**
 * 
 * @author nkthota
 *
 */
public class Traveler {
	
	private int funds;
	private City city;
	private String journal;
	private int totalCards;
	private int train;
	/*
	 * Proportionality constant when calling home for more money: the amount of
money received is this constant times the number of postcards the traveler has
sent home (since the last time she called home for money).
	 */
	public static final int SYMPATHY_FACTOR = 30;
	
	/*
	 * Constructs a traveler starting out with the given amount of money in the given
	 *city. The journal is initially a string consisting of "cityname(start)", where
	 *cityname is the name of the starting city.
	 *!accessor!
	 *
	 */
	/**
	 * 
	 * @param initialFunds
	 * 	How much money the traveler starts with
	 * @param initialCity
	 * 	The city that the traveler starts in
	 */
	public Traveler(int initialFunds, City initialCity)
	{
		funds = initialFunds;
		city = initialCity;
		journal = city.getName()+"(start)";
		train = 0;
		totalCards = 0;
	}
	/*
	 * Returns the name of the traveler's current city
	 * !accessor!
	 */
	/**
	 * 
	 * @return
	 * 	the name of the city that the traveler is currently in
	 */
	public String getCurrentCity()
	{
		
		return city.getName();
	}
	
	/*
	 * Returns the amount of money the traveler currently has.
	 * !accessor!
	 */
	/**
	 * 
	 * @return
	 * 	the amount of money the travel currently has
	 */
	public int getCurrentFunds()
	{
		
		return funds;
	}
	
	/*
	 * Returns the traveler's journal. The journal is a string of comma-separated values
	 *of the form cityname(number_of_nights) containing the cities visited by the
	 *traveler, in the order visited, along with the number of nights spent in each. The
	 *first value always has the form cityname(start) for the starting city. For example,
	 *if a traveler starts in Paris, spends 5 nights in Rome, and then spends 2 nights in
	 *Prague, the journal string would be: Paris(start),Rome(5),Prague(2)
	 *!accessor!
	 */
	/**
	 * 
	 * @return
	 * 	the traveler's journal
	 */
	public String getJournal()
	{
		return journal;
	}
	
	/*
	 * Returns true if the traveler does not have enough money to send a postcard from
	 *the current city.
	 *!accessor!
	 */
	/**
	 * 
	 * @return
	 * 	if the traveler has enough money to send a postcard home
	 */
	public boolean isSOL()
	{
		return funds < city.costToSendPostcard();
	}
	
	/*
	 * Returns the number of nights the traveler has spent in a train station when visting
	 *a city without enough money for lodging
	 *!accessor!
	 */
	/**
	 * 
	 * @return
	 * 	how many nights the traveler had to spend in the train station
	 */
	public int getTotalNightsInTrainStation()
	{
		return train;
	}
	
	/*
	 * Simulates a visit by this traveler to the given city for the given number of nights.
	 *The traveler's funds are reduced based on the number of nights of lodging
	 *purchased. When the funds are not sufficient for numNights nights of lodging in
	 *the city, the number of nights spent in the train station is updated accordingly. The
	 *journal is updated by appending a comma, the city name, and the number of
	 *nights in parentheses.
	 *!mutator!
	 */
	/**
	 * 
	 * @param c
	 * the city that the traveler is visiting
	 * @param numNights
	 * the number of nights they are visiting the city
	 */
	public void visit(City c, int numNights)
	{
		city = c;
		journal = journal + ", " + city.getName() + "(" + numNights + ")";
		int nightsLodged = Math.min(numNights, city.maxLengthOfStay(funds));
		funds -= city.lodgingCost()*nightsLodged;
		train += numNights - nightsLodged;
	}
	
	/*
	 * Sends the given number of postcards, if possible, reducing the traveler's funds
	 *appropriately and increasing the count of postcards sent. If there is not enough
	 *money, sends as many postcards as possible without allowing the funds to go
	 *below zero.
	 *!mutator!
	 */
	/**
	 * 
	 * @param howMany
	 * the number of postcards the traveler wants to send
	 */
	public void sendPostcardsHome(int howMany)
	{
		int cards = Math.min(howMany, city.maxNumberOfPostcards(funds));
		totalCards += cards;
		funds -= cards*city.costToSendPostcard();
		
	}
	
	/*
	 * Increases the traveler's funds by an amount equal to SYMPATHY_FACTOR
	 *times the number of postcards sent since the last call to this method, and resets the
	 *count of the number of postcards sent back to zero.
	 */
	
	public void callHomeForMoney()
	{
		funds += SYMPATHY_FACTOR * totalCards;
		totalCards = 0;
	}
}	
