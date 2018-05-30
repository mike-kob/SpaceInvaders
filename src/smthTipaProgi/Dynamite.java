package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import levelpac.GameManager;

public class Dynamite extends JLabel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game = GameManager.getCurrentGame();
	
	public Dynamite(Alien alien) {
		super();
		ImageIcon im = new ImageIcon(Const.DYNAMITE_PATH);
		this.setIcon(im);
		this.setBackground(Const.TRANSPARENT);
		this.setOpaque(false);
 		setLocation(alien.getX() + game.getAlienCont().getGridX()+17, alien.getY() + game.getAlienCont().getGridY()+17);
		setSize(im.getIconWidth(), im.getIconHeight());
	}
}
