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

	public static Integer BACK_LAYER = 1;
	public static Integer ROCKET_LAYER = 2;
	public static Integer ALIEN_LAYER = 3;
	public static Integer BOMB_LAYER = 4;

	private static int ALIEN_ROWS = 5;
	private static int ALIEN_COLUMNS = 7;

	static Alien[][] aliens = new Alien[ALIEN_ROWS][ALIEN_COLUMNS];
	public static Set<Bomb> bombs = Collections.newSetFromMap(new ConcurrentHashMap<Bomb, Boolean>());

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				drawEverything();
				frame.addKeyListener(keyL);
				
				/*boolean flag = true;
				while(flag) {
					updateAll(bombs);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
					
				}*/}

		
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
		lp.add(l, BACK_LAYER);

		fighter = new Rocket(0, 770);
		lp.add(fighter, Game.ROCKET_LAYER);

		grid = new JPanel(null);
		/*
		 * for (int i = 0; i < ALIEN_ROWS; i++) { for (int j = 0; j < ALIEN_COLUMNS;
		 * j++) { Alien cur = new Alien(); cur.setLocation(i*30, j*30); aliens[i][j] =
		 * cur; grid.add(cur); } }
		 */

		grid.setOpaque(true);
		grid.setSize(700, 450);
		grid.setLocation((frame.getWidth() - grid.getWidth()) / 2, 50);
		grid.setVisible(true);
		lp.add(grid, Game.ALIEN_LAYER);
	}

	static KeyListener keyL = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				fighter.left();

			} else if (key == KeyEvent.VK_RIGHT) {
				fighter.right();
			} else if (key == KeyEvent.VK_ENTER) {

				Bomb bomb = new Bomb(fighter.getX());
				lp.add(bomb, Game.BOMB_LAYER);
				bombs.add(bomb);

			}
		}

	};

	public static void updateAll(Set<? extends Updatable> elements) {
		System.out.println("huh");
		for (Updatable temp : elements) {
			temp.update();
		}
	}
}
