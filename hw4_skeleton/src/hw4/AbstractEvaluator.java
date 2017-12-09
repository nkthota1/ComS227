package hw4;


import java.util.ArrayList;
import java.util.Arrays;

import api.Card;
import api.Hand;
import api.IEvaluator;
import api.SubsetFinder;
import api.Util;


/**
 * The class AbstractEvaluator includes common code for all evaluator types.
 * 
 * TODO: Expand this comment with an explanation of how your class hierarchy
 * is organized.
 * 
 * I have a class called AbstractPairsEvaluator that extends this class. The classes OnePairEvaluator, ThreeOfAKindEvaluator, and FourOfAKindEvaluator all 
 * extend AbstractPairsEvaluator. I did this because I thought it would be a good idea to generalize the satisfiedBy method for the classes
 * that are just looking for the same rank cards. The other classes all directly extend this class. satisfiedBy is the only method that is different for all of
 * them, except the ones that check for the same ranking. 
 */
public abstract class AbstractEvaluator implements IEvaluator
{
	/**
	 * This is the ranking of the hand.
	 */
	private int rank;
	/**
	 * This is the number of total cards.
	 */
	private int num;
	/**
	 * This is the name of the evaluator.
	 */
	private String name;
	/**
	 * This is the number of main cards.
	 */
	private int main;
	/**
	 * This is the constructor for an evaluator
	 * @param ranking This is the ranking of the hand.
	 * @param totalCards This is the number of total cards.
	 * @param eName This is the name of the evaluator.
	 * @param mCards This is the number of main cards. 
	 */
	protected AbstractEvaluator(int ranking, int totalCards, String eName, int mCards)
	{
		
		rank = ranking;
		num = totalCards;
		name = eName;
		main = mCards;
	}
	
	/**
	 * Returns the name for this evaluator.
	 * @return name of the evaluator
	 */
	@Override
	public String getName()
	{
		return name;
	}
	/**
	 * Returns the ranking for this evaluator, where a lower number is considered "better".
	 * @return ranking for this evaluator
	 */
	@Override
	public int getRanking() 
	{
		return rank;
	}
	/**
	 *Returns the number of main cards needed to satisfy this evaluator.
	 * @return number of main cards
	 */
	@Override
	public int numMainCards()
	{
		return main;
	}
	/**
	 * Returns the total number of cards in a hand created by this evaluator. (main cards plus side cards). This value is generally determined by the type of game being played, and may be larger than the value returned by numMainCards.
	 * @return the total number of cards in a hand
	 */
	@Override
	public int totalCards() {
		return num;
	}
	/**
	 * Determines whether the given array of cards satisfies the criteria for this evaluator. The length of the given array must exactly match the value returned by numMainCards(). The caller must ensure that the given array is sorted with highest-ranked card first according to Card.compareTo(). The array is not modified by this operation.
	 * @param mainCards - array of cards
	 * @return true if the given cards satisfy this evaluator
	 */
	@Override
	public abstract boolean satisfiedBy(Card[] mainCards);
	/**
	 * Determines whether there exists a subset of the given cards that satisfies the criteria for this evaluator. The length of the given array must be greater than or equal to the value returned by numMainCards(). The caller must ensure that the given array is sorted with highest-ranked card first according to Card.compareTo(). The array is not modified by this operation.
	 * @param allCards - list of cards to evaluate
	 * @return true if some subset of the given cards satisfy this evaluator
	 */
	@Override
	public boolean canSubsetSatisfy(Card[] allCards) {
		
		ArrayList <Card[]> allSubs = SubsetFinder.findSubsets(allCards, numMainCards());
		for(Card[] subset: allSubs)
		{
			if(satisfiedBy(subset)) return true;
		}
		return false;
	}
	/**
	 * Returns a hand whose main cards consist of the indicated subset of the given cards. If the indicated subset does not satisfy the criteria for this evaluator, this method returns null. The subset is described as an ordered array of indices to be selected from the given Card array. The number of main cards in the hand will be the value of numMainCards() and the total number of cards in the hand will be the value of totalCards(). The side cards will consist of the best cards remaining after selecting the indicated main cards. If the length of the given array of cards is less than totalCards(), or if the length of the given array of indices is not equal to numMainCards() this method returns null. The caller must ensure that the given array is sorted with highest-ranked card first according to Card.compareTo(). The array is not modified by this operation.
	 * @param allCards - list of cards from which to select the main cards for the hand
	 * @param subset - list of indices of cards to be selected, in ascending order
	 * @return hand whose main cards consist of the indicated subset, or null if the indicated subset does not satisfy this evaluator
	 */
	@Override
	public Hand createHand(Card[] allCards, int[] subset) {
		if(subset.length != numMainCards() || allCards.length < totalCards()) return null;
		
		Card[] mainCards = Util.getCardSubset(allCards, subset);
		
		Card[] otherCards = Util.getCardNonSubset(allCards, subset);
		
		Card[] sideCards = new Card[totalCards() - subset.length];
		
		if(!satisfiedBy(mainCards))
		{
			return null;
		}
		for(int i=0; i<sideCards.length; i++)
		{
			sideCards[i] = otherCards[i];
		}
		Hand myHand = new Hand(mainCards, sideCards, getName(), getRanking());
		return myHand;
	}
	/**
	 * Returns the best possible hand satisfying this evaluator's criteria that can be formed from the given list of cards. "Best" is defined to be first according to the compareTo() method of Hand. Returns null if there is no such hand. The number of main cards in the hand will be the value of numMainCards(). If the length of the given array of cards is less than totalCards(), this method returns null. The given array must be sorted with highest-ranked card first according to Card.compareTo(). The array is not modified by this operation.
	 * @param allCards - list of cards from which to create the hand
	 * @return best possible hand satisfying this evaluator that can be formed from the given cards, or null if there is no such hand
	 */
	@Override
	public Hand createBestHand(Card[] allCards) {
		if(!canSubsetSatisfy(allCards))
		{
			return null;
		}
		ArrayList <Hand> possibleHands = new ArrayList();
		ArrayList<int[]> allSubsets = SubsetFinder.findSubsets(allCards.length, numMainCards());
		for(int[] subset: allSubsets)
		{
			Card[] cardSubset = new Card[subset.length];
			for(int i=0; i<subset.length; i++)
			{
				cardSubset[i] = allCards[subset[i]];
			}
			if(satisfiedBy(cardSubset)) possibleHands.add(createHand(allCards, subset));
		}
		Hand[] hands = new Hand[possibleHands.size()];
		possibleHands.toArray(hands);
		Arrays.sort(hands);
		return hands[0];
	}
}
