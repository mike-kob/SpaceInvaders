package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel{
	
	private static final long serialVersionUID = 1L;

	public Bomb(int x) {
		super();
		ImageIcon im = new ImageIcon(Const.BOMB_PATH);
		this.setIcon(im);
		this.setBackground(Const.TRANSPARENT);
		this.setOpaque(false);
 		setLocation(x + 20, Const.BOMB_STARTING_LEVEL);
		setSize(im.getIconWidth(), im.getIconHeight());
		
	}
}
