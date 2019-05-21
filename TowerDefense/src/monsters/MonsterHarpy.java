package monsters;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MonsterHarpy extends MonsterBee{
	
	public MonsterHarpy(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/Harpy.png", 3, 12, 64, 64);
	}
}
