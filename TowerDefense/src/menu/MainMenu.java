package menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;
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
	private ImageButton aboutBTN;
	private ImageButton quitBTN;
	private ImageButton backBTN;
	
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
		aboutBTN = new ImageButton("/resources/AboutButton.png", true, 0.7, false);
		quitBTN = new ImageButton("/resources/QuitButton.png", true, 0.7, false);
		backBTN = new ImageButton("/resources/BackButton.png", true, 0.7, false);
		
		CreateFont zombieFont = new CreateFont("/resources/Zombie_Holocaust.ttf", 120);

		title.setFont(zombieFont.getFont());
		title.setForeground(new Color(100, 12, 0));
		
		// Adds compenents to panel
		menu.add(Box.createRigidArea(new Dimension(0, 40))); // Spacing
		menu.add(title);
		menu.add(Box.createRigidArea(new Dimension(0, 70)));
		menu.add(startBTN);
		menu.add(Box.createRigidArea(new Dimension(0, 50)));
		menu.add(aboutBTN);
		menu.add(Box.createRigidArea(new Dimension(0, 50)));
		menu.add(quitBTN);
		
		
		// Adds actionListeners
		startBTN.addActionListener(this);
		aboutBTN.addActionListener(this);
		quitBTN.addActionListener(this);
		
		// Adds panel to frame
		add(menu);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startBTN) {
			frame.dispose();
			main.Main.startGame();
			
		} else if (e.getSource() == aboutBTN) {
			MenuPanel about = new MenuPanel("/resources/Menu-Small.png");
			about.setLayout(new BoxLayout(about, BoxLayout.Y_AXIS));
		
			CreateFont zombieFont = new CreateFont("/resources/Zombie_Holocaust.ttf", 120);
			OutlineLabel title = new OutlineLabel("Undead Defense", true, Color.BLACK);
			title.setFont(zombieFont.getFont());
			title.setForeground(new Color(100, 12, 0));
			
			CreateFont bf2 = new CreateFont("/resources/BreatheFire2.ttf", 40);
			String s = "Undead Defense is a tower defense game. Your goal is to stop the monster girls from getting to your base by placing undead creatures around the map. You purchase towers using gold acquired from killing enemies. If an enemy gets through, you lose a life. If you run out of lives, or let the boss through, you lose. Good Luck!";
			JTextArea text = new JTextArea();
			text.setMaximumSize(new Dimension(1000, 300));
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			text.setText(s);
			text.setFont(bf2.getFont());
			text.setForeground(Color.BLACK);//new Color(100, 12, 0));
			text.setBackground(Color.GRAY);
			text.setEditable(false);
			
			// Adding components to about panel
			about.add(Box.createRigidArea(new Dimension(0, 40))); // Spacing
			about.add(title);
			about.add(Box.createRigidArea(new Dimension(0, 70)));
			about.add(text);
			about.add(Box.createRigidArea(new Dimension(0, 70)));
			about.add(backBTN);
			
			backBTN.addActionListener(this);
			
			// Setting panel to JFrame
			frame.setContentPane(about);
			
		} else if (e.getSource() == quitBTN) {
			frame.dispose();
		} else if (e.getSource() == backBTN) {
			frame.setContentPane(menu);
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