package main;

import java.awt.*;
import javax.imageio.ImageIO;
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
		
		// Creates Map
		for(int r = 0; r < buttons.length; r++)
		{	
			for(int c = 0; c < buttons[0].length; c++)
	        {
				if (c < 15) // Map
	        	{
					try
					{
		        		board[r][c] = 0;
						buttons[r][c] = new JButton();
						Image grass = ImageIO.read(getClass().getResource("/resources/Grass.png"));
						buttons[r][c].setIcon(new ImageIcon(grass));
						buttons[r][c].setBackground(new Color(65, 74, 45));
						buttons[r][c].addActionListener(new PlaceTower(towers, pickedTower, buttons, r, c));
						pickedTower[r][c] = false;
						towers[r][c] = null;
						center.add(buttons[r][c]);
					}
					catch (Exception e)
					{
						System.out.println("Can't find grass image!");
					}
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
		try
		{
			towers[0][15] = new Skeleton();
			Icon skeleton = new ImageIcon(towers[0][15].getImage());
			buttons[0][15].setIcon(skeleton);
			buttons[0][15].setDisabledIcon(skeleton); // Prevents the icon from going grey when the button is disabled
		}
		catch (Exception e)
		{
			System.out.println("Can't find tower image!");
		}
		
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
			try
			{
				board[pathX[i]][pathY[i]] = pathCounter;
				Image path = ImageIO.read(getClass().getResource("/resources/Path.png"));
				buttons[pathX[i]][pathY[i]].setIcon(new ImageIcon(path));
				buttons[pathX[i]][pathY[i]].setBackground(new Color(127, 114, 48));
				buttons[pathX[i]][pathY[i]].setBorder(null);
				//buttons[pathX[i]][pathY[i]].setEnabled(false);
				buttons[pathX[i]][pathY[i]].setDisabledIcon(new ImageIcon(path));
				pathCounter++;
			}
			catch (Exception e)
			{
				System.out.println("Can't find path image!");
			}
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
