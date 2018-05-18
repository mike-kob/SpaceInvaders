package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Defence extends JLabel implements Updatable {
	private int life;
	private HealthBar hb;
	ImageIcon[] imageIcons = new ImageIcon[9];
	String str = "res/камень_1.png";
	ImageIcon im;
	int middle;

	public Defence(int mid) {
		super();
		im = new ImageIcon(str);
		middle = mid;
		this.setIcon(im);
		this.setBackground(Const.TRANSPARENT);
		this.setOpaque(false);
		setSize(im.getIconWidth(), im.getIconHeight());
		setLocation(mid - this.getWidth() / 2, Const.DEFENCE_Y);

		hb = new HealthBar(mid - this.getWidth() / 2, Const.DEFENCE_Y, im.getIconHeight());
		Game.lp.add(hb, Const.DEFENCE_LAYER);
		
	}

	public void changeRock(int level) {

		str = String.format("res/камень_%d.png", (level + 1));
		im = new ImageIcon(str);
		this.setIcon(im);
		hb.remove(life);
		if (level == Const.LIFE_OF_ROCK) {
			im = new ImageIcon("res/bubuh.gif");
			this.setIcon(im);
			Game.lp.remove(hb);

			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			DefenceContainer.remove(this);

			// Game.lp.repaint();

		}
	

	}

	public void update() {
		for (Bomb bomb : BombContainer.getBombs()) {
			if (isHit(bomb)) {
				BombContainer.remove(bomb);
				life++;
				changeRock(life);
			}
		}

		for (Dynamite dyn : BombContainer.getEnemyBombs()) {
			if (isHit(dyn)) {
				BombContainer.removeDyn(dyn);
				life++;
				changeRock(life);

			}
		}
	}

	public boolean isHit(Bomb bomb) {
		int x = this.getX();
		int y = this.getY();

		if (x <= bomb.getX() && x + this.getWidth() >= bomb.getX() + bomb.getWidth()) {
			return y + this.getHeight() >= bomb.getY();
		}
		return false;
	}

	public boolean isHit(Dynamite dyn) {
		int x = this.getX();
		int y = this.getY();

		if (x - 20 <= dyn.getX() && x + this.getWidth() >= dyn.getX()) {
			return y <= dyn.getY();
		}
		return false;
	}
}
