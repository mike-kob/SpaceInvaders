package levelpac;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private JButton skip1;
	private JButton skip2;
	private JButton menu;

	public Leaderboard(int page) {
		super(new ImageIcon(Const.LEADER_PATH));
		setSize(getPreferredSize());
		grid = new JLabel();
		grid.setLayout(null);
		this.page = page;
		grid.setLocation(30, 128);
		grid.setSize(875, 425);
		grid.setOpaque(false);
		last.setSize(1, 1);
		last.setLocation(0, 0);
		grid.add(last);

		skip1 = new JButton(new ImageIcon("res/fast-forward.png"));
		skip1.setLocation(150, 565);
		skip1.setSize(32, 32);
		skip1.setOpaque(false);
		skip1.addActionListener(next);
		skip1.setBackground(new Color(255, 255, 255));
		skip1.setBorderPainted(false);
		skip1.setEnabled(true);
		add(skip1);

		skip2 = new JButton(new ImageIcon("res/skip2.png"));
		skip2.setLocation(40, 565);
		skip2.setSize(32, 32);
		skip2.setOpaque(false);
		skip2.addActionListener(back);
		skip2.setBackground(new Color(255, 255, 255));
		skip2.setBorderPainted(false);
		skip2.setEnabled(true);
		add(skip2);

		menu = new JButton(new ImageIcon("res/home.png"));
		menu.setLocation(95, 565);
		menu.setSize(32, 32);
		menu.setOpaque(true);
		menu.addMouseListener(exit);
		menu.setBorderPainted(false);
		menu.setEnabled(true);
		add(menu);

		add(grid);
		addRecords();

	}

	private ActionListener next = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			page++;
			deleteRecords();
			last.setSize(1, 1);
			last.setLocation(0, 0);
			addRecords();
		}
	};

	private ActionListener back = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			page--;
			deleteRecords();
			last.setSize(1, 1);
			last.setLocation(0, 0);
			addRecords();
		}
	};

	private MouseListener exit = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			GameManager.drawMenu();
		}
	};

	public void addRecords() {
		int to;
		

		skip1.setEnabled(true);
		skip2.setEnabled(true);
	
		
	
		if (GameManager.list.getSize() > 10 + 10 * page) {
			to = 10 + 10 * page;
		} else {
			to = GameManager.list.getSize();
		}
		for (int i = 10 * page; i < to; i++) {
			addRecord(String.valueOf(i + 1), GameManager.list.getObject(i).getName(),
					GameManager.list.getObject(i).getLevel(), GameManager.list.getObject(i).getPoints());
		}

		if (page == 0) {
			skip2.setEnabled(false);
		
			grid.repaint();
		}
		if (page == getNumOfPages() -1) {
			skip1.setEnabled(false);
			grid.repaint();
	
		}
	}

	public void deleteRecords() {
		grid.removeAll();
		grid.repaint();
	}

	public int getNumOfPages() {
		int size = GameManager.list.getSize();
		int page = 1;
		while (size > 10) {
			size -= 10;
			page++;
		}
		return page;
	}

	public void addRecord(String place, String name, String level, String score) {
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
