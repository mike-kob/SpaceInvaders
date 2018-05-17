package smthTipaProgi;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AlienContainer {
	private static JPanel grid = new JPanel(null);
	private static JFrame frame = Game.frame;
	private static final Set<Alien> aliens = Collections.newSetFromMap(new ConcurrentHashMap<Alien, Boolean>());

	private static Alien[][] matrix = new Alien[Const.ALIEN_ROWS][Const.ALIEN_COLUMNS];

	public static Set<Alien> getAliens() {
		return aliens;
	}

	public static boolean isEmpty() {
		return aliens.size() == 0;
	}

	public static void drawPanel() {
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
		
		Game.lp.add(grid, Const.ALIEN_LAYER);
	}

	public static int getGridX() {
		return grid.getX();
	}

	public static int getGridY() {
		return grid.getY();
	}

	public static void remove(Alien alien) {
		aliens.remove(alien);
		grid.remove(alien);
		grid.repaint();
		Game.lp.repaint();
		removeFromMatrix(alien);
	}

	public static void updateAliens() {
		for(Alien alien: aliens) {
			alien.update();
		}
	}
	
	
	private static void removeFromMatrix(Alien alien) {
		for (int column = 0; column < Const.ALIEN_COLUMNS; column++) {
			for (int row = 0; row < Const.ALIEN_ROWS; row++) {
				if (matrix[row][column] == alien) {
					matrix[row][column] = null;
					return;
				}
			}
		}

	}

	public static void update() {
		int direction = Const.ALIEN_STEP;

		int x = grid.getX();
		int y = grid.getY();

		while (y + grid.getHeight() < frame.getHeight()) {
			x = grid.getX();
			y = grid.getY();
			try {
				if (getRightBorder() > frame.getWidth() || getLeftBorder() < 0) {
					direction *= -1;
					y += 50;
					grid.setLocation(x, y);
					grid.repaint();
					Game.lp.repaint();
					Thread.sleep(Const.ALIEN_TIME_STEP);
				}
				grid.setLocation(x + direction, y);
				grid.repaint();
				Game.lp.repaint();
				Thread.sleep(Const.ALIEN_TIME_STEP);
			} catch (InterruptedException e) {

			}
		}
	}

	public static Alien getLastInColumn(int i) {
		for (int j = Const.ALIEN_ROWS - 1; j >= 0; j--) {
			if (matrix[j][i] != null) {
				return matrix[j][i];
			}
		}
		return null;
	}

	public static int getLeftBorder() {
		for (int column = 0; column < Const.ALIEN_COLUMNS; column++) {
			for (int row = 0; row < Const.ALIEN_ROWS; row++) {
				if (matrix[row][column] != null) {
					return matrix[row][column].getX() + grid.getX();
				}
			}
		}
		return -1;
	}

	public static int getRightBorder() {
		for (int column = Const.ALIEN_COLUMNS - 1; column >= 0; column--) {
			for (int row = 0; row < Const.ALIEN_ROWS; row++) {
				if (matrix[row][column] != null) {
					return matrix[row][column].getX() + grid.getX() + matrix[row][column].getWidth();
				}
			}
		}
		return -1;
	}

}
