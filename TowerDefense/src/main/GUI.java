package main;

import java.awt.*;
import javax.swing.*;

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
		center.setLayout(new GridLayout(15,17));
		add(center, BorderLayout.CENTER);
		
		board = new int[15][17];
		buttons = new JButton[15][17];
		pickedTower = new boolean[15][17];
		towers = new Tower[15][17];
		
		for(int r = 0; r < buttons.length; r++)
		{	
			for(int c = 0; c < buttons[0].length; c++)
	        {
				if (c < 15)
	        	{
	        		board[r][c] = 0;
					buttons[r][c] = new JButton();
					buttons[r][c].setBackground(Color.GREEN);
					buttons[r][c].addActionListener(new PlaceTower(towers, pickedTower, buttons, r, c));
					pickedTower[r][c] = false;
					towers[r][c] = null;
					center.add(buttons[r][c]);
	        	}
	        	else // Tower Panel
	        	{
	        		buttons[r][c] = new JButton();
	        		buttons[r][c].setBackground(Color.WHITE);
	        		buttons[r][c].addActionListener(new PickTower(buttons, pickedTower, r, c));
	        		pickedTower[r][c] = false;
	        		center.add(buttons[r][c]);
	        	}
	        }
		}
		
		// Towers
		towers[0][15] = new Skeleton();
		buttons[0][15].setBackground(towers[0][15].getColor());
		
		// Creates Start Button
		buttons[14][15].setBorder(null);
		buttons[14][16].setBorder(null);
		buttons[14][15].setBackground(Color.CYAN);
		buttons[14][16].setBackground(Color.CYAN);
		buttons[14][15].setText("START");
		buttons[14][16].setText("GAME");
		buttons[14][15].addActionListener(new StartLevel(buttons, board));
		buttons[14][16].addActionListener(new StartLevel(buttons, board));
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
