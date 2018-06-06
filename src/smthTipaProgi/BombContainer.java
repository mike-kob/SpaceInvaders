package smthTipaProgi;

import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import levelpac.GameManager;

public class BombContainer {
	private Set<Bomb> bombs = Collections.newSetFromMap(new ConcurrentHashMap<Bomb, Boolean>());
	private Set<JLabel> heal = Collections.newSetFromMap(new ConcurrentHashMap<JLabel, Boolean>());
	private Set<Dynamite> enemyBombs = Collections
			.newSetFromMap(new ConcurrentHashMap<Dynamite, Boolean>());

	private long lastTimeAdded = 0;
	private long currentTimeAdded = 0;
	private Game game = GameManager.getCurrentGame();
	//d
	public void add() {
		currentTimeAdded = System.currentTimeMillis();
		if (currentTimeAdded - lastTimeAdded > Const.BOMB_TIME_INTERVAL) {
			Bomb bomb = new Bomb(game.getFighter().getX() + 15);
			game.getLp().add(bomb, Const.BOMB_LAYER);
			bombs.add(bomb);
			lastTimeAdded = System.currentTimeMillis();
		}

	}
//m
	public void removeAllBombs() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		for(JLabel l:bombs) {
			game.getLp().remove(l);
		}
		for(JLabel l:heal) {
			game.getLp().remove(l);
		}
		for(JLabel l:enemyBombs) {
			game.getLp().remove(l);
		}
		bombs.removeAll(bombs);
		heal.removeAll(heal);
		enemyBombs.removeAll(enemyBombs);
		game.getLp().repaint();
	}
	//m
	public Set<Bomb> getBombs() {
		return bombs;
	}
	//m
	public Set<JLabel> getHeel() {
		return heal;
	}
	//m
	public Set<Dynamite> getEnemyBombs() {
		return enemyBombs;
	}
	//m
	public void addEnemyBomb() {
		Random rand = new Random();
		int column = rand.nextInt(Const.ALIEN_COLUMNS);

		try {
		while (game.getAlienCont().getLastInColumn(column) == null && !game.getAlienCont().isEmpty()) {
			column = rand.nextInt(Const.ALIEN_COLUMNS);
		}
		
		Dynamite dyn = new Dynamite(game.getAlienCont().getLastInColumn(column));
		enemyBombs.add(dyn);
		game.getLp().add(dyn, Const.DYNAMITE_LAYER);
		} catch (Exception e) {
		}
	}
    //m
	public void addAid(Alien alien) {
		Random rand = new Random();
		double chance = rand.nextDouble();
	
		if(chance<Const.HEAL_PROBABILITY) {
			JLabel aid = new JLabel(new ImageIcon("res/first-aid-kit.png"));
			aid.setSize(64,64);
			aid.setLocation(alien.getX()+game.getAlienCont().getGridX(), alien.getY()+game.getAlienCont().getGridY());
			heal.add(aid);
			game.getLp().add(aid, Const.DYNAMITE_LAYER);
		}
	}	
	//m
	public void remove(Bomb bomb) {
		game.getLp().remove(bomb);
		bombs.remove(bomb);
		game.getLp().repaint();
	}
	//m
	public void removeDyn(Dynamite dyn) {
		game.getLp().remove(dyn);
		enemyBombs.remove(dyn);
		game.getLp().repaint();
	}
	//m
	public void removeAid(JLabel aid) {
		game.getLp().remove(aid);
		heal.remove(aid);
		game.getLp().repaint();
	}
	//m
	public void update() {
		for (Bomb bomb : bombs) {
			int x = bomb.getX();
			int y = bomb.getY();
			if (x < 0 || y < 0) {
				remove(bomb);
			} else {
				bomb.setLocation(x, y - Const.BOMB_SPEED);
			}
		}

		for (Dynamite dyn : enemyBombs) {
			int x = dyn.getX();
			int y = dyn.getY();
			if (x > game.getFrame().getWidth() || y > game.getFrame().getHeight()) {
				removeDyn(dyn);
			} else {
				dyn.setLocation(x, y + Const.DYN_SPEED);
			}
		}
		
		for (JLabel aid : heal) {
			int x = aid.getX();
			int y = aid.getY();
			if (x > game.getFrame().getWidth() || y > game.getFrame().getHeight()) {
				removeAid(aid);
			} else {
				aid.setLocation(x, y + Const.DYN_SPEED);
			}
		}
	}
}
