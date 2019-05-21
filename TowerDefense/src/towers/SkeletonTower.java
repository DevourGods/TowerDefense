package towers;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import main.AnimatedSprite;

public class SkeletonTower extends AnimatedSprite {
	
	protected int range = 100;
	protected Rectangle fireBounds;

	public SkeletonTower(JPanel _frame, Graphics2D _g2, int x, int y) {
		super(_frame, _g2);
		this.position.x = x;
		this.position.y = y;
		load("/resources/Skeleton.png",1,1,48,48);
		fireBounds = new Rectangle(position.x-range, position.y-range, range*2, range*2);
	}
	
	public int getRange() {return range;}
	public Rectangle getFireBounds() {return fireBounds;}

}
