package main.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.Algorithms.CollisionSound;
import main.Algorithms.PowerUps;
import Maps.*;

public class Map {
	CollisionSound s;
	private int[][] map;
	private int brickHeight, brickWidth;
	
	public final int HOR_PAD = 80, VERT_PAD = 50;
	
	String backGround;
	
	public Map(String mode) {
		initMap(mode);
	}
	
	public void initMap(String mode) {
		if (mode.equals("Tornado")) {
			Tornado temp = new Tornado();
			map = temp.buildMap();
			this.brickHeight = temp.getH() ;
			this.brickWidth = temp.getW() ;
			this.backGround = temp.getBackground();
		} else if (mode.equals("OOP")) {
			OOP temp = new OOP();
			map = temp.buildMap();
			this.brickHeight = temp.getH() ;
			this.brickWidth = temp.getW() ;
			this.backGround = temp.getBackground();
		}else if (mode.equals("Diamond")) {
			Diamond temp = new Diamond();
			map = temp.buildMap();
			this.brickHeight = temp.getH() ;
			this.brickWidth = temp.getW() ;
			this.backGround = temp.getBackground();
		} else if (mode.equals("Butterfly")) {
			Butterfly temp = new Butterfly();
			map = temp.buildMap();
			this.brickHeight = temp.getH() ;
			this.brickWidth = temp.getW() ;
			this.backGround = temp.getBackground();
		}else if (mode.equals("House")) {
			House temp = new House();
			map = temp.buildMap();
			this.brickHeight = temp.getH() ;
			this.brickWidth = temp.getW() ;
			this.backGround = temp.getBackground();
		}else if (mode.equals("X")) {
			X temp = new X();
			map = temp.buildMap();
			this.brickHeight = temp.getH() ;
			this.brickWidth = temp.getW() ;
			this.backGround = temp.getBackground();
		}else if (mode.equals("Classic")) {
			Classic temp = new Classic();
			map = temp.buildMap();
			this.brickHeight = temp.getH() ;
			this.brickWidth = temp.getW() ;
			this.backGround = temp.getBackground();
		}
	}
	
	public String getBackground() {
		return backGround;
	}
	
	public void draw(Graphics2D g) {
		
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[0].length; col++) {
				if (map[row][col] > 0) {
					if (map[row][col] == 1) {
						g.setColor(new Color(200, 200, 200));
					}
					if (map[row][col] == 2) {
						g.setColor(new Color(125, 125, 125));
					}
					if (map[row][col] == 3) {
						g.setColor(new Color(50,50,50));
					}
					if (map[row][col] == 4) {
						g.setColor(PowerUps.WIDENCOLOR);
					}
					if (map[row][col] == 5) {
						g.setColor(PowerUps.ENLARGECOLOR);
					}
					if (map[row][col] == 6) {
						g.setColor(PowerUps.MULTIPLIERCOLOR);
					}
					if (map[row][col] == 7) {
						g.setColor(PowerUps.EXPLODECOLOR);
					} 
					if (map[row][col] == 8)
						g.setColor(PowerUps.SPLITCOLOR);
					if (map[row][col] == 9) {
						g.setColor(new Color(255, 165, 0));
					}
					g.fillRect(col*this.brickWidth + this.HOR_PAD, row*this.brickHeight + this.VERT_PAD, this.brickWidth, this.brickHeight);
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.WHITE);
					g.drawRect(col*this.brickWidth + this.HOR_PAD, row*this.brickHeight + this.VERT_PAD, this.brickWidth, this.brickHeight);
				}
			}
		}
	}
	
	public int getBrickWidth() {return brickWidth;}
	public int getBrickHeight() {return brickHeight;}
	
	public int[][] getMapArray() {return map;}
		
	public void setBrick(int row, int col, int value) {
		map[row][col] = value;
	}

	public void hitBrick(int row, int col) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		if (Ball.countBall > 0) {
			sound(map[row][col]);
		}
		map[row][col] -= 1; 
		if (map[row][col] == 7) {	
			map[row-1][col-1] = 0;
			map[row-1][col] = 0;
			map[row-1][col+1] = 0;
			map[row][col-1] = 0;
			map[row][col] = 0;
			map[row][col+1] = 0;
			map[row+1][col-1] = 0;
			map[row+1][col] = 0;
			map[row+1][col+1] = 0;
		} else if (map[row][col] < 0) {
			map[row][col] = 0;
		}
	}
	public void sound(int t) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		s = new CollisionSound(t);
		s.start();
	}
	
	
	public boolean winCheck() {
		boolean win = false;
		int HP = 0;
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				HP += map[row][col];
			}
		}
		if (HP == 0) {win = true;}
		
		return win;
	}
}
