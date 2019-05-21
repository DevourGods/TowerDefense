package monsters;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MonsterDarkHarpy extends MonsterBee{
	
	public MonsterDarkHarpy(JPanel _frame, Graphics2D _g2, int _spriteNum) {
		super(_frame, _g2, _spriteNum);
		load("/resources/DarkHarpy.png", 3, 12, 64, 64);
	}
}
