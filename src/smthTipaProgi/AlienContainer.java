package smthTipaProgi;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import levelpac.GameManager;

public class AlienContainer {
	//m
	private JPanel grid = new JPanel(null);
	private Set<Alien> aliens = Collections.newSetFromMap(new ConcurrentHashMap<Alien, Boolean>());
	private Game currGame = GameManager.getCurrentGame();
	private JFrame frame = GameManager.frame;
	private Alien[][] matrix  = new Alien[Const.ALIEN_ROWS][Const.ALIEN_COLUMNS];
	//m
	public boolean isEmpty() {
		return aliens.size() == 0;
	}
	//m
	public void removeAliens() {
		aliens.removeAll(aliens);
		grid.removeAll();
	}
	//m
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
		
		currGame.getLp().add(grid, Const.ALIEN_LAYER);
	}
	//m
	public int getGridX() {
		return grid.getX();
	}
	//m
	public int getGridY() {
		return grid.getY();
	}
	//m
	public void remove(Alien alien) {
		aliens.remove(alien);
		grid.remove(alien);
		grid.repaint();
		currGame.getLp().repaint();
		removeFromMatrix(alien);
	}
	//m
	public void updateAliens() {
		for(Alien alien: aliens) {
			alien.update();
		}
	}
	
	//m
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
	//m
	public void update() {
		int direction = Const.ALIEN_STEP;

		while (currGame.isRunning()) {
			int x = grid.getX();
			int y = grid.getY();
			if(isEmpty()) {
				currGame.stop(false);
				return;
			}
			try {
				if(getBottomBorderY()> currGame.getFighter().getY() && getLeftBorderX() <= 0) {
					currGame.stop(true);
					return;
				}
				long pause = (currGame.level>7)? 400 : (Const.ALIEN_TIME_STEP - (currGame.level-1)*100);
				if (getRightBorderX() > frame.getWidth() || getLeftBorderX() < 0) {
					direction *= -1;
					y += 50;
					grid.setLocation(x, y);
					grid.repaint();
					currGame.getLp().repaint();
					Thread.sleep(pause);
				}
				grid.setLocation(x + direction, y);
				grid.repaint();
				currGame.getLp().repaint();
				
				Thread.sleep(pause);
			} catch (Exception e) {
			}
		}
	}
	//m
	public Alien getLastInColumn(int column) {
		for (int j = Const.ALIEN_ROWS - 1; j >= 0; j--) {
			if (matrix[j][column] != null) {
				return matrix[j][column];
			}
		}
		return null;
	}
	//m
	public int getLeftBorderX() {
		for (int column = 0; column < Const.ALIEN_COLUMNS; column++) {
			for (int row = 0; row < Const.ALIEN_ROWS; row++) {
				if (matrix[row][column] != null) {
					return matrix[row][column].getX() + grid.getX();
				}
			}
		}
		return -1;
	}
	//m
	public int getRightBorderX() {
		for (int column = Const.ALIEN_COLUMNS - 1; column >= 0; column--) {
			for (int row = 0; row < Const.ALIEN_ROWS; row++) {
				if (matrix[row][column] != null) {
					return matrix[row][column].getX() + grid.getX() + matrix[row][column].getWidth();
				}
			}
		}
		return -1;
	}
	//m
	public int getBottomBorderY() {
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
