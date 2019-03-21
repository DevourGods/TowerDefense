package towers;

import java.awt.Color;
import javax.swing.*;

public class Tower
{
	public int range, speed, r, c;
	public Color color;
	public JButton [][] towerButtons;
	
	public Tower()
	{
		
	}
	
	public Tower(JButton[][] towerButtons, int r, int c)
	{
		this.towerButtons = towerButtons;
		this.r = r;
		this.c = c;
	}
	public Color getColor()
	{
		return color;
	}
	
	public int getRange()
	{
		return range;
	}
	public int getSpeed()
	{
		return speed;
	}
	
	
}
