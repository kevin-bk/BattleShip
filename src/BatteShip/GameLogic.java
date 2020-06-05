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
	
	public void setRandom() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				if (i % 2 == 0) playerMap.isShip[i][j] = true;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		frame.setVisible(false);
		setRandom();
		new Play(1120,680,playerMap);
	}
	
	public static void main(String args[]) {
		new GameLogic(1140,690);
	}

}
