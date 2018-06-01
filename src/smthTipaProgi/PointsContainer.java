package smthTipaProgi;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import levelpac.GameManager;


public class PointsContainer {
	private JLabel label;
	private int count;
	private Game game = GameManager.getCurrentGame();
	
	public void draw(int score) {
		count = score;
		label = new JLabel();
		label.setText("Points: "+String.valueOf(count));
		label.setFont(new Font("Courier New", Font.BOLD, 42));
		label.setForeground(Color.WHITE);
		label.setLocation(0, 5);
		label.setSize(500,60);
		game.lp.add(label, Const.LIVES_LAYER);
	}
	
	public void change(int rang) {
		count+=rang;
		label.setText("Points: "+String.valueOf(count));
		game.lp.repaint();
		label.repaint();
		game.score = count;
	}
	
	public void delete() {
		game.lp.remove(label);
	}
	
	
	

	
	
}
