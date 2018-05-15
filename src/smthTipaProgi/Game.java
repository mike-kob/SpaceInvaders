package smthTipaProgi;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
				frame.addKeyListener(keyL);

			}
		});
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

	static KeyListener keyL = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				fighter.left();

			} else if (key == KeyEvent.VK_RIGHT) {
				fighter.right();
			} else if (key == KeyEvent.VK_SPACE) {
				Bomb bomb = new Bomb(fighter.getX());
				System.out.println(fighter.getX());
				lp.add(bomb, Constants.BOMB_LAYER);
				bombs.add(bomb);
			}
		}

	};

	public static void updateAll(Set<? extends Updatable> elements) {

		for (Updatable temp : elements) {
			temp.update();
		}
	}
}
