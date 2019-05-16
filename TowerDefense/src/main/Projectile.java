package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Projectile extends AnimatedSprite{

	public Projectile(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/fireball.png",1,1,10,10);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)position.x+4, (int) position.y+4,
				1, 1);
	}

}
