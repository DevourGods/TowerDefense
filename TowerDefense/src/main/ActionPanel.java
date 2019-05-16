package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ActionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	boolean started = false;
	boolean gameStarted = false;
	public boolean nextLevelStarted = false;
	public int goldLeft = 10000;
	public int livesLeft = 10;
	public int wavesCompleted = 0;
	private int towerSelection = 1;
	private OutlineLabel towerSelectionLabel;
	private ImageButton skeletonTower;
	private JButton towerTwoButton;
	private ImageButton playButton;
	private ImageButton pauseButton;
	private OutlineLabel gold;
	private OutlineLabel lives;
	private OutlineLabel towerSel;
	private ImageButton nextLevelButton;
	private OutlineLabel waves;
	private BufferedImage backgroundImage;
	
	public ActionPanel(Dimension size) {
		super();
		this.setPreferredSize(size);
		initializeVariables();
		initializePanel();
	}
	
	private void initializeVariables() {
		
		CreateFont bf2 = new CreateFont("/resources/BreatheFire2.ttf", 40);
		
		skeletonTower = new ImageButton("/resources/Skeleton.png", false, 1, true);
		skeletonTower.addActionListener(this);
		
		towerTwoButton = new JButton();
		towerTwoButton.addActionListener(this);
		
		playButton = new ImageButton("/resources/PlayButton.png", true, 0.5, false);
		playButton.addActionListener(this);
		pauseButton = new ImageButton("/resources/PauseButton.png", true, 0.5, false);
		pauseButton.addActionListener(this);
		
		gold = new OutlineLabel("Gold: " + goldLeft, true, Color.BLACK);
		gold.setFont(bf2.getFont());
		gold.setForeground(new Color(212, 175, 55));
		
		lives = new OutlineLabel("Lives: " + livesLeft, true, Color.BLACK);
		lives.setFont(bf2.getFont());
		lives.setForeground(Color.CYAN);
		
		towerSel = new OutlineLabel("Click to Place", true, Color.BLACK);
		towerSel.setFont(bf2.getFont());
		towerSel.setForeground(Color.GREEN);
		
		towerSelectionLabel = new OutlineLabel("Skeleton", true, Color.BLACK);
		towerSelectionLabel.setFont(bf2.getFont());
		towerSelectionLabel.setForeground(Color.WHITE);
		
		waves = new OutlineLabel("Wave: " + wavesCompleted, true, Color.BLACK);
		waves.setFont(bf2.getFont());
		waves.setForeground(new Color(0, 51, 102));
		
		nextLevelButton = new ImageButton("/resources/StartButton.png", true, 0.5, false);
		nextLevelButton.addActionListener(this);
		playButton.setEnabled(false);
		pauseButton.setEnabled(false);
		
		gameStarted = false;
		
		try{
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("/resources/Paper.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void initializePanel() {
		this.setLayout(null);
		this.add(skeletonTower);
		this.add(towerTwoButton);
		this.add(playButton);
		this.add(pauseButton);
		
		skeletonTower.setBounds(20,20,50,50);
		towerTwoButton.setBounds(74,15,48,48);
		playButton.setBounds(10,135,150,100);
		pauseButton.setBounds(160,135,150,100);
		this.add(gold);
		this.add(lives);
		gold.setBounds(95,210,400,100);
		lives.setBounds(92,275,400,100);
		this.add(towerSel);
		this.add(towerSelectionLabel);
		towerSel.setBounds(50,350,400,100);
		towerSelectionLabel.setBounds(85,400,400,100);
		this.add(nextLevelButton);
		nextLevelButton.setBounds(83,560,150,100);
		add(waves);
		waves.setBounds(95,475,400,100);
	}
	
	public void loseLife() {
		livesLeft -= 1;
		lives.setText("Lives: "+livesLeft);
		if(livesLeft == 0) {
			playButton.setEnabled(false);
			Main.gamePanel.gameOver = true;
			Main.gamePanel.repaint();
			Main.pause();
		}
	}
	
	public void buySkeletonTower() {
		goldLeft -= 25;
		gold.setText("Gold: "+goldLeft);
	}
	
	public void buyTowerTwo() {
		goldLeft -= 100;
		gold.setText("Gold: "+goldLeft);
	}
	
	public void addGold(int amount) {
		goldLeft += amount;
		gold.setText("Gold: "+goldLeft);
	}
	
	public int getTowerSelection() {
		return towerSelection;
	}
	
	public void enableNextLevel() {
		nextLevelButton.setEnabled(true);
	}
	
	public void disableNextLevel() {
		nextLevelButton.setEnabled(false);
	}
	
	public void gameOver() {
		nextLevelButton.setEnabled(false);
		playButton.setEnabled(false);
		pauseButton.setEnabled(false);
	}
	
	public void incrementWave() {
		wavesCompleted +=1;
		waves.setText("Waves Done: "+wavesCompleted+"/5"); // wavesLabel.setText("Wave: " + waves);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.drawImage(backgroundImage, 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == playButton) {
			pauseButton.setEnabled(true);
			playButton.setEnabled(false);
			Main.start();
		}
		if(e.getSource() == pauseButton) {
			playButton.setEnabled(true);
			pauseButton.setEnabled(false);
			Main.pause();
		}
		if(e.getSource() == skeletonTower) {
			towerSelection = 1;
			towerSelectionLabel.setText("SKELETON");
		}
		if(e.getSource() == towerTwoButton) {
			towerSelection = 2;
			towerSelectionLabel.setText("ADVANCED T.");
		}
		if(e.getSource() == nextLevelButton) {
			Main.clipTime = Main.soundPlayer.clip.getMicrosecondPosition();
			Main.start();
			nextLevelStarted = false;
			pauseButton.setEnabled(true);
			// playButton.setEnabled(true); // No need for both buttons to be enabled
			if(gameStarted) {
				nextLevelStarted = true;
			}
			nextLevelButton.setImage("/resources/NextLevelButton.png", 0.5);
			nextLevelButton.setBounds(83,560,150,100);
			nextLevelButton.setEnabled(false);
			gameStarted = true;
		}
	}

}
