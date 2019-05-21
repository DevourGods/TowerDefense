package towers;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class BeholderTower extends SkeletonTower{
	
	public BeholderTower(JPanel _frame, Graphics2D _g2, int x, int y) {
		super(_frame, _g2, x, y);
		load("/resources/Beholder.png",1,1,48,48);
	}
}
