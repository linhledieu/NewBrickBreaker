package Maps;

import main.GamePanel;

public class Classic {
	public int[][] map;
	public int brickHeight;
	public int brickWidth;
	public int row = 7;
	public int col = 7; 
	String backGround;
	
	public Classic() { 
		map = new int[row][col];
		this.brickHeight = (GamePanel.HEIGHT/2 -2*50)/row; 
		this.brickWidth = (GamePanel.WIDTH - 2*80)/col;
		this.backGround = "resources/Image/ackground.png";
	}
	public int getH() {
		return brickHeight;
	}
	public String getBackground() {
		return backGround;
	}
	
	public int getW() {
		return brickWidth;
	}
	
	public int[][] buildMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		
		map[1][3]= 5;
		map[2][4] = 6;
		map[3][5] = 8;
		map[5][5] = 8;
		map[2][6] = 5;
		map[3][4] = 4;
		map[4][2] = 6;
		map[0][2] = 4;
		map[6][2] = 7;
		map[6][6] = 5;
		map[5][1] = 7;
		map[4][2] = 9;
		map[4][1] = 4;
		map[5][2] = 6;
		
		return map;
		
	}


}
