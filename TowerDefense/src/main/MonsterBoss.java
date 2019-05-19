package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MonsterBoss extends MonsterBee {
	
	public MonsterBoss(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/Boss.png", 3, 12, 64, 64);
	}
}
