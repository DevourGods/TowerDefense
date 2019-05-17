package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

// Custom button class, created to make an image a button easier and with options like changing the size
public class ImageButton extends JButton
{
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private int w, h;
	
	// Non-default constructor
	public ImageButton(String name, boolean isCenter, double scale, boolean hasBorder)
	{
		try
		{
			image = ImageIO.read(getClass().getResource(name));
			w = (int)(image.getWidth() * scale);
	        h = (int)(image.getHeight() * scale);
	        setSize(w, h);
	        Image newImage = image.getScaledInstance(w, h, Image.SCALE_DEFAULT);
	        setIcon(new ImageIcon(newImage));
			setOpaque(false);
			setContentAreaFilled(false);
			
			if (hasBorder)
			{
				setBorderPainted(true);
				setBorder(BorderFactory.createLineBorder(Color.RED));
			}
			else
			{
				setBorderPainted(false);
				setBorder(null);
			}
			if (isCenter)
			{
				setAlignmentX(Component.CENTER_ALIGNMENT);
			}
			
		}
		catch (Exception e)
		{
			System.out.println("Can't find item in ImageButton class");
		}
	}
	
	// Change the image of the button
	public void setImage(String name, double scale)
	{
		try
		{
			image = ImageIO.read(getClass().getResource(name));
			w = (int)(image.getWidth() * scale);
	        h = (int)(image.getHeight() * scale);
	        setSize(w, h);
	        Image newImage = image.getScaledInstance(w, h, Image.SCALE_DEFAULT);
	        setIcon(new ImageIcon(newImage));
			setOpaque(false);
			setContentAreaFilled(false);
		}
		catch (Exception e)
		{
			System.out.println("Can't find new image in ImageButton class");
		}
	}
	
	public void setBorder(boolean hasBorder)
	{
		if (hasBorder)
		{
			setBorderPainted(true);
			setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		else
		{
			setBorderPainted(false);
			setBorder(null);
		}
	}
}
