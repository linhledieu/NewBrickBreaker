package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import Manager.Constants;

public class SettingGame {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 700;
	
	static Image img = Toolkit.getDefaultToolkit().getImage("resources/Image/main_image.jpeg");
	static Image newImage = img.getScaledInstance(480, 678, Image.SCALE_DEFAULT);
	
	static JFrame frame;
	static JPanel bg;
	
	static JPanel containerPanel;
	
	
	//SETTING
	//JPanel container for each button group
	static JPanel paddleColorPanel = new JPanel();
	static JPanel ballColorPanel = new JPanel();
	static JPanel musicSettingPanel = new JPanel();
	static JPanel difficultyPanel = new JPanel();
	static JPanel infoButtonPanel = new JPanel();
     
	//PADDLE
	static JLabel paddleColorLabel = new JLabel("PADDLE COLOR");
	static JRadioButton yellowPaddleButton = new JRadioButton("yellow");
	static JRadioButton bluePaddleButton = new JRadioButton("blue");
	static JRadioButton redPaddleButton = new JRadioButton("red");
	static ButtonGroup paddleButtonGroup = new ButtonGroup();
	
	//BALL
	static JLabel ballColorLabel = new JLabel("BALL COLOR");
	static JRadioButton yellowBallButton = new JRadioButton("yellow");
	static JRadioButton blueBallButton = new JRadioButton("blue");
	static JRadioButton redBallButton = new JRadioButton("red");
	static ButtonGroup ballButtonGroup = new ButtonGroup();
     
	//MUSIC
	static JLabel musicSettingLabel = new JLabel("MUSIC" );
    static JRadioButton onMusicButton = new JRadioButton("on");
    static JRadioButton offMusicButton = new JRadioButton("off");
    static ButtonGroup musicSettingGroup = new ButtonGroup();
	
	//DIFICU
    static JLabel difficultyLabel = new JLabel("DIFFICULTY");
    static JRadioButton easyButton = new JRadioButton("easy");
    static JRadioButton normalButton = new JRadioButton("normal");
    static JRadioButton hardButton = new JRadioButton("hard");
    static ButtonGroup difficultyGroup = new ButtonGroup();
    
    //INFO BUTTON
    static JButton saveButton = new JButton("Save");
    static JButton menuButton = new JButton("Main Menu");
	
	static ActionListener listener;
	static Timer timer;
	
	public SettingGame() throws InterruptedException {
	//public static void main(String[] args) throws InterruptedException {
		//Create a Jframe
		frame = new JFrame("OOP Project");
	
		//A JPanel of Panel holder
        containerPanel = new JPanel();
        
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
        
		//ADD ELEMENT TO PANEL 
		//COLOR
		paddleButtonGroup.add(yellowPaddleButton);
		paddleButtonGroup.add(bluePaddleButton);
		paddleButtonGroup.add(redPaddleButton);
        
        //BALL
		ballButtonGroup.add(yellowBallButton);
		ballButtonGroup.add(blueBallButton);
		ballButtonGroup.add(redBallButton);
		
		//MUSIC
        musicSettingGroup.add(onMusicButton);
        musicSettingGroup.add(offMusicButton);
        
        //DIFICU
        difficultyGroup.add(easyButton);
        difficultyGroup.add(normalButton);
        difficultyGroup.add(hardButton);

        //INFO BUTTON
        infoButtonPanel.add(saveButton);
        infoButtonPanel.add(menuButton);
        
        //Create array for different type
        List<JLabel> labelList = new ArrayList<JLabel>(Arrays.asList(ballColorLabel, paddleColorLabel, musicSettingLabel, difficultyLabel));
        List<JRadioButton> buttonList = new ArrayList<JRadioButton>(Arrays.asList(easyButton, normalButton, hardButton, onMusicButton, offMusicButton, blueBallButton, redBallButton, yellowBallButton, bluePaddleButton, redPaddleButton, yellowPaddleButton));
        List<JButton> infoButtonList = new ArrayList<JButton>(Arrays.asList(menuButton, saveButton));
        List<JPanel> panelList = new ArrayList<JPanel>();
        
		//Edit button and add to button holder
        panelList.add(paddleColorPanel);
        panelList.add(ballColorPanel);
        panelList.add(musicSettingPanel);
        panelList.add(difficultyPanel);
        panelList.add(infoButtonPanel);
               
        //Set layout for the button holder -> add button to the panel
        containerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(0, 0, 0, 0); // -> adding distance for each panel
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.ipady = 20;
        c.fill = GridBagConstraints.BOTH;
        
        for (int i = 0; i < panelList.size(); i++) {
        	c.gridx = 0;
        	c.gridy = i;
        	containerPanel.add(panelList.get(i), c);
        }

        //set layout the the bg panel then add then button holder into it
        bg.setLayout(new GridBagLayout());
        c.insets = new Insets(150, 50, 50, 50); // adjust the size of button and button holder
        bg.add(containerPanel, c);


        //SET LAYOUT FOR EACH INNER PANEL AND ADD ELEMENTS AND COLOR
        for (JPanel panel: panelList) {
        	panel.setLayout(new GridBagLayout());
        	panel.setBackground(new Color(250, 250, 250, 0));
        	panel.setVisible(true);
        }
        
        for (JRadioButton button: buttonList) {
        	button.setForeground(new Color(1, 247, 253));
        	button.setFont(Manager.Constants.textFont);
        }
        
        for (JLabel label: labelList) {
        	label.setForeground(new Color(250, 0, 250));
        	label.setFont(Manager.Constants.titleFont);
        } 
	  	
	  	for (JButton button: infoButtonList) {
	  		button.setOpaque(true);
	  		button.setBorderPainted(true);
	  		button.setFont(Manager.Constants.titleFont);
	  		button.setBackground(new Color(1, 247, 253));
	  		button.setForeground(new Color(254, 10, 207));
	  		button.setBorder(new Manager.RoundBtn(15));
	  		button.setVisible(true);
	  	}    
        
        //Create new constraints
    	GridBagConstraints innerPannel_c = new GridBagConstraints();
      
    	//PADDLE
	  	innerPannel_c.insets = new Insets(10, 0, 10, 0); // -> adding distance for each button
	  	innerPannel_c.weightx = 1;
	  	innerPannel_c.weighty = 1;
	  	innerPannel_c.ipadx = 0;
	  	innerPannel_c.ipady = 0;
	  	innerPannel_c.fill = GridBagConstraints.BOTH;
	  	
	  	innerPannel_c.gridx = 1;
	  	innerPannel_c.gridy = 0;
	  	paddleColorLabel.setHorizontalAlignment(JLabel.CENTER);
    	paddleColorPanel.add(paddleColorLabel, innerPannel_c);
    	
    	innerPannel_c.gridx = 0;
	  	innerPannel_c.gridy = 1;
	  	redPaddleButton.setHorizontalAlignment(JRadioButton.CENTER);
    	paddleColorPanel.add(redPaddleButton, innerPannel_c);
    	innerPannel_c.gridx = 1;
	  	innerPannel_c.gridy = 1;
	  	bluePaddleButton.setHorizontalAlignment(JRadioButton.CENTER);
    	paddleColorPanel.add(bluePaddleButton, innerPannel_c);
    	innerPannel_c.gridx = 2;
	  	innerPannel_c.gridy = 1;
	  	yellowPaddleButton.setHorizontalAlignment(JRadioButton.CENTER);
    	paddleColorPanel.add(yellowPaddleButton, innerPannel_c);

        //BALL
	  	innerPannel_c.gridx = 1;
	  	innerPannel_c.gridy = 0;
	  	ballColorLabel.setHorizontalAlignment(JLabel.CENTER);
    	ballColorPanel.add(ballColorLabel, innerPannel_c);
    	
    	innerPannel_c.gridx = 0;
	  	innerPannel_c.gridy = 1;
	  	redBallButton.setHorizontalAlignment(JRadioButton.CENTER);
	  	ballColorPanel.add(redBallButton, innerPannel_c);
    	innerPannel_c.gridx = 1;
	  	innerPannel_c.gridy = 1;
	  	blueBallButton.setHorizontalAlignment(JRadioButton.CENTER);
    	ballColorPanel.add(blueBallButton, innerPannel_c);
    	innerPannel_c.gridx = 2;
	  	innerPannel_c.gridy = 1;
	  	yellowBallButton.setHorizontalAlignment(JRadioButton.CENTER);
    	ballColorPanel.add(yellowBallButton, innerPannel_c);
    	
        //SETTING SOUND
	  	innerPannel_c.gridx = 0;
	  	innerPannel_c.gridy = 0;
	  	innerPannel_c.gridwidth = 2;
	  	musicSettingLabel.setHorizontalAlignment(JLabel.CENTER);
	  	musicSettingPanel.add(musicSettingLabel, innerPannel_c);
	  	
    	innerPannel_c.gridx = 0;
	  	innerPannel_c.gridy = 1;
	  	innerPannel_c.gridwidth = 1;
	  	onMusicButton.setHorizontalAlignment(JRadioButton.CENTER);
	  	musicSettingPanel.add(onMusicButton, innerPannel_c);
    	innerPannel_c.gridx = 1;
	  	innerPannel_c.gridy = 1;
	  	offMusicButton.setHorizontalAlignment(JRadioButton.CENTER);
	  	musicSettingPanel.add(offMusicButton, innerPannel_c);

        //DIFICU	
	  	innerPannel_c.gridx = 1;
	  	innerPannel_c.gridy = 0;
	  	difficultyLabel.setHorizontalAlignment(JLabel.CENTER);
	  	difficultyPanel.add(difficultyLabel, innerPannel_c);
	  	
    	innerPannel_c.gridx = 0;
	  	innerPannel_c.gridy = 1;
	  	easyButton.setHorizontalAlignment(JRadioButton.CENTER);
	  	difficultyPanel.add(easyButton, innerPannel_c);
    	innerPannel_c.gridx = 1;
	  	innerPannel_c.gridy = 1;
	  	normalButton.setHorizontalAlignment(JRadioButton.CENTER);
	  	difficultyPanel.add(normalButton, innerPannel_c);
    	innerPannel_c.gridx = 2;
	  	innerPannel_c.gridy = 1;
	  	hardButton.setHorizontalAlignment(JRadioButton.CENTER);
	  	difficultyPanel.add(hardButton, innerPannel_c);
	  	
        //Info Button	  
	  	innerPannel_c.insets = new Insets(0, 10, 0, 10);
	  	innerPannel_c.fill = GridBagConstraints.NONE;
    	innerPannel_c.gridx = 0;
	  	innerPannel_c.gridy = 0;
	  	
	  	saveButton.setPreferredSize(new Dimension(120, 80));
	  	menuButton.setPreferredSize(new Dimension(120, 80));
	  	
	  	saveButton.setHorizontalAlignment(JRadioButton.CENTER);
	  	infoButtonPanel.add(saveButton, innerPannel_c);
    	innerPannel_c.gridx = 1;
	  	innerPannel_c.gridy = 0;
	  	menuButton.setHorizontalAlignment(JRadioButton.CENTER);
	  	infoButtonPanel.add(menuButton, innerPannel_c);
    	
	  	
	  	//Edit container panel
        containerPanel.setBackground(new Color(250, 250, 250, 0));
        containerPanel.setVisible(true);

        
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
              
        
        //ADD FUNCTION TO     
        yellowPaddleButton.setActionCommand("Paddle Yellow");       
        bluePaddleButton.setActionCommand("Paddle Blue");
        redPaddleButton.setActionCommand("Paddle Red");
     
        redBallButton.setActionCommand("Ball Red");
        yellowBallButton.setActionCommand("Ball Yellow");    
        blueBallButton.setActionCommand("Ball Blue");

        onMusicButton.setActionCommand("ON");
        offMusicButton.setActionCommand("OFF");

        
	    //difficulty settings

        
      //button to save data
        saveButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("Save")) {
        			
        			//set the customized color of the paddle
        			if (paddleButtonGroup.getSelection().getActionCommand().equals("Paddle Yellow")) {
        				Constants.PADDLE_COLOR = Color.yellow;
        			} else if (paddleButtonGroup.getSelection().getActionCommand().equals("Paddle Blue")) {
        				Constants.PADDLE_COLOR = Color.blue;
        			} else if (paddleButtonGroup.getSelection().getActionCommand().equals("Paddle Red")) {
        				Constants.PADDLE_COLOR = Color.red;
        			}
        			
        			//set the customized color of the ball
        			if (ballButtonGroup.getSelection().getActionCommand().equals("Ball Red")) {
        				Constants.BALL_COLOR = Color.red;
        			} else if (ballButtonGroup.getSelection().getActionCommand().equals("Ball Yellow")) {
        				Constants.BALL_COLOR = Color.yellow;
        			} else if (ballButtonGroup.getSelection().getActionCommand().equals("Ball Blue")) {
        				Constants.BALL_COLOR = Color.blue;
        			}
        			if (musicSettingGroup.getSelection().getActionCommand().equals("ON")) {
       				
        			} else if (musicSettingGroup.getSelection().getActionCommand().equals("OFF")) {
        			
        			}
        		
        			System.out.println(Constants.PADDLE_COLOR);
        			System.out.println(Constants.BALL_COLOR);
        		}
        	}
        });

        
        //Main menu at the bottom
        menuButton.addActionListener(new ActionListener() {
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