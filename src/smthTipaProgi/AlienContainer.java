package smthTipaProgi;

import java.awt.Color;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlienContainer {
	private static JPanel grid = new JPanel(null);
	private static JFrame frame = Game.frame;
	private static final Set<Alien> aliens = Collections.newSetFromMap(new ConcurrentHashMap<Alien, Boolean>());

	private static Alien[][] matrix = new Alien[Constants.ALIEN_ROWS][Constants.ALIEN_COLUMNS];

	public static Set<Alien> getAliens() {
		return aliens;
	}

	public static boolean isEmpty() {
		return aliens.size() == 0;
	}

	public static JPanel getPanel() {
		for (int i = 0; i < Constants.ALIEN_ROWS; i++) {
			for (int j = 0; j < Constants.ALIEN_COLUMNS; j++) {
				Alien cur = new Alien(j * (64 + Constants.INTERVAL_X), i * 64, i);
				matrix[i][j] = cur;
				cur.setOpaque(true);
				aliens.add(cur);
				grid.add(cur);

			}
		}

		
		grid.setSize(1000, 1000);
		

		grid.setOpaque(true);
		grid.setSize(700, 350);;

		grid.setOpaque(false);
		grid.setBackground(new Color(255, 255, 255, 30));
	
		grid.setSize(700, 350);


		grid.setLocation((frame.getWidth() - grid.getWidth()) / 2, 50);
		grid.setVisible(true);
		return grid;
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

	private static void removeFromMatrix(Alien alien) {
		for (int column = 0; column < Constants.ALIEN_COLUMNS; column++) {
			for (int row = 0; row < Constants.ALIEN_ROWS; row++) {
				if (matrix[row][column] == alien) {
					matrix[row][column] = null;
					return;
				}
			}
		}

	}

	public static void update() {
		int direction = Constants.ALIEN_SPEED;

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
					Thread.sleep(1000);
				}
				grid.setLocation(x + direction, y);
				grid.repaint();
				Game.lp.repaint();
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
	}

	public static Alien getLastInColumn(int i) {
		for (int j = Constants.ALIEN_ROWS - 1; j >= 0; j--) {
			if (matrix[j][i] != null) {
				return matrix[j][i];
			}
		}
		return null;
	}

	public static int getLeftBorder() {
		for (int column = 0; column < Constants.ALIEN_COLUMNS; column++) {
			for (int row = 0; row < Constants.ALIEN_ROWS; row++) {
				if (matrix[row][column] != null) {
					return matrix[row][column].getX() + grid.getX();
				}
			}
		}
		return -1;
	}

	public static int getRightBorder() {
		for (int column = Constants.ALIEN_COLUMNS - 1; column >= 0; column--) {
			for (int row = 0; row < Constants.ALIEN_ROWS; row++) {
				if (matrix[row][column] != null) {
					return matrix[row][column].getX() + grid.getX() + matrix[row][column].getWidth();
				}
			}
		}
		return -1;
	}

}
