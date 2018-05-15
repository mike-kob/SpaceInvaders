package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel implements Updatable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bomb(int x) {
		super(new ImageIcon("res/bomb (2).png"));
		setLocation( x+20, 480);
		setSize(50, 50);
		System.out.println("ooops");
	}
	 
	@Override
	public void update() {
		int x = this.getX();
		System.out.println("x"+x);
		int y = this.getY();
		System.out.println("y"+y);
		this.setLocation(x,y-10);
	
	}

}
