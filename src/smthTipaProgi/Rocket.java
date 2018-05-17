package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Rocket extends JLabel {
	public static final int STEP = 2;
	public int lives = 5;

	public Rocket(int x, int y) {
		super(new ImageIcon("res/rocket (2).png"));
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
				for (int i = 0; i < 10; i++) {
					int x = rocket.getX();
					rocket.setLocation(x + step, y);
					pause(5);
				}
			}
		}.start();
	}

	public boolean isHit(Dynamite dyn) {
		int x = this.getX();
		int y = this.getY();

		if (x <= dyn.getX() && x + this.getWidth() >= dyn.getX()) {
			return y <= dyn.getY();
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
