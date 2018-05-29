package smthTipaProgi;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class Game {
	public static JFrame frame;
	public static JLayeredPane lp;
	public static final Rocket fighter = new Rocket(0, 770);
	public static final GameListener listener = new GameListener();
	public static boolean running = true;

	public static void init(JFrame fr, JLayeredPane lpn) {
		frame = fr;
		lp = lpn;

		drawEverything();

		frame.addKeyListener(listener);
		bombFactory();
		alienFactory();
		gridFactory();
		defenceFactory();
		enemyBombFactory();
		specialAlienFactory();
		try {
			musicFactory();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	protected static void musicFactory() throws FileNotFoundException {
		new Thread() {
			public void run() {
				Mp3Player mp = new Mp3Player("res/Hiding Your Reality.mp3");
				mp.play();
			}
		}.start();
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

		LivesContainer.draw();
		PointsContainer.draw();

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

	private static void specialAlienFactory() {
		new Thread() {
			public void run() {
				pause(Const.PAUSE_FOR_SPECIAL_UFO);
				SpecialAlienContainer.draw();
				while (running) {
					SpecialAlienContainer.update();
				}
				SpecialAlienContainer.delete();
			}
		}.start();
	}

	public static void enemyBombFactory() {
		new Thread() {
			public void run() {
				pause(1000 + (int) (Math.random() * Const.BOMB_FREQUENCY));
				while (running) {
					BombContainer.addEnemyBomb();
					pause(1000 + (int) (Math.random() * Const.BOMB_FREQUENCY));
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

	public static void stop(boolean fail) {
		running = false;
		JLabel msg = new JLabel();
		msg.setSize(650, 150);
		msg.setFont(new Font("Courier new", Font.PLAIN, 72));
		msg.setForeground(Color.WHITE);
		msg.setLocation((frame.getWidth() - 650) / 2, (frame.getHeight() - 150) / 2);
		if (fail) {
			msg.setText("Game over");
			fighter.explode(true);
		} else {
			msg.setText("Level complete");
		}
		msg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		Game.lp.add(msg, Const.FINAL_MSG_LAYER);
		frame.removeKeyListener(listener);
		AlienContainer.removeAliens();
		DefenceContainer.removeDefences();
		BombContainer.removeAllBombs();
		Game.lp.repaint();
	}

	private static void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
