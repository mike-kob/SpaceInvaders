package smthTipaProgi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Dynamite extends JLabel  {
	
	public Dynamite(int x) {
		super();
		ImageIcon im = new ImageIcon("res/dynamite.png");
		this.setIcon(im);
		this.setBackground(new Color(255, 255, 255, 30));
		this.setOpaque(false);
 		setLocation(x + 20, Constants.ROCKET_LEVEL);
		setSize(im.getIconWidth(), im.getIconHeight());
	}
}
