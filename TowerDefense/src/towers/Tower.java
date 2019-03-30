package towers;

import java.awt.Image;
import javax.swing.*;

public class Tower
{
	public int range, speed, r, c;
	public Image image;
	public JButton [][] towerButtons;
	
	public Tower()
	{
		//System.out.println("Tower Default Constructor");
	}
	
	public Tower(JButton[][] towerButtons, int r, int c)
	{
		this.towerButtons = towerButtons;
		this.r = r;
		this.c = c;
	}
	
	public Image getImage()
	{
		return image;
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
