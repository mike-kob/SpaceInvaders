package smthTipaProgi;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import levelpac.GameManager;

public class AlienContainer {
	
	private JPanel grid = new JPanel(null);
	private Set<Alien> aliens = Collections.newSetFromMap(new ConcurrentHashMap<Alien, Boolean>());
	private Game currGame = GameManager.getCurrentGame();
	private JFrame frame = GameManager.getFrame();
	private Alien[][] matrix  = new Alien[Const.ALIEN_ROWS][Const.ALIEN_COLUMNS];

	public boolean isEmpty() {
		return aliens.size() == 0;
	}

	public void removeAliens() {
		aliens.removeAll(aliens);
		grid.removeAll();
	}
	
	public void drawPanel() {
		for (int i = 0; i < Const.ALIEN_ROWS; i++) {
			for (int j = 0; j < Const.ALIEN_COLUMNS; j++) {
				Alien cur = new Alien(j * (64 + Const.INTERVAL_X), i * 64, i);
				matrix[i][j] = cur;
				cur.setOpaque(true);
				aliens.add(cur);
				grid.add(cur);
			}
		}
		grid.setOpaque(false);
		grid.setBackground(Const.TRANSPARENT);
		grid.setSize(700, 350);
		grid.setLocation((frame.getWidth() - grid.getWidth()) / 2, Const.ALIENT_START_Y);
		
		currGame.lp.add(grid, Const.ALIEN_LAYER);
	}

	public int getGridX() {
		return grid.getX();
	}

	public int getGridY() {
		return grid.getY();
	}

	public void remove(Alien alien) {
		aliens.remove(alien);
		grid.remove(alien);
		grid.repaint();
		currGame.lp.repaint();
		removeFromMatrix(alien);
	}

	public void updateAliens() {
		for(Alien alien: aliens) {
			alien.update();
		}
	}
	
	
	private void removeFromMatrix(Alien alien) {
		for (int column = 0; column < Const.ALIEN_COLUMNS; column++) {
			for (int row = 0; row < Const.ALIEN_ROWS; row++) {
				if (matrix[row][column] == alien) {
					matrix[row][column] = null;
					return;
				}
			}
		}

	}

	public void update() {
		int direction = Const.ALIEN_STEP;

		while (currGame.running) {
			int x = grid.getX();
			int y = grid.getY();
			if(isEmpty()) {
				currGame.stop(false);
				return;
			}
			try {
				if(getLowBorder()> currGame.fighter.getY() && getLeftBorder() <= 0) {
					currGame.stop(true);
					return;
				}
				long pause = (currGame.level>7)? 400 : (Const.ALIEN_TIME_STEP - (currGame.level-1)*100);
				if (getRightBorder() > frame.getWidth() || getLeftBorder() < 0) {
					direction *= -1;
					y += 50;
					grid.setLocation(x, y);
					grid.repaint();
					currGame.lp.repaint();
					Thread.sleep(pause);
				}
				grid.setLocation(x + direction, y);
				grid.repaint();
				currGame.lp.repaint();
				
				Thread.sleep(pause);
			} catch (Exception e) {
			}
		}
	}

	public Alien getLastInColumn(int i) {
		for (int j = Const.ALIEN_ROWS - 1; j >= 0; j--) {
			if (matrix[j][i] != null) {
				return matrix[j][i];
			}
		}
		return null;
	}

	public int getLeftBorder() {
		for (int column = 0; column < Const.ALIEN_COLUMNS; column++) {
			for (int row = 0; row < Const.ALIEN_ROWS; row++) {
				if (matrix[row][column] != null) {
					return matrix[row][column].getX() + grid.getX();
				}
			}
		}
		return -1;
	}

	public int getRightBorder() {
		for (int column = Const.ALIEN_COLUMNS - 1; column >= 0; column--) {
			for (int row = 0; row < Const.ALIEN_ROWS; row++) {
				if (matrix[row][column] != null) {
					return matrix[row][column].getX() + grid.getX() + matrix[row][column].getWidth();
				}
			}
		}
		return -1;
	}
	
	public int getLowBorder() {
		for (int row = Const.ALIEN_ROWS - 1; row >= 0; row--) {
			for (int column = 0; column < Const.ALIEN_COLUMNS; column++) {
				if (matrix[row][column] != null) {
					return matrix[row][column].getY() + grid.getY() + matrix[row][column].getHeight();
				}
			}
		}
		return -1;
	}
}
