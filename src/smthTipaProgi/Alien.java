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
		this.setBackground(new Color(0, 0, 0, 1));
		this.setOpaque(false);
		setSize(64, 44);
		setVisible(true);
		setLocation(x, y);
	}

	@Override
	public void update() {
		for (Bomb bomb : BombContainer.getBombs()) {
			if (isHit(bomb)) {
				BombContainer.remove(bomb);
				AlienContainer.remove(this);
			}
		}
	}

	public boolean isHit(Bomb bomb) {
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
