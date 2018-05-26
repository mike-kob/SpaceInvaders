package smthTipaProgi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameListener implements KeyListener, Runnable {
	boolean isLeftPressed, isRightPressed, isSpacePressed;
	boolean attackable = true;
	private ImageIcon glow = new ImageIcon(Const.ROCKET_GLOW_PATH);

	public GameListener() {
		new Thread(this).start();
	}

	public void keyTyped(KeyEvent ke) {
	}

	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			isLeftPressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			isRightPressed = true;
			break;
		case KeyEvent.VK_SPACE:
			isSpacePressed = true;
			break;
		}
	}

	public void keyReleased(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			isLeftPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			isRightPressed = false;
			break;
		case KeyEvent.VK_SPACE:
			isSpacePressed = false;
			break;
		}
	}

	public void run() {
		while (true) {
			if (isLeftPressed) {
				Game.fighter.left();
			}
			if (isRightPressed) {
				Game.fighter.right();
			}
			if (isSpacePressed) {
				BombContainer.add();
			}
			if (attackable) {
				for (Dynamite dyn : BombContainer.getEnemyBombs()) {
					if (Game.fighter.isHit(dyn)) {
						BombContainer.removeDyn(dyn);
						Game.fighter.lives--;
						LivesContainer.remove();
						Game.lp.add(LivesContainer.panel, Const.LIVES_LAYER);
						Game.fighter.explode(false);
						makeInvincible();
					}
				}
			}
			for (JLabel aid : BombContainer.getHeel()) {
				if (Game.fighter.plusLife(aid)) {
					BombContainer.removeAid(aid);
					Game.fighter.lives++;
					LivesContainer.add();
					Game.lp.add(LivesContainer.panel, Const.ROCKET_LAYER);

				}
			}
			pause(20);
		}
	}

	private void makeInvincible() {
		new Thread() {
			public void run() {
				attackable = false;
				Icon temp = Game.fighter.getIcon();
				Game.fighter.setIcon(glow);
				pause(Const.TIME_NOT_ATTACK);
				Game.fighter.setIcon(temp);
				attackable = true;
			}
		}.start();
	}
	
	private void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}