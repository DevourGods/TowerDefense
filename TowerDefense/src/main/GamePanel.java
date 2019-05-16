package main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;

import javax.imageio.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private boolean gameWon = false;
	private boolean incremented = false;
	private int level = 1;
	public boolean levelDone = false;
	public boolean gameOver = false;
	private boolean nextLevel = false;
	private BufferedImage grass;
	private BufferedImage path;
	private BufferedImage rock;
	public static Scanner scan;
	private int xCoordinate = 0;
	private int yCoordinate = 0;
	private String row = "";
	
	private Monster monster1;
	private Monster2 monster2;
	private Monster3 monster3;
	private Monster4 monster4;
	private Monster5 monster5;
	private Tower tower1;
	private AdvancedTower tower2;
	private Level levelOne;
	private Level levelTwo;
	private Level levelThree;
	private Level levelFour;
	private Level levelFive;
	private int timesLooped = 0;
	boolean loaded = false;
	
	public Thread gameLoop;
	private AnimatedSprite[] enemies;
	private Projectile projectile;
	
	public MouseHandler mouseHandler = new MouseHandler(this.getGraphics());
	public ArrayList<Point> towerCoordinates = new ArrayList<Point>();
	public ArrayList<Point> towerTwoCoordinates = new ArrayList<Point>();
	public static CopyOnWriteArrayList<Tower> towerOnes = new CopyOnWriteArrayList<Tower>();
	public static CopyOnWriteArrayList<Tower> towerTwos = new CopyOnWriteArrayList<Tower>();
	private CopyOnWriteArrayList<Projectile> towerOneProjectiles = new CopyOnWriteArrayList<Projectile>();
	private CopyOnWriteArrayList<ProjectileTwo> towerTwoProjectiles = new CopyOnWriteArrayList<ProjectileTwo>();
	private Projectile proj;
	private ProjectileTwo proj2;
	
	public GamePanel(Dimension size) {
		this.setSize(size);
		this.setFocusable(true);
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.setDoubleBuffered(true);
		this.loadResources();
		this.initializeGameData();
	}
	
	private void initializeGameData() {
		monster1 = new Monster(this, (Graphics2D) this.getGraphics());
		monster2 = new Monster2(this, (Graphics2D) this.getGraphics());
		monster3 = new Monster3(this, (Graphics2D) this.getGraphics());
		monster4 = new Monster4(this, (Graphics2D) this.getGraphics());
		monster5 = new Monster5(this, (Graphics2D) this.getGraphics()); 
		tower1 = new Tower(this, (Graphics2D) this.getGraphics(),0,0);
		tower2 = new AdvancedTower(this, (Graphics2D) this.getGraphics(), 0, 0);
		
		levelOne = new Level(25, monster1, 100, 0);
		levelTwo = new Level(50, monster2, 500, 1);
		levelThree = new Level(50, monster3, 1500, 2);
		levelFour = new Level(40, monster4, 4500, 3);
		levelFive = new Level(50, monster5, 8000, 4);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		buildMap(g2);
		mouseHandler.g2 = this.getGraphics();

		for(AnimatedSprite m : levelOne.getEnemyArray()) {
			m.setGraphics(g2);
			m.draw();
		}
		// Drawing all the towers
		for(Point p : towerCoordinates) {
			g2.setColor(Color.BLUE);
			tower1.setGraphics(g2);
			tower1.position = p;
			tower1.draw();
		}
		for(Point p : towerTwoCoordinates) {
			g2.setColor(Color.BLUE);
			tower2.setGraphics(g2);
			tower2.position = p;
			tower2.draw();
		}
		
		// Drawing all the projectiles
		for(Projectile pj : towerOneProjectiles) {
			pj.setGraphics(g2);
			pj.draw();
		}
		for(Projectile pj2 : towerTwoProjectiles) {
			pj2.setGraphics(g2);
			pj2.draw();
		}
		
		// If game over
		if(gameOver) {
			g2.setFont(new Font("Times New Roman", Font.BOLD, 72));
			g2.setColor(Color.RED);
			String go = "Game Over";
			Rectangle2D bounds = g2.getFontMetrics().getStringBounds(go, g2);
			g2.drawString(go, (int) ((this.getWidth() - bounds.getWidth())/2), 200);
		}
		// If game won
		if(gameWon) {
			g2.setFont(new Font("Times New Roman", Font.BOLD, 72));
			g2.setColor(Color.GREEN);
			String go = "You Win!";
			Rectangle2D bounds = g2.getFontMetrics().getStringBounds(go, g2);
			g2.drawString(go, (int) ((this.getWidth() - bounds.getWidth())/2), 200);
		}
		
		// If between levels
		if(nextLevel) {
			g2.setFont(new Font("Times New Roman", Font.BOLD, 24));
			g2.setColor(Color.BLUE);
			String go = "Level Cleared. Get ready for next level.";
			Rectangle2D bounds = g2.getFontMetrics().getStringBounds(go, g2);
			g2.drawString(go, (int) ((this.getWidth() - bounds.getWidth())/2), 55);
		}

	}
	
	private void buildMap(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		yCoordinate = 0;
		scan = new Scanner(getClass().getResourceAsStream("/resources/map_data.txt"));
		for(int y = 0; y < 15; y++) {
			xCoordinate = 0;
			row = scan.nextLine();
			for(int x = 0; x < 20; x++) {
				if(row.charAt(x) == '0') {
					g2.drawImage(grass, xCoordinate, yCoordinate, null);
				}else if(row.charAt(x) == '1') {
					g2.drawImage(path, xCoordinate, yCoordinate, null);
				}else if(row.charAt(x) == '2') {
					g2.drawImage(rock, xCoordinate, yCoordinate, null);
				}
				xCoordinate+=48;
			}
			yCoordinate+=48;
		}
	}
	
	private void loadResources() {
		try{
			grass = ImageIO.read(getClass().getResourceAsStream("/resources/Grass.png"));
			path = ImageIO.read(getClass().getResourceAsStream("/resources/Path.png"));
			rock = ImageIO.read(getClass().getResourceAsStream("/resources/Rock.png"));
			scan = new Scanner(getClass().getResourceAsStream("/resources/map_data.txt"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private Point calculateNeededVelocity(Point p1, Point p2) {
		Point v = new Point();
		v.x = (p2.x - p1.x)/20;
		v.y = (p2.y - p1.y)/20;
		if(v.x == 0)	v.x = 1;
		if(v.y == 0)	v.y = 1;
		//System.out.printf("%d,%d // %d%n", v.x, v.y, towerOneProjectiles.size());
		return v;
	}
	
	@Override
	public synchronized void run(){
		Thread t = Thread.currentThread();
		// Use the System time to moderate length of sleep periods
		long beginTime, timeTaken, timeLeft;
		while(t == gameLoop) {
			// Get time at beginning of loop
			beginTime = System.nanoTime();
			timesLooped++;
			
			if(timesLooped%25 == 0 && levelOne.goneBy < levelOne.getEnemyNumber()) {
				levelOne.getEnemyArray()[levelOne.goneBy].position = new Point(50,0); // Starting point of where the monsters spawn
				levelOne.getEnemyArray()[levelOne.goneBy].velocity = new Point(0,1); // Speed of the mosnters
				levelOne.goneBy++;
			}			
			
			// Iterate through all the towers to see if they can fire at something
			if(timesLooped%100 == 0) {
				for(Tower tower: towerOnes) {
					for(int i = 0; i < levelOne.getEnemyNumber(); i++) {
						if(tower.getFireBounds().intersects(levelOne.enemies[i].getBounds())) {
							
							// Fire
							proj = new Projectile(this, null);
							proj.position = tower.position;
							proj.velocity = calculateNeededVelocity(tower.position, levelOne.enemies[i].position);
							towerOneProjectiles.add(proj);
							Point tempCoordinate = tower.position;
							towerOnes.remove(tower);
							Tower newTower = new Tower(this, (Graphics2D) this.getGraphics(), tempCoordinate.x, tempCoordinate.y);
							towerOnes.add(newTower);
							break;
						}
					}
				}
				for(Tower t2: towerTwos) {
					for(int i = 0; i < levelOne.getEnemyNumber(); i++) {
						if(t2.getFireBounds().intersects(levelOne.enemies[i].getBounds())) {
							
							// Fire
							proj2 = new ProjectileTwo(this, null);
							proj2.position = t2.position;
							proj2.velocity = calculateNeededVelocity(t2.position, levelOne.enemies[i].position);
							towerTwoProjectiles.add(proj2);
							Point tempCoordinate = t2.position;
							towerTwos.remove(t2);
							AdvancedTower newTower = new AdvancedTower(this, (Graphics2D) this.getGraphics(), tempCoordinate.x, tempCoordinate.y);
							towerTwos.add(newTower);
							break;
						}
					}
				}
			}
			
			// Conditions for removing projectile/enemy
			for(Projectile proj : towerOneProjectiles) {
				if(proj.position.x < 0 || proj.position.y < 0 || proj.position.x > this.getWidth() || proj.position.y > this.getHeight()) {
					towerOneProjectiles.remove(proj);
				}
				for(int ctr = 0; ctr < levelOne.getEnemyNumber(); ctr++) {
					if(proj.getBounds().intersects(levelOne.enemies[ctr].getBounds())) {
						levelOne.enemies[ctr].health -= 100;
						towerOneProjectiles.remove(proj);
						if(levelOne.enemies[ctr].health <=0) {
							levelOne.enemies[ctr].alive = false;
							levelOne.enemies[ctr].position = new Point(-200,-200);
							Main.actionPanel.addGold(5);
							if(level == 3)	Main.actionPanel.addGold(15);
							if(level ==4)	Main.actionPanel.addGold(25);
							if(level ==5) 	Main.actionPanel.addGold(35);
							Main.effectsPlayer = new EffectsPlayer();
							Main.effectsPlayer.clip.loop(0);
						}
					}
				}
			}
			for(ProjectileTwo projects : towerTwoProjectiles) {
				if(projects.position.x < 0 || projects.position.y < 0 || projects.position.x > this.getWidth() || projects.position.y > this.getHeight()) {
					towerTwoProjectiles.remove(projects);
				}
				for(int ctr = 0; ctr < levelOne.getEnemyNumber(); ctr++) {
					if(projects.getBounds().intersects(levelOne.enemies[ctr].getBounds())) {
						levelOne.enemies[ctr].health -= 500;
						towerTwoProjectiles.remove(projects);
						if(levelOne.enemies[ctr].health <=0) {
							levelOne.enemies[ctr].alive = false;
							levelOne.enemies[ctr].position = new Point(-200,-200);
							Main.actionPanel.addGold(5);
							
							// Add more gold on higher levels
							if(level==3)	Main.actionPanel.addGold(15);
							if(level ==4)	Main.actionPanel.addGold(25);
							if(level ==5) 	Main.actionPanel.addGold(35);
							Main.effectsPlayer = new EffectsPlayer();
							Main.effectsPlayer.clip.loop(0);
						}
					}
				}
			}
			
			for(AnimatedSprite as : levelOne.enemies) { // Conditions for losing a life
				if(as.getBounds().intersects(new Rectangle(720,578, 25,25))) { // Death box
					as.alive = false;
					as.position = new Point(-200,-200);
					Main.actionPanel.loseLife();
				}
			}
			
			// Checks if all enemies from current level are dead
			levelDone = true;
			for(AnimatedSprite animS : levelOne.enemies) {
				if(animS.alive == true) 	levelDone = false;
			}
			
			if(levelDone) {
				nextLevel = true;
				repaint();
				if(!incremented) {
					Main.actionPanel.incrementWave();
					incremented = true;
					if(Main.actionPanel.wavesCompleted == 5) {
						nextLevel = false;
						gameWon = true;
						repaint();
						Main.actionPanel.gameOver();
						break;
					} 	

				}	
				Main.actionPanel.enableNextLevel();
				if(Main.actionPanel.nextLevelStarted && Main.actionPanel.gameStarted) {
					if(level == 1) levelOne = levelTwo;
					if(level == 2) levelOne = levelThree;
					if(level == 3) levelOne = levelFour;
					if(level == 4) levelOne = levelFive;
					level++;
					nextLevel = false;
					repaint();
					Main.actionPanel.disableNextLevel();
					Main.actionPanel.nextLevelStarted = false;
					incremented = false;
				}
			}

			repaint();
			
			timeTaken = System.nanoTime() - beginTime;
			timeLeft = ((15*1000000) - timeTaken)/1000000;
			if (timeLeft < 1) timeLeft = 1;
			try{
				Thread.sleep(timeLeft);
			}catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

}
