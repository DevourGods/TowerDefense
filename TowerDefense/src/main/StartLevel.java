package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.JButton;

import enemy.Enemy;

public class StartLevel implements ActionListener
{
	public int level;
	public JButton [][] buttons;
	public int [][] board;
	public ArrayList<Enemy> enemy;
	public int r;
	public int c;
	
	public Timer timer;
	private final int ONE_SECOND = 1000;
	private final static int startX = 5;
	private final static int startY = 0;
	
	public StartLevel(JButton[][] buttons, int[][] board)
	{
		this.buttons = buttons;
		this.board = board;
		level = 1;
	}
	public void actionPerformed(ActionEvent e)
	{
		LevelDatabase l = new LevelDatabase();
		enemy = l.returnLevel(level);
		
		timer = new Timer(ONE_SECOND, new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (board[r+1][c] == board[r][c] + 1)
					{
						buttons[r][c].setBackground(Color.YELLOW);
						buttons[r+1][c].setBackground(enemy.get(0).getColor());
						r++;
					}
					else if (board[r-1][c] == board[r][c] + 1)
					{
						buttons[r][c].setBackground(Color.YELLOW);
						buttons[r-1][c].setBackground(enemy.get(0).getColor());
						r--;
					}
					else if (board[r][c+1] == board[r][c] + 1)
					{
						buttons[r][c].setBackground(Color.YELLOW);
						buttons[r][c+1].setBackground(enemy.get(0).getColor());
						c++;
					}
					else if (board[r][c-1] == board[r][c] + 1)
					{
						buttons[r][c].setBackground(Color.YELLOW);
						buttons[r][c-1].setBackground(enemy.get(0).getColor());
						c--;
					}
					
				}
			}
		); //timer
		timer.start();
		buttons[startX][startY].setBackground(enemy.get(0).getColor());
		r = startX;
		c = startY;
		
	}
}
