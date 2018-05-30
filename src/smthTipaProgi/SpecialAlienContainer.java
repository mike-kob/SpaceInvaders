package smthTipaProgi;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import levelpac.GameManager;

public class SpecialAlienContainer extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private Game game = GameManager.getCurrentGame();
	private Mp3Player mp;

	public void draw() {
		label = new JLabel();
		ImageIcon im = new ImageIcon(Const.UFO_SPECIAL_PATH);
		label.setIcon(im);
		label.setLocation(-64, 50);
		label.setSize(64, 44);
		game.lp.add(label, Const.ALIEN_LAYER);
		mp = new Mp3Player(Const.SOUND_OF_EXPLOSION);
	}

	public void update() {
		if (isChecker(label.getX())) {
			delete("");
			draw();
		}
		label.setLocation(label.getX() + 10, label.getY());
		game.lp.repaint();

		for (Bomb bomb : game.getBombCont().getBombs()) {
			if (isHit(bomb)) {
				int reward = Const.POINTS_FOR_SPECIAL+(int)(Math.random()*150);
				game.getPointsCont().change(reward);
				game.getBombCont().remove(bomb);
				new Thread() {
					public void run() {
						mp.play();
					}
				}.start();
				delete(reward+"");
				draw();
			}
		}
	}
	
	
	public void delete(String reward) {
		label.setIcon(null);
		label.setFont(new Font("Calibri", Font.BOLD, 42));
		label.setForeground(Color.GREEN);
		label.setText(String.valueOf(reward));
		pause(2000);
		game.lp.remove(label);
		game.lp.repaint();
		pause(Const.PAUSE_FOR_SPECIAL_UFO + (long)(Math.random()*15000));
	}

	private boolean isHit(Bomb bomb) {
		int x = label.getX();
		int y = label.getY();

		if (x-20  < bomb.getX() && bomb.getX() + bomb.getWidth() < x + label.getWidth()+20) {
			if (y + label.getHeight() >= bomb.getY()) {
				return true;
		}}
		return false;
		
	}

	private boolean isChecker(int x) {
		return x >= game.frame.getWidth();
	}

	private void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

}
