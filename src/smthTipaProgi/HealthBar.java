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
	switch(level) {
	case 5:
		Game.lp.remove(labels[2]);
		Game.lp.repaint();
		break;
	case 10:
	Game.lp.remove(labels[1]);
	Game.lp.repaint();
	break;
	case 15:
	Game.lp.remove(labels[0]);
	Game.lp.repaint();
}

}

}
