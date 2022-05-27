package maps;

import main.MainMenu;

public class Hard {
	public int[][] map;
	public int brickHeight, brickWidth;
	public int row = 6;
	public int col = 10; 
	
	public Hard() { 
		map = new int[row][col];
		this.brickHeight = (MainMenu.HEIGHT/2 - 2*50)/row; 
		this.brickWidth = (MainMenu.WIDTH - 2*80)/col;
	}
	
	public int[][] buildMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 3;
			}
		}
		map[0][2] = 6;
		map[0][7] = 6;
		map[1][3] = 4;
		map[1][6] = 4;
		map[2][1] = 8;
		map[2][3] = 5;
		map[2][6] = 5;
		map[2][8] = 8;
		map[3][3] = 4;
		map[3][6] = 4;
		map[4][4] = 6;
		map[4][5] = 6;
		return map;
		
	}
}
