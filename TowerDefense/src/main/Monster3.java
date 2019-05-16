package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Monster3 extends Monster{
	
	public Monster3(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/monster3.png", 3, 12, 40, 40);
	}

}
