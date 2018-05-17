package smthTipaProgi;

import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class Game {
	public static final JFrame frame = new JFrame();
	public static final JLayeredPane lp = frame.getLayeredPane();;
	public static final Rocket fighter =  new Rocket(0, 770);

	private static boolean running = true;
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				drawEverything();
				frame.addKeyListener(new GameListener());
				bombFactory();
				alienFactory();
				gridFactory();
				defenceFactory();
				enemyBombFactory();
			}
		});
	}

	private static void drawEverything() {
		frame.setSize(1280, 980);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JLabel background = new JLabel(new ImageIcon(Const.BACKGROUND_PATH));
		background.setLocation(0, 0);
		background.setVisible(true);
		background.setSize(1280, 980);
		lp.add(background, Const.BACKGROUND_LAYER);

		lp.add(fighter, Const.ROCKET_LAYER);

		AlienContainer.drawPanel();

		DefenceContainer.drawDefences();
	}

	private static void bombFactory() {
		new Thread() {
			public void run() {
				while (running) {
					BombContainer.update();
					pause(5);
				}
			}
		}.start();
	}

	private static void alienFactory() {
		new Thread() {
			public void run() {
				while (running) {
					AlienContainer.updateAliens();
					pause(10);
				}
			}
		}.start();
	}

	public static void enemyBombFactory() {
		new Thread() {
			public void run() {
				while (running) {
					pause(1000);
					BombContainer.addEnemyBomb();

				}
			}
		}.start();
	}

	public static void gridFactory() {
		new Thread() {
			public void run() {
				while (running) {
					AlienContainer.update();
				}
			}
		}.start();
	}

	public static void defenceFactory() {
		new Thread() {
			public void run() {
				while (running) {
					DefenceContainer.updateDef();
				}
			}
		}.start();
	}

	private static void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
