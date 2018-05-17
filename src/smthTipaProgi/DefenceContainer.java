package smthTipaProgi;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefenceContainer {
	private static final Set<Defence> defences = Collections.newSetFromMap(new ConcurrentHashMap<Defence, Boolean>());

	public static void drawDefences() {
		for (int i = 1; i <= Const.NUM_OF_DEF; i++) {
			Defence df1 = new Defence(i * Game.frame.getWidth() / (Const.NUM_OF_DEF + 1));
			Game.lp.add(df1, Const.DEFENCE_LAYER);
			defences.add(df1);
		}
	}

	public static void updateDef() {
		for (Defence def : defences) {
			def.update();
		}
	}

}
