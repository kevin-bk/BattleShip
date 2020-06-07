package BatteShip;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameLogic implements ActionListener {
	private SmallMap playerMap;
	private SmallMap computerMap;
	private MainMenu menu;
	private JFrame frame;
	private Creator creator;
	
	public GameLogic(int w, int h) {
		frame = new JFrame("Battle Ship") ;
		frame.setSize(w, h);
		
		creator = new Creator(1120,690,3,3,3);
		creator.frame.setVisible(false);
		
		menu = new MainMenu(w,h);
		playerMap = new SmallMap(w/2,h);
		computerMap = new SmallMap(w/2,h);
		
		frame.add(menu);
		menu.play.setActionCommand("play");
		menu.play.addActionListener(this);
		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		frame.setVisible(false);
		menu.setVisible(false);
		frame.remove(menu);
		frame.setVisible(false);
		creator.frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		new GameLogic(1120,690);
	}

}
