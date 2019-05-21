package monsters;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MonsterHarpy extends MonsterBee{
	
	public MonsterHarpy(JPanel _frame, Graphics2D _g2, int _spriteNum) {
		super(_frame, _g2, _spriteNum);
		load("/resources/Harpy.png", 3, 12, 64, 64);
	}
}
