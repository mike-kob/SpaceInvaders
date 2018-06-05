package smthTipaProgi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

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
	private JLabel msg;
	private JLabel levelTxt;
	private JLabel menulabel;
	private long currentTime;
	public JLabel getMenulabel() {
		return menulabel;
	}

	public void setMenulabel(JLabel menulabel) {
		this.menulabel = menulabel;
	}

	private AlienContainer alienCont;
	private BombContainer bombCont;
	private DefenceContainer defenceCont;
	private LivesContainer livesCont;
	private PointsContainer pointsCont;

	public GameListener getListener() {
		return listener;
	}

	public void setListener(GameListener listener) {
		this.listener = listener;
	}

	private SpecialAlienContainer spAlienCont;
	private Thread bombThread;

	public Thread getBombThread() {
		return bombThread;
	}

	public void setBombThread(Thread bombThread) {
		this.bombThread = bombThread;
	}

	public Thread getAlienThread() {
		return alienThread;
	}

	public void setAlienThread(Thread alienThread) {
		this.alienThread = alienThread;
	}

	public Thread getSpecialAlienThread() {
		return specialAlienThread;
	}

	public void setSpecialAlienThread(Thread specialAlienThread) {
		this.specialAlienThread = specialAlienThread;
	}

	public Thread getEnemyBombThread() {
		return enemyBombThread;
	}

	public void setEnemyBombThread(Thread enemyBombThread) {
		this.enemyBombThread = enemyBombThread;
	}

	public Thread getGridThread() {
		return gridThread;
	}

	public void setGridThread(Thread gridThread) {
		this.gridThread = gridThread;
	}

	public Thread getDefenceThread() {
		return defenceThread;
	}

	public void setDefenceThread(Thread defenceThread) {
		this.defenceThread = defenceThread;
	}

	private Thread alienThread;
	private Thread specialAlienThread;
	private Thread enemyBombThread;
	private Thread gridThread;
	private Thread defenceThread;
	private MenuBar menu;

	public MenuBar getMenu() {
		return menu;
	}

	public void setMenu(MenuBar menu) {
		this.menu = menu;
	}

	public Game(JFrame fr, JLayeredPane lpn, int scoreP, int levelP, int livesP) {
		frame = fr;
		lp = lpn;
		score = scoreP;
		level = levelP;
		lives = livesP;
	}

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
				menu.setLocation((frame.getWidth() - 350) / 2, (frame.getHeight() - 300) / 2);
				lp.add(menu, new Integer(20));
				bombThread.suspend();
				alienThread.suspend();
				specialAlienThread.suspend();
				enemyBombThread.suspend();
				gridThread.suspend();
				defenceThread.suspend();
				GameManager.getMusicThread().suspend();
				frame.removeKeyListener(listener);
				currentTime = System.currentTimeMillis();

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

	public void enemyBombFactory() {
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

	public void gridFactory() {
		gridThread = new Thread() {
			public void run() {
				while (running) {
					alienCont.update();
				}
			}
		};
		gridThread.start();
	}

	public void defenceFactory() {
		defenceThread = new Thread() {
			public void run() {
				while (running) {
					defenceCont.updateDef();
				}
			}
		};
		defenceThread.start();
	}

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
		lp.remove(menulabel);
		lp.repaint();
	//	pause(1000);
		if (fail) {
			GameManager.askName(level, score);
		} else {
			GameManager.continueGame(score, level, lives);
		}
	}

	private static void pause(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public JLabel getMsg() {
		return msg;
	}

	public JLabel levelTxt() {
		return levelTxt;
	}

	public AlienContainer getAlienCont() {
		return alienCont;
	}

	public JFrame getFrame() {
		return frame;
	}

	public Rocket getFighter() {
		return fighter;
	}

	public BombContainer getBombCont() {
		return bombCont;
	}

	public DefenceContainer getDefenceCont() {
		return defenceCont;
	}

	public LivesContainer getLivesCont() {
		return livesCont;
	}

	public PointsContainer getPointsCont() {
		return pointsCont;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public int getLives() {
		return lives;
	}

	public JLayeredPane getLp() {
		return lp;
	}

	public boolean isRunning() {
		return running;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
}
