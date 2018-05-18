package smthTipaProgi;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HealthBar extends JPanel {
	JLabel[] labels = new JLabel[3];

	HealthBar(int x, int y, int height) {
		super();
		setBackground(Color.WHITE);
		setOpaque(true);
		setLocation(x, y + height - 20);
		setSize(Const.WEIGHT_OF_BAR, Const.HEIGHT_OF_BAR);

		for(int i=1;i<=3;i++) {
		JLabel l = new JLabel();
		l.setBackground(Color.GREEN);
		l.setOpaque(true);
		l.setLocation(x, y + height -20);
		l.setSize(i*Const.WEIGHT_OF_BAR / 3, Const.HEIGHT_OF_BAR);
		Game.lp.add(l, Const.DEFENCE_LAYER);
		labels[i-1]=l;
		}
	}

public void remove(int level) {
	if(level==Const.LIFE_OF_ROCK/3) {
		Game.lp.remove(labels[2]);
		Game.lp.repaint();
	}
	else if(level==2*Const.LIFE_OF_ROCK/3) {
		Game.lp.remove(labels[1]);
		Game.lp.repaint();
	}else if(level==Const.LIFE_OF_ROCK){
		Game.lp.remove(labels[0]);
		Game.lp.repaint();
	}

}

}
