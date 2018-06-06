package smthTipaProgi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import levelpac.GameManager;
import levelpac.MenuBar;

public class Game {
	private JFrame frame;
	private JLayeredPane lp;
	private Rocket fighter;
	private GameListener listener;
	private boolean running = true;
	public int score, level, lives;
	private JLabel msg, levelTxt, menulabel;
	private long currentTime;

	private Thread alienThread, specialAlienThread, enemyBombThread;
	private Thread gridThread, defenceThread, invincibleThread, bombThread;
	private MenuBar menu;

	private AlienContainer alienCont;
	private BombContainer bombCont;
	private DefenceContainer defenceCont;
	private LivesContainer livesCont;
	private PointsContainer pointsCont;
	private SpecialAlienContainer spAlienCont;
	//d
	public JLabel getMenulabel() {
		return menulabel;
	}
	//d
	public GameListener getListener() {
		return listener;
	}
	//d
	public Thread getBombThread() {
		return bombThread;
	}
	//d
	public Thread getAlienThread() {
		return alienThread;
	}
	//d
	public Thread getSpecialAlienThread() {
		return specialAlienThread;
	}
	//d
	public Thread getEnemyBombThread() {
		return enemyBombThread;
	}
	//d
	public Thread getGridThread() {
		return gridThread;
	}
	//d
	public Thread getDefenceThread() {
		return defenceThread;
	}
	//d
	public Thread getInvincibleThread() {
		return invincibleThread;
	}
	//m
	public void setInvincible(Thread invincible) {
		this.invincibleThread = invincible;
	}
	//m
	public AlienContainer getAlienCont() {
		return alienCont;
	}
	//m
	public JFrame getFrame() {
		return frame;
	}
	//m
	public Rocket getFighter() {
		return fighter;
	}
	//m
	public BombContainer getBombCont() {
		return bombCont;
	}
	//m
	public DefenceContainer getDefenceCont() {
		return defenceCont;
	}
	//m
	public LivesContainer getLivesCont() {
		return livesCont;
	}
	//m
	public PointsContainer getPointsCont() {
		return pointsCont;
	}
	//m
	public JLayeredPane getLp() {
		return lp;
	}
	//m
	public boolean isRunning() {
		return running;
	}
	//d
	public long getCurrentTime() {
		return currentTime;
	}
	//d
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
	//d
	public MenuBar getMenu() {
		return menu;
	}
	//d
	public JLabel getMsg() {
		return msg;
	}
	//m
	public JLabel levelTxt() {
		return levelTxt;
	}
//dm
	public Game(JFrame fr, JLayeredPane lpn, int scoreP, int levelP, int livesP) {
		frame = fr;
		lp = lpn;
		score = scoreP;
		level = levelP;
		lives = livesP;
	}
	//dm
	public void start() {
		running = true;
		fighter = new Rocket(0, 770);

		alienCont = new AlienContainer();
		bombCont = new BombContainer();
		defenceCont = new DefenceContainer();
		livesCont = new LivesContainer();
		pointsCont = new PointsContainer();
		spAlienCont = new SpecialAlienContainer();

		listener = new GameListener();

		drawEverything();

		frame.addKeyListener(listener);
		bombFactory();
		alienFactory();
		gridFactory();
		defenceFactory();
		enemyBombFactory();
		specialAlienFactory();
	}
	//dm
	private void drawEverything() {

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

		levelTxt = new JLabel();
		levelTxt.setText("Level: " + level);
		levelTxt.setFont(new Font("Courier New", Font.BOLD, 42));
		levelTxt.setSize(400, 60);
		levelTxt.setForeground(Color.WHITE);
		levelTxt.setLocation((frame.getWidth() - levelTxt.getWidth()) / 2, 5);
		lp.add(levelTxt, Const.LIVES_LAYER);

		livesCont.draw(lives);

		pointsCont.draw(score);

		alienCont.drawPanel();

		defenceCont.drawDefences();
		drawMenu();
	}
	//m
	private void drawMenu() {
		menu = new MenuBar(false);
		ImageIcon setting = new ImageIcon("res/settings (4).png");
		menulabel = new JLabel(setting);
		menulabel.setSize(64, 50);
		menulabel.setLocation(10, 5);
		lp.add(menulabel, Const.LIVES_LAYER);

		menulabel.addMouseListener(new MouseListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					menu.setLocation((frame.getWidth() - 350) / 2, (frame.getHeight() - 300) / 2);
					lp.add(menu, new Integer(20));
					bombThread.suspend();
					alienThread.suspend();
					specialAlienThread.suspend();
					enemyBombThread.suspend();
					gridThread.suspend();
					defenceThread.suspend();
					invincibleThread.suspend();
					frame.removeKeyListener(listener);
					currentTime = System.currentTimeMillis();
				} catch (Exception e) {

				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}
	//dm
	private void bombFactory() {
		bombThread = new Thread() {
			public void run() {
				while (running) {
					bombCont.update();
					pause(5);
				}
			}
		};
		bombThread.start();
	}
	//dm
	private void alienFactory() {
		alienThread = new Thread() {
			public void run() {
				while (running) {
					alienCont.updateAliens();
					pause(10);
				}
			}
		};
		alienThread.start();
	}
	//dm
	private void specialAlienFactory() {
		specialAlienThread = new Thread() {
			public void run() {
				pause(Const.PAUSE_FOR_SPECIAL_UFO);
				pause(GameManager.getCurrTime());
				GameManager.setCurrTime(0);
				spAlienCont.draw();
				while (running) {
					spAlienCont.update();
					pause(50);
				}
				spAlienCont.delete("");
			}
		};
		specialAlienThread.start();
	}
	//dm
	private void enemyBombFactory() {
		enemyBombThread = new Thread() {
			public void run() {
				pause(1000 + (int) (Math.random() * Const.BOMB_FREQUENCY));
				while (running) {
					bombCont.addEnemyBomb();
					pause(1000 + (int) (Math.random() * Const.BOMB_FREQUENCY));
				}
			}
		};
		enemyBombThread.start();
	}
	//dm
	private void gridFactory() {
		gridThread = new Thread() {
			public void run() {
				while (running) {
					alienCont.update();
				}
			}
		};
		gridThread.start();
	}
	//dm
	private void defenceFactory() {
		defenceThread = new Thread() {
			public void run() {
				while (running) {
					defenceCont.updateDef();
				}
			}
		};
		defenceThread.start();
	}
	//m
	public void stop(boolean fail) {
		running = false;

		if (fail) {
			msg = new JLabel("Game over");
			fighter.explode(true);

		} else {
			msg = new JLabel("Level complete");
		}
		msg.setFont(new Font("Courier new", Font.PLAIN, 72));
		msg.setForeground(Color.WHITE);
		msg.setSize(msg.getPreferredSize());
		msg.setLocation((frame.getWidth() - msg.getWidth()) / 2,
				(frame.getHeight() - msg.getHeight()) / 2 - msg.getHeight());
		lp.add(msg, Const.FINAL_MSG_LAYER);
		frame.removeKeyListener(listener);
		alienCont.removeAliens();
		defenceCont.removeDefences();
		bombCont.removeAllBombs();
		if (spAlienCont.getLabel() != null) {
			spAlienCont.delete();
		}
		lp.remove(menulabel);
		lp.repaint();
		// pause(1000);
		if (fail) {
			GameManager.askName(level, score);
		} else {
			GameManager.continueGame(score, level, lives);
		}
	}
	//d
	private void pause(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
