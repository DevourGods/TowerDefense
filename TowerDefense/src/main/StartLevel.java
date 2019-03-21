package main;

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
					System.out.println("TEST");
				}
			}
		); //timer
		timer.start();
	}
}
