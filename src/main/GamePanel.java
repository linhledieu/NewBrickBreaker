package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private MouseListener MouseListener;
	private Scoreboard scoreboard;
	
	private int mouseX;
	
	private Ball ball;
	private Ball b1;
	private Ball b2;
	private Paddle paddle;
	private Map map;
	private HUD HUD;
	private ArrayList<PowerUps> powerUps;
	
	public GamePanel() throws InterruptedException {
		this.addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		init();
	}
	
	public void init() throws InterruptedException {
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Mode: ");
//		String mode = sc.next();
		mouseX = 0;
		ball = new Ball();
		paddle = new Paddle();
		map = new Map("Hard");
		HUD = new HUD();
		MouseListener = new MouseListener();
		powerUps = new ArrayList<PowerUps>();
		scoreboard = new Scoreboard();
		
		addMouseMotionListener(MouseListener);
		Thread.sleep(2000);
		running = true;
		image = new BufferedImage(MainMenu.WIDTH, MainMenu.HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	@Override
	public void run() {
		while (running) { 
			update();
			
			draw();
			
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkCollisions() {
		Rectangle ballRect = ball.getRect();
		Rectangle paddleRect = paddle.getRect();
		int[][] mapArray = map.getMapArray();
		
		for (int i = 0; i < powerUps.size(); i++) {
			Rectangle pRect = powerUps.get(i).getRect();
			
			if (paddleRect.intersects(pRect)) {
				if (powerUps.get(i).getType() == PowerUps.WIDEN && !powerUps.get(i).getUsed()) {
					paddle.setWidth(paddle.getWidth()*2);
					powerUps.get(i).setUsed(true);
				} else if (powerUps.get(i).getType() == PowerUps.ENLARGE && !powerUps.get(i).getUsed()) {
					ball.setSize(ball.getSize()*2);
					powerUps.get(i).setUsed(true);
				} else if (powerUps.get(i).getType() == PowerUps.MULTIPLIER && !powerUps.get(i).getUsed()) {
					HUD.MULTIPLIER++;
					powerUps.get(i).setUsed(true); 
				}
			} 
		}
		
		if (ballRect.intersects(paddleRect)) {
			ball.setDY(-ball.getDY());
			if(ball.getX() < mouseX + paddle.getWidth()/4) {
				ball.setDX(ball.getDX() - 0.5);
			}
			if(ball.getX() < mouseX + paddle.getWidth() && ball.getX() > mouseX + paddle.getWidth()/4) {
				ball.setDX(ball.getDX() + 0.5);
			} 
		}
		
		A: for (int row = 0; row < mapArray.length; row++) {
			for (int col = 0; col < mapArray[0].length; col++) {
				if (mapArray[row][col] > 0) {
						
					int brickx = col*map.getBrickWidth() + map.HOR_PAD; 
					int bricky = row*map.getBrickHeight() + map.VERT_PAD;
					int width = map.getBrickWidth();
					int height = map.getBrickHeight();
					
					Rectangle brickRect = new Rectangle(brickx, bricky, width, height); 
					
					if (ballRect.intersects(brickRect)) {
						if (mapArray[row][col] == 8) {
							map.hitBrick(row, col);
						} else if(mapArray[row][col] > 3) {
							powerUps.add(new PowerUps(brickx, bricky, mapArray[row][col], width, height));
							map.setBrick(row, col, 0);
						}
						if (ball.getPowered() == false) {
							map.hitBrick(row, col);
							ball.setDY(-ball.getDY());
							if (mapArray[row][col]== 0) {
								HUD.addScore();
							}
						} else if (ball.getPowered() == true) {
							map.hitBrick(row, col);
							map.hitBrick(row, col);
							ball.setDY(-ball.getDY());
							if (mapArray[row][col]== 0) {
								HUD.addScore();
							}
						}
						
						
						break A;
					}
				}
			}
		}
	}
	
	public void update() {
		checkCollisions();
		ball.update();
		if (!(this.b1 == null)) {
			b1.update();
			b2.update();
		}
		paddle.update();
		
		for (PowerUps p: powerUps) {
			p.update();
		}
	}
	
	public void draw() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, MainMenu.WIDTH, MainMenu.HEIGHT); 
		
		ball.draw(g);
		if (!(this.b1 == null)) {
			b1.draw(g);
			b2.draw(g);
		}
		paddle.draw(g);
		map.draw(g);
		HUD.draw(g);
		drawPower();
		
		if (map.winCheck() == true) {
			drawWin();
		}
		if (Ball.checkLose() == true) {
			drawLose();
		}
	}
	
	public void drawPower() {
		for (PowerUps p: powerUps) {
			p.draw(g);
		}
	}
	
	public void drawWin() {
		g.setColor(Color.RED);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("You Win!", 240, 250);
	}
	
	public void drawLose() {
		g.setColor(Color.RED);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("You Lose", 255, 250);
		g.drawString("Score: " + HUD.getScore(), 255, 275);
		g.drawString("Press ENTER to Replay" , 165 , 300);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(image, 0, 0, MainMenu.WIDTH, MainMenu.HEIGHT, null);
		g2.dispose();
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle.mouseMoved((int) paddle.getX()+10);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			paddle.mouseMoved((int) paddle.getX()-10);
			
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				init();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
//		if(e.getKeyCode() == KeyEvent.VK_S) {
//			try {
//				scoreboard.writeScore(HUD.getScore());
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
//				scoreboard.readScore();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//		if(e.getKeyCode() == KeyEvent.VK_H) {
//			try {
//				ArrayList<Integer> temp = scoreboard.readScore();
//				for (int i = 0; i < 5; i++) {
//					System.out.println("Top " + String.valueOf(i+1) + ": " + temp.get(i));
//				}
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
	
	private class MouseListener implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent e) {}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			mouseX = e.getX();
			paddle.mouseMoved(e.getX()-50);
		}
	}
}
	