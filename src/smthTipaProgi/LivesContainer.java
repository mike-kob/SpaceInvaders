package smthTipaProgi;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LivesContainer {
	// TODO created
	public static JPanel panel;

	public static void draw() {
		panel = new JPanel();
		JLabel livesTxt = new JLabel("Lives:");
		livesTxt.setFont(new Font("Courier New", Font.BOLD, 42));
		livesTxt.setForeground(Color.WHITE);
		livesTxt.setLocation(850, 5);
		livesTxt.setSize(500,60);
		Game.lp.add(livesTxt, Const.LIVES_LAYER);
		
		panel.setLayout(new GridLayout(0, 5));
		panel.setSize(270, 60);
		panel.setLocation(990, 0);
		panel.setOpaque(false);
		add();
		add();
		add();
		Game.lp.add(panel, Const.LIVES_LAYER);
	}

	public static void remove() {
		int  count = panel.getComponentCount();
		if(count>0) {
			panel.remove(count-1);
		} else {
		//	Game.stop();
		}
	}

	public static void add() {
		try {
			if(panel.getComponentCount()==10) {
				return;
			}
			Image im = ImageIO.read(new File(Const.ROCKET_PATH)).getScaledInstance(45, 45, Image.SCALE_DEFAULT);
			ImageIcon ii = new ImageIcon();
			ii.setImage(im);
			JLabel live = new JLabel(ii);
			panel.add(live);
			panel.repaint();
			Game.lp.repaint();
			if (panel.getComponentCount() == 6) {
				panel.setSize(panel.getWidth(), panel.getHeight() * 2);
			}
		} catch (IOException e) {
		}
	}

}
