package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Alien extends JLabel implements Updatable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Alien() {
		super(new ImageIcon("res/ufo.png"));
		setSize(100, 100);
		setVisible(true);
		setLocation(0,0);
	}

	@Override
	public void update() {
			
	}
}
