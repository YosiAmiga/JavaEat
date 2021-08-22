package controller;

import java.io.File;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sounds {
	private final static String badSound = "sounds\\error.wav";
	private final static String goodSound = "sounds\\toggle.wav";
	private final static String addSound = "sounds\\success.wav";
	private final static String programOn = "sounds\\programOn.wav";
	private final static String login = "sounds\\loginOK.wav";
	private final static String exit = "sounds\\exit.wav";
	
	
	public void exitSound() throws Exception{
	    File yourFile = new File(exit);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}
	
	public void loginSound() throws Exception{
	    File yourFile = new File(login);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}
	
	public void programOnSound() throws Exception{
	    File yourFile = new File(programOn);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}
	
	public void errorSound() throws Exception{
	    File yourFile = new File(badSound);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}

	public void successSound() throws Exception{
	    File yourFile = new File(goodSound);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}
	
	public void addSound() throws Exception{
	    File yourFile = new File(addSound);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format); 
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}

}
