package main;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main {
	
	private static JFrame mainFrame;
	private Dimension frameSize;
	public static ActionPanel actionPanel;
	public static MusicPlayer soundPlayer;
	public static GamePanel gamePanel;
	public static boolean isPaused = true;
	public static long clipTime = 0;
	public static EffectsPlayer effectsPlayer;
	
	public Main() {
		initializeVariables();
		initializeInterface();
	}
	
	private void initializeVariables() {
		mainFrame  = new JFrame("Undead Defense");
		frameSize = new Dimension(1280, 720);
		actionPanel = new ActionPanel(new Dimension(320, 720));
		gamePanel = new GamePanel(new Dimension(720, 720));
		soundPlayer = new MusicPlayer();
		effectsPlayer = new EffectsPlayer();
	}
	
	public static void start() {
		gamePanel.gameLoop = new Thread(gamePanel);
		gamePanel.gameLoop.start();
		isPaused = false;
		
		soundPlayer.clip.setMicrosecondPosition(clipTime);
		soundPlayer.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void pause() {
		gamePanel.gameLoop = null;
		isPaused = true;
		
		clipTime = soundPlayer.clip.getMicrosecondPosition();
		soundPlayer.clip.stop();
	}
	
	private void initializeInterface() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);
		mainFrame.setSize(frameSize);
		mainFrame.setResizable(false);
		mainFrame.setFocusable(true);
		
		URL iconURL = getClass().getResource("/resources/Icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		mainFrame.setIconImage(icon.getImage());
		
		mainFrame.add(actionPanel);
		actionPanel.setBounds(0,0,320,720);
		
		mainFrame.add(gamePanel);
		gamePanel.setBounds(320,0,960,720);
		
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	public static void startGame() {
		new Main();
	}
}