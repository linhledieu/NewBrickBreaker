package main.UI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import main.Algorithms.GamePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameModeSelectionMenu extends JFrame {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 700;
	
	static Image img = Toolkit.getDefaultToolkit().getImage("resources/Image/gameMode_selection_image.jpeg");
	static Image newImage = img.getScaledInstance(480, 678, Image.SCALE_DEFAULT);
	
	static JFrame frame;
	static JPanel bg;
	static JPanel buttonHolder;
	static JButton classicModeButton, superBreakoutButton, backButton;
	static List<JButton> buttonList;
	
	static ActionListener listener;
	static Timer timer;
	
	
	public GameModeSelectionMenu() throws InterruptedException {
		//Create a Jframe
		frame = new JFrame("OOP Project");

		//A JPanel of Button holder
        buttonHolder = new JPanel();
        
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
       	
		//Add JPanel to JFrame, pack and set visible
		frame.add(bg);   
		frame.setVisible(true);
        
		
		//Edit button and add to button holder
		classicModeButton = new JButton("Classic Mode");
        superBreakoutButton = new JButton("Super Breakout Mode");
        backButton = new JButton("Back");
        buttonList = new ArrayList<JButton>();
        
        buttonList.add(classicModeButton);
        buttonList.add(superBreakoutButton);
        buttonList.add(backButton);
               
        //Set layout for the button holder -> add button to the panel
        buttonHolder.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(25, 0, 30, 0); // -> adding distance for each button
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.ipady = 30;
        c.fill = GridBagConstraints.BOTH;
        
        for (int i = 0; i < buttonList.size(); i++) {
        	c.gridx = 0;
        	c.gridy = i;
        	buttonHolder.add(buttonList.get(i), c);
        }

        //set layout the the bg panel then add then button holder into it
        bg.setLayout(new GridBagLayout());
        c.insets = new Insets(250, 100, 120, 100); // adjust the size of button and button holder
        bg.add(buttonHolder, c);

        
        //Customize the buttons and panel
        for (JButton button: buttonList) {
        	button.setOpaque(true);
        	button.setBorderPainted(true);
        	button.setFont(Manager.Constants.font);
        	button.setBackground(new Color(1, 247, 253));
        	button.setForeground(new Color(254, 10, 207));
        	button.setBorder(new Manager.RoundBtn(15));
        	button.setVisible(true);     	
        }

        buttonHolder.setBackground(new Color(250, 250, 250, 0));
        buttonHolder.setVisible(true);

        
		//menuFrame.add(bg);      
		frame.pack();
		frame.setVisible(true);		
		
		//Other function
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		//NOT ALLOW USER RESIZE
		frame.setResizable(false);
		frame.setTitle("THUNDER BRICK BREAKER PRO MAX ULTRA VIP");     
		frame.setLocationRelativeTo(null);		
		frame.setVisible(true);
		
		//Validate and repaint if needed
		frame.revalidate();
		frame.validate();
		frame.repaint();
        
		// Fix the bug of not showing bg
		listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {              
            	frame.setSize(480, 700);
            	frame.validate();
            	frame.repaint();
            	//timer.stop();
            }
        };        
        timer = new Timer(10, listener);
        timer.start();
     
        
        //ADD FUNTION TO BUTTON
        classicModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//                NormalGame_View.main(null);
            }
        });

        superBreakoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
            	frame.dispose();
    			GamePanel.frame = new JFrame("Brick Breaker");
    			GamePanel panel = null;
				try {
					panel = new GamePanel();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			Thread thread = new Thread(panel);
    			
    			GamePanel.frame.setLocation(450, 50);
    			GamePanel.frame.setResizable(false);
    			GamePanel.frame.setSize(900, 650);
    			GamePanel.frame.add(panel);
    			thread.start();
    			
    			GamePanel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			GamePanel.frame.setVisible(true);
            }
		});
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
                try {
					new MainMenu();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });


    }
}
