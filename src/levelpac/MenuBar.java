package levelpac;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MenuBar extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon start_off = new ImageIcon("res/menu/start_bar_off.png");
	private ImageIcon start_on = new ImageIcon("res/menu/start_bar_on.png");
	
	public MenuBar() {
		super(new ImageIcon("res/menu/menu.png"));
		setLayout(null);
		setSize(350,300);
		
	}
	
	public void drawStart() {
		
		JLabel start = new JLabel(start_off);
		start.setSize(240,40);
		start.setLocation((getWidth()-240)/2, 100);
		start.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				GameManager.startNewGame();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				start.setIcon(start_on);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				start.setIcon(start_off);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		add(start);
	}
}
