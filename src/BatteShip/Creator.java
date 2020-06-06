package BatteShip;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Creator extends JFrame implements MouseListener, MouseMotionListener {
	public Ship[] shipArray;		// ảnh ship
	public JPanel shipMap;			// panel chứa ship và map
	public JLabel map;				// label chứa ảnh map
	public JLabel[] ship;			// label chứa ship (ảnh ship)
	private int X, Y;				// biến dùng để get tọa độ khi di chuyển
	private final int xLeft = 272;	// tọa độ cua map trên frame
	private final int yUp = 60;		// tọa độ của map trên frame
	private final int xRight = xLeft + 560;	
	private final int yDown = yUp + 560;
	private static int xStart;	// tọa độ điểm bắt đầu khi kéo thả
	private static int yStart;	// tọa độ điểm bắt đầu khi kéo thả
	
	public JButton start, back;	// nút start và back;


	public Creator(int w, int h, int numShip3, int numShip2, int numShip1) {
		super("Battle Ship");
		this.setSize(w, h);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// shipmap
		map = new JLabel();
		map.setIcon(new ImageIcon(loadImage("src\\img\\bigMap.png", 560, 560)));
		map.setSize(560, 560);

		shipMap = new JPanel();
		shipMap.setSize(w, h);

		// numShipi = số lượng tàu độ dài i
		// x: số lượng tàu
		int x = numShip3 + numShip2 + numShip1;
		shipArray = new Ship[x + 1];
		ship = new JLabel[x + 1];
		int cnt = 1;
		for (int i = 1; i <= numShip3; i++) {
			shipArray[cnt] = new Ship(3, 150, 50);
			ship[cnt] = new JLabel();
			ship[cnt].setIcon(new ImageIcon(shipArray[cnt].getShip()));
			cnt++;
		}
		for (int i = 1; i <= numShip2; i++) {
			shipArray[cnt] = new Ship(2, 100, 50);
			ship[cnt] = new JLabel();
			ship[cnt].setIcon(new ImageIcon(shipArray[cnt].getShip()));
			cnt++;
		}

		for (int i = 1; i <= numShip1; i++) {
			shipArray[cnt] = new Ship(1, 50, 50);
			ship[cnt] = new JLabel();
			ship[cnt].setIcon(new ImageIcon(shipArray[cnt].getShip()));
			cnt++;
		}

		for (int i = 1; i <= x; i++) {
			shipMap.add(ship[i]);
			ship[i].addMouseListener(this);
			ship[i].addMouseMotionListener(this);
		}

		shipMap.add(map);
//		this.add(map);
		this.add(shipMap);
		this.setLayout(new GridLayout(1, 2));
//		Move mm = new Move(shipMap.getComponents());

//		this.addMouseListener(this);
//		map.addMouseListener(this);

		this.setVisible(true);
	}

	public static void main(String args[]) {
		new Creator(1120, 690, 3, 3, 3);
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int xNew = e.getX() + e.getComponent().getX() - X;
		int yNew = e.getY() + e.getComponent().getY() - Y;
		e.getComponent().setLocation(xNew, yNew);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		X = e.getX();
		Y = e.getY();
		xStart = e.getComponent().getX();
		yStart = e.getComponent().getY();

	}
	
	// Nếu ship nằm ngoài map -> trả về vị trí cũ
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int xNew = e.getX() + e.getComponent().getX() - X;
		int yNew = e.getY() + e.getComponent().getY() - Y;
		if (xNew < xLeft || xNew > xRight || yNew < yUp || yNew > yDown) {
			e.getComponent().setLocation(xStart, yStart);						
			return;
		}
		e.getComponent().setLocation(272, 60);

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
