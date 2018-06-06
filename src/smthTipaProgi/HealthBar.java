package smthTipaProgi;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import levelpac.GameManager;

public class HealthBar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[] labels = new JLabel[3];
	private Game game = GameManager.getCurrentGame();
	//d
	HealthBar(int x, int y, int height) {
		super();
		setBackground(Color.WHITE);
		setOpaque(true);
		setLocation(x, y + height - 20);
		setSize(Const.WEIGHT_OF_BAR, Const.HEIGHT_OF_BAR);

		for (int i = 1; i <= 3; i++) {
			JLabel l = new JLabel();
			l.setBackground(Color.GREEN);
			l.setOpaque(true);
			l.setLocation(x, y + height - 20);
			l.setSize(i * Const.WEIGHT_OF_BAR / 3, Const.HEIGHT_OF_BAR);
			game.getLp().add(l, Const.DEFENCE_LAYER);
			labels[i - 1] = l;
		}
	}
	//d
	public void remove(int level) {
		if (level == Const.LIFE_OF_ROCK / 3) {
			game.getLp().remove(labels[2]);
			game.getLp().repaint();
		} else if (level == 2 * Const.LIFE_OF_ROCK / 3) {
			game.getLp().remove(labels[1]);
			game.getLp().repaint();
		} else if (level == Const.LIFE_OF_ROCK) {
			game.getLp().remove(labels[0]);
			game.getLp().repaint();
		}
	}
	//d
	public void removeBars() {
		for (JLabel l : labels) {
			game.getLp().remove(l);
			game.getLp().repaint();
		}
	}

}
