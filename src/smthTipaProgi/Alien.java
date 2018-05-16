package smthTipaProgi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Alien extends JLabel implements Updatable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Alien(int x, int y, int rang) {
		super();
	
		switch (rang) {
		case 0:
			this.setIcon(new ImageIcon("res/ufo/rowhigh.png"));
			break;
		case 1:
			this.setIcon(new ImageIcon("res/ufo/rowmid.png"));
			break;
		case 2:
			this.setIcon(new ImageIcon("res/ufo/rowmid.png"));
			break;
		default:
			this.setIcon(new ImageIcon("res/ufo/rowlow.png"));
		}
		this.setBackground(new Color(255,255,255));
		setSize(64, 44);
		setVisible(true);
		setLocation(x, y);
	}

	@Override
	public void update() {
		for (Bomb bomb : Game.bombs) {
			if (isHit(bomb)) {
				Game.aliens.remove(this);
				Game.bombs.remove(bomb);
				Game.grid.remove(this);
				Game.lp.remove(bomb);
				Game.lp.repaint();
				System.out.println("bla");
			}
		}
	}

	public boolean isHit(Bomb bomb) {
		int x = Game.grid.getX() + this.getX();
		int y = Game.grid.getY() + this.getY();

		if (x - 20 < bomb.getX()  && bomb.getX() + bomb.getWidth() < x + this.getWidth() + 20) {
			return y + this.getHeight() >= bomb.getY();
		}
		return false;
	}
}
