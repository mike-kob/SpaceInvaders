package smthTipaProgi;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game {
	static JFrame frame = new JFrame();
	static JLayeredPane lp = frame.getLayeredPane();;
	static Rocket fighter;
	static JPanel grid;

	static final Alien[][] aliens = new Alien[Constants.ALIEN_ROWS][Constants.ALIEN_COLUMNS];
	public static final Set<Bomb> bombs = Collections.newSetFromMap(new ConcurrentHashMap<Bomb, Boolean>());

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				drawEverything();
				frame.addKeyListener(new GameListener());
				addBombs();
				moveGrid();
			}
		});
	}

	private static void addBombs() {

		new Thread() {
			public void run() {
				boolean flag = true;
				while (flag) {
					updateAll(bombs);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();

	}

	private static void drawEverything() {
		frame.setSize(1280, 980);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l = new JLabel(new ImageIcon("res/background.png"));
		l.setLocation(0, 0);
		l.setVisible(true);
		l.setSize(1280, 980);

		lp.add(l, Constants.BACK_LAYER);
		fighter = new Rocket(0, 770);
		lp.add(fighter, Constants.ROCKET_LAYER);
		grid = new JPanel(null);

		for (int i = 0; i < Constants.ALIEN_ROWS; i++) {
			for (int j = 0; j < Constants.ALIEN_COLUMNS; j++) {
				Alien cur = new Alien();
				cur.setLocation(i * 150, j * 40);
				aliens[i][j] = cur;
				grid.add(cur);
			}
		}

		grid.setOpaque(true);
		grid.setSize(700, 450);

		JLabel d = new JLabel(new ImageIcon("res/defence.png"));
		d.setLocation(50, 570);
		d.setSize(250, 200);
		lp.add(d, Constants.DEFENCE_LAYER);

		grid.setOpaque(true);
		grid.setSize(700, 300);

		grid.setLocation((frame.getWidth() - grid.getWidth()) / 2, 50);
		grid.setVisible(true);
		lp.add(grid, Constants.ALIEN_LAYER);
	}

	public static void moveGrid() {
		new Thread() {
			private int direction = 50;

			public void run() {
				int x = grid.getX();
				int y = grid.getY();
				while (y + grid.getHeight() < frame.getHeight()) {
					x = grid.getX();
					y = grid.getY();
					try {
						if (x + grid.getWidth() > frame.getWidth() || x < 0) {
							direction *= -1;
							y += 50;
							grid.setLocation(x, y);
							Thread.sleep(1000);
						}

						grid.setLocation(x + direction, y);
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
				}
			}
		}.start();
	}

	public static void updateAll(Set<? extends Updatable> elements) {
		for (Updatable temp : elements) {
			temp.update();
		}
	}
}
