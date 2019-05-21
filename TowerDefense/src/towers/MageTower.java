package towers;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MageTower extends SkeletonTower{

	public MageTower(JPanel _frame, Graphics2D _g2, int x, int y) {
		super(_frame, _g2, x, y);
		load("/resources/Mage.png",1,1,48,48);
	}
}
