package main.Algorithms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class PowerUps {
	CollisionSound s;
	private int x, y, type, size;
	private double  dy; 
	private boolean isOnScreen;
	private boolean used;
	private Color color;
	Image pic;
	Image pic1;
	int num;
	private double dyi;
	
	public final static int WIDEN = 4;
	public final static int ENLARGE = 5;
	public final static int MULTIPLIER = 6;
	public final static int EXPLODE = 8;
	public final static int SPLIT = 7;
	
	public final static Color WIDENCOLOR = new Color(50,50,125); // BLUE
	public final static Color ENLARGECOLOR = new Color(255, 60, 60); // RED
	public final static Color MULTIPLIERCOLOR = new Color(20, 255, 20); // GREEN
	public final static Color EXPLODECOLOR = new Color(255, 255, 0); // YELLOW
	public final static Color SPLITCOLOR = new Color(230, 230, 250); // PURPLE
	
	public PowerUps (int x, int y, int type, int size) throws IOException {
		this.x = x;
		this.y = y;
		this.type = type;
		this.size = size;
		
		if (type == WIDEN) {pic = ImageIO.read(new File("resources/Image/powerUp1.png"));}
		if (type == ENLARGE) {pic = ImageIO.read(new File("resources/Image/powerUp4.png"));}
		if (type == MULTIPLIER) {pic = ImageIO.read(new File("resources/Image/powerUp7.png"));}
		if (type == SPLIT) {pic = ImageIO.read(new File("resources/Image/powerUp6.png"));}
//		if (type == EXPLODE) {pic = ImageIO.read(new File("resources/Image/explosion3.gif"));}
		if (type == EXPLODE) {pic1 = Toolkit.getDefaultToolkit().createImage("resources/Image/explosion7.gif");}
		
		
		dy = (double) (Math.random()*2+1);
		this.used = false;
	}
	
	public void draw(Graphics2D g, Component c) {
		g.drawImage(pic, x, y, size , size, c);
	}
	public void draw1(Graphics2D g, Component c) {
		g.drawImage(pic1, x, y, c);
	}
	
	public void update() {
		this.y += this.dy;
		this.size = 30;
		
		if (this.y > GamePanel.HEIGHT) {isOnScreen = false;}
		
		if (used == true) {
			this.size = 0;
		}
	}
	public void stop() {
		this.dyi = dy;
		this.dy = 0;
	}
	
	public void conti() {
		this.dy = dyi;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public int getSize() {
		return size;
	}

	public boolean isOnScreen() {
		return isOnScreen;
	}

	public void setOnScreen(boolean isOnScreen) {
		this.isOnScreen = isOnScreen;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, size, size);
	}
	
	public boolean getUsed() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		sound();
		return this.used;}
	public void sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		s = new CollisionSound();
		s.start();
	}
	
	public void setUsed(boolean used) {this.used = used;}
	
	public void explode() {
		
	}
	}
