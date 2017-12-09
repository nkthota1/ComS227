package lab5;
public class Basketball
{
	private double diameter;
	private boolean inflated;
  public Basketball(double givenDiameter)
  {
	  diameter = givenDiameter;
	  inflated = false;
  }

  public boolean isDribbleable()
  {
    return inflated;
  }

  public double getDiameter()
  {
    return diameter;
  }

  public double getCircumference()
  {
    return (diameter/2)*(diameter/2)*Math.PI;
  }

  public void inflate()
  {
	  inflated=true;
  }
}