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
		setLocation(x, y + height);
		setSize(Constants.WEIGHT_OF_BAR, Constants.HEIGHT_OF_BAR);

		for(int i=1;i<=3;i++) {
		JLabel l = new JLabel();
		l.setBackground(Color.GREEN);
		l.setOpaque(true);
		l.setLocation(x, y + height);
		l.setSize(i*Constants.WEIGHT_OF_BAR / 3, Constants.HEIGHT_OF_BAR);
		Game.lp.add(l, Constants.DEFENCE_LAYER);
		labels[i-1]=l;
		}
	}

public void remove(int level) {
	switch(level) {
	case 2:
		Game.lp.remove(labels[2]);
		Game.lp.repaint();
		break;
	case 4:
	Game.lp.remove(labels[1]);
	Game.lp.repaint();
	break;
	case 6:
	Game.lp.remove(labels[0]);
	Game.lp.repaint();
}

}

}
