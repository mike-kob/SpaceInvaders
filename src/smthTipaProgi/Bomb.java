package smthTipaProgi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel{
	
	private static final long serialVersionUID = 1L;

	public Bomb(int x) {
		super();
		ImageIcon im = new ImageIcon("res/bomb (2).png");
		this.setIcon(im);
		setSize(im.getIconWidth(), im.getIconHeight());
	}



}
