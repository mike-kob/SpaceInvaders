package smthTipaProgi;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;


public class PointsContainer {
	private static JLabel label;
	private static int count;
	
	public static void draw() {
		label = new JLabel();
		label.setText("Points: "+String.valueOf(count));
		label.setFont(new Font("Courier New", Font.BOLD, 42));
		label.setForeground(Color.WHITE);
		label.setLocation(0, 5);
		label.setSize(500,60);
		Game.lp.add(label, Const.LIVES_LAYER);
	}
	
	public static void change(int rang) {
		count+=rang;
		label.setText("Points: "+String.valueOf(count));
		Game.lp.repaint();
		label.repaint();
	}
	
	
	

	
	
}
