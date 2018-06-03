package smthTipaProgi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import levelpac.GameManager;

public class GameListener implements KeyListener, Runnable {
	private boolean isLeftPressed, isRightPressed, isSpacePressed;
	private boolean attackable = true;
	private ImageIcon glow = new ImageIcon(Const.ROCKET_GLOW_PATH);
	private Game game = GameManager.getCurrentGame();
	
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
		while (game.isRunning()) {
			if (isLeftPressed&& game.isRunning()) {
				game.getFighter().left();
			}
			if (isRightPressed&& game.isRunning()) {
				game.getFighter().right();
			}
			if (isSpacePressed && game.isRunning()) {
				game.getBombCont().add();
			}
			if (attackable) {
				for (Dynamite dyn : game.getBombCont().getEnemyBombs()) {
					if (game.getFighter().isHit(dyn)) {
						game.getBombCont().removeDyn(dyn);
						game.getLivesCont().remove();
						game.lives--;
						game.getLp().add(game.getLivesCont().getPanel(), Const.LIVES_LAYER);
						game.getFighter().explode(false);
						makeInvincible();
					}
				}
			}
			for (JLabel aid : game.getBombCont().getHeel()) {
				if (game.getFighter().plusLife(aid)) {
					game.getBombCont().removeAid(aid);
					game.getLivesCont().add();
					game.lives++;
					game.getLp().add(game.getLivesCont().getPanel(), Const.ROCKET_LAYER);

				}
			}
			pause(20);
		}
	}

	private void makeInvincible() {
		new Thread() {
			public void run() {
				attackable = false;
				Icon temp = game.getFighter().getIcon();
				game.getFighter().setIcon(glow);
				pause(Const.TIME_NOT_ATTACK);
				game.getFighter().setIcon(temp);
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