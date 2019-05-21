package towers;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DragonProjectile extends SkeletonProjectile {

	public DragonProjectile(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/DragonProjectile.png",1,1,20,20);
	}
}
