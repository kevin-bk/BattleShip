package BatteShip;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SmallMap extends JPanel {
	public JButton mapPiece[][];
	public boolean isShip[][];
	public ImageIcon icon;

	public SmallMap(int w, int h) {
		super();
		mapPiece = new JButton[11][11];
		isShip = new boolean[11][11];
		init();

		this.setSize(w, h);
		this.setLayout(new GridLayout(10, 10)); // tạo GridLayout cho Map

		// load ảnh
		icon = new ImageIcon(loadImage("src\\img\\piece.png", w / 10, h / 10));

		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				mapPiece[i][j] = new JButton(); // chưa có action
				mapPiece[i][j].setIcon(icon);
				this.add(mapPiece[i][j]);
			}
		}
	}

	// mặc định ban đầu không có tàu
	public void init() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				isShip[i][j] = false;
			}
		}
	}

	// setShip: đặt con tàu vào map, bắt đầu từ x,y, dài length, isNgang: tàu ngang
	// hay dọc
	public void setShip(int x, int y, int length, boolean isNgang) {
		if (isNgang) {
			for (int i = 0; i < length; i++) {
				isShip[x + i][y] = true;
			}
		} else {
			for (int i = 0; i < length; i++) {
				isShip[x][y + i] = true;
			}
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
