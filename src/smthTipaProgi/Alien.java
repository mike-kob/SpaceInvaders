package smthTipaProgi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Alien extends JLabel implements Updatable {

	private static final long serialVersionUID = 1L;

	public Alien(int x, int y, int rang) {
		super();

		switch (rang) {
		case 0:
			this.setIcon(new ImageIcon(Const.UFO_HIGH_PATH));
			break;
		case 1:
			this.setIcon(new ImageIcon(Const.UFO_MID_PATH));
			break;
		case 2:
			this.setIcon(new ImageIcon(Const.UFO_MID_PATH));
			break;
		default:
			this.setIcon(new ImageIcon(Const.UFO_LOW_PATH));
		}

		this.setBackground(new Color(0, 0, 0, 1));
		setSize(64, 44);
		setLocation(x, y);
	}

	@Override
	public void update() {
		for (Bomb bomb : BombContainer.getBombs()) {
			if (isHit(bomb)) {
				BombContainer.addAid(this);
				BombContainer.remove(bomb);
				AlienContainer.remove(this);
			}
		}
	}

	private boolean isHit(Bomb bomb) {
		int x = AlienContainer.getGridX() + this.getX();
		int y = AlienContainer.getGridY() + this.getY();

		if (x - 20 < bomb.getX() && bomb.getX() + bomb.getWidth() < x + this.getWidth() + 20) {
			if (y + this.getHeight() >= bomb.getY() && (y < bomb.getY())) {
				return true;
			}
		}
		return false;
	}
}
