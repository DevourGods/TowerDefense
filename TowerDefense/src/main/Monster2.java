package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Monster2 extends Monster{
	
	public Monster2(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/monster2.png", 3, 12, 43, 40);
	}

}
