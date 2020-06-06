package BatteShip;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Play extends Container implements ActionListener {
	public SmallMap playerMap; // bản đồ của người chơi -> máy bắn trên này
	public SmallMap computerMap; // bản đồ của computer -> người chơi bắn trên này
	public JPanel panel1; // panel1 chứa 2 bản đồ
	public JPanel panel2; // panel2 chứa thông tin chơi (turn,...)
	public Container cn; // cn chứa panel1 và panel2
	public JLabel turn; // hiển thị turn của player hoặc computer

	private ImageIcon miss; // bắn trượt
	private ImageIcon hit; // bắn trúng
	private ImageIcon dead; // tàu bị phá hoàn toàn

	public Play(int w, int h, SmallMap player, SmallMap computer) {
		super();

		// add map và thông tin
		panel1 = new JPanel();
		playerMap = player;
		computerMap = computer;
		
		panel1.setLayout(new GridLayout(1, 2, 20, 10));
		panel1.add(computerMap);
		panel1.add(playerMap);

		panel1.setBounds(0, 120, w - 15, h - 160);

		panel2 = new JPanel();
		turn = new JLabel("Player Turn");
		panel2.add(turn);
		panel2.setSize(w, 120);
		addAction();

		this.add(panel2);
		this.add(panel1, "North");
		this.setSize(w, h);
		
		// tạo image icon 
		hit = new ImageIcon(loadImage("src\\img\\hit.png",w/20,56));
		miss = new ImageIcon(loadImage("src\\img\\miss.png",w/20,56));
		dead = new ImageIcon(loadImage("src\\img\\Dead.png",w/20,56));
		
	}

	private void addAction() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				playerMap.mapPiece[i][j].setActionCommand("" + (i * 100 + j));
				playerMap.mapPiece[i][j].addActionListener(this);

				computerMap.mapPiece[i][j].setActionCommand("" + (i * 10000 + j));
				computerMap.mapPiece[i][j].addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int x = Integer.parseInt(e.getActionCommand());
		if (x < 2000) { // playerMap
			int i = x / 100;
			int j = x % 100;
//			JOptionPane.showMessageDialog(this, "clicked PlayerMap" + i + " " + j + " " + x);
//			playerMap.mapPiece[i][j].setIcon(hit);
			if (playerMap.isShip[i][j]) playerMap.mapPiece[i][j].setIcon(hit);
			else playerMap.mapPiece[i][j].setIcon(miss);
		} else { // computerMap
			int i = x / 10000;
			int j = x % 10000;
//			JOptionPane.showMessageDialog(this, "clicked computerMap" + i + " " + j + " " + x);
			if (computerMap.isShip[i][j]) computerMap.mapPiece[i][j].setIcon(hit);
			else computerMap.mapPiece[i][j].setIcon(miss);
		}

	}
	
	private Image loadImage(String s, int w, int h) {
		BufferedImage i = null; // doc anh duoi dang Buffered Image
		try {
			i = ImageIO.read(new File(s));
		} catch (Exception e) {
			System.out.println("Duong dan anh k hop le!");
		}

		Image dimg = i.getScaledInstance(w, h, Image.SCALE_SMOOTH); // thay doi kich thuoc anh
		return dimg;

	}
}
