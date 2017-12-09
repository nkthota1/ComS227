package lab3;

public class RabbitTest
{
  public static void main(String[] args)
  {
    RabbitModel model = new RabbitModel();
    
    // Check that the initial population is 2
    System.out.println(model.getPopulation());
    System.out.println("Expected 2");
    
    // A year passes
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 3");
    
    // Restart
    model.reset();
    System.out.println(model.getPopulation());
    System.out.println("Expected 2");
  }
}