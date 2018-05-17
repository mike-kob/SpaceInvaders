package smthTipaProgi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Defence extends JLabel implements Updatable {
	private int life;
	private HealthBar hb;
	
	public  Defence(int x,int y) {
		super();
		ImageIcon im = new ImageIcon("res/asteroid.png");
		this.setIcon(im);
		this.setBackground(new Color(0,0,0,1));
		this.setOpaque(false);
		setLocation(x, y);
		setSize(im.getIconWidth(), im.getIconHeight());
		
		hb = new HealthBar(x,y,im.getIconHeight());
		Game.lp.add(hb, Constants.DEFENCE_LAYER);
		
	}
	
	
	public void update() {
		for (Bomb bomb : BombContainer.getter()) {
			if (isHit(bomb)) {
				BombContainer.remove(bomb);
				life++;
				hb.remove(life);
			}
		}
	}

	public boolean isHit(Bomb bomb) {
	int	x = this.getX();
	int	y = this.getY();
	
		if (x<=bomb.getX()&&x+this.getWidth()>=bomb.getX()+bomb.getWidth()) {
			return y+this.getHeight()>=bomb.getY();
		}
		return false;
	}
}
