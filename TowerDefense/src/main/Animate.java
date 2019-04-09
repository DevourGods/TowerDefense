package main;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Animate
{
	protected JPanel frame;
	protected Graphics2D g2;
	public BufferedImage image;
	public boolean alive;
	public Point position;
	public Point velocity;
	public int health;
	
	public Animate(JPanel _frame, Graphics2D _g2)
	{
		frame = _frame;
		g2 = _g2;
		image = null;
		alive = true;
		position = new Point(0,0);
		velocity = new Point(0,0);
		
	}
	
	public void load(String name, int _columns, int _totalFrames, int _frameWidth, int _frameHeight)
	{
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream(name));
		}
		catch (Exception e)
		{
			System.out.println("Can't animate image");
		}
	}
}
