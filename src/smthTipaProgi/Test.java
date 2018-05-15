package smthTipaProgi;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Test {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				drawBackground();
			}
		});

	}

	static JFrame frame;
	static JPanel panel;
	
	private static void drawBackground() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setResizable(true);
		
		JLabel l = new JLabel(new ImageIcon("res/n.jpg"));
		JLabel l2 = new JLabel(new ImageIcon("res/background.png"));
		
		JPanel panel = new JPanel(new GridLayout());
		JPanel panel2 = new JPanel(new GridLayout());
		
		panel2.add(l2);
		frame.add(panel2);

	}
}
