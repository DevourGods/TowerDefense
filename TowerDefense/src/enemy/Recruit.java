package enemy;

import java.awt.Color;

public class Recruit extends Enemy
{
	int amount;
	public Recruit()
	{
		amount = 1;
		health = 1;
		speed = 1;
		color = Color.RED;
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
