package Maps;

import main.Algorithms.GamePanel;

public class Diamond {
	public int[][] map;
	public int brickHeight;
	public int brickWidth;
	public int row = 7;
	public int col = 10; 
	String backGround;
	
	public Diamond() { 
		map = new int[row][col];
		this.brickHeight = (GamePanel.HEIGHT/2)/row; 
		this.brickWidth = (GamePanel.WIDTH - 2*80)/col;
		this.backGround = "resources/Image/background.png";
	}
	public String getBackground() {
		return backGround;
	}
	public int getH() {
		return brickHeight;
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
		map[0][3]=6;
		map[0][4]=6;
		map[0][5]=6;
		map[0][6]=6;
		map[1][2]=6;
		map[1][3]=8;
		map[1][4]=8;
		map[1][5]=8;
		map[1][6]=8;
		map[1][7]=6;
		map[2][1]=6;
		map[2][2]=5;
		map[2][3]=5;
		map[2][4]=5;
		map[2][5]=5;
		map[2][6]=5;
		map[2][7]=5;
		map[2][8]=6;
		map[3][0]=6;
		map[3][1]=6;
		map[3][2]=6;
		map[3][3]=6;
		map[3][4]=6;
		map[3][5]=6;
		map[3][6]=6;
		map[3][7]=6;
		map[3][8]=6;
		map[3][9]=6;
		map[4][1]=6;
		map[4][2]=5;
		map[4][3]=5;
		map[4][4]=5;
		map[4][5]=5;
		map[4][6]=5;
		map[4][7]=5;
		map[4][8]=6;
		map[5][2]=6;
		map[5][3]=4;
		map[5][4]=4;
		map[5][5]=4;
		map[5][6]=4;
		map[5][7]=6;
		map[6][3]=6;
		map[6][4]=6;
		map[6][5]=6;
		map[6][6]=6;
		return map;
		
	}

}
