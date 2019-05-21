package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start implements ActionListener
{
	
	public void actionPerformed(ActionEvent e)
	{	
		MainMenu.frame.dispose();
		main.Main.startGame();
		
	}
}
