package hw4;

import api.Card;
import api.Suit;

/**
 * Evaluator for a hand that contains at least one card from
 * each suit.  The number of main cards is four.
 * 
 * The name of this evaluator is "All Suits".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class AllSuitsEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this type of hand
   * @param totalCards
   *   total number of cards in a hand
   */
  public AllSuitsEvaluator(int ranking, int totalCards)
  {
    super(ranking, totalCards, "All Suits", 4);
  }

  /**
	 * Determines whether the given array of cards satisfies the criteria for this evaluator. The length of the given array must exactly match the value returned by numMainCards(). The caller must ensure that the given array is sorted with highest-ranked card first according to Card.compareTo(). The array is not modified by this operation.
	 * @param mainCards - array of cards
	 * @return true if the given cards satisfy this evaluator
	 */
@Override
public boolean satisfiedBy(Card[] mainCards) {
	if(mainCards.length == numMainCards())
	{
		boolean hearts = false;
		boolean spades = false;
		boolean clubs = false;
		boolean diamonds = false;
		for(int i = 0; i < mainCards.length; i++)
		{
			if(mainCards[i].getSuit() == Suit.CLUBS)
			{
				clubs = true;
			}
			else if(mainCards[i].getSuit() == Suit.DIAMONDS)
			{
				diamonds = true;
			}
			else if(mainCards[i].getSuit() == Suit.HEARTS)
			{
				hearts = true;
			}
			else if(mainCards[i].getSuit() == Suit.SPADES)
			{
				spades = true;
			}
		}
		if(clubs&&diamonds&&hearts&&spades)
		{
			return true;
		}
	}
	
	return false;
}
  
}
