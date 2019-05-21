package monsters;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class MonsterWerebat extends MonsterBee{
	
	public MonsterWerebat(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/Werebat.png", 3, 12, 64, 64);
	}
}
