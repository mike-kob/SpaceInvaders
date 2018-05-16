package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel implements Updatable {
	public static final int STEP = 2;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bomb(int x) {
		super(new ImageIcon("res/bomb (2).png"));
		setLocation(x+20, 720);
		setSize(50, 50);
	}
	 
	@Override
	public void update() {
	
		int x = this.getX();
		int y = this.getY();
		if(x<0 || y<0) {
		Game.lp.remove(this);
		Game.bombs.remove(this);
		Game.lp.repaint();
		}else {
		this.setLocation(x,y-STEP);
		}
	}

}
