package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class OrcTower extends SkeletonTower{

	public OrcTower(JPanel _frame, Graphics2D _g2, int x, int y) {
		super(_frame, _g2, x, y);
		load("/resources/Orc.png",1,1,48,48);
	}
}
