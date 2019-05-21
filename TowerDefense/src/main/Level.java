package main;

import java.util.ArrayList;
import monsters.MonsterBee;
import monsters.MonsterBoss;
import monsters.MonsterDarkHarpy;
import monsters.MonsterHarpy;
import monsters.MonsterSiren;
import monsters.MonsterWerebat;

public class Level {
	
	private int enemyNumber; 
	public AnimatedSprite[] enemies;
	public int goneBy = 0; 
	
	public Level(ArrayList<Integer> enemyAmount, ArrayList<AnimatedSprite> enemyType, ArrayList<Integer> health, ArrayList<Integer> type, int enemyNumber) {
		int numTypes = enemyAmount.size();
		int count = 0;
		int cnt = 0;
		int previousTotal = 0;
		this.enemyNumber = enemyNumber;
		enemies = new AnimatedSprite[enemyNumber];
		while (count < numTypes)
		{
			while (cnt < enemyAmount.get(count) + previousTotal)
			{
				if(type.get(count) == 0)
					enemies[cnt] = new MonsterBee(enemyType.get(count).getJFrame(), enemyType.get(count).getGraphics(), 0);
				if(type.get(count) == 1)
					enemies[cnt] = new MonsterWerebat(enemyType.get(count).getJFrame(), enemyType.get(count).getGraphics(), 1);
				if(type.get(count) == 2)
					enemies[cnt] = new MonsterSiren(enemyType.get(count).getJFrame(), enemyType.get(count).getGraphics(), 2);
				if(type.get(count) == 3)
					enemies[cnt] = new MonsterHarpy(enemyType.get(count).getJFrame(), enemyType.get(count).getGraphics(), 3);
				if(type.get(count) == 4)
					enemies[cnt] = new MonsterDarkHarpy(enemyType.get(count).getJFrame(), enemyType.get(count).getGraphics(), 4);
				if(type.get(count) == 5)
					enemies[cnt] = new MonsterBoss(enemyType.get(count).getJFrame(), enemyType.get(count).getGraphics(), 5);
				enemies[cnt].frameDelay = 25;
				enemies[cnt].health = health.get(count);
				cnt++;
			}
			previousTotal = cnt;
			count++;
		}
	}
	
	public boolean anyAlive() {
		for(AnimatedSprite as : enemies) {
			if(as.alive) return true;
		}
		return false;
	}
	
	public int getEnemyNumber() {return enemyNumber;}
	public AnimatedSprite[] getEnemyArray() {return enemies;}
}