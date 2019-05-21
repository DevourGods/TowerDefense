package menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;
import main.Main;
import custom.CreateFont;
import custom.ImageButton;
import custom.MenuPanel;
import custom.OutlineLabel;

public class MainMenu extends JPanel implements ActionListener
{
	public static final long serialVersionUID = 1L;
	public static JFrame frame;
	private static Dimension frameSize = new Dimension(1280, 720);
	public final static int w = 1280;
	public final static int h = 720;
	
	private MenuPanel menu;
	
	private ImageButton startBTN;
	private ImageButton optionsBTN;
	private ImageButton quitBTN;
	
	public MainMenu()
	{	
		setLayout(new BorderLayout());
		startMenu();
	}
	
	public void startMenu()
	{	
		menu = new MenuPanel("/resources/Menu-Small.png");
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		URL iconURL = getClass().getResource("/resources/Icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		frame.setIconImage(icon.getImage());
		
		OutlineLabel title = new OutlineLabel("Undead Defense", true, Color.BLACK);
		startBTN = new ImageButton("/resources/StartButton.png", true, 0.7, false);
		optionsBTN = new ImageButton("/resources/OptionsButton.png", true, 0.7, false);
		quitBTN = new ImageButton("/resources/QuitButton.png", true, 0.7, false);
		
		CreateFont zombieFont = new CreateFont("/resources/Zombie_Holocaust.ttf", 120);

		title.setFont(zombieFont.getFont());
		title.setForeground(new Color(100, 12, 0));
		
		// Adds compenents to panel
		menu.add(Box.createRigidArea(new Dimension(0, 40))); // Spacing
		menu.add(title);
		menu.add(Box.createRigidArea(new Dimension(0, 70)));
		menu.add(startBTN);
		menu.add(Box.createRigidArea(new Dimension(0, 50)));
		menu.add(optionsBTN);
		menu.add(Box.createRigidArea(new Dimension(0, 50)));
		menu.add(quitBTN);
		
		
		// Adds actionListeners
		startBTN.addActionListener(this);
		optionsBTN.addActionListener(this);
		quitBTN.addActionListener(this);
		
		// Adds panel to frame
		add(menu);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startBTN) {
			MainMenu.frame.dispose();
			main.Main.startGame();
			
		} else if (e.getSource() == optionsBTN) {
			MenuPanel options = new MenuPanel("/resources/Menu-Small.png");
			frame.setContentPane(options);
		} else if (e.getSource() == quitBTN) {
			frame.dispose();
		}
	}
	
	// Main Menu
	public static void main(String[] args)
    {
		frame = new JFrame("Undead Defense");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setSize(frameSize);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new MainMenu());
        frame.setVisible(true);
        frame.setResizable(false);
    }
}