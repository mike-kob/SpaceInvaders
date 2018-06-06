package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import levelpac.GameManager;

public class Rocket extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int STEP = 3;
	private Game game = GameManager.getCurrentGame();

	public Rocket(int x, int y) {
		super(new ImageIcon(Const.ROCKET_PATH));
		setSize(100, 140);
		setLocation(x, y);
	}

	public void left() {
		if (this.getX() + 20 > 0) {
			move(this, -STEP);
		}
	}

	public void right() {
		if (this.getX() + this.getWidth() < 1280) {
			move(this, STEP);
		}
	}

	private void move(Rocket rocket, int step) {
		int y = this.getY();
		new Thread() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					int x = rocket.getX();
					rocket.setLocation(x + step, y);
					pause(5);
				}
			}
		}.start();
	}

	public void explode(boolean fail) {
		ImageIcon boom = new ImageIcon(Const.BOOM_PATH);
		int x = this.getX();
		int y = this.getY();
		this.setIcon(boom);
		this.setSize(200,170);
		this.setLocation(this.getX()+this.getWidth()/2-boom.getIconWidth()/2-20, this.getY()-20);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		if(!fail) {
		this.setSize(100, 140);
		this.setLocation(x, y);
		this.setIcon(new ImageIcon(Const.ROCKET_PATH));
		} else {
			game.getLp().remove(this);
			game.getLp().repaint();
		}
	}
	
	public boolean plusLife(JLabel aid) {
		int x = this.getX();
		int y = this.getY();

		if (x <= aid.getX() && x + this.getWidth() >= aid.getX()) {
			return y <= aid.getY();
		}
		return false;
	}
	
	public boolean isHit(Dynamite dyn) {
		int x = this.getX();
		int y = this.getY();

		if (x <= dyn.getX() && x + this.getWidth() >= dyn.getX()) {
			return y <= dyn.getY()&&dyn.getY()<=y+this.getHeight()/3;
		}
		return false;
	}

	private void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
