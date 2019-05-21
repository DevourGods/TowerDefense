package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class KeyHandler implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keypress");
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ESCAPE) {
			System.out.println("test");
			int input = JOptionPane.showConfirmDialog(null, "Exit to main menu?");
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
