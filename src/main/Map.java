package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import maps.*;

public class Map {
	private int[][] map;
	private int brickHeight, brickWidth;
	
	public final int HOR_PAD = 80, VERT_PAD = 50;
	
	public Map(String mode) {
		initMap(mode);
		this.brickHeight = (MainMenu.HEIGHT/2 - 2*VERT_PAD)/6; 
		this.brickWidth = (MainMenu.WIDTH - 2*HOR_PAD)/10;
	}
	
	public void initMap(String mode) {
		if (mode.equals("Classic")) {
			Classic temp = new Classic();
			map = temp.buildMap();
		} else if (mode.equals("Easy")) {
			Easy temp = new Easy();
			map = temp.buildMap();
		}else if (mode.equals("Medium")) {
			Medium temp = new Medium();
			map = temp.buildMap();
		} else if (mode.equals("Hard")) {
			Hard temp = new Hard();
			map = temp.buildMap();
		}
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
						g.setColor(PowerUps.SPLITCOLOR);
					} 
					if (map[row][col] == 8) {
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

	public void hitBrick(int row, int col) {
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
