package BatteShip;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PlayGame extends Container implements ActionListener {
	public SmallMap playerMap; // bản đồ của người chơi -> máy bắn trên này
	public SmallMap computerMap; // bản đồ của computer -> người chơi bắn trên này
	public JPanel panel1; // panel1 chứa 2 bản đồ
	public JPanel panel2; // panel2 chứa thông tin chơi (turn,...)
	public Container cn; // cn chứa panel1 và panel2
	public JLabel turn; // hiển thị turn của player hoặc computer

	private ImageIcon miss; // bắn trượt
	private ImageIcon hit; // bắn trúng
	private ImageIcon dead; // tàu bị phá hoàn toàn

	private static int playerHit, computerHit; // số điểm bắn trúng hiện tại
	private static int sumPoint; // tổng số điểm ship trên mỗi map
	private static boolean isPlayer; // turn của player hay của computer
	private static boolean[][] markP, markC; // markP : đánh dấu điểm đã bắn trên computerMap, ngược lại với markC
	private static boolean isHard; // chế độ khó
	private static Queue<String> Q = new LinkedList<>();
	
	private static ArrayList<String> A;	// ứng với bản đồ của máy chơi
	private static ArrayList<String> B; // ứng với bản đồ của người chơi

	public PlayGame(int w, int h, SmallMap player, SmallMap computer, boolean gamemode, ArrayList <String> pl, ArrayList <String> cm) {
		super();
		
		if (gamemode) isHard = true;
		else isHard = false;
		A = pl;
		B = cm;
		
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
//		panel2.add(turn);
		
		panel2.setSize(w, 120);
		addAction(); // tạo listener trên button

		this.add(panel2);
		this.add(panel1, "North");
		this.setSize(w, h);

		// tạo image icon
		hit = new ImageIcon(loadImage("src\\img\\hit.png", w / 20, 56));
		miss = new ImageIcon(loadImage("src\\img\\miss2.png", w / 20, 56));
		dead = new ImageIcon(loadImage("src\\img\\Dead.png", w / 20, 56));

		init();
	}

	private void init() {
		int cnt = 0;
		markP = new boolean[11][11];
		markC = new boolean[11][11];
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				if (playerMap.isShip[i][j])
				cnt++;
				markP[i][j] = false;
				markC[i][j] = false;
			}
		}
		sumPoint = cnt;
		playerHit = 0;
		computerHit = 0;
		isPlayer = true;

	}

	private boolean shot(int i, int j) {
		markC[i][j] = true;
		if (playerMap.isShip[i][j]) {
			playerMap.mapPiece[i][j].setIcon(hit);
			computerHit++;
//			System.out.println("Computer: " + computerHit);
			return true;
		} else
			playerMap.mapPiece[i][j].setIcon(miss);

		return false;
	}

	private void hitRandom() {
//		if (isPlayer) return;
		Random rd = new Random();
		int i = rd.nextInt(10) + 1;
		int j = rd.nextInt(10) + 1;
		if (!markC[i][j]) {
			shot(i, j);
			isPlayer = true;
		} else
			hitRandom();
	}

	// random cho chế độ Hard
	private void hitRandomHard() {
		if (!Q.isEmpty()) {
			int x = Integer.parseInt(Q.peek());
			int i = x / 100;
			int j = x % 100;
//			System.out.println("Queue " + i + " " + j);
			Q.remove();
			boolean c = shot(i, j);
			if (c) {
				if (i > 1 && i < 10 && j > 1 && j < 10) {
					if (!markC[i][j + 1])
						Q.add("" + (i * 100 + j + 1));
					if (!markC[i][j - 1])
						Q.add("" + (i * 100 + j - 1));
					if (!markC[i - 1][j])
						Q.add("" + ((i - 1) * 100 + j));
					if (!markC[i + 1][j])
						Q.add("" + ((i + 1) * 100 + j));
				}
			}
			isPlayer = true;
			return;
		}
		Random rd = new Random();
		int i = rd.nextInt(10) + 1;
		int j = rd.nextInt(10) + 1;
//		System.out.println("Random " + i + " " + j);
		if (!markC[i][j]) {
			boolean c = shot(i, j);
			if (c) {
				if (i > 1 && i < 10 && j > 1 && j < 10) {
					if (!markC[i][j + 1]) {
						Q.add("" + (i * 100 + j + 1));
//						System.out.println("add " + i + (j + 1));
					}
					if (!markC[i][j - 1]) {
						Q.add("" + (i * 100 + j - 1));
//						System.out.println("add " + i + (j-1));
					}
					if (!markC[i - 1][j]) {
						Q.add("" + ((i - 1) * 100 + j));
//						System.out.println("add " + (i-1) + j);
					}
					if (!markC[i + 1][j]) {
						Q.add("" + ((i + 1) * 100 + j));
//						System.out.println("add " + (i+1) + j);
					}
				}
			}
			isPlayer = true;
			return;
		}
		else hitRandom();
	}

	private void addAction() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				computerMap.mapPiece[i][j].setActionCommand("" + (i * 100 + j));
				computerMap.mapPiece[i][j].addActionListener(this);
			}
		}
	}
	
	public boolean isPlayerWin() {
		if (playerHit == sumPoint) return true;
		return false;
	}
	
	public boolean isComputerWin() {
		if (computerHit == sumPoint) return true;
		return false;
	}
	
	public void checkDeadOnComputerMap() {
		for (String s : B) {
			if (s == "") continue;
			int x = Integer.parseInt(s);
			int leng = x/10000;
			int j = (x - leng*10000)/100;
			int i = x % 100;
			boolean c = true;
//			System.out.println(leng + " " + i + " " + j);
			for (int t = 0; t < leng; t++) {
				if (!markP[i][j + t]) c = false;
//				System.out.println("isShip[" + i + "," + (j+t) + " = " + computerMap.isShip[i][j + t]);
			}
			if (!c) continue;
//			System.out.println("Dead");
			for (int t = 0; t < leng; t++) {
				computerMap.mapPiece[i][j + t].setIcon(new ImageIcon(loadImage("src\\img\\" + leng + (t+1) + ".png", 56,56)));
			}
		}
	}
	
	
	public void checkDeadOnPlayerMap() {
		for (String s : A) {
			if (s == "") continue;
			int x = Integer.parseInt(s);
			int leng = x/10000;
			int j = (x - leng*10000)/100;
			int i = x % 100;
			boolean c = true;
//			System.out.println(leng + " " + i + " " + j);
			for (int t = 0; t < leng; t++) {
				if (!markC[i][j + t]) c = false;
//				System.out.println("isShip[" + i + "," + (j+t) + " = " + computerMap.isShip[i][j + t]);
			}
			if (!c) continue;
//			System.out.println("Dead");
			for (int t = 0; t < leng; t++) {
				playerMap.mapPiece[i][j + t].setIcon(new ImageIcon(loadImage("src\\img\\" + leng + (t+1) + ".png", 56,56)));
			}
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (!isPlayer)
			return;
		int x = Integer.parseInt(e.getActionCommand());
		int i = x / 100;
		int j = x % 100;
		if (markP[i][j]) {
			return;
		}
		markP[i][j] = true;
		if (computerMap.isShip[i][j]) {
			computerMap.mapPiece[i][j].setIcon(hit);
			playerHit++;
			checkDeadOnComputerMap();
//			System.out.println("Player: " + playerHit);
		} else
			computerMap.mapPiece[i][j].setIcon(miss);
		isPlayer = false;
		
		
		if (isPlayerWin()) {
			JOptionPane.showMessageDialog(this, "You Win!!!");
		}
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (isHard) hitRandomHard();
		else hitRandom();
		
		checkDeadOnPlayerMap();
		if (isComputerWin()) {
			JOptionPane.showMessageDialog(this, "You Lose!!!");
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
