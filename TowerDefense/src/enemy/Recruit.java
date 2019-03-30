package enemy;

import javax.imageio.ImageIO;

public class Recruit extends Enemy
{
	int amount;

	public Recruit(int amount)
	{
		try
		{
			this.amount = amount;
			health = 1;
			speed = 1;
			image = ImageIO.read(getClass().getResource("/resources/SlimePath.png"));
		}
		catch(Exception e)
		{
			System.out.println("Can't find recruit image");
		}
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
