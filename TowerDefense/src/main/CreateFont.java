package main;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class CreateFont // Imports a new font
{
	public Font font;
	
	public CreateFont(String name, int size)
	{	
		try
		{
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource(name).openStream());
			GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
			g.registerFont(font);
			font = font.deriveFont((float)size);
		}
		catch (Exception e)
		{
			System.out.println("Font not found!");
		}
	}
	
	public Font getFont()
	{
		return font;
	}
}
