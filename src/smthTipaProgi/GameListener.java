package smthTipaProgi;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameListener implements KeyListener, Runnable {
    boolean isLeftPressed, isRightPressed, isSpacePressed;

    public GameListener() {
        new Thread(this).start();
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_LEFT: isLeftPressed = true; break;
            case KeyEvent.VK_RIGHT: isRightPressed = true; break;
            case KeyEvent.VK_SPACE: 
            	isSpacePressed = true;
            	break;
        }
    }

    public void keyReleased(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_LEFT: isLeftPressed = false; break;
            case KeyEvent.VK_RIGHT: isRightPressed = false; break;
            case KeyEvent.VK_SPACE: isSpacePressed = false; break;
        }
    }

    public void run() {
        while(true) {
            try {
            	if(isLeftPressed) {
            		Game.fighter.left();
            	}
            	if(isRightPressed) {
            		Game.fighter.right();
            	}
            	if(isSpacePressed) {
            		Bomb bomb = new Bomb(Game.fighter.getX());
    				Game.lp.add(bomb, Constants.BOMB_LAYER);
    				Game.bombs.add(bomb);
            	}
                Thread.sleep(20);
            } catch(Exception exc) {
                exc.printStackTrace();
                break;
            }
        }
    }
}