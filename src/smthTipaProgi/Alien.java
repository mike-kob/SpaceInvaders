package smthTipaProgi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import smthTipaProgi.Const;
import smthTipaProgi.PointsContainer;

public class Alien extends JLabel implements Updatable {

	private static final long serialVersionUID = 1L;
	private int amount;
	Mp3Player mp = new Mp3Player("res/Explosion+1.mp3");
	


	public Alien(int x, int y, int rang) {
		super();

		switch (rang) {
		case 0:
			this.setIcon(new ImageIcon(Const.UFO_HIGH_PATH));
			this.amount = Const.POINTS_FOR_HIGHT;
			break;
		case 1: case 2:
			this.setIcon(new ImageIcon(Const.UFO_MID_PATH));
			this.amount = Const.POINTS_FOR_MID;
			break;
		default:
			this.setIcon(new ImageIcon(Const.UFO_LOW_PATH));
			this.amount = Const.POINTS_FOR_LOW;
		}

		this.setBackground(new Color(0, 0, 0, 1));
		setSize(64, 44);
		setLocation(x, y);
	}

	@Override
	public void update() {
		for (Bomb bomb : BombContainer.getBombs()) {
			if (isHit(bomb)) {
				PointsContainer.change(this.amount);
				BombContainer.addAid(this);
				BombContainer.remove(bomb);
				AlienContainer.remove(this);
				new Thread() {
					public void run() {
						mp.play();
					}
				}.start();
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
