package levelpac;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import smthTipaProgi.Game;

public class MenuBar extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ImageIcon start_off = new ImageIcon("res/menu/start_bar_off.png");
	private final ImageIcon start_on = new ImageIcon("res/menu/start_bar_on.png");

	private final ImageIcon leader_off = new ImageIcon("res/menu/leader_bar_off.png");
	private final ImageIcon leader_on = new ImageIcon("res/menu/leader_bar_on.png");

	private final ImageIcon quit_off = new ImageIcon("res/menu/quit_bar_off.png");
	private final ImageIcon quit_on = new ImageIcon("res/menu/quit_bar_on.png");

	private final ImageIcon resume_off = new ImageIcon("res/menu/resume_bar_off.png");
	private final ImageIcon resume_on = new ImageIcon("res/menu/resume_bar_on.png");

	private final ImageIcon exit_menu_off = new ImageIcon("res/menu/main_menu_bar.png");
	private final ImageIcon exit_menu_on = new ImageIcon("res/menu/main_menu_bar_on.png");

	private final ImageIcon main_menu = new ImageIcon("res/menu/main_menu.png");

	private final ImageIcon pause_menu = new ImageIcon("res/menu/pause_menu.png");
	
	
	private final ImageIcon sound_on = new ImageIcon("res/menu/speaker (1).png");
	private final ImageIcon sound_off = new ImageIcon("res/menu/speaker.png");

	private final ImageIcon music_off = new ImageIcon("res/menu/music-player (1).png");
	private final ImageIcon music_on = new ImageIcon("res/menu/music-player (2).png");
	private Game game = GameManager.getCurrentGame();
	private JLabel label;
	private long currentTime;

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public MenuBar(boolean flag) {
		super();
		label = this;
		setLayout(null);
		setSize(350, 300);
		if (flag) {
			setIcon(main_menu);
			drawStart();
			drawLeader();
			drawQuit();
			drawSound();
			drawMusic();
		} else {
			setIcon(pause_menu);
			drawSound();
			drawResume();
			drawExitToMenu();
			drawMusic();
		}
	}

	public void drawMusic() {
		ImageIcon musicIcon;
		if (GameManager.isFlagForMusic()) {
			musicIcon = music_on;
		} else {
			musicIcon = music_off;
		}
		JLabel music = new JLabel(musicIcon);
		music.setSize(32, 32);
		music.setLocation(getWidth() - 60, 40);//можешь поменять тут, я опять хочу спать
		add(music);

		music.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (GameManager.isFlagForMusic()) {
					GameManager.setFlagForMusic(false);
					GameManager.getMusicThread().suspend();
					music.setIcon(music_off);
				} else {
					GameManager.getMusicThread().resume();
					GameManager.setFlagForMusic(true);
					music.setIcon(music_on);
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		
		
	
	}

	public void drawSound() {
	
		
		ImageIcon soundIcon;
		if (GameManager.isFlagForSound()) {
			soundIcon = sound_on;
		} else {
			soundIcon = sound_off;
		}
		JLabel music = new JLabel(soundIcon);
		music.setSize(32, 32);
		music.setLocation(30, 40);//можешь поменять тут, я опять хочу спать
		add(music);

		music.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (GameManager.isFlagForSound()) {
					GameManager.setFlagForSound(false);
					music.setIcon(sound_off);
				} else {
					GameManager.setFlagForSound(true);
					music.setIcon(sound_on);
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	public void drawStart() {
		JLabel start = new JLabel(start_off);
		start.setSize(240, 40);
		start.setLocation((getWidth() - 240) / 2, getHeight() * 2 / 5 - 30);
		add(start);
		start.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				GameManager.startNewGame();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				start.setIcon(start_on);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				start.setIcon(start_off);
			}
		});

	}

	public void drawLeader() {
		JLabel leader = new JLabel(leader_off);
		leader.setSize(240, 40);
		leader.setLocation((getWidth() - 240) / 2, getHeight() * 3 / 5 - 30);
		add(leader);
		leader.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				GameManager.drawLeader(0);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				leader.setIcon(leader_on);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				leader.setIcon(leader_off);
			}
		});
	}

	public void drawQuit() {
		JLabel quit = new JLabel(quit_off);
		quit.setSize(240, 40);
		quit.setLocation((getWidth() - 240) / 2, getHeight() * 4 / 5 - 30);
		add(quit);
		quit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				quit.setIcon(quit_on);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				quit.setIcon(quit_off);
			}
		});
	}

	public void drawExitToMenu() {
		JLabel exit = new JLabel(exit_menu_off);
		exit.setSize(240, 40);
		exit.setLocation((getWidth() - 240) / 2, getHeight() * 3 / 5 - 30);
		add(exit);
		exit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				game.getLp().remove(label);
				repaint();
				// resumeGame();
				game.stop(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				exit.setIcon(exit_menu_on);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				exit.setIcon(exit_menu_off);
			}
		});
	}

	public void drawResume() {
		JLabel resume = new JLabel(resume_off);
		resume.setSize(240, 40);
		resume.setLocation((getWidth() - 240) / 2, getHeight() * 2 / 5 - 30);
		add(resume);
		resume.addMouseListener(new MouseListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {

				game.getLp().remove(label);
				resumeGame();
				game.getFrame().addKeyListener(game.getListener());

				// Game.listener.notifyAll();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				resume.setIcon(resume_on);

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				resume.setIcon(resume_off);

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	@SuppressWarnings("deprecation")
	private void resumeGame() {
		game.getBombThread().resume();
		game.getAlienThread().resume();
		game.getDefenceThread().resume();
		game.getEnemyBombThread().resume();
		game.getGridThread().resume();
		game.getSpecialAlienThread().resume();
		currentTime = System.currentTimeMillis();

	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

}
