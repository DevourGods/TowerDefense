package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Skeleton extends Animate
{
	public Skeleton(JPanel _frame, Graphics2D _g2)
	{
		super(_frame, _g2);
		load("Skeleton.png", 3, 12, 48, 48);
	}
}
