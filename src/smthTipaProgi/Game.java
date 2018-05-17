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
		
		JLabel l = new JLabel(new ImageIcon("res/background.png"));
		l.setLocation(0, 0);
		l.setVisible(true);
		l.setSize(1280, 980);

		lp.add(l, Constants.BACK_LAYER);
		fighter = new Rocket(0, 770);
		lp.add(fighter, Constants.ROCKET_LAYER);

		lp.add(AlienContainer.getPanel(), Constants.ALIEN_LAYER);
		DefenceContainer.add();
	} 
	
	private static void bombFactory() {
		new Thread() {
			public void run() {
				while (true) {
					BombContainer.update();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
	}	
	
	private static void alienFactory() {
		new Thread() {
			public void run() {
				while (true) {
					AlienContainer.updateAliens();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
	}
	
	public static void enemyBombFactory(){
		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					BombContainer.addEnemyBomb();
					
				}
			}
		}.start();
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
	
	public static void defenceFactory() {
		new Thread() {
			public void run() {
				while (true) {
					DefenceContainer.updateDef();
				}
			}
		}.start();
	}
}
