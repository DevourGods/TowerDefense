package main;

import java.util.*;

import enemy.Enemy;
import enemy.Goblin;

public class LevelDatabase 
{
	ArrayList<ArrayList<Enemy>> levels = new ArrayList<ArrayList<Enemy>>();
	
	public LevelDatabase()
	{	
		ArrayList<Enemy> dummy = null;
		levels.add(dummy);
		
		ArrayList<Enemy> L1 = new ArrayList<Enemy>(Arrays.asList( new Goblin(5)  ));
		levels.add(L1);
	}
	
	public ArrayList<Enemy> returnLevel(int level)
	{
		return levels.get(level);
	}
}
