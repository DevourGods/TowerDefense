package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import towers.Tower;

public class PickTower extends Tower implements ActionListener
{
	public int r, c;
	public JButton [][] towerButtons;
	public boolean [][] pickedTower;
	
	public PickTower(JButton[][] towerButtons, boolean[][] pickedTower, int r, int c)
	{
		this.towerButtons = towerButtons;
		this.pickedTower = pickedTower;
		this.r = r;
		this.c = c;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		for (int row = 0; row < pickedTower.length; row++)
		{
			for (int col = 0; col < pickedTower[0].length; col++)
			{
				if (pickedTower[row][col])
				{
					towerButtons[row][col].setBorder(new LineBorder(Color.GRAY));
					pickedTower[row][col] = false;
				}
			}
		}
		
		towerButtons[r][c].setBorder(new LineBorder(Color.BLACK, 2));
		pickedTower[r][c] = true;
	}
}
