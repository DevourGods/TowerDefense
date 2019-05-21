package towers;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import main.AnimatedSprite;

public class SkeletonProjectile extends AnimatedSprite{

	public SkeletonProjectile(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/SkeletonProjectile2.png",1,1,17,17);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)position.x+4, (int) position.y+4,
				1, 1);
	}

}
