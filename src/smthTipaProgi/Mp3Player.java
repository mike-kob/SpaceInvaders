package smthTipaProgi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Mp3Player {

	File file;
	FileInputStream fis;
	BufferedInputStream bis;
	Player player;
Mp3Player(String str){
	file = new File(str);
	fis = null;
	try {
		fis = new FileInputStream(file);
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	bis = new BufferedInputStream(fis);
	

}

public void play() {
	try {
		player = new Player(bis);
		player.play();
	
	}catch(JavaLayerException e)
	{
	}
}

public void close() {
	player.close();
	
}

}
