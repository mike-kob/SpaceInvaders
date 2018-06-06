package smthTipaProgi;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import levelpac.GameManager;


public class PointsContainer {
	private JLabel label;
	private int count;
	private Game game = GameManager.getCurrentGame();
	//d
	public void draw(int score) {
		count = score;
		label = new JLabel();
		label.setText("Points: "+String.valueOf(count));
		label.setFont(new Font("Courier New", Font.BOLD, 42));
		label.setForeground(Color.WHITE);
		label.setLocation(90, 5);
		label.setSize(500,60);
		game.getLp().add(label, Const.LIVES_LAYER);
	}
	//d
	public void change(int rang) {
		count+=rang;
		label.setText("Points: "+String.valueOf(count));
		game.getLp().repaint();
		label.repaint();
		game.score = count;
	}
	//d
	public void delete() {
		
		game.getLp().remove(label);
	}
}
