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
		for (int row = 0; row < pickedTower.length-1; row++)
		{
			for (int col = 0; col < pickedTower[0].length; col++)
			{
				if (pickedTower[row][col])
				{
					Icon icon = new ImageIcon(towers[row][col].getImage());
					buttons[r][c].setIcon(icon);
					buttons[r][c].setDisabledIcon(icon);
				}
			}
		}
	}
}
