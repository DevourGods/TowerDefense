package monsters;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MonsterSiren extends MonsterBee{
	
	public MonsterSiren(JPanel _frame, Graphics2D _g2) {
		super(_frame, _g2);
		load("/resources/Siren.png", 3, 12, 64, 64);
	}
}
