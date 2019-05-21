package custom;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public MenuPanel (String name)
	{
		super();
		setLayout(null);
		setSize(1280, 720);
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream(name));
	    } 
		catch (Exception e) 
		{
	        System.out.println("Can't find " + image);
	    }
	}
	
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    g.drawImage(image,0,0,this);
	}
}
