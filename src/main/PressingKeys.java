package main;

import main.UI.MainMenu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PressingKeys extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        int action = e.getKeyCode();

        if(action == KeyEvent.VK_ENTER) {
//			String file = "Resources/scores.txt";
//			try {
//				FileWriter writer = new FileWriter(file, true);
//				BufferedWriter fileWriter = new BufferedWriter(writer);
//
//				fileWriter.write(IntroMenu.playerName.getText());
//				fileWriter.newLine();
//				fileWriter.flush();
//			} catch (IOException ex) {
//				throw new RuntimeException(ex);
//			}

            IntroMenu.window.dispose();
            try {
                new MainMenu();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int action = e.getKeyCode();
    }

}