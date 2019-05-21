package towers;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class OrcProjectile extends SkeletonProjectile{

	public OrcProjectile(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/OrcProjectile.png",1,1,25,25);
	}
}
