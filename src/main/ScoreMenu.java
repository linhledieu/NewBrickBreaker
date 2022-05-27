package main;

import Manager.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;


public class ScoreMenu extends JFrame {

	static Scoreboard score;
	static Image img = Toolkit.getDefaultToolkit().getImage("resources/Image/leaderboard_image.jpeg");
	static Image newImage = img.getScaledInstance(480, 678, Image.SCALE_DEFAULT);
	static JFrame window;
	static JPanel bg;
	static JPanel scorePanel = new JPanel();
	static JLabel nameLabel;
	static JLabel scoreLabel;
	static ActionListener listener;
	static Timer timer;

	static ArrayList<String> playerNameArr;
	static ArrayList<Integer> playerScoreArr;

//	public ScoreMenu(String title) throws IOException {
	public static void main(String[] args) throws IOException {
		window = new JFrame();
		window.getContentPane().setLayout(new CardLayout(0, 0));

		//JPanel background
		bg = new JPanel() {

			@Override
			public void setPreferredSize(Dimension d) {
				// TODO Auto-generated method stub
				d = new Dimension(480, 678);
				super.setPreferredSize(d);
			}

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(newImage, 0, 0, null);
			}
		};
		//Set bg size
		bg.setPreferredSize(new Dimension(489, 678));

		//panel for user input and add into the bg panel
		bg.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 1.0;
		c.weighty = 1.0;
		c.ipady = 20;
		c.fill = GridBagConstraints.BOTH;

		score = new Scoreboard();
		LinkedHashMap<String, Integer> playerInfoArr = score.readScore();
		playerNameArr= new ArrayList<String>(playerInfoArr.keySet());
		playerScoreArr = new ArrayList<Integer>(playerInfoArr.values());

		scorePanel.setLayout(new GridBagLayout());
		c.insets = new Insets(10, 40, 10, 0); // -> adding distance for each panel
		for (int i = 0; i < 10 && i < playerScoreArr.size(); i++) {
			c.gridx = 0;
			c.gridy = i;
			nameLabel = new JLabel(playerNameArr.get(i));
			scorePanel.add(nameLabel, c);

			c.gridx = 1;
			scoreLabel = new JLabel(String.valueOf(playerScoreArr.get(i)));
			scorePanel.add(scoreLabel, c);

			nameLabel.setOpaque(true);
			nameLabel.setFont(Manager.Constants.font);
			nameLabel.setBackground(new Color(254, 10, 207, 0));
			nameLabel.setForeground(new Color(0, 250, 250));
			nameLabel.setVisible(true);

			scoreLabel.setOpaque(true);
			scoreLabel.setFont(Manager.Constants.font);
			scoreLabel.setBackground(new Color(254, 10, 207, 0));
			scoreLabel.setForeground(new Color(254, 10, 207));
			scoreLabel.setVisible(true);
		}


		// ADD SCORE PANEL INTO FRAME AND MAKE TRANSPARENT
		c.insets = new Insets(180, 70, 80 , 20); //-> positioning Scorepanel
		bg.add(scorePanel, c);
		scorePanel.setOpaque(true);
		scorePanel.setBackground(new Color(254, 10, 207, 0));


		//Add JPanel to JFrame, pack and set visible
		window.add(bg);
		window.pack();
		window.setVisible(true);

		//Other function
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(new PressingKeys());
		//NOT ALLOW USER RESIZE
		window.setResizable(true);
		window.setTitle("THUNDER BRICK BREAKER PRO MAX ULTRA VIP");
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		//Validate and repaint if needed
		window.revalidate();
		window.validate();
		window.repaint();

		// Fix the bug of not showing bg
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setSize(480, 700);
				window.validate();
				window.repaint();
				//timer.stop();
			}
		};

		timer = new Timer(5, listener);
		timer.start();

	}


	
//	// test
//	public void printScore() throws IOException {
//
//
//	}

}

