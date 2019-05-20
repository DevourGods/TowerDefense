package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DragonTower extends SkeletonTower{

	public DragonTower(JPanel _frame, Graphics2D _g2, int x, int y) {
		super(_frame, _g2, x, y);
		load("/resources/Dragon.png",1,1,48,48);
	}
}
