package levelpac;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import smthTipaProgi.Const;

public class Leaderboard extends JLabel{
	
	private JLabel grid;
	private final int PAD = 4;
	private final Font recFont = new Font("Courier", Font.BOLD, 25);
	private JLabel last = new JLabel();
	
	public Leaderboard() {
		super(new ImageIcon(Const.LEADER_PATH));
		setSize(getPreferredSize());
		grid = new JLabel();
		grid.setLayout(null);
		
		grid.setLocation(30, 128);
		grid.setSize(875, 425);
		grid.setOpaque(false);
		last.setSize(1, 1);
		last.setLocation(0, 0);
		grid.add(last);
		
		add(grid);
	}
	
	public void addRecord(String place, String name, String level, String score) {
		JLabel record = new JLabel();
		record.setLayout(null);
		record.setSize(grid.getWidth(), (grid.getHeight()-PAD*10)/10);
		record.setLocation(0,last.getY()+last.getHeight()+PAD);
		record.setBackground(new Color(28, 153, 118, 200));
		record.setOpaque(true);
		
		JLabel lab1 = new JLabel(place, SwingConstants.CENTER);
		lab1.setFont(recFont);
		lab1.setSize(90, 40);
		lab1.setLocation(0,0);
		record.add(lab1);

		JLabel lab2 = new JLabel(name, SwingConstants.CENTER);
		lab2.setFont(recFont);
		lab2.setSize(278, 40);
		lab2.setLocation(95,0);
		record.add(lab2);
		
		JLabel lab3 = new JLabel(level, SwingConstants.CENTER);
		lab3.setFont(recFont);
		lab3.setSize(240, 40);
		lab3.setLocation(380,0);
		record.add(lab3);
		
		JLabel lab4 = new JLabel(score, SwingConstants.CENTER);
		lab4.setFont(recFont);
		lab4.setSize(250, 40);
		lab4.setLocation(628,0);
		record.add(lab4);
		
		grid.add(record);
		
		last = record;
		grid.repaint();	
	}
	
	public void clearPage() {
		grid.removeAll();
		grid.repaint();
	}
}
