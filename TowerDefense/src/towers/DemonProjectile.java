package towers;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DemonProjectile extends SkeletonProjectile {
	public DemonProjectile(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/DemonProjectile.png",1,1,20,20);
	}
}
