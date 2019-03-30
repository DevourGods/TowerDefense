package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	
	public Icon test;
	public Icon path;
	
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
						buttons[r+1][c].setIcon(test);
						buttons[r][c].setIcon(path);
						r++;
					}
					else if (board[r-1][c] == board[r][c] + 1)
					{
						buttons[r-1][c].setIcon(test);
						buttons[r][c].setIcon(path);
						r--;
					}
					else if (board[r][c+1] == board[r][c] + 1)
					{
						buttons[r][c+1].setIcon(test);
						buttons[r][c].setIcon(path);
						c++;
					}
					else if (board[r][c-1] == board[r][c] + 1)
					{
						buttons[r][c-1].setIcon(test);
						buttons[r][c].setIcon(path);
						c--;
					}
					
				}
			}
		); //timer
		
		timer.start();
		r = startX;
		c = startY;
		test = new ImageIcon(enemy.get(0).getImage());
		try
		{
			Image image = ImageIO.read(getClass().getResource("/resources/Path.png"));
			path = new ImageIcon(image);
		}
		catch(Exception ex)
		{
		}
		buttons[r][c].setIcon(test);

	}
}
