package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private double x, y, dx, dy;
	private int ballSize = 15;
	private boolean powered;
	private long powerTimer;
	
	public final static int startSize = 15;
	public static int COUNT;
	
	public Ball() {
		this.x = 200;
		this.y = 200;
		
		this.dx = 1;
		this.dy = 3; 
		this.powerTimer = 0;
		this.powered = false;
		COUNT++;
	}
	
	public Ball(double x, double y, double dx, double dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		COUNT++;
		System.out.println(COUNT);
	}
	
	public void update() {
		setPosition();
		if ((System.nanoTime() - powerTimer) / 1000 > 9000000 && powered == true) {
			this.ballSize = startSize;
			this.powered = false;
		}
	}
	
	public void setPosition() {
		x += dx;
		y += dy;
		
		if (x < 0) {
			this.dx = -this.dx;
		} 
		if (y < 0) {
			this.dy = -this.dy;
		}
		if (x > MainMenu.WIDTH - (ballSize*2-7)) {
			this.dx = -this.dx;
		}
		if (y > MainMenu.HEIGHT - ballSize*2 && COUNT > 0) {
			COUNT--;
			return;
		}
	}
	
	public static boolean checkLose() {
		if (COUNT == 0)  {
			return true;
		}
		return false;
	}
		

	public void draw(Graphics2D g) {
		 g.setColor(Color.DARK_GRAY);
		 g.setStroke(new BasicStroke(4));
		 g.drawOval((int) x, (int) y, ballSize, ballSize);
		 
		 if (powered == true) {
				g.setColor(Color.RED);
				g.setFont(new Font("serif", Font.BOLD, 18));
				g.drawString("Ball Shrinking in " + (9 - (System.nanoTime() - powerTimer) / 1000000000), 450, 30);
		 }
	}
	
	public Rectangle getRect() {
		return new Rectangle((int) x,(int) y, ballSize, ballSize);
	}
	
	public double getX() {return this.x;}
	public double getY() {return this.y;}
	
	public double getDY() {return this.dy;}
	public void setDY(double dy) {this.dy = dy;}
	
	public double getDX() {return this.dx;}
	public void setDX(double dx) {this.dx = dx;}
	
	public int getSize() {return this.ballSize;}
	public void setSize(int ballSize) {
		if (powered == false) {
			powered = true;
			this.ballSize = ballSize;
			setTimer();
		}
	}
	public void setTimer() {
		this.powerTimer = System.nanoTime();
	}
	public boolean getPowered() {return this.powered;}
	
}
