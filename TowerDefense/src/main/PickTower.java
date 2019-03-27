package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import towers.Tower;

public class PickTower extends Tower implements ActionListener
{
	public int r, c;
	public JButton [][] buttons;
	public boolean [][] pickedTower;
	
	public PickTower(JButton[][] buttons, boolean[][] pickedTower, int r, int c)
	{
		this.buttons = buttons;
		this.pickedTower = pickedTower;
		this.r = r;
		this.c = c;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		for (int row = 0; row < buttons.length-1; row++)
		{
			for (int col = 0; col < buttons[0].length; col++)
			{
				if (pickedTower[row][col]) // Resets a button that was selected previously
				{
					buttons[row][col].setBorder(new LineBorder(Color.GRAY));
					pickedTower[row][col] = false;
					buttons[row][col].setEnabled(true);
				}
			}
		}
		
		if (r < pickedTower.length && c < pickedTower[0].length) // Clicked one of the towers
		{	
			if (r == 14)
			{
				buttons[14][15].setText("");
				buttons[14][15].setBackground(Color.WHITE);
				buttons[14][15].setEnabled(false);
				buttons[14][15].setText("");
				buttons[14][15].setBackground(Color.WHITE);
				buttons[14][15].setEnabled(false);
				
				buttons[14][16].setText("");
				buttons[14][16].setBackground(Color.WHITE);
				buttons[14][16].setEnabled(false);
				buttons[14][16].setText("");
				buttons[14][16].setBackground(Color.WHITE);
				buttons[14][16].setEnabled(false);
			}
			else
			{
				buttons[r][c].setBorder(new LineBorder(Color.BLACK, 3));
				pickedTower[r][c] = true;
				buttons[r][c].setEnabled(false);
			}
		}
	}
}
