package smthTipaProgi;

import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BombContainer {
	private static final Set<Bomb> bombs = Collections.newSetFromMap(new ConcurrentHashMap<Bomb, Boolean>());
	private static final Set<JLabel> heal = Collections.newSetFromMap(new ConcurrentHashMap<JLabel, Boolean>());
	private static final Set<Dynamite> enemyBombs = Collections
			.newSetFromMap(new ConcurrentHashMap<Dynamite, Boolean>());

	private static long lastTimeAdded = 0;
	private static long currentTimeAdded = 0;

	public static void add() {
		currentTimeAdded = System.currentTimeMillis();
		if (currentTimeAdded - lastTimeAdded > Const.BOMB_TIME_INTERVAL) {
			Bomb bomb = new Bomb(Game.fighter.getX() + 15);
			Game.lp.add(bomb, Const.BOMB_LAYER);
			bombs.add(bomb);
			lastTimeAdded = System.currentTimeMillis();
		}

	}

	public static Set<Bomb> getBombs() {
		return bombs;
	}
	public static Set<JLabel> getHeel() {
		return heal;
	}
	
	public static Set<Dynamite> getEnemyBombs() {
		return enemyBombs;
	}

	public static void addEnemyBomb() {
		Random rand = new Random();
		int column = rand.nextInt(Const.ALIEN_COLUMNS);

		while (AlienContainer.getLastInColumn(column) == null && !AlienContainer.isEmpty()) {
			column = rand.nextInt(Const.ALIEN_COLUMNS);
		}
		
		Dynamite dyn = new Dynamite(AlienContainer.getLastInColumn(column));
		enemyBombs.add(dyn);
		Game.lp.add(dyn, Const.DYNAMITE_LAYER);
	}

	public static void addAid(Alien alien) {
		Random rand = new Random();
		double chance = rand.nextDouble();
	
		if(chance<Const.HEAL_PROBABILITY) {
			JLabel aid = new JLabel(new ImageIcon("res/first-aid-kit.png"));
			aid.setSize(64,64);
			aid.setLocation(alien.getX()+AlienContainer.getGridX(), alien.getY()+AlienContainer.getGridY());
			heal.add(aid);
			Game.lp.add(aid, Const.DYNAMITE_LAYER);
		}
	}	
	
	public static void remove(Bomb bomb) {
		Game.lp.remove(bomb);
		bombs.remove(bomb);
		Game.lp.repaint();
	}

	public static void removeDyn(Dynamite dyn) {
		Game.lp.remove(dyn);
		enemyBombs.remove(dyn);
		Game.lp.repaint();
	}
	
	public static void removeAid(JLabel aid) {
		Game.lp.remove(aid);
		heal.remove(aid);
		Game.lp.repaint();
	}

	public static void update() {
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
			if (x > Game.frame.getWidth() || y > Game.frame.getHeight()) {
				removeDyn(dyn);
			} else {
				dyn.setLocation(x, y + Const.DYN_SPEED);
			}
		}
		
		for (JLabel aid : heal) {
			int x = aid.getX();
			int y = aid.getY();
			if (x > Game.frame.getWidth() || y > Game.frame.getHeight()) {
				removeAid(aid);
			} else {
				aid.setLocation(x, y + Const.DYN_SPEED);
			}
		}
	}
}
