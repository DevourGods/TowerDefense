package main;

import javax.swing.*;

import custom.CreateFont;
import monsters.MonsterBee;
import monsters.MonsterBoss;
import monsters.MonsterDarkHarpy;
import monsters.MonsterHarpy;
import monsters.MonsterSiren;
import monsters.MonsterWerebat;
import towers.BeholderProjectile;
import towers.BeholderTower;
import towers.DemonProjectile;
import towers.DemonTower;
import towers.DragonProjectile;
import towers.DragonTower;
import towers.MageProjectile;
import towers.MageTower;
import towers.OrcProjectile;
import towers.OrcTower;
import towers.SkeletonProjectile;
import towers.SkeletonTower;

import java.awt.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.*;

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
	private CreateFont bf2;
	
	private MonsterBee monsterBee;
	private MonsterWerebat monsterWerebat;
	private MonsterSiren monsterSiren;
	private MonsterHarpy monsterHarpy;
	private MonsterDarkHarpy monsterDarkHarpy;
	private MonsterBoss monsterBoss;
	private SkeletonTower skeleton;
	private MageTower mage;
	private OrcTower orc;
	private DemonTower demon;
	private BeholderTower beholder;
	private DragonTower dragon;
	private Level levelOne;
	private Level levelTwo;
	private Level levelThree;
	private Level levelFour;
	private Level levelFive;
	private Level levelSix;
	private Level levelSeven;
	private Level levelEight;
	private Level levelNine;
	private Level levelTen;
	private Level levelEleven;
	private Level levelTwelve;
	private Level levelThirteen;
	private Level levelFourteen;
	private Level levelFifteen;
	private Level levelSixteen;
	private Level levelSeventeen;
	private Level levelEighteen;
	private Level levelNineteen;
	private Level levelTwenty;
	private int timesLooped = 0;
	boolean loaded = false;
	
	public Thread gameLoop;
	//private AnimatedSprite[] enemies;
	//private SkeletonProjectile projectile;
	
	public MouseHandler mouseHandler = new MouseHandler(this.getGraphics());
	
	public ArrayList<Point> skeletonCoordinates = new ArrayList<Point>();
	public ArrayList<Point> mageCoordinates = new ArrayList<Point>();
	public ArrayList<Point> orcCoordinates = new ArrayList<Point>();
	public ArrayList<Point> demonCoordinates = new ArrayList<Point>();
	public ArrayList<Point> beholderCoordinates = new ArrayList<Point>();
	public ArrayList<Point> dragonCoordinates = new ArrayList<Point>();
	public static CopyOnWriteArrayList<SkeletonTower> skeletonCopy = new CopyOnWriteArrayList<SkeletonTower>();
	public static CopyOnWriteArrayList<SkeletonTower> mageCopy = new CopyOnWriteArrayList<SkeletonTower>();
	public static CopyOnWriteArrayList<SkeletonTower> orcCopy = new CopyOnWriteArrayList<SkeletonTower>();
	public static CopyOnWriteArrayList<SkeletonTower> demonCopy = new CopyOnWriteArrayList<SkeletonTower>();
	public static CopyOnWriteArrayList<SkeletonTower> beholderCopy = new CopyOnWriteArrayList<SkeletonTower>();
	public static CopyOnWriteArrayList<SkeletonTower> dragonCopy = new CopyOnWriteArrayList<SkeletonTower>();
	private CopyOnWriteArrayList<SkeletonProjectile> skeletonProjCopy = new CopyOnWriteArrayList<SkeletonProjectile>();
	private CopyOnWriteArrayList<MageProjectile> mageProjCopy = new CopyOnWriteArrayList<MageProjectile>();
	private CopyOnWriteArrayList<OrcProjectile> orcProjCopy = new CopyOnWriteArrayList<OrcProjectile>();
	private CopyOnWriteArrayList<DemonProjectile> demonProjCopy = new CopyOnWriteArrayList<DemonProjectile>();
	private CopyOnWriteArrayList<BeholderProjectile> beholderProjCopy = new CopyOnWriteArrayList<BeholderProjectile>();
	private CopyOnWriteArrayList<DragonProjectile> dragonProjCopy = new CopyOnWriteArrayList<DragonProjectile>();
	private SkeletonProjectile skeletonProj;
	private MageProjectile mageProj;
	private OrcProjectile orcProj;
	private DemonProjectile demonProj;
	private BeholderProjectile beholderProj;
	private DragonProjectile dragonProj;
	
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
		bf2 = new CreateFont("/resources/BreatheFire2.ttf", 40);
		
		monsterBee = new MonsterBee(this, (Graphics2D) this.getGraphics(), 0);
		monsterWerebat = new MonsterWerebat(this, (Graphics2D) this.getGraphics(), 1);
		monsterSiren = new MonsterSiren(this, (Graphics2D) this.getGraphics(), 2);
		monsterHarpy = new MonsterHarpy(this, (Graphics2D) this.getGraphics(), 3);
		monsterDarkHarpy = new MonsterDarkHarpy(this, (Graphics2D) this.getGraphics(), 4); 
		monsterBoss = new MonsterBoss(this, (Graphics2D) this.getGraphics(), 5);
		skeleton = new SkeletonTower(this, (Graphics2D) this.getGraphics(), 0, 0);
		mage = new MageTower(this, (Graphics2D) this.getGraphics(), 0, 0);
		orc = new OrcTower(this, (Graphics2D) this.getGraphics(), 0, 0);
		demon = new DemonTower(this, (Graphics2D) this.getGraphics(), 0, 0);
		beholder = new BeholderTower(this, (Graphics2D) this.getGraphics(), 0, 0);
		dragon = new DragonTower(this, (Graphics2D) this.getGraphics(), 0, 0);
		
		levelOne = new Level(new ArrayList<Integer>(Arrays.asList(10)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee)), new ArrayList<Integer>(Arrays.asList(100)), new ArrayList<Integer>(Arrays.asList(0)), 10); // Amount per type, Enemy types, Health per type, Type num, total
		levelTwo = new Level(new ArrayList<Integer>(Arrays.asList(20)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee)), new ArrayList<Integer>(Arrays.asList(100)), new ArrayList<Integer>(Arrays.asList(0)), 20);
		levelThree = new Level(new ArrayList<Integer>(Arrays.asList(10, 5)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee, monsterWerebat)), new ArrayList<Integer>(Arrays.asList(100, 160)), new ArrayList<Integer>(Arrays.asList(0, 1)), 15);
		levelFour = new Level(new ArrayList<Integer>(Arrays.asList(10, 10)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee, monsterWerebat)), new ArrayList<Integer>(Arrays.asList(100, 160)), new ArrayList<Integer>(Arrays.asList(0, 1)), 20);
		levelFive = new Level(new ArrayList<Integer>(Arrays.asList(5, 10, 3)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee, monsterWerebat, monsterSiren)), new ArrayList<Integer>(Arrays.asList(100, 160, 250)), new ArrayList<Integer>(Arrays.asList(0, 1, 2)), 18);
		levelSix = new Level(new ArrayList<Integer>(Arrays.asList(5, 5, 5)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterWerebat, monsterBee, monsterSiren)), new ArrayList<Integer>(Arrays.asList(160, 100, 250)), new ArrayList<Integer>(Arrays.asList(1, 0, 2)), 15);
		levelSeven = new Level(new ArrayList<Integer>(Arrays.asList(10, 10, 10)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee, monsterSiren, monsterBee)), new ArrayList<Integer>(Arrays.asList(100, 250, 100)), new ArrayList<Integer>(Arrays.asList(0, 2, 0)), 30);
		levelEight = new Level(new ArrayList<Integer>(Arrays.asList(5, 15)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterWerebat, monsterSiren)), new ArrayList<Integer>(Arrays.asList(160, 250)), new ArrayList<Integer>(Arrays.asList(1, 2)), 20);
		levelNine = new Level(new ArrayList<Integer>(Arrays.asList(5, 3)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterSiren, monsterHarpy)), new ArrayList<Integer>(Arrays.asList(250, 400)), new ArrayList<Integer>(Arrays.asList(2, 3)), 8);
		levelTen = new Level(new ArrayList<Integer>(Arrays.asList(15, 10, 1)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterWerebat, monsterSiren, monsterHarpy)), new ArrayList<Integer>(Arrays.asList(160, 250, 400)), new ArrayList<Integer>(Arrays.asList(1, 2, 3)), 26);
		levelEleven = new Level(new ArrayList<Integer>(Arrays.asList(10)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterHarpy)), new ArrayList<Integer>(Arrays.asList(400)), new ArrayList<Integer>(Arrays.asList(3)), 10);
		levelTwelve = new Level(new ArrayList<Integer>(Arrays.asList(10, 15, 5, 1)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterSiren, monsterWerebat, monsterHarpy, monsterDarkHarpy)), new ArrayList<Integer>(Arrays.asList(250, 160, 400, 750)), new ArrayList<Integer>(Arrays.asList(2, 1, 3, 4)), 31);
		levelThirteen = new Level(new ArrayList<Integer>(Arrays.asList(35, 1)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee, monsterDarkHarpy)), new ArrayList<Integer>(Arrays.asList(100, 750)), new ArrayList<Integer>(Arrays.asList(0, 4)), 36);
		levelFourteen = new Level(new ArrayList<Integer>(Arrays.asList(30, 2)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterWerebat, monsterDarkHarpy)), new ArrayList<Integer>(Arrays.asList(160, 750)), new ArrayList<Integer>(Arrays.asList(1, 4)), 32);
		levelFifteen = new Level(new ArrayList<Integer>(Arrays.asList(25, 3)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterSiren, monsterDarkHarpy)), new ArrayList<Integer>(Arrays.asList(250, 750)), new ArrayList<Integer>(Arrays.asList(2, 4)), 28);
		levelSixteen = new Level(new ArrayList<Integer>(Arrays.asList(20, 4)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterHarpy, monsterDarkHarpy)), new ArrayList<Integer>(Arrays.asList(400, 750)), new ArrayList<Integer>(Arrays.asList(3, 4)), 24);
		levelSeventeen = new Level(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee, monsterWerebat, monsterSiren, monsterHarpy, monsterDarkHarpy)), new ArrayList<Integer>(Arrays.asList(100, 160, 250, 400, 750)), new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4)), 15);
		levelEighteen = new Level(new ArrayList<Integer>(Arrays.asList(15, 5, 10)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterHarpy, monsterSiren, monsterDarkHarpy)), new ArrayList<Integer>(Arrays.asList(400, 250, 750)), new ArrayList<Integer>(Arrays.asList(3, 2, 4)), 30);
		levelNineteen = new Level(new ArrayList<Integer>(Arrays.asList(20, 20, 20, 20, 15)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee, monsterWerebat, monsterSiren, monsterHarpy, monsterDarkHarpy)), new ArrayList<Integer>(Arrays.asList(100, 160, 250, 400, 750)), new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4)), 95);
		levelTwenty = new Level(new ArrayList<Integer>(Arrays.asList(5, 1)), new ArrayList<AnimatedSprite>(Arrays.asList(monsterBee, monsterBoss)), new ArrayList<Integer>(Arrays.asList(100, 5000)), new ArrayList<Integer>(Arrays.asList(0, 5)), 6);

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
		for(Point p : skeletonCoordinates) {
			g2.setColor(Color.BLUE);
			skeleton.setGraphics(g2);
			skeleton.position = p;
			skeleton.draw();
		}
		for(Point p : mageCoordinates) {
			g2.setColor(Color.BLUE);
			mage.setGraphics(g2);
			mage.position = p;
			mage.draw();
		}
		for(Point p : orcCoordinates) {
			g2.setColor(Color.BLUE);
			orc.setGraphics(g2);
			orc.position = p;
			orc.draw();
		}
		for(Point p : demonCoordinates) {
			g2.setColor(Color.BLUE);
			demon.setGraphics(g2);
			demon.position = p;
			demon.draw();
		}
		for(Point p : beholderCoordinates) {
			g2.setColor(Color.BLUE);
			beholder.setGraphics(g2);
			beholder.position = p;
			beholder.draw();
		}
		for(Point p : dragonCoordinates) {
			g2.setColor(Color.BLUE);
			dragon.setGraphics(g2);
			dragon.position = p;
			dragon.draw();
		}
		
		// Drawing all the projectiles
		for(SkeletonProjectile pj : skeletonProjCopy) {
			pj.setGraphics(g2);
			pj.draw();
		}
		for(SkeletonProjectile pj2 : mageProjCopy) {
			pj2.setGraphics(g2);
			pj2.draw();
		}
		for(SkeletonProjectile pj3 : orcProjCopy) {
			pj3.setGraphics(g2);
			pj3.draw();
		}
		for(DemonProjectile pj4 : demonProjCopy) {
			pj4.setGraphics(g2);
			pj4.draw();
		}
		for(BeholderProjectile pj5 : beholderProjCopy) {
			pj5.setGraphics(g2);
			pj5.draw();
		}
		for(SkeletonProjectile pj6 : dragonProjCopy) {
			pj6.setGraphics(g2);
			pj6.draw();
		}
		// If game over
		if(gameOver) {
			g2.setFont(bf2.getFont());
			g2.setColor(new Color(100, 12, 0));
			String go = "Game Over";
			Rectangle2D bounds = g2.getFontMetrics().getStringBounds(go, g2);
			g2.drawString(go, (int) ((this.getWidth() - bounds.getWidth())/2 - 100), 200);
		}
		// If game won
		if(gameWon) {
			g2.setFont(bf2.getFont());
			g2.setColor(Color.GREEN);
			String go = "You Win!";
			Rectangle2D bounds = g2.getFontMetrics().getStringBounds(go, g2);
			g2.drawString(go, (int) ((this.getWidth() - bounds.getWidth())/2 - 100), 200);
		}
		
		// If between levels
		if(nextLevel) {
			/*
			g2.setFont(bf2.getFont());
			g2.setColor(new Color(100, 12, 0));
			String go = "Level Cleared. Get ready for next level.";
			Rectangle2D bounds = g2.getFontMetrics().getStringBounds(go, g2);
			g2.drawString(go, (int) ((this.getWidth() - bounds.getWidth())/2 - 100), 85);
			*/
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
				} else if(row.charAt(x) == '1') {
					g2.drawImage(path, xCoordinate, yCoordinate, null);
				} else if(row.charAt(x) == '2') {
					g2.drawImage(rock, xCoordinate, yCoordinate, null);
				}
				xCoordinate+=48;
			}
			yCoordinate+=48;
		}
		
		// Stats Panel
		/*
		BufferedImage temp;
		try {
			temp = ImageIO.read(getClass().getResourceAsStream("/resources/Dragon.png"));
			g2.drawImage(temp, 500, 500, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
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
		if(v.x == 0)	{v.x = 1;}
		if(v.y == 0)	{v.y = 1;}
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
				levelOne.getEnemyArray()[levelOne.goneBy].position = new Point(40, 0); // Starting point of where the monsters spawn
				levelOne.getEnemyArray()[levelOne.goneBy].velocity = new Point(0, 1); // Speed of the mosnters
				levelOne.goneBy++;
			}			
			
			// Iterate through all the towers to see if they can fire at something
			if(timesLooped%275 == 0) { // Fire rate, higher = slower
				for(SkeletonTower t3: orcCopy) {
					for(int i = 0; i < levelOne.getEnemyNumber(); i++) {
						if(t3.getFireBounds().intersects(levelOne.enemies[i].getBounds())) {
							
							// Fire
							orcProj = new OrcProjectile(this, null);
							orcProj.position = t3.position;
							orcProj.velocity = calculateNeededVelocity(t3.position, levelOne.enemies[i].position);
							orcProjCopy.add(orcProj);
							Point tempCoordinate = t3.position;
							orcCopy.remove(t3);
							OrcTower newTower = new OrcTower(this, (Graphics2D) this.getGraphics(), tempCoordinate.x, tempCoordinate.y);
							orcCopy.add(newTower);
							break;
						}
					}
				}
			}
			if(timesLooped%100 == 0) {
				for(SkeletonTower tower: skeletonCopy) {
					for(int i = 0; i < levelOne.getEnemyNumber(); i++) {
						if(tower.getFireBounds().intersects(levelOne.enemies[i].getBounds())) {
							
							// Fire
							skeletonProj = new SkeletonProjectile(this, null);
							skeletonProj.position = tower.position;
							skeletonProj.velocity = calculateNeededVelocity(tower.position, levelOne.enemies[i].position);
							skeletonProjCopy.add(skeletonProj);
							Point tempCoordinate = tower.position;
							skeletonCopy.remove(tower);
							SkeletonTower newTower = new SkeletonTower(this, (Graphics2D) this.getGraphics(), tempCoordinate.x, tempCoordinate.y);
							skeletonCopy.add(newTower);
							break;
						}
					}
				}
			}
			if(timesLooped%75 == 0) {
				for(SkeletonTower t2: mageCopy) {
					for(int i = 0; i < levelOne.getEnemyNumber(); i++) {
						if(t2.getFireBounds().intersects(levelOne.enemies[i].getBounds())) {
							
							// Fire
							mageProj = new MageProjectile(this, null);
							mageProj.position = t2.position;
							mageProj.velocity = calculateNeededVelocity(t2.position, levelOne.enemies[i].position);
							mageProjCopy.add(mageProj);
							Point tempCoordinate = t2.position;
							mageCopy.remove(t2);
							MageTower newTower = new MageTower(this, (Graphics2D) this.getGraphics(), tempCoordinate.x, tempCoordinate.y);
							mageCopy.add(newTower);
							break;
						}
					}
				}
				for(SkeletonTower t6: dragonCopy) {
					for(int i = 0; i < levelOne.getEnemyNumber(); i++) {
						if(t6.getFireBounds().intersects(levelOne.enemies[i].getBounds())) {
							
							// Fire
							dragonProj = new DragonProjectile(this, null);
							dragonProj.position = t6.position;
							dragonProj.velocity = calculateNeededVelocity(t6.position, levelOne.enemies[i].position);
							dragonProjCopy.add(dragonProj);
							Point tempCoordinate = t6.position;
							dragonCopy.remove(t6);
							DragonTower newTower = new DragonTower(this, (Graphics2D) this.getGraphics(), tempCoordinate.x, tempCoordinate.y);
							dragonCopy.add(newTower);
							break;
						}
					}
				}
			}
			if(timesLooped%50 == 0) {
				for(SkeletonTower t4: demonCopy) {
					for(int i = 0; i < levelOne.getEnemyNumber(); i++) {
						if(t4.getFireBounds().intersects(levelOne.enemies[i].getBounds())) {
							
							// Fire
							demonProj = new DemonProjectile(this, null);
							demonProj.position = t4.position;
							demonProj.velocity = calculateNeededVelocity(t4.position, levelOne.enemies[i].position);
							demonProjCopy.add(demonProj);
							Point tempCoordinate = t4.position;
							demonCopy.remove(t4);
							DemonTower newTower = new DemonTower(this, (Graphics2D) this.getGraphics(), tempCoordinate.x, tempCoordinate.y);
							demonCopy.add(newTower);
							break;
						}
					}
				}
			}
			if(timesLooped%10 == 0) {
				for(SkeletonTower t5: beholderCopy) {
					for(int i = 0; i < levelOne.getEnemyNumber(); i++) {
						if(t5.getFireBounds().intersects(levelOne.enemies[i].getBounds())) {
							
							// Fire
							beholderProj = new BeholderProjectile(this, null);
							beholderProj.position = t5.position;
							beholderProj.velocity = calculateNeededVelocity(t5.position, levelOne.enemies[i].position);
							beholderProjCopy.add(beholderProj);
							Point tempCoordinate = t5.position;
							beholderCopy.remove(t5);
							BeholderTower newTower = new BeholderTower(this, (Graphics2D) this.getGraphics(), tempCoordinate.x, tempCoordinate.y);
							beholderCopy.add(newTower);
							break;
						}
					}
				}
			}

			// Conditions for removing projectile/enemy
			for(SkeletonProjectile skeletonProj : skeletonProjCopy) {
				if(skeletonProj.position.x < 0 || skeletonProj.position.y < 0 || skeletonProj.position.x > this.getWidth() || skeletonProj.position.y > this.getHeight()) {
					skeletonProjCopy.remove(skeletonProj);
				}
				/*
				if(skeletonProj.position.x > skeletonCoordinates.get(proj1).getX() + 200 || skeletonProj.position.x < skeletonCoordinates.get(proj1).getX() - 200 || skeletonProj.position.y > skeletonCoordinates.get(proj1).getY() + 200 || skeletonProj.position.y < skeletonCoordinates.get(proj1).getY() - 200) {
					skeletonProjCopy.remove(skeletonProj);
				}
				*/
				for(int ctr = 0; ctr < levelOne.getEnemyNumber(); ctr++) {
					if(skeletonProj.getBounds().intersects(levelOne.enemies[ctr].getBounds())) {
						levelOne.enemies[ctr].health -= 50;
						skeletonProjCopy.remove(skeletonProj);
						if(levelOne.enemies[ctr].health <=0) {
							levelOne.enemies[ctr].alive = false;
							levelOne.enemies[ctr].position = new Point(-200,-200);
							
							// Add gold based on enemy type
							int tempType = levelOne.enemies[ctr].getSpriteType();
							if (tempType == 0) {
								Main.actionPanel.addGold(25);
							}
							if (tempType == 1) {
								Main.actionPanel.addGold(40);
							}
							if (tempType == 2) {
								Main.actionPanel.addGold(75);
							}
							if (tempType == 3) {
								Main.actionPanel.addGold(95);
							}
							if (tempType == 4) {
								Main.actionPanel.addGold(135);
							}
							if (tempType == 5) {
								Main.actionPanel.addGold(500);
							}
							
							Main.effectsPlayer = new EffectsPlayer();
							Main.effectsPlayer.clip.loop(0);
						}
					}
				}
			}
			for(MageProjectile mp : mageProjCopy) {
				if(mp.position.x < 0 || mp.position.y < 0 || mp.position.x > this.getWidth() || mp.position.y > this.getHeight()) {
					mageProjCopy.remove(mp);
				}
				for(int ctr = 0; ctr < levelOne.getEnemyNumber(); ctr++) {
					if(mp.getBounds().intersects(levelOne.enemies[ctr].getBounds())) {
						levelOne.enemies[ctr].health -= 75;
						mageProjCopy.remove(mp);
						if(levelOne.enemies[ctr].health <=0) {
							levelOne.enemies[ctr].alive = false;
							levelOne.enemies[ctr].position = new Point(-200,-200);
							
							// Add gold based on enemy type
							int tempType = levelOne.enemies[ctr].getSpriteType();
							if (tempType == 0) {
								Main.actionPanel.addGold(25);
							}
							if (tempType == 1) {
								Main.actionPanel.addGold(40);
							}
							if (tempType == 2) {
								Main.actionPanel.addGold(75);
							}
							if (tempType == 3) {
								Main.actionPanel.addGold(95);
							}
							if (tempType == 4) {
								Main.actionPanel.addGold(135);
							}
							if (tempType == 5) {
								Main.actionPanel.addGold(500);
							}
							
							Main.effectsPlayer = new EffectsPlayer();
							Main.effectsPlayer.clip.loop(0);
						}
					}
				}
			}
			for(OrcProjectile op : orcProjCopy) {
				if(op.position.x < 0 || op.position.y < 0 || op.position.x > this.getWidth() || op.position.y > this.getHeight()) {
					orcProjCopy.remove(op);
				}
				for(int ctr = 0; ctr < levelOne.getEnemyNumber(); ctr++) {
					if(op.getBounds().intersects(levelOne.enemies[ctr].getBounds())) {
						levelOne.enemies[ctr].health -= 150;
						orcProjCopy.remove(op);
						if(levelOne.enemies[ctr].health <=0) {
							levelOne.enemies[ctr].alive = false;
							levelOne.enemies[ctr].position = new Point(-200,-200);
							
							// Add gold based on enemy type
							int tempType = levelOne.enemies[ctr].getSpriteType();
							if (tempType == 0) {
								Main.actionPanel.addGold(25);
							}
							if (tempType == 1) {
								Main.actionPanel.addGold(40);
							}
							if (tempType == 2) {
								Main.actionPanel.addGold(75);
							}
							if (tempType == 3) {
								Main.actionPanel.addGold(95);
							}
							if (tempType == 4) {
								Main.actionPanel.addGold(135);
							}
							if (tempType == 5) {
								Main.actionPanel.addGold(500);
							}
							
							Main.effectsPlayer = new EffectsPlayer();
							Main.effectsPlayer.clip.loop(0);
						}
					}
				}
			}
			for(DemonProjectile dep : demonProjCopy) {
				if(dep.position.x < 0 || dep.position.y < 0 || dep.position.x > this.getWidth() || dep.position.y > this.getHeight()) {
					demonProjCopy.remove(dep);
				}
				for(int ctr = 0; ctr < levelOne.getEnemyNumber(); ctr++) {
					if(dep.getBounds().intersects(levelOne.enemies[ctr].getBounds())) {
						levelOne.enemies[ctr].health -= 35;
						demonProjCopy.remove(dep);
						if(levelOne.enemies[ctr].health <=0) {
							levelOne.enemies[ctr].alive = false;
							levelOne.enemies[ctr].position = new Point(-200,-200);
							
							// Add gold based on enemy type
							int tempType = levelOne.enemies[ctr].getSpriteType();
							if (tempType == 0) {
								Main.actionPanel.addGold(25);
							}
							if (tempType == 1) {
								Main.actionPanel.addGold(40);
							}
							if (tempType == 2) {
								Main.actionPanel.addGold(75);
							}
							if (tempType == 3) {
								Main.actionPanel.addGold(95);
							}
							if (tempType == 4) {
								Main.actionPanel.addGold(135);
							}
							if (tempType == 5) {
								Main.actionPanel.addGold(500);
							}
							
							Main.effectsPlayer = new EffectsPlayer();
							Main.effectsPlayer.clip.loop(0);
						}
					}
				}
			}
			for(BeholderProjectile bp : beholderProjCopy) {
				if(bp.position.x < 0 || bp.position.y < 0 || bp.position.x > this.getWidth() || bp.position.y > this.getHeight()) {
					beholderProjCopy.remove(bp);
				}
				for(int ctr = 0; ctr < levelOne.getEnemyNumber(); ctr++) {
					if(bp.getBounds().intersects(levelOne.enemies[ctr].getBounds())) {
						levelOne.enemies[ctr].health -= 5;
						beholderProjCopy.remove(bp);
						if(levelOne.enemies[ctr].health <=0) {
							levelOne.enemies[ctr].alive = false;
							levelOne.enemies[ctr].position = new Point(-200,-200);
							
							// Add gold based on enemy type
							int tempType = levelOne.enemies[ctr].getSpriteType();
							if (tempType == 0) {
								Main.actionPanel.addGold(25);
							}
							if (tempType == 1) {
								Main.actionPanel.addGold(40);
							}
							if (tempType == 2) {
								Main.actionPanel.addGold(75);
							}
							if (tempType == 3) {
								Main.actionPanel.addGold(95);
							}
							if (tempType == 4) {
								Main.actionPanel.addGold(135);
							}
							if (tempType == 5) {
								Main.actionPanel.addGold(500);
							}
							
							Main.effectsPlayer = new EffectsPlayer();
							Main.effectsPlayer.clip.loop(0);
						}
					}
				}
			}
			for(DragonProjectile drp : dragonProjCopy) {
				if(drp.position.x < 0 || drp.position.y < 0 || drp.position.x > this.getWidth() || drp.position.y > this.getHeight()) {
					dragonProjCopy.remove(drp);
				}
				for(int ctr = 0; ctr < levelOne.getEnemyNumber(); ctr++) {
					if(drp.getBounds().intersects(levelOne.enemies[ctr].getBounds())) {
						levelOne.enemies[ctr].health -= 325;
						dragonProjCopy.remove(drp);
						if(levelOne.enemies[ctr].health <=0) {
							levelOne.enemies[ctr].alive = false;
							levelOne.enemies[ctr].position = new Point(-200,-200);
							
							// Add gold based on enemy type
							int tempType = levelOne.enemies[ctr].getSpriteType();
							if (tempType == 0) {
								Main.actionPanel.addGold(25);
							}
							if (tempType == 1) {
								Main.actionPanel.addGold(40);
							}
							if (tempType == 2) {
								Main.actionPanel.addGold(75);
							}
							if (tempType == 3) {
								Main.actionPanel.addGold(95);
							}
							if (tempType == 4) {
								Main.actionPanel.addGold(135);
							}
							if (tempType == 5) {
								Main.actionPanel.addGold(500);
							}
							
							Main.effectsPlayer = new EffectsPlayer();
							Main.effectsPlayer.clip.loop(0);
						}
					}
				}
			}
			
			for(AnimatedSprite as : levelOne.enemies) { // Conditions for losing a life
				if(as.getBounds().intersects(new Rectangle(720,578,25,25))) { // Death box
					as.alive = false;
					as.position = new Point(-200,-200);
					Main.actionPanel.loseLife();
				}
			}
			 	
			// Checks if all enemies from current level are dead
			levelDone = true;
			for(AnimatedSprite animS : levelOne.enemies) {
				if(animS.alive == true) {
					levelDone = false;
				}
			}
			
			if(levelDone) {
				nextLevel = true;
				repaint();
				if(!incremented) {
					Main.actionPanel.incrementWave();
					incremented = true;
					if(Main.actionPanel.wavesCompleted == 20) { // Max level
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
					if(level == 5) levelOne = levelSix;
					if(level == 6) levelOne = levelSeven;
					if(level == 7) levelOne = levelEight;
					if(level == 8) levelOne = levelNine;
					if(level == 9) levelOne = levelTen;
					if(level == 10) levelOne = levelEleven;
					if(level == 11) levelOne = levelTwelve;
					if(level == 12) levelOne = levelThirteen;
					if(level == 13) levelOne = levelFourteen;
					if(level == 14) levelOne = levelFifteen;
					if(level == 15) levelOne = levelSixteen;
					if(level == 16) levelOne = levelSeventeen;
					if(level == 17) levelOne = levelEighteen;
					if(level == 18) levelOne = levelNineteen;
					if(level == 19) levelOne = levelTwenty;
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
