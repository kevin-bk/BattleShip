package BatteShip;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu implements ActionListener, MouseListener {
	private JFrame frame;
	
	private JLabel welcome; // nền khi bắt đầu game
	private ImageIcon introIcon; // nền khi vào game
	public JButton play; // kèm nền khi vào game

	private JLabel menu; // phần menu tiếp theo (gồm các thành phần bên dưới)
	private JButton exit;
	private JButton gamemode;
	private JButton start;
	private JButton highScore;
	private JButton sound;
	
	private JLabel select; // phần menu thứ 3, khi ấn start sẽ hiện ra để chọn số lượng tàu
	private JLabel ship[]; // mảng chứa các hình ảnh tàu
	private JButton numShip[]; 
	private JButton back,next;
	public Creator creator;

	// khởi tạo Menu ban đầu
	public MainMenu(int w, int h) {
		frame = new JFrame();
		frame.setSize(w, h);
		welcome = new JLabel();
		welcome.setSize(w, h);
		play = new JButton();
		play.setSize(30, 20);
		play.setActionCommand("Play");
		play.addActionListener(this);
		ImageIcon playIcon = new ImageIcon(loadImage("src\\img\\play.jpg", 80, 50));
		play.setIcon(playIcon);
		play.setBounds(w / 2 - 40, 3 * h / 4, 80, 50);
//		this.setLayout(new BorderLayout());
		introIcon = new ImageIcon(loadImage("src\\img\\Title.png", w, h));
		welcome.add(play);
		welcome.setIcon(introIcon);
		welcome.addMouseListener(this);
		frame.add(welcome);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	// menu chứa các lựa chọn: exit, start, sound, highScore,...
	private void menu2() {
		menu = new JLabel();
		menu.setSize(1120, 690);
		menu.setIcon(new ImageIcon(loadImage("src\\img\\background.png", 1120, 690)));
		// nút start
		start = new JButton();
		start.setBounds(485, 511, 120, 65);
		start.setIcon(new ImageIcon(loadImage("src\\img\\buttonStart.png", 120, 65)));
		start.setActionCommand("start");
		start.addActionListener(this);
		// nút exit
		exit = new JButton();
		exit.setBounds(804, 511, 78, 65);
		exit.setIcon(new ImageIcon(loadImage("src\\img\\exitRight.png", 78, 65)));
		exit.setActionCommand("exit");
		exit.addActionListener(this);
		// nút sound
		sound = new JButton();
		sound.setBounds(181, 511, 78, 65);
		sound.setIcon(new ImageIcon(loadImage("src\\img\\musicOn.png", 78, 65)));
		sound.setActionCommand("sound on");
		sound.addActionListener(this);
		// nút game mode
		gamemode = new JButton();
		gamemode.setBounds(291, 511, 156, 65);
		gamemode.setIcon(new ImageIcon(loadImage("src\\img\\easy.PNG", 156, 65)));
		gamemode.setActionCommand("easy");
		gamemode.addActionListener(this);
		// nút high score
		highScore = new JButton();
		highScore.setBounds(643, 511, 100, 65);
		highScore.setIcon(new ImageIcon(loadImage("src\\img\\trophy.png", 100, 65)));
		highScore.setActionCommand("highScore");
		highScore.addActionListener(this);

		menu.add(sound);
		menu.add(start);
		menu.add(gamemode);
		menu.add(highScore);
		menu.add(exit);
		
		//tạo trước numShip tránh exception null pointer
		numShip = new JButton[4];
		next = new JButton();
		back = new JButton();
		for (int i = 1; i < 4; i++) {
			numShip[i] = new JButton();
		}
		
		// gỡ welcome, cài menu
		welcome.setVisible(false);
		frame.remove(welcome);
		frame.add(menu);
	}

	// menu chứa lựa chọn số lượng tàu
	public void menu3() {
		select = new JLabel();
		select.setSize(1120,690);
		select.setIcon(new ImageIcon(loadImage("src\\img\\blue.png",1120,690)));
		select.addMouseListener(this);
		ship = new JLabel[4];

		next.setIcon(new ImageIcon(loadImage("src\\img\\arrowRight.png", 50, 50)));
		back.setIcon(new ImageIcon(loadImage("src\\img\\arrowLeft.png", 50, 50)));
		back.setBounds(70, 552, 50, 50);
		next.setBounds(990, 552, 50, 50);
		next.addActionListener(this);
		back.addActionListener(this);
		
		for (int i = 1; i < ship.length; i++) {
			ship[i] = new JLabel();
//			numShip[i] = new JButton();
			ship[i].setIcon(new ImageIcon(loadImage("src\\img\\" + i + ".png",i * 100, 100)));
		}
		
		ship[1].setBounds(500, 180, 100, 100);
		ship[2].setBounds(500, 330, 200, 100);
		ship[3].setBounds(500, 480, 300, 100);
		numShip[1].setBounds(400, 200, 50, 50);
		numShip[2].setBounds(400, 350, 50, 50);
		numShip[3].setBounds(400, 500, 50, 50);
		
		for (int i = 1; i < ship.length; i++) {
			select.add(ship[i]);
			select.add(numShip[i]);
			numShip[i].setText("1");
			numShip[i].setFont(new Font("Arial",Font.PLAIN,20));
			numShip[i].setBackground(Color.white);
			numShip[i].setForeground(Color.red);
			numShip[i].setActionCommand("2");
			numShip[i].addActionListener(this);
		}
		select.add(back);
		select.add(next);
		
		menu.setVisible(false);
		frame.remove(menu);
		frame.add(select);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("Play".equals(e.getActionCommand())) {
			menu2();
		}
		if ("exit".equals(e.getActionCommand())) {
			frame.setVisible(false);
			System.exit(0);
		}

		if ("sound on".equals(e.getActionCommand())) {
			sound.setIcon(new ImageIcon(loadImage("src\\img\\musicOff.png", 78, 65)));
			sound.setActionCommand("sound off");
		}

		if ("sound off".equals(e.getActionCommand())) {
			sound.setIcon(new ImageIcon(loadImage("src\\img\\musicOn.png", 78, 65)));
			sound.setActionCommand("sound on");
		}

		if ("start".equals(e.getActionCommand())) {
			menu3();
		}

		if ("highScore".equals(e.getActionCommand())) {

		}

		if ("easy".equals(e.getActionCommand())) {
			gamemode.setIcon(new ImageIcon(loadImage("src\\img\\hard.PNG", 156, 65)));
			gamemode.setActionCommand("hard");
		}

		if ("hard".equals(e.getActionCommand())) {
			gamemode.setIcon(new ImageIcon(loadImage("src\\img\\easy.PNG", 156, 65)));
			gamemode.setActionCommand("easy");
		}
		
		if (e.getSource() == numShip[1]) {
			String s = numShip[1].getText();
			if (s == "1") numShip[1].setText("2");
			if (s == "2") numShip[1].setText("3");
			if (s == "3") numShip[1].setText("1");
		}
		
		if (e.getSource() == numShip[2]) {
			String s = numShip[2].getText();
			if (s == "1") numShip[2].setText("2");
			if (s == "2") numShip[2].setText("3");
			if (s == "3") numShip[2].setText("1");
		}
		
		if (e.getSource() == numShip[3]) {
			String s = numShip[3].getText();
			if (s == "1") numShip[3].setText("2");
			if (s == "2") numShip[3].setText("3");
			if (s == "3") numShip[3].setText("1");
		}
		
		if (e.getSource() == back) {
			select.setVisible(false);
			frame.remove(select);
			menu.setVisible(true);
			frame.add(menu);
		}
		
		if (e.getSource() == next) {
			int n1 = Integer.parseInt(numShip[1].getText());
			int n2 = Integer.parseInt(numShip[2].getText());
			int n3 = Integer.parseInt(numShip[3].getText());
			creator = new Creator(1120,690,n3,n2,n1, gamemode.getActionCommand());
			creator.back.setActionCommand("back");
			creator.back.addActionListener(this);
			frame.setVisible(false);
		}
		
		if ("back".equals(e.getActionCommand())) {
			creator.frame.setVisible(false);
			frame.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getX() + " " + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
