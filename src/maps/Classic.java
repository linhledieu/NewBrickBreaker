package maps;

import main.MainMenu;

public class Classic {
	public int[][] map;
	public int brickHeight, brickWidth;
	public int row = 6;
	public int col = 10; 
	
	public Classic() { 
		map = new int[row][col];
		this.brickHeight = (MainMenu.HEIGHT/2 - 2*50)/row; 
		this.brickWidth = (MainMenu.WIDTH - 2*80)/col;
	}
	
	public int[][] buildMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == 0 || j == 0 || i == map.length-1 || j == map[0].length-1) {
					map[i][j] = 2;
				} else {
					map[i][j] = 1;
				}
			}
		}
		map[2][4] = 3;
		map[2][5] = 3;
		map[3][4] = 3;
		map[3][5] = 3;
		return map;
		
	}
}
