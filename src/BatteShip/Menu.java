package BatteShip;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu implements ActionListener {
	public JFrame frame;
	public JLabel background;
	public JButton start;
	public JButton exit;
	public JButton highScore;
	public JButton sound;
	public ImageIcon icon;
	
	public Menu(int w, int h) {
		frame = new JFrame();
		frame.setSize(w, h);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		background = new JLabel();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
