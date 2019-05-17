package main;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class Monster2 extends Monster{
	
	public final static int x = 0;
	public final static int y = 0;
	
	private Point[] turnRight = {new Point(40, 194+y), new Point(242+x, 50+y), new Point(146+x, 578+y), new Point(290+x, 482+y), new Point(434+x, 578+y)};
	private Point[] turnLeft = {new Point(626+x, 194+y), new Point(434+x, 338+y)};
	private Point[] turnTop = {new Point(242+x, 194+y), new Point(290+x, 578+y)};
	private Point[] turnBottom = {new Point(626+x, 50+y), new Point(434+x, 194+y), new Point(146+x, 338+y), new Point(434+x, 482+y)};
	private Point[] death = {new Point(720+x,578+y)};
	
	public Monster2(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/monster2.png", 3, 12, 43, 40);
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
