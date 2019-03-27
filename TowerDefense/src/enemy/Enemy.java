package enemy;

import java.awt.Color;

import javax.swing.JButton;

public class Enemy 
{
	public int health, speed;
	public JButton[][] buttons;
	public int[][] board;
	public Color color;
	
	public Enemy()
	{
		//System.out.println("Enemy Default Constructor");
	}
	
	public Enemy(int health, int speed)
	{
		this.health = health;
		this.speed = speed;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public Color getColor()
	{
		return color;
	}
}
