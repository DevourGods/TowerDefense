package main;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton
{
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private int w, h;
	
	public ImageButton(String name, boolean isCenter, double scale)
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
			setBorderPainted(false);
			setBorder(null);
			
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
}
