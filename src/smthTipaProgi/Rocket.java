package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Rocket extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int STEP = 2;
	public int lives = 5;

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
				for (int i = 0; i < 10; i++) {
					int x = rocket.getX();
					rocket.setLocation(x + step, y);
					pause(5);
				}
			}
		}.start();
	}

	public void explode() {
		ImageIcon boom = new ImageIcon("res/boom2.gif");
		int x = this.getX();
		int y = this.getY();
		this.setIcon(boom);
		this.setSize(200,170);
		this.setLocation(this.getX()+this.getWidth()/2-boom.getIconWidth()/2-20, this.getY()-20);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		this.setSize(100, 140);
		this.setLocation(x, y);
		this.setIcon(new ImageIcon(Const.ROCKET_PATH));
		
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
