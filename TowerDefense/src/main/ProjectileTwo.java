package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ProjectileTwo extends Projectile{
	
	public ProjectileTwo(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/iceball.png",1,1,28,28);
	}
}
