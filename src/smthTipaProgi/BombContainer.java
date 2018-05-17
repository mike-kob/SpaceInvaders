package smthTipaProgi;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class BombContainer {
	private static final Set<Bomb> bombs = Collections.newSetFromMap(new ConcurrentHashMap<Bomb, Boolean>());

	private static long t1 = 0;
	private static long t2 = 0;
	
	public static void add() {
		t2 = System.currentTimeMillis();
		if(t2-t1>1000) {
		Bomb bomb = new Bomb(Game.fighter.getX());
		Game.lp.add(bomb, Constants.BOMB_LAYER);
		bombs.add(bomb);
		t1 =  System.currentTimeMillis();
		}
		
	}
	public static Set<Bomb> getter() {
		return bombs;
	}
	
	public static void remove(Bomb bomb) {
		Game.lp.remove(bomb);
		bombs.remove(bomb);
		Game.lp.repaint();
	}
	
	public static void update() {
		for(Bomb bomb : bombs) {
			int x = bomb.getX();
			int y = bomb.getY();
			if (x < 0 || y < 0) {
				remove(bomb);
			} else {
				bomb.setLocation(x, y - Constants.BOMB_SPEED);
			}
		}
	}
}
