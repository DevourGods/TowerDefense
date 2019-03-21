package main;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import towers.Skeleton;
import towers.Tower;

public class GUI extends JPanel
{
	public static final long serialVersionUID = 1L;
		
	public JButton [][] buttons;
	public int [][] board;
	
	public JButton[][] towerButtons;
	public boolean[][] pickedTower;
	public Tower[][] towers;
	
	public GUI()
	{	
		setLayout(new BorderLayout());
		createNorth();
		createEast();
		createCenter();
		createPath();
	}
	
	public void createNorth()
	{
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		north.setBackground(Color.DARK_GRAY);
		add(north, BorderLayout.NORTH);
		
		JLabel title = new JLabel("Undead Defense");
		title.setFont(new Font("Courier", Font.BOLD, 26));
		title.setForeground(Color.WHITE);
		north.add(title);
	}
	
	public void createCenter()
	{
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(15,15));
		center.setPreferredSize(new Dimension(920, 920));
		add(center, BorderLayout.CENTER);
		buttons = new JButton[15][15];
		board = new int[15][15];
		
		for(int r = 0; r < board.length; r++)
		{	
	         for(int c = 0; c < board[0].length; c++)
	         {
	            board[r][c] = 0;
	            buttons[r][c] = new JButton();
	            buttons[r][c].setBackground(Color.GREEN);
	            buttons[r][c].addActionListener(new PlaceTower(towers, pickedTower, buttons, r, c));
	            center.add(buttons[r][c]);
	         }
		}
	}
	
	public void createEast()
	{
		JPanel east = new JPanel();
		east.setLayout(new GridLayout(15, 2));
		east.setPreferredSize(new Dimension(200,920));
		add(east, BorderLayout.EAST);
		
		towerButtons = new JButton[15][2];
		pickedTower = new boolean[15][2];
		towers = new Tower[15][2];
		
		for(int towerR = 0; towerR < towers.length; towerR++)
		{
			for(int towerC = 0; towerC < towers[0].length; towerC++)
			{
				towerButtons[towerR][towerC] = new JButton();
				towerButtons[towerR][towerC].setBackground(Color.WHITE);
				east.add(towerButtons[towerR][towerC]);
				towerButtons[towerR][towerC].addActionListener(new PickTower(towerButtons, pickedTower, towerR, towerC));
				pickedTower[towerR][towerC] = false;
			}
		}
		
		towers[0][0] = new Skeleton();
		towerButtons[0][0].setBackground(towers[0][0].getColor());
	}
	
	public void createPath()
	{
		int [] pathX = {5,5,5,6,7,8,9,10,11,11,12,12,13,13,13,13,13,13,12,11,10,10,10,9,9,8,7,6,5,4,3,3,2,2,2,1,1,1,1,2,3,4,5,6,7,8,9,9,10,10};
		int [] pathY = {0,1,2,2,2,2,2,2,2,3,3,4,4,5,6,7,8,9,9,9,9,8,7,7,6,6,6,6,6,6,6,7,7,8,9,9,10,11,12,12,12,12,12,12,12,12,12,13,13,14};
		
		int pathCounter = 1;
		for (int i = 0; i < pathX.length; i++)
		{
			board[pathX[i]][pathY[i]] = pathCounter;
			buttons[pathX[i]][pathY[i]].setBackground(Color.YELLOW);
			buttons[pathX[i]][pathY[i]].setEnabled(false);
			pathCounter++;
		}
	}

	public static void main(String[] args)
    {
		JFrame frame = new JFrame("Undead Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(1180, 920);
        frame.setLocation(350, 100);
        frame.setContentPane(new GUI());
        frame.setVisible(true);
    }
}
