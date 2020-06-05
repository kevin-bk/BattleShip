package BatteShip;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MainMenu extends JLabel {
	private ImageIcon introIcon;	// nền khi vào game
	public JButton play;			// kèm nền khi vào game
	
	// khởi tạo Menu ban đầu
	public MainMenu(int w, int h) {
		super();
		
		this.setSize(w, h);
		play = new JButton();
		play.setSize(30, 20);
		ImageIcon playIcon = new ImageIcon(loadImage("src\\img\\play.jpg",80,50));
		play.setIcon(playIcon);
		play.setBounds(w/2 - 40, 3 * h/4, 80, 50);
//		this.setLayout(new BorderLayout());
		introIcon = new ImageIcon(loadImage("src\\img\\Title.png",w,h));
		this.add(play);
		this.setIcon(introIcon);
	}
	
	
	
	
	
	private Image loadImage(String s,int w, int h) {
		BufferedImage i = null;		// doc anh duoi dang Buffered Image
		try {
			i = ImageIO.read(new File(s));
		} catch (Exception e) {
			System.out.println("Duong dan anh k hop le!");
		}
		
		Image dimg = i.getScaledInstance(w, h, Image.SCALE_SMOOTH);	// thay doi kich thuoc anh
		return dimg;
		
	}
}
