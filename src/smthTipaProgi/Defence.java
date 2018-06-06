package smthTipaProgi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import levelpac.GameManager;

public class Defence extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int life;
	private HealthBar hb;
	private ImageIcon skin;
	private Game game = GameManager.getCurrentGame();
	//d
	public Defence(int mid) {
		super();
		skin = new ImageIcon(Const.ASTEROID_PATH);
		this.setIcon(skin);
		this.setBackground(Const.TRANSPARENT);
		this.setOpaque(false);
		setSize(skin.getIconWidth(), skin.getIconHeight());
		setLocation(mid - this.getWidth() / 2, Const.DEFENCE_Y);

		hb = new HealthBar(mid - this.getWidth() / 2, Const.DEFENCE_Y, skin.getIconHeight());
		game.getLp().add(hb, Const.DEFENCE_LAYER);
		
	}
	//d
	public void changeRock(int level) {
		String path = String.format("res/rock/������_%d.png", (level + 1));
		skin = new ImageIcon(path);
		this.setIcon(skin);
		hb.remove(life);
		if (level == Const.LIFE_OF_ROCK) {
			skin = new ImageIcon(Const.ROCK_BUBUH_PATH);
			this.setIcon(skin);
			game.getLp().remove(hb);
			try {
				Thread.sleep(700);
			} catch (Exception e) {
			}
			game.getDefenceCont().remove(this);
		}
	

	}
	//d
	public void update() {
		for (Bomb bomb : game.getBombCont().getBombs()) {
			if (isHit(bomb)) {
				game.getBombCont().remove(bomb);
				life++;
				changeRock(life);
			}
		}

		for (Dynamite dyn : game.getBombCont().getEnemyBombs()) {
			if (isHit(dyn)) {
				game.getBombCont().removeDyn(dyn);
				life++;
				changeRock(life);

			}
		}
	}
	//d
	public boolean isHit(Bomb bomb) {
		int x = this.getX();
		int y = this.getY();

		if (x <= bomb.getX() && x + this.getWidth() >= bomb.getX() + bomb.getWidth()) {
			return y + this.getHeight() >= bomb.getY();
		}
		return false;
	}
	//d
	public boolean isHit(Dynamite dyn) {
		int x = this.getX();
		int y = this.getY();

		if (x - 20 <= dyn.getX() && x + this.getWidth() >= dyn.getX()) {
			return y <= dyn.getY() && dyn.getY()<y+20;
		}
		return false;
	}
	//d
	public void removeHealthBar() {
		hb.removeBars();
		game.getLp().remove(hb);
	}
}
