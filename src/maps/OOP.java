package Maps;

import main.GamePanel;

public class OOP {
	public int[][] map;
	public int brickHeight;
	public int brickWidth;
	public int row = 6;
	public int col = 13; 
	String backGround;
	
	public OOP() { 
		map = new int[row][col];
		this.brickHeight = (GamePanel.HEIGHT/2 - 2*50)/row; 
		this.brickWidth = (GamePanel.WIDTH - 2*80)/col;
		this.backGround = "resources/Image/background.png";
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
				map[i][j] = 0;
			}
		}
		map[0][1]=6;
		map[0][2]=6;
		map[1][0]=6;
		map[1][1]=8;
		map[1][2]=8;
		map[1][3]=6;
		map[2][0]=6;
		map[2][1]=8;
		map[2][2]=8;
		map[2][3]=6;
		map[3][0]=6;
		map[3][1]=8;
		map[3][2]=8;
		map[3][3]=6;
		map[4][0]=6;
		map[4][1]=8;
		map[4][2]=8;
		map[4][3]=6;
		map[5][1]=6;
		map[5][2]=6;
		map[0][6]=5;
		map[0][7]=5;
		map[1][5]=5;
		map[1][6]=8;
		map[1][7]=8;
		map[1][8]=5;
		map[2][5]=5;
		map[2][6]=8;
		map[2][7]=8;
		map[2][8]=5;
		map[3][5]=5;
		map[3][6]=8;
		map[3][7]=8;
		map[3][8]=5;
		map[4][5]=5;
		map[4][6]=8;
		map[4][7]=8;
		map[4][8]=5;
		map[5][6]=5;
		map[5][7]=5;
		map[0][10]=4;
		map[0][11]=4;
		map[0][12]=4;
		map[1][10]=4;
		map[1][11]=8;
		map[1][12]=4;
		map[2][10]=4;
		map[2][11]=8;
		map[2][12]=4;
		map[3][10]=4;
		map[3][11]=4;
		map[3][12]=4;
		map[4][10]=4;
		map[5][10]=4;
		return map;
		
	}

}
