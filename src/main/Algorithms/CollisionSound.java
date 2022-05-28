package main.Algorithms;

import main.GamePanel;
import main.UI.Ball;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class CollisionSound {
	static Long currentFrame;
    static Clip clip;
    AudioInputStream audioInputStream;
    public CollisionSound(int t) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (t == 8) {
    	audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Sound/sound4.wav").getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        currentFrame = clip.getMicrosecondPosition();
        }else {
        	audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Sound/brickHitSound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            currentFrame = clip.getMicrosecondPosition();
        }
    }
    public CollisionSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Sound/powerUpSound.wav").getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        currentFrame = clip.getMicrosecondPosition();
    }
    public CollisionSound(String s) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Sound/ballHitSound.wav").getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        currentFrame = clip.getMicrosecondPosition();
    }
    
    public void start() throws InterruptedException {
    	clip.start();
    	while (clip.isRunning()) {
    		long counter = clip.getMicrosecondPosition();
    		if (counter > 1100000) {
    			clip.stop();
    			clip.close();
    		}
    	}
    }
    public static void close() {clip.stop(); clip.close();}

    public static class PauseMenu extends JFrame implements ActionListener {

        public PauseMenu(String title) {
            super(title);
            addKeyListener(new PressingKeys());
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);

            //This creates a custom icon for the window using an image
            ImageIcon logoIcon = new ImageIcon(("resources/Image/breakout logo.png"));
            Image logo = logoIcon.getImage();
            setIconImage(logo);

            JPanel panel1 = new JPanel();
            JLabel backToGame = new JLabel("PRESS 'ESC' TO RETURN TO THE GAME");
            panel1.add(backToGame);
            add(panel1, BorderLayout.NORTH);

            JPanel panel2 = new JPanel();
            JButton mainMenuButton = new JButton("MAIN MENU");
            JButton exitButton = new JButton("EXIT");
            mainMenuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    GamePanel.getBackTOMenu = true;


                }
            });
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { System.exit(0); }
            });
            panel2.add(mainMenuButton);
            panel2.add(exitButton);
            add(panel2, BorderLayout.SOUTH);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setSize(300, 100);
            setVisible(true);
            setLocationRelativeTo(null);
            repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        public class PressingKeys extends KeyAdapter
        {
            @Override
            public void keyPressed(KeyEvent e) {
                int action = e.getKeyCode();

                if(action == KeyEvent.VK_ESCAPE)
                {
                    try {
                        GamePanel.s.resumeAudio();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    for (Ball ball: GamePanel.ballList) {
                        ball.conti();

                    }
                    for (PowerUps p: GamePanel.powerUps) {
                        p.conti();
                        }
                    GamePanel.a = 1;
                    GamePanel.d = 0;
                    dispose();
                }
            }
        }
    }
}
