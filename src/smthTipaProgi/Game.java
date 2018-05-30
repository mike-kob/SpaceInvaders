package smthTipaProgi;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import levelpac.GameManager;

public class Game {
	public JFrame frame;
	public JLayeredPane lp;
	public Rocket fighter;
	private GameListener listener;
	public boolean running = true;
	public int score, level, lives;
	
	private AlienContainer alienCont;
	private BombContainer bombCont;
	private DefenceContainer defenceCont;
	private LivesContainer livesCont;
	private PointsContainer pointsCont;
	private SpecialAlienContainer spAlienCont;
	
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

		JLabel levelTxt = new JLabel();
		levelTxt.setText("Level: " + level);
		levelTxt.setFont(new Font("Courier New", Font.BOLD, 42));
		levelTxt.setSize(400, 60);
		levelTxt.setForeground(Color.WHITE);
		levelTxt.setLocation((frame.getWidth()-levelTxt.getWidth())/2, 5);
		lp.add(levelTxt, Const.LIVES_LAYER);
		
		livesCont.draw(lives);
		
		pointsCont.draw(score);

		alienCont.drawPanel();

		defenceCont.drawDefences();
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

	private void bombFactory() {
		new Thread() {
			public void run() {
				while (running) {
					bombCont.update();
					pause(5);
				}
			}
		}.start();
	}

	private void alienFactory() {
		new Thread() {
			public void run() {
				while (running) {
					alienCont.updateAliens();
					pause(10);
				}
			}
		}.start();
	}

	private void specialAlienFactory() {
		new Thread() {
			public void run() {
				pause(Const.PAUSE_FOR_SPECIAL_UFO);
				spAlienCont.draw();
				while (running) {
					spAlienCont.update();
					pause(50);
				}
				spAlienCont.delete("");
			}
		}.start();
	}

	public void enemyBombFactory() {
		new Thread() {
			public void run() {
				pause(1000 + (int) (Math.random() * Const.BOMB_FREQUENCY));
				while (running) {
					bombCont.addEnemyBomb();
					pause(1000 + (int) (Math.random() * Const.BOMB_FREQUENCY));
				}
			}
		}.start();
	}

	public void gridFactory() {
		new Thread() {
			public void run() {
				while (running) {
					alienCont.update();
				}
			}
		}.start();
	}

	public void defenceFactory() {
		new Thread() {
			public void run() {
				while (running) {
					defenceCont.updateDef();
				}
			}
		}.start();
	}

	public void stop(boolean fail) {
		running = false;
		JLabel msg;
		
		if (fail) {
			msg = new JLabel("Game over");
			fighter.explode(true);
		} else {
			msg = new JLabel("Level complete");
		}
		msg.setFont(new Font("Courier new", Font.PLAIN, 72));
		msg.setForeground(Color.WHITE);
		msg.setSize(msg.getPreferredSize());
		msg.setLocation((frame.getWidth() - msg.getWidth()) / 2, (frame.getHeight() - msg.getHeight()) / 2);
		lp.add(msg, Const.FINAL_MSG_LAYER);
		frame.removeKeyListener(listener);
		alienCont.removeAliens();
		defenceCont.removeDefences();
		bombCont.removeAllBombs();
		lp.repaint();
		pause(1000);
		if(fail) {
			GameManager.askName();
		} else {
			GameManager.continueGame(score, level, lives);
		}
	}

	private static void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
}
