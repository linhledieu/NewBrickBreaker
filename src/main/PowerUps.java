package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class PowerUps {
	
	private int x, y, dy, type, width, height;
	private boolean isOnScreen;
	private boolean used;
	private Color color;
	
	public final static int WIDEN = 4;
	public final static int ENLARGE = 5;
	public final static int MULTIPLIER = 6;
	public final static int SPLIT = 7;
	public final static Color WIDENCOLOR = new Color(50,50,125); // BLUE
	public final static Color ENLARGECOLOR = new Color(255, 60, 60); // RED
	public final static Color MULTIPLIERCOLOR = new Color(20, 255, 20); // GREEN
	public final static Color SPLITCOLOR = new Color(230, 230, 250); // PURPLE
	
	public PowerUps (int x, int y, int type, int width, int height) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.width = width;
		this.height = height;
		
		if (type == WIDEN) {this.color = WIDENCOLOR;}
		if (type == ENLARGE) {this.color = ENLARGECOLOR;}
		if (type == MULTIPLIER) {this.color = MULTIPLIERCOLOR;}
		if (type == SPLIT) {this.color = SPLITCOLOR;}
		
		dy = (int) (Math.random()*2+1);
		this.used = false;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
	}
	
	public void update() {
		this.y += this.dy;
		
		if (this.y > MainMenu.HEIGHT) {isOnScreen = false;}
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

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isOnScreen() {
		return isOnScreen;
	}

	public void setOnScreen(boolean isOnScreen) {
		this.isOnScreen = isOnScreen;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
	
	public boolean getUsed() {return this.used;}
	
	public void setUsed(boolean used) {this.used = used;}
	
	public void explode() {
		
	}
	}
