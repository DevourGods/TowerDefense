package main;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private BufferedImage grass;
	private BufferedImage path;
	private BufferedImage rock;
	
	public static Scanner scan;
	
	public Thread gameLoop;
	
	public GamePanel(Dimension size)
	{
		this.setSize(size);
		this.loadResources();
		this.initializeGameData();
	}
	
	private void initializeGameData()
	{
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		buildMap(g2);

	}
	
	private void buildMap(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		String row = "";
		int xCoordinate = 0;
		int yCoordinate = 0;
		
		scan = new Scanner(getClass().getResourceAsStream("/resources/map_data.txt"));
		
		for(int y = 0; y < 15; y++) // Size of map
		{
			xCoordinate = 0;
			row = scan.nextLine();
			
			for(int x = 0; x < 20; x++)
			{
				if (row.charAt(x) == '0')
				{
					g2.drawImage(grass, xCoordinate, yCoordinate, null);
				}
				else if (row.charAt(x) == '1')
				{
					g2.drawImage(path, xCoordinate, yCoordinate, null);
				}
				else if (row.charAt(x) == '2')
				{
					g2.drawImage(rock, xCoordinate, yCoordinate, null);
				}
				xCoordinate += 48;
			}
			yCoordinate += 48;
		}
	}
	
	private void loadResources()
	{
		try
		{
			grass = ImageIO.read(getClass().getResourceAsStream("/resources/Grass.png"));
			path = ImageIO.read(getClass().getResourceAsStream("/resources/Path.png"));
			rock = ImageIO.read(getClass().getResourceAsStream("/resources/Rock.png"));
		}
		catch(Exception e)
		{
			System.out.println("Error loading resources");
		}
	}
	
	@Override
	public synchronized void run()
	{
		//
	}
}
