package BatteShip;

import java.awt.Color;
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
	
	public GameLogic(int w, int h) {
		frame = new JFrame("Battle Ship") {
			
		};
		
		frame.setSize(w, h);
//		this.setBackground(Color.blue);
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
//		menu.setVisible(false);
//		frame.remove(menu);
//
//		playerMap.setVisible(true);
//		computerMap.setVisible(true);
//
//		frame.add(playerMap);
//		frame.add(computerMap);
//		frame.setLayout(new GridLayout(1,2,40,10));
		frame.setVisible(false);
		new Play(1120,680);
	}
	
	public static void main(String args[]) {
		new GameLogic(1140,690);
	}

}
