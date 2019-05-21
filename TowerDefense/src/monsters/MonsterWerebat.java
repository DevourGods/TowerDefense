package monsters;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class MonsterWerebat extends MonsterBee {
	
	public MonsterWerebat(JPanel _frame, Graphics2D _g2, int _spriteNum) {
		super(_frame, _g2, _spriteNum);
		load("/resources/Werebat.png", 3, 12, 64, 64);
	}
}
