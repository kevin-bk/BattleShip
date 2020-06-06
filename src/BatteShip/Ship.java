package BatteShip;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Ship {
	public Image ship;	// ảnh tàu nằm ngang
	public Image shipRotate;
	
	public Ship(int length, int w, int h) {
//		String s = "src\\img\\" + name + ".png";
//		ship = loadImage(s,w,h);
		if (length == 3) {
			ship = loadImage("src\\img\\3.png",w,h);
			shipRotate = loadImage("src\\img\\3r.png",h,w);
		}
		if (length == 2) {
			ship = loadImage("src\\img\\2.png",w,h);
			shipRotate = loadImage("src\\img\\2r.png",h,w);
		}
		if (length == 1) {
			ship = loadImage("src\\img\\1.png",w,h);
			shipRotate = loadImage("src\\img\\1r.png",h,w);
		}
	}
	
	
	public Image getShip() {
		return this.ship;
	}
	
	public Image getShipRorate() {
		return this.shipRotate;
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
