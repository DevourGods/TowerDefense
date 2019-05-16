package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Start implements ActionListener
{
	public JFrame frame;
	
	public Start(JFrame frame)
	{
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e)
	{	
		frame.dispose();
		
		//Main m = new Main();
		//frame = m.getFrame();
	}
}
