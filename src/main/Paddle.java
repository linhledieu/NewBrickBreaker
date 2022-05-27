package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {

	private double x;
	private int width, height;
	private long powerTimer;
	private boolean powered;
	
	public final int startWidth = 75;
	public final int y = MainMenu.HEIGHT - 100;
	
	public Paddle() {
		this.width = 75;
		this.height = 10;
		this.powerTimer = 0;
		this.powered = false;
		this.x = MainMenu.WIDTH/2 - this.width/2;
	}
	
	public void update() {
		if ((System.nanoTime() - powerTimer) / 1000 > 9000000 && powered == true) {
			this.width = startWidth;
			powered = false;
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) x, y, width, height);
		
		if (powered == true) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 18));
			g.drawString("Paddle Shrinking in " + (9 - (System.nanoTime() - powerTimer) / 1000000000), 435, 20);
		}
	}
	
	public double getX() {return this.x;}
	
	public void mouseMoved(int mouseX) {
		this.x = mouseX;
		if (this.x > MainMenu.WIDTH - width) {
			this.x = MainMenu.WIDTH - width;
		} 
		if (this.x < 0) {
			this.x = 0;
		}
	}
	
	public Rectangle getRect() {
		return new Rectangle((int) x, y, width, height);
	} 
	
	public int getWidth() {return this.width;}
	public void setWidth(int width) {
		if (powered == false) {
			powered = true;
			this.width = width;
			setTimer();
				
		}
	}
	
	public void setTimer() {
		this.powerTimer = System.nanoTime();
	}
}
