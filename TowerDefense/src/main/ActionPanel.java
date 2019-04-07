package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ActionPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	boolean started = false;
	boolean gameStarted = false;
	public boolean nextLevelStarted = false;
	public int gold = 100;
	public int lives = 100;
	public int waves = 0;
	private int towerSelection = 1;
	private ImageButton skeletonBTN;
	private ImageButton playBTN;
	private ImageButton pauseBTN;
	private ImageButton nextLevelBTN;
	private OutlineLabel goldLabel;
	private OutlineLabel livesLabel;
	private OutlineLabel clickToPlaceLabel;
	private OutlineLabel towerSelectionLabel;
	private OutlineLabel wavesLabel;
	private BufferedImage backgroundImage;
	
	public ActionPanel(Dimension size)
	{
		super();
		this.setPreferredSize(size);
		initializeVariables();
		initializePanel();
	}
	
	private void initializeVariables()
	{
		CreateFont bf2 = new CreateFont("/resources/BreatheFire2.ttf", 40);
		
		skeletonBTN = new ImageButton("/resources/Skeleton.png", false, 1);
		skeletonBTN.addActionListener(this);
		
		playBTN = new ImageButton("/resources/PlayButton.png", true, 0.5);
		playBTN.addActionListener(this);
		pauseBTN = new ImageButton("/resources/PauseButton.png", true, 0.5);
		pauseBTN.addActionListener(this);
		
		goldLabel = new OutlineLabel("Gold: " + gold, true, Color.BLACK);
		goldLabel.setFont(bf2.getFont());
		goldLabel.setForeground(new Color(212, 175, 55));
		
		livesLabel = new OutlineLabel("Lives: " + lives, true, Color.BLACK);
		livesLabel.setFont(bf2.getFont());
		livesLabel.setForeground(Color.CYAN);
		
		clickToPlaceLabel = new OutlineLabel("Click to Place", true, Color.BLACK);
		clickToPlaceLabel.setFont(bf2.getFont());
		clickToPlaceLabel.setForeground(Color.GREEN);
		
		towerSelectionLabel = new OutlineLabel("Skeleton", true, Color.BLACK);
		towerSelectionLabel.setFont(bf2.getFont());
		towerSelectionLabel.setForeground(Color.WHITE);
		
		wavesLabel = new OutlineLabel("Wave: " + waves, true, Color.BLACK);
		wavesLabel.setFont(bf2.getFont());
		wavesLabel.setForeground(new Color(0, 51, 102));
		
		nextLevelBTN = new ImageButton("/resources/StartButton.png", true, 0.5);
		nextLevelBTN.addActionListener(this);
		playBTN.setEnabled(false);
		pauseBTN.setEnabled(false);
		
		try
		{
			backgroundImage = ImageIO.read(getClass().getResource("/resources/Paper.png"));
		}
		catch (Exception e)
		{
			System.out.println("Image not found!");
		}
		
		gameStarted = false;
	}
	
	private void initializePanel()
	{
		setLayout(null);
		add(skeletonBTN);
		add(playBTN);
		add(pauseBTN);
		add(goldLabel);
		add(livesLabel);
		add(clickToPlaceLabel);
		add(towerSelectionLabel);
		add(wavesLabel);
		add(nextLevelBTN);

		skeletonBTN.setBounds(20, 20, 50, 50);
		playBTN.setBounds(34, 135, 100, 100);
		pauseBTN.setBounds(184, 135, 100, 100);
		goldLabel.setBounds(95, 210, 400, 100);
		livesLabel.setBounds(92, 275, 400, 100);
		clickToPlaceLabel.setBounds(50, 350, 400, 100);
		towerSelectionLabel.setBounds(85, 400, 400, 100);
		wavesLabel.setBounds(95, 475, 400, 100);
		nextLevelBTN.setBounds(83, 560, 150, 100);
	}

	public void loseLife()
	{
		lives -= 1;
		livesLabel.setText("Lives: " + lives);
		
		if(lives == 0)
		{
			playBTN.setEnabled(false);
			//Main.gamePanel.gameOver = true;
			Main.gamePanel.repaint();
			Main.pause();
		}
	}
	
	public void buySkeleton()
	{
		gold -= 1;
		goldLabel.setText("Gold: " + gold);
	}
	
	public void addGold(int amount)
	{
		gold += amount;
		goldLabel.setText("Gold: " + gold);
	}
	
	public int getTowerSelection()
	{
		return towerSelection;
	}
	
	public void enableNextLevel()
	{
		nextLevelBTN.setEnabled(true);
	}
	
	public void disableNextLevel()
	{
		nextLevelBTN.setEnabled(false);
	}
	
	public void gameOver()
	{
		nextLevelBTN.setEnabled(false);
		playBTN.setEnabled(false);
		pauseBTN.setEnabled(false);
	}
	
	public void incrementWave()
	{
		waves += 1;
		wavesLabel.setText("Wave: " + waves);
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.drawImage(backgroundImage, 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == playBTN)
		{
			Main.start();
		}
		
		if (e.getSource() == pauseBTN)
		{
			Main.pause();
		}
		
		if (e.getSource() == skeletonBTN)
		{
			towerSelection = 1;
			towerSelectionLabel.setText("Skeleton");
		}
		
		if (e.getSource() == nextLevelBTN)
		{
			Main.start();
			nextLevelStarted = false;
			pauseBTN.setEnabled(true);
			playBTN.setEnabled(true);
			if (gameStarted)
			{
				nextLevelStarted = true;
			}
			nextLevelBTN = new ImageButton("/resources/NextLevelButton.png", true, 0.5);
			nextLevelBTN.setEnabled(false);
			gameStarted = true;
		}
	}
}
