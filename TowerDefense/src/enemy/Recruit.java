package enemy;

public class Recruit extends Enemy
{
	int amount;
	public Recruit()
	{
		amount = 1;
		health = 1;
		speed = 1;
	}
	public Recruit(int amount)
	{
		this.amount = amount;
		health = 1;
		speed = 1;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public String toString()
	{
		return "Recruit(" + amount + ")"; 
	}
}
