package BatteShip;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private static int xLeft;	// tọa độ cua map trên frame
	private static int yUp;		// tọa độ của map trên frame
	private static int xRight;	
	private static int yDown;
	private static int xStart;	// tọa độ điểm bắt đầu khi kéo thả
	private static int yStart;	// tọa độ điểm bắt đầu khi kéo thả
	private static int numShip;
	public static int nShip1;
	public static int nShip2;
	public static int nShip3;
	public static boolean[][] M = new boolean[12][12];	// đánh dấu ô đã có tàu
	public static int[] xS;		// đánh dấu tọa độ của ship khi chưa vào map
	public static int[] yS;
	public static boolean[] isNgang; // tàu nằm ngang hay nằm dọc
	public static int[] lengShip;	// độ dài tàu i
	
	public JLabel start, back, random;	// nút start và back;

	public JLabel test;
	
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

//		shipMap = new JPanel();
//		shipMap.setSize(w, h);

		// numShipi = số lượng tàu độ dài i
		// x: số lượng tàu
		int x = numShip3 + numShip2 + numShip1;
		nShip1 = numShip1;
		nShip2 = numShip2;
		nShip3 = numShip3;
		
		numShip = x;
		shipArray = new Ship[x + 1];
		xS = new int[x+1];
		yS = new int[x+1];
		isNgang = new boolean[x+1];
		lengShip = new int[x+1];
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
			ship[i].addMouseListener(this);
			ship[i].addMouseMotionListener(this);
		}
		
		test = new JLabel();
		build(x);
		init();
		
		this.add(test);

		this.setVisible(true);

//		System.out.println(map.getX() + " " + map.getY());
		xLeft = map.getX();
		xRight = xLeft + 560;
		yUp = map.getY();
		yDown = yUp + 560;
	}

	public static void main(String args[]) {
		new Creator(1120, 690, 2, 3, 3);
	}
	
	// hàm itit(): khởi tạo mảng M và xS,yS để random.
	public void init() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				M[i][j] = false;
				if (i <= numShip) {
					xS[i] = ship[i].getX();
					yS[i] = ship[i].getY();
					isNgang[i] = true;
				}
			}
		}
		
		for (int i = 1; i <= nShip3; i++) lengShip[i] = 3;
		for (int i = nShip3 +1; i <= nShip3 + nShip2; i++) lengShip[i] = 2;
		for (int i = nShip3 + nShip2 + 1; i <= numShip; i++) lengShip[i] = 1;
	}
	
	public void build(int x) {
		//tạo Button
		start = new JLabel("Start");
		back = new JLabel("Back");
		random = new JLabel("Random");
		start.setFont(new Font("Arial",Font.PLAIN,40));
		start.setForeground(Color.white);
//		start.setBackground(Color.white);
		back.setFont(new Font("Arial",Font.PLAIN,40));
		back.setForeground(Color.white);
		random.setFont(new Font("Arial",Font.PLAIN,40));
		random.setForeground(Color.white);
		
		test.add(back);
		
		for (int i = 1; i <= x; i++) {
//			shipMap.add(ship[i]);
			test.add(ship[i]);
		}

		int xB = 100;
		int yB = 50;
//		back.setIcon(new ImageIcon(loadImage("src\\img\\back.png",xB,yB)));
//		start.setIcon(new ImageIcon(loadImage("src\\img\\start.png",xB,yB)));
//		random.setIcon(new ImageIcon(loadImage("src\\img\\random.png",xB,yB)));
		start.setSize(xB,yB);
		back.setSize(xB,yB);
		random.setSize(xB, yB);
		JLabel lb1 = new JLabel("                       ");
		lb1.setSize(400, 200);
		
//		test.add(lb1);

		test.add(random);
		test.add(map);
		test.add(start);
		test.setLayout(new FlowLayout());
		test.setIcon(new ImageIcon(loadImage("src\\img\\nen2.png", 1120, 690)));
		
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

	
	public void put(int tmp, int x, int y, int i, int j) {
//		System.out.println("i = " + i + " j = " + j);
		
		int leng = lengShip[tmp];
		if (isNgang[tmp]) {
				if ((x + leng * 50 > xRight && leng > 1) || (leng == 1 && x > xRight)) {
					ship[tmp].setLocation(xStart, yStart);
					return;
				}
				
				int t1 = 0, t2 = 0, t3 = 0, cnt = 1;
				int[] a = new int[4]; a[1] = 0; a[2] = 0; a[3] = 0;
				if (xStart >= xLeft && xStart <= xRight && yStart >= yUp && yStart <= yDown) {
					int u = (xStart - xLeft)/56 +1 ;
					int v = (yStart - yUp)/56 + 1;
					if (v == j) {
						for (int t = 0; t < leng; t++) {
							for (int w = 0; w <leng; w++) {
								if (u + t == i + w) a[++cnt] = u + t; 
							}
						}
						t1 = a[1]; t2 = a[2]; t3 = a[3];
					}
				}
				for (int t = 0; t < leng; t++) if (M[i + t][j] && (i + t != t1) && (i+t != t2) && (i+t) != t3) {
					ship[tmp].setLocation(xStart, yStart);
					return;
				}
	
				if (leng > 1) {
				ship[tmp].setLocation(x, y);
				}
				else ship[tmp].setLocation(x - 10, y);
				for (int t = 0; t < leng; t++) M[i + t][j] = true;
				
				if (xStart < xLeft || xStart > xRight || yStart < yUp || yStart > yDown) return;
				int p = (xStart - xLeft)/56 +1;
				int q = (yStart - yUp)/56 +1;
				for (int t = 0; t < leng; t++) M[p + t][q] = false;
				
//				System.out.println("p = " + p + " q = " + q);
				return;
		}
		
		// tàu nằm dọc
		if ((y + leng *50 > yDown && leng > 1) || (leng == 1 && y > yDown)) {
			ship[tmp].setLocation(xStart, yStart);
			return;
		}
		int t1 = 0, t2 = 0, t3 = 0, cnt = 1;
		int[] a = new int[4]; a[1] = 0; a[2] = 0; a[3] = 0;
		if (xStart >= xLeft && xStart <= xRight && yStart >= yUp && yStart <= yDown) {
			int u = (xStart - xLeft)/56 +1 ;
			int v = (yStart - yUp)/56 + 1;
			if (u == i) {
				for (int t = 0; t < leng; t++) {
					for (int w = 0;w < leng; w++) {
						if (v + t == j + w) a[++cnt] = v+t;
					}
				}
				t1 = a[1]; t2 = a[2]; t3 = a[3];
			}
			
		}
		
		for (int t = 0; t < leng; t++) if (M[i][j+t] && (j+t != t1) && (j+t != t2) && (j+t) != t3) {
			ship[tmp].setLocation(xStart, yStart);
			return;
		}
		
		ship[tmp].setLocation(x, y);
		for (int t = 0; t < leng; t++) M[i][j + t] = true;
		if (xStart < xLeft || xStart > xRight || yStart < yUp || yStart > yDown) return;
		int p = (xStart - xLeft)/56;
		int q = (yStart - yUp)/56;
		for (int t = 0; t < leng; t++) M[p][q + t] = false;
		return;
	}
	
	public void rotate(int tmp, int x, int y, int i, int j) {
		System.out.println("i = " + i + " j = " + j);
		int leng = lengShip[tmp];
		// ngang -> dọc
		if (isNgang[tmp]) {
			if (y + leng * 50 > yDown && leng > 1) return;
			for (int t = 1; t < leng; t++) {
				if (M[i][j + t]) return;
			}
			
			JLabel lb = new JLabel();
			lb.setIcon(new ImageIcon(shipArray[tmp].getShipRorate()));
			lb.setSize(50, leng * 50);
			for (int t = 1; t < leng; t++) {
				M[i][j + t] = true;
				M[i + t][j] = false;
			}
			ship[tmp].setVisible(false);
			test.remove(ship[tmp]);
			test.add(lb);
			lb.setLocation(x, y);
//			ship[tmp].setSize(50, leng * 50);
//			ship[tmp].setIcon(new ImageIcon(shipArray[tmp].getShipRorate()));
//			ship[tmp].setLocation(x, y);
			isNgang[tmp] = false;
			return;
		}
		
		// dọc -> ngang
		
		if (x + leng * 50 > xRight && leng > 1) return;
		for (int t = 1; t < leng; t++) if (M[i + t][j]) return;
		
		ship[tmp].setSize(leng * 50, 50);
		ship[tmp].setIcon(new ImageIcon(shipArray[tmp].getShipRorate()));
		ship[tmp].setLocation(x, y);
		isNgang[tmp] = true;
		for (int t = 1; t < leng; t++) {
			M[i][j + t] = false;
			M[i + t][j] = true;
		}
		return;
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
		if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
			xStart = e.getComponent().getX();
			yStart = e.getComponent().getY();
			if (xStart < xLeft || xStart > xRight || yStart < yUp || yStart > yDown) return;
			
			int tmp = 0;	// tmp: tàu bị xoay
			for (int k = 1; k <= numShip; k++) {
				if (e.getSource() == ship[k]) {
					tmp = k;
					break;
				}
			}
			
			int i = (xStart - xLeft)/56 + 1;
			int j = (yStart - yUp)/56 + 1;
//			rotate(tmp,xStart,yStart,i,j);
			
		}
		
		
		
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
		
		int i = (xNew + 10 - xLeft)/56;
		int j = (yNew + 5 - yUp)/56;
		xNew = xLeft + i * 56 + 10;
		yNew = yUp + j * 56 + 3;		// tàu sẽ ở ô i+1 và j+1
		int tmp = 0;
		for (int k = 1; k <= numShip; k++) {
			if (e.getSource() == ship[k]) {
				tmp = k;
				break;
			}
		}
		
		put(tmp,xNew,yNew,i+1,j+1);
		

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