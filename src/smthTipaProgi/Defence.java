package smthTipaProgi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Defence extends JLabel implements Updatable {
	private int life;
	private HealthBar hb;
	
	public  Defence(int mid) {
		super();
		ImageIcon im = new ImageIcon("res/asteroid (1).png");
		this.setIcon(im);
		this.setBackground(new Color(255,255,255,0));
		this.setOpaque(false);
		setSize(im.getIconWidth(), im.getIconHeight());
		setLocation(mid-this.getWidth()/2, Constants.DEFENCE_Y);
		System.out.println(this.getWidth()/2);
		
		
		hb = new HealthBar(mid-this.getWidth()/2, Constants.DEFENCE_Y,im.getIconHeight());
		Game.lp.add(hb, Constants.DEFENCE_LAYER);
	}
	
	
	public void update() {
		for (Bomb bomb : BombContainer.getBombs()) {
			if (isHit(bomb)) {
				BombContainer.remove(bomb);
				life++;
				hb.remove(life);
			}
		}
		
		for (Dynamite dyn : BombContainer.getEnemyBombs()) {
			if (isHit(dyn)) {
				BombContainer.removeDyn(dyn);
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
	
	public boolean isHit(Dynamite dyn) {
		int	x = this.getX();
		int	y = this.getY();
		
			if (x-20<=dyn.getX()&&x+this.getWidth()>=dyn.getX()) {
				return y<=dyn.getY();
			}
			return false;
		}
}
