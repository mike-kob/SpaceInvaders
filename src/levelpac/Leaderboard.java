package levelpac;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import smthTipaProgi.Const;

public class Leaderboard extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel grid;
	private final static int PAD = 4;
	private final static Font recFont = new Font("Courier", Font.BOLD, 25);
	private JLabel last = new JLabel();
	private int page = 0;

	

	public Leaderboard() {
		super(new ImageIcon(Const.LEADER_PATH));
		setSize(getPreferredSize());
		grid = new JLabel();
		grid.setLayout(null);
		page = 0;
		grid.setLocation(30, 128);
		grid.setSize(875, 425);
		grid.setOpaque(false);
		last.setSize(1, 1);
		last.setLocation(0, 0);
		grid.add(last);

		JButton skip1 = new JButton(new ImageIcon("res/fast-forward.png"));
		skip1.setLocation(100, 570);
		skip1.setSize(32, 32);
		skip1.setOpaque(false);
		skip1.addActionListener(next);
		add(skip1);

		JButton menu = new JButton(new ImageIcon("res/home.png"));
		menu.setLocation(150, 570);
		menu.setSize(32, 32);
		menu.setOpaque(false);
		menu.addActionListener(exit);
		add(menu);

		add(grid);
		addRecords();
	}

	 ActionListener next = (ActionEvent e) -> {
		page++;
		deleteRecords();
		last.setSize(1, 1);
		last.setLocation(0, 0);
		addRecords();
	};

	 ActionListener back = (ActionEvent e) -> {
		page--;
		deleteRecords();
		last.setSize(1, 1);
		last.setLocation(0, 0);
		addRecords();
	};

	 ActionListener exit = (ActionEvent e) -> {
		
		GameManager.lp.remove(this);
		GameManager.lp.repaint();
		GameManager.drawMenu();
		
	};

	public void addRecords() {
		int to;
		if (GameManager.list.getSize() > 10 + 10 * page) {
			to = 10 + 10 * page;
		} else {
			to = GameManager.list.getSize();
		}
		for (int i = 10 * page; i < to; i++) {
			addRecord(String.valueOf(i + 1), GameManager.list.getObject(i).getName(),
					GameManager.list.getObject(i).getLevel(), GameManager.list.getObject(i).getPoints());
		}
	}

	public void deleteRecords() {
		grid.removeAll();
		grid.repaint();
	}

	public  void addRecord(String place, String name, String level, String score) {
		JLabel record = new JLabel();
		record.setLayout(null);
		record.setSize(grid.getWidth(), (grid.getHeight() - PAD * 10) / 10);
		record.setLocation(0, last.getY() + last.getHeight() + PAD);
		record.setBackground(new Color(28, 153, 118, 200));
		record.setOpaque(true);

		JLabel lab1 = new JLabel(place, SwingConstants.CENTER);
		lab1.setFont(recFont);
		lab1.setSize(90, 40);
		lab1.setLocation(0, 0);
		record.add(lab1);

		JLabel lab2 = new JLabel(name, SwingConstants.CENTER);
		lab2.setFont(recFont);
		lab2.setSize(278, 40);
		lab2.setLocation(95, 0);
		record.add(lab2);

		JLabel lab3 = new JLabel(level, SwingConstants.CENTER);
		lab3.setFont(recFont);
		lab3.setSize(240, 40);
		lab3.setLocation(380, 0);
		record.add(lab3);

		JLabel lab4 = new JLabel(score, SwingConstants.CENTER);
		lab4.setFont(recFont);
		lab4.setSize(250, 40);
		lab4.setLocation(628, 0);
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
