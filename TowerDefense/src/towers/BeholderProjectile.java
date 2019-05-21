package towers;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class BeholderProjectile extends SkeletonProjectile {

	public BeholderProjectile(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/BeholderProjectile.png",1,1,25,25);
	}
}
