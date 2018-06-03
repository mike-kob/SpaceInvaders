package smthTipaProgi;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import levelpac.GameManager;

public class LivesContainer {
	private JPanel panel;
	private JLabel livesTxt;
	private Game game = GameManager.getCurrentGame();
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void draw(int lives) {
		panel = new JPanel();
	    livesTxt = new JLabel("Lives:");
		livesTxt.setFont(new Font("Courier New", Font.BOLD, 42));
		livesTxt.setForeground(Color.WHITE);
		livesTxt.setLocation(850, 5);
		livesTxt.setSize(500, 60);
		game.getLp().add(livesTxt, Const.LIVES_LAYER);

		panel.setLayout(new GridLayout(0, 5));
		panel.setSize(270, 60);
		panel.setLocation(990, 0);
		panel.setOpaque(false);
		for (int i = 0; i < lives; i++)
			add();
		game.getLp().add(panel, Const.LIVES_LAYER);
	}

	public void remove() {
		int count = panel.getComponentCount();
		if (count > 1) {
			panel.remove(count - 1);
		} else {
			panel.removeAll();
			game.stop(true);
		}
	}

	public void add() {
		try {
			if (panel.getComponentCount() == 10) {
				return;
			}
			Image im = ImageIO.read(new File(Const.HEART_PATH)).getScaledInstance(45, 45, Image.SCALE_DEFAULT);
			ImageIcon ii = new ImageIcon();
			ii.setImage(im);
			JLabel live = new JLabel(ii);
			panel.add(live);
			panel.repaint();
			game.getLp().repaint();
			if (panel.getComponentCount() == 6) {
				panel.setSize(panel.getWidth(), panel.getHeight() * 2);
			}
		} catch (IOException e) {
		}
	}
	
	public void delete() {
		game.getLp().remove(panel);
		game.getLp().remove(livesTxt);
	}

	public JLabel getLivesTxt() {
		return livesTxt;
	}

	public void setLivesTxt(JLabel livesTxt) {
		this.livesTxt = livesTxt;
	}

}
