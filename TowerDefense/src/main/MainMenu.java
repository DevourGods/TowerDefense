package main;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel
{
	public static final long serialVersionUID = 1L;
	public static JFrame frame;
	private static Dimension frameSize = new Dimension(1280, 720);
	public final static int w = 1280;
	public final static int h = 720;
	
	public MainMenu()
	{	
		setLayout(new BorderLayout());
		startMenu();
	}
	
	public void startMenu()
	{	
		MenuPanel menu = new MenuPanel("/resources/Menu-Small.png");
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
		menu.add(Box.createRigidArea(new Dimension(0, 70)));
		menu.add(startBTN);
		menu.add(Box.createRigidArea(new Dimension(0, 50)));
		menu.add(optionsBTN);
		menu.add(Box.createRigidArea(new Dimension(0, 50)));
		menu.add(quitBTN);
		
		
		// Adds actionListeners
		startBTN.addActionListener(new Start(frame));
		quitBTN.addActionListener(new Quit());
		
		// Adds panel to frame
		add(menu);
		
	}
	
	// Main Menu
	
	public static void main(String[] args)
    {
		frame = new JFrame("Undead Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(frameSize);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new MainMenu());
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
}