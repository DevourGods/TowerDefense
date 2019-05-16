package main;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Monster extends AnimatedSprite {
	
	public final static int STATE_WALK_RIGHT = 0;
	public final static int STATE_WALK_LEFT = 1;
	public final static int STATE_WALK_TOP = 2;
	public final static int STATE_WALK_BOTTOM = 3;
	public int state;
	
	private Point[] turnRight = {new Point(50, 194), new Point(242, 50), new Point(146, 578), new Point(290, 482), new Point(434, 578)};
	private Point[] turnLeft = {new Point(626, 194), new Point(434, 338)};
	private Point[] turnTop = {new Point(242, 194), new Point(290, 578)};
	private Point[] turnBottom = {new Point(626, 50), new Point(434, 194), new Point(146, 338), new Point(434, 482)};
	private Point[] death = {new Point(720,578)};
	

	public Monster(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/monster1.png", 3, 12, 47, 35);
		state = STATE_WALK_BOTTOM;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public synchronized void update() {
		findPath();
		// totalFrames = 3;
		if(state == STATE_WALK_BOTTOM && currentFrame != 0 && currentFrame !=1 && currentFrame !=2) currentFrame = 0;
		if(state == STATE_WALK_RIGHT && currentFrame  !=6 && currentFrame !=7 && currentFrame !=8) currentFrame = 6;
		if(state == STATE_WALK_TOP && currentFrame  !=7 && currentFrame != 8 && currentFrame !=9) currentFrame = 9;
		if(state == STATE_WALK_LEFT && currentFrame !=3 && currentFrame !=4 && currentFrame != 5) currentFrame = 3;
		super.update();
	}
	
	public void findPath() {
		for(Point p : turnRight) {
			if(p.equals(position)) {
				velocity = new Point(1,0);
				state = STATE_WALK_RIGHT;
			}
		}
		for(Point p : turnLeft) {
			if(p.equals(position)) {
				velocity = new Point(-1,0);
				state = STATE_WALK_LEFT;
			}
		}
		for(Point p : turnTop) {
			if(p.equals(position)) {
				velocity = new Point(0,-1);
				state = STATE_WALK_TOP;
			}
		}
		for(Point p : turnBottom) {
			if(p.equals(position)) {
				velocity = new Point(0,1);
				state = STATE_WALK_BOTTOM;
			}
		}
		for(Point p : death) {
			if(p.equals(position)) {
				alive = false;
			}
		}
	}
	
}