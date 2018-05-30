package smthTipaProgi;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import levelpac.GameManager;

public class DefenceContainer {
	private final Set<Defence> defences = Collections.newSetFromMap(new ConcurrentHashMap<Defence, Boolean>());
	private Game game = GameManager.getCurrentGame();
	
	public void drawDefences() {
		for (int i = 1; i <= Const.NUM_OF_DEF; i++) {
			Defence df1 = new Defence(i * game.getFrame().getWidth() / (Const.NUM_OF_DEF + 1));
			game.lp.add(df1, Const.DEFENCE_LAYER);
			defences.add(df1);
		}
	}

	public void removeDefences() {
		for(Defence l:defences) {
			l.removeHealthBar();
			game.lp.remove(l);
		}
		game.lp.repaint();
		defences.removeAll(defences);
	}
	
	public void updateDef() {
		for (Defence def : defences) {
			def.update();
		}
	}
	
	public void remove(Defence def) {
		game.lp.remove(def);
		defences.remove(def);
	}

}
