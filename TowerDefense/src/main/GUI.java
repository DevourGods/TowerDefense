package main;

import java.awt.*;
import javax.swing.*;

public class GUI extends JPanel
{
	public static final long serialVersionUID = 1L;
	private static Dimension frameSize = new Dimension(1280, 720);
	
	public final static int w = 1280;
	public final static int h = 720;
	
	public GUI()
	{	
		setLayout(new BorderLayout());
		
		startMenu();
	}
	
	public void startMenu()
	{	
		ImagePanel menu = new ImagePanel("/resources/Menu-Small.png");
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		OutlineLabel title = new OutlineLabel("Undead Defense", true, Color.BLACK);
		ImageButton startBTN = new ImageButton("/resources/StartButton.png", true, 0.7);
		ImageButton optionsBTN = new ImageButton("/resources/OptionsButton.png", true, 0.7);
		ImageButton quitBTN = new ImageButton("/resources/QuitButton.png", true, 0.7);
		
		CreateFont zombieFont = new CreateFont("/resources/Zombie_Holocaust.ttf", 120);

		title.setFont(zombieFont.getFont());
		title.setForeground(new Color(100, 12, 0));
		
		// Adds compenents to panel
		menu.add(Box.createRigidArea(new Dimension(0, 40))); // Spacing
		menu.add(title);
		menu.add(Box.createRigidArea(new Dimension(0, 40)));
		menu.add(startBTN);
		menu.add(optionsBTN);
		menu.add(quitBTN);
		
		
		// Adds actionListeners
		quitBTN.addActionListener(new Quit());
		
		// Adds panel to frame
		add(menu);
		
	}
	
	// Main Menu
	/*
	public static void main(String[] args)
    {
		JFrame frame = new JFrame("Undead Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(frameSize);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new GUI());
        frame.setVisible(true);
        frame.setResizable(false);
    }
    */
}