package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Rocket extends JLabel {
	public Rocket() {
		super(new ImageIcon("res/rocket (2).png"));

		setSize(100, 140);
		setLocation(0, 523);
	}

	public void left() {
		int x = this.getX();
		int y = this.getY();

		this.setLocation(x - 10, y);

	}

	public void right() {
		int x = this.getX();
		int y = this.getY();

		this.setLocation(x + 10, y);

	}
}
