package main;

import java.awt.event.*;
import javax.swing.*;
import towers.Tower;

public class PlaceTower extends Tower implements ActionListener
{
	public int r, c;
	public Tower [][] towers;
	public JButton [][] buttons;
	public int [][] board;
	public boolean [][] pickedTower;
	
	public PlaceTower(Tower [][] towers, boolean[][] pickedTower, JButton[][] buttons, int[][] board, int r, int c)
	{
		this.towers = towers;
		this.pickedTower = pickedTower;
		this.buttons = buttons;
		this.board = board;
		this.r = r;
		this.c = c;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		for (int row = 0; row < pickedTower.length-1; row++)
		{
			for (int col = 0; col < pickedTower[0].length; col++)
			{
				if (pickedTower[row][col] && board[r][c] == 0)
				{
					Icon icon = new ImageIcon(towers[row][col].getImagePlaced());
					buttons[r][c].setIcon(icon);
					buttons[r][c].setDisabledIcon(icon);
				}
			}
		}
	}
}
