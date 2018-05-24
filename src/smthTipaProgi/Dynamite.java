package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Dynamite extends JLabel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Dynamite(Alien alien) {
		super();
		ImageIcon im = new ImageIcon(Const.DYNAMITE_PATH);
		this.setIcon(im);
		this.setBackground(Const.TRANSPARENT);
		this.setOpaque(false);
 		setLocation(alien.getX() + AlienContainer.getGridX()+17, alien.getY() + AlienContainer.getGridY()+17);
		setSize(im.getIconWidth(), im.getIconHeight());
	}
}
