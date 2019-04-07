package main;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main
{
	private JFrame mainFrame;
	private Dimension frameSize;
	
	public static ActionPanel actionPanel;
	public static GamePanel gamePanel;
	public static boolean isPaused = true;
	
	public final static int w = 1280;
	public final static int h = 720;
	
	
	private Main()
	{
		initializeVariables();
		initializeInterface();
	}
	
	private void initializeVariables()
	{
		mainFrame = new JFrame("Undead Defense");
		frameSize = new Dimension(w, h);
		
		actionPanel = new ActionPanel(new Dimension(w/4, h));
		gamePanel = new GamePanel(new Dimension(w/4*3, h));
	}
	
	public static void start()
	{
		gamePanel.gameLoop = new Thread(gamePanel);
		gamePanel.gameLoop.start();
		isPaused = false;
	}
	
	public static void pause()
	{
		gamePanel.gameLoop = null;
		isPaused = true;
	}
	
	private void initializeInterface()
	{
		mainFrame = new JFrame("Undead Defense");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		mainFrame.setSize(frameSize);
		mainFrame.setLayout(null);
		mainFrame.setResizable(false);
		
		URL iconURL = getClass().getResource("/resources/Icon.png"); // JFrame icon
		ImageIcon icon = new ImageIcon(iconURL);
		mainFrame.setIconImage(icon.getImage());
		
		mainFrame.add(actionPanel);
		actionPanel.setBounds(0,0, w/4, h);
		
		mainFrame.add(gamePanel);
		gamePanel.setBounds(w/4, 0, w/4*3, h);
		
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	public static void main(String [] args)
	{
		new Main();
	}
	
}
