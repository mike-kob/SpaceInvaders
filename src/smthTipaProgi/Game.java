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

	static final Set<Alien> aliens = Collections.newSetFromMap(new ConcurrentHashMap<Alien, Boolean>());
	//public static final Set<Bomb> bombs = Collections.newSetFromMap(new ConcurrentHashMap<Bomb, Boolean>());

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				drawEverything();
				frame.addKeyListener(new GameListener());
				BombFactory();
				addAliens();
				gridFactory();
			}
		});
	}

	private static void BombFactory() {
		new Thread() {
			public void run() {
				boolean flag = true;
				while (flag) {
					BombContainer.update();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
	}
	
	
	private static void addAliens() {
		new Thread() {
			public void run() {
				boolean flag = true;
				while (flag) {
					updateAll(AlienContainer.getAliens());
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

		lp.add(AlienContainer.getPanel(), Constants.ALIEN_LAYER);
	}

	public static void gridFactory() {
		new Thread() {
			public void run() {
				while (true) {
					AlienContainer.update();
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
