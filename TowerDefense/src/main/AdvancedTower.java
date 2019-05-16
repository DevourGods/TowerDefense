package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class AdvancedTower extends Tower{

	public AdvancedTower(JPanel _frame, Graphics2D _g2, int x, int y) {
		super(_frame, _g2, x, y);
		load("/resources/tower_two.png",1,1,25,30);
	}
}
