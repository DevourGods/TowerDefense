package main;

import java.awt.Point;

public class Level {
	
	private int enemyNumber;
	private AnimatedSprite enemyType; 
	private int health;
	public AnimatedSprite[] enemies;
	public int goneBy = 0; 
	private int type = 0;
	
	public Level(int enemyNumber, AnimatedSprite enemyType, int health, int type) {
		this.enemyNumber = enemyNumber;
		this.enemyType = enemyType;
		this.type = type;
		enemies = new AnimatedSprite[enemyNumber];
		for(int i = 0; i < enemyNumber; i++) {
			if(type == 0) enemies[i] = new Monster(enemyType.getJFrame(), enemyType.getGraphics());
			if(type == 1) enemies[i] = new Monster2(enemyType.getJFrame(), enemyType.getGraphics());
			if(type == 2) enemies[i] = new Monster3(enemyType.getJFrame(), enemyType.getGraphics());
			if(type == 3) enemies[i] = new Monster4(enemyType.getJFrame(), enemyType.getGraphics());
			if(type == 4) enemies[i] = new Monster5(enemyType.getJFrame(), enemyType.getGraphics());
			enemies[i].frameDelay = 25;
			enemies[i].health = health;
		}
	}
	
	public boolean anyAlive() {
		for(AnimatedSprite as : enemies) {
			if(as.alive) return true;
		}
		return false;
	}
	
	public int getEnemyNumber() {return enemyNumber;}
	public AnimatedSprite getAnimatedSprite() {return enemyType;}
	public int getHealth() {return health;}
	public AnimatedSprite[] getEnemyArray() {return enemies;}
}
