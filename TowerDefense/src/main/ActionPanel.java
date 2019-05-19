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
	public int goldLeft = 250;
	public int livesLeft = 10;
	public int wavesCompleted = 1; // This is really the wave that the game is on
	private int towerSelection = 1;
	private OutlineLabel towerSelectionLabel;
	private ImageButton playButton;
	private ImageButton pauseButton;
	private OutlineLabel gold;
	private OutlineLabel lives;
	private OutlineLabel towerSel;
	private ImageButton nextLevelButton;
	private OutlineLabel waves;
	private BufferedImage backgroundImage;
	
	private ImageButton skeletonTower;
	private ImageButton mageTower;
	private ImageButton orcTower;
	
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
		
		mageTower = new ImageButton("/resources/Mage.png", false, 1, false);
		mageTower.addActionListener(this);
		
		orcTower = new ImageButton("/resources/Orc.png", false, 1, false);
		orcTower.addActionListener(this);
		
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
		this.add(mageTower);
		this.add(orcTower);
		
		this.add(playButton);
		this.add(pauseButton);
		
		skeletonTower.setBounds(20,20,50,50);
		mageTower.setBounds(80,20,50,50);
		orcTower.setBounds(140,20,50,50);
		
		playButton.setBounds(10,135,150,100);
		pauseButton.setBounds(160,135,150,100);
		this.add(gold);
		this.add(lives);
		gold.setBounds((int)(320 - gold.getPreferredSize().getWidth()) / 2,210,400,100);
		lives.setBounds((int)(320 - lives.getPreferredSize().getWidth()) / 2,275,400,100);
		this.add(towerSel);
		this.add(towerSelectionLabel);
		towerSel.setBounds((int)(320 - towerSel.getPreferredSize().getWidth()) / 2,350,400,100);
		towerSelectionLabel.setBounds((int)(320 - towerSelectionLabel.getPreferredSize().getWidth()) / 2,400,400,100);
		this.add(nextLevelButton);
		nextLevelButton.setBounds((int)(320 - nextLevelButton.getPreferredSize().getWidth()) / 2,560,150,100);
		add(waves);
		waves.setBounds((int)(320 - waves.getPreferredSize().getWidth()) / 2,475,400,100);
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
		gold.setBounds((int)(320 - gold.getPreferredSize().getWidth()) / 2,210,400,100);
	}
	
	public void buyMageTower() {
		goldLeft -= 100;
		gold.setText("Gold: "+goldLeft);
		gold.setBounds((int)(320 - gold.getPreferredSize().getWidth()) / 2,210,400,100);
	}
	
	public void buyOrcTower() {
		goldLeft -= 150;
		gold.setText("Gold: "+goldLeft);
		gold.setBounds((int)(320 - gold.getPreferredSize().getWidth()) / 2,210,400,100);
	}
	
	public void addGold(int amount) {
		goldLeft += amount;
		gold.setText("Gold: "+goldLeft);
		gold.setBounds((int)(320 - gold.getPreferredSize().getWidth()) / 2,210,400,100);
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
		waves.setText("Wave: " + wavesCompleted);
		waves.setBounds((int)(320 - waves.getPreferredSize().getWidth()) / 2,475,400,100);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.drawImage(backgroundImage, 0, 0, null);
	}
	
	public void clearBorders() {
		skeletonTower.setBorder(false);
		mageTower.setBorder(false);
		orcTower.setBorder(false);
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
			clearBorders();
			skeletonTower.setBorder(true);
			towerSelectionLabel.setBounds((int)(320 - towerSelectionLabel.getPreferredSize().getWidth()) / 2,400,400,100);
		}
		if(e.getSource() == mageTower) {
			towerSelection = 2;
			towerSelectionLabel.setText("MAGE");
			clearBorders();
			mageTower.setBorder(true);
			towerSelectionLabel.setBounds((int)(320 - towerSelectionLabel.getPreferredSize().getWidth()) / 2,400,400,100);
		}
		if(e.getSource() == orcTower) {
			towerSelection = 3;
			towerSelectionLabel.setText("ORC");
			clearBorders();
			orcTower.setBorder(true);
			towerSelectionLabel.setBounds((int)(320 - towerSelectionLabel.getPreferredSize().getWidth()) / 2,400,400,100);
		}
		if(e.getSource() == nextLevelButton) {
			Main.clipTime = Main.soundPlayer.clip.getMicrosecondPosition();
			Main.start();
			nextLevelStarted = false;
			pauseButton.setEnabled(true);
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
