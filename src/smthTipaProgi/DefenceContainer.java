package smthTipaProgi;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefenceContainer {
	public static final Set<Defence> defence = Collections.newSetFromMap(new ConcurrentHashMap<Defence, Boolean>());
	
	public static void add() {
		
		for(int i =1;i<=Constants.NUM_OF_DEF;i++) {
		Defence df1 = new Defence(i*Game.frame.getWidth()/(Constants.NUM_OF_DEF+1));
		Game.lp.add(df1,Constants.DEFENCE_LAYER);
		defence.add(df1);
		
	}
	}
		
		public static void updateDef() {
			for(Defence def:defence) {
				def.update();
			}
		}
		
		
		
}
