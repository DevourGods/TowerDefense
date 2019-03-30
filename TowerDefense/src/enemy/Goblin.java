package enemy;

import javax.imageio.ImageIO;

public class Goblin extends Enemy
{
	int amount;

	public Goblin(int amount)
	{
		try
		{
			this.amount = amount;
			health = 1;
			speed = 1;
			image = ImageIO.read(getClass().getResource("/resources/Goblin-Path.png"));
		}
		catch(Exception e)
		{
			System.out.println("Can't find goblin image");
		}
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public String toString()
	{
		return "Goblin(" + amount + ")"; 
	}
}
