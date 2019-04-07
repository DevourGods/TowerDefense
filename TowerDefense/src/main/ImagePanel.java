package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private int w,h;
	
	public ImagePanel (String name){

		try
		{
			image = ImageIO.read(getClass().getResource(name));
	        w = image.getWidth();
	        h = image.getHeight();

	    } 
		catch (Exception e) 
		{
	        System.out.println("Can't find " + image);
	    }

	}

	public Dimension getPreferredSize()
	{
	    return new Dimension(w,h);
	}
	
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    g.drawImage(image,0,0,this);
	}
}
