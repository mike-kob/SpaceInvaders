package smthTipaProgi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel implements Updatable {
	public static final int STEP = 2;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bomb(int x) {
		super();
		ImageIcon im = new ImageIcon("res/bomb (2).png");
		this.setIcon(im);
		this.setBackground(new Color(255,255,255));
		this.setOpaque(true);
		setLocation(x + 20, Constants.ROCKET_LEVEL);
		setSize(im.getIconWidth(), im.getIconHeight());
	}

	@Override
	public void update() {

		int x = this.getX();
		int y = this.getY();
		if (x < 0 || y < 0) {
			Game.lp.remove(this);
			Game.bombs.remove(this);
			Game.lp.repaint();
		} else {
			this.setLocation(x, y - STEP);
			Game.lp.repaint();
		}
	}

}
