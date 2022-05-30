package main.Algorithms;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
  
public class Sound {
    static Long currentFrame;
    static Clip clip;
    AudioInputStream audioInputStream;
    public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Sound/sound1.wav").getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        currentFrame = clip.getMicrosecondPosition();
    }
    public Sound(String s) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	audioInputStream = AudioSystem.getAudioInputStream(new File("resources/Sound/lose.wav").getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        currentFrame = clip.getMicrosecondPosition();
        
    }
    public void start() throws InterruptedException {
    	clip.start();
    	while (clip.isRunning()) {
    		long counter = clip.getMicrosecondPosition();
    		if (counter > 1000) {
    			clip.stop();
    			clip.close();
    		}
    	}
    }
   
//    public static void main(String[] args) {
//        try {
//            Sound audioPlayer = new Sound();
//            audioPlayer.play();
//            currentFrame = clip.getMicrosecondPosition();
//            Scanner sc = new Scanner(System.in);
//            while (true) { 
//                System.out.println("1. turn on");
//                System.out.println("2. turn off");
//                //System.out.println("3. restart");
//                //System.out.println("4. stop");
//                //System.out.println("5. Jump to specific time");
//                int c = sc.nextInt();
//                audioPlayer.gotoChoice(c);
//                if (c == 0)
//                break;
//            }
//            sc.close();
//        } catch (Exception ex) {
//            System.out.println("Error with playing sound.");
//            ex.printStackTrace();
//          }
//    }
    public void gotoChoice(int c) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        switch (c) {
            case 1:
                resumeAudio();
                break;
            case 2:
                pause();
                break;
            //case 3:
            //    restart();
            //    break;
            //case 4:
            //    stop();
            //    break;
        }
    }
    public void play() {
    	clip.setMicrosecondPosition(0L);
        clip.start();
    }
    public void pause() {
    	clip.stop();
    }
    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
        new File("resources/Sound/sound1.wav").getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    
}