package main;

import java.awt.event.*;
import javax.swing.*;
import towers.Tower;

public class PlaceTower extends Tower implements ActionListener
{
	public int r, c;
	public Tower [][] towers;
	public JButton [][] buttons;
	public boolean [][] pickedTower;
	
	public PlaceTower(Tower [][] towers, boolean[][] pickedTower, JButton[][] buttons, int r, int c)
	{
		this.towers = towers;
		this.pickedTower = pickedTower;
		this.buttons = buttons;
		this.r = r;
		this.c = c;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		for (int row = 0; row < 15; row++)
		{
			for (int col = 0; col < 2; col++)
			{
				if (pickedTower[row][col])
				{
					buttons[r][c].setBackground(towers[row][col].getColor());
				}
			}
		}
	}
}
