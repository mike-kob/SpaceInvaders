package smthTipaProgi;

import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BombContainer {
	private static final Set<Bomb> bombs = Collections.newSetFromMap(new ConcurrentHashMap<Bomb, Boolean>());
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
	}
}
