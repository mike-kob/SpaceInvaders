package smthTipaProgi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Mp3Player {

	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private Player player;

	public Mp3Player(String str) {
		file = new File(str);
		fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
		}
		bis = new BufferedInputStream(fis);
	}

	public void play() {
		try {
			player = new Player(bis);
			player.play();
		} catch (JavaLayerException e) {
		}
	}

	public void close() {
		player.close();
	}

}
