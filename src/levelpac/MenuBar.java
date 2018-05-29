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
	private final ImageIcon start_off = new ImageIcon("res/menu/start_bar_off.png");
	private final ImageIcon start_on = new ImageIcon("res/menu/start_bar_on.png");
	
	private final ImageIcon leader_off = new ImageIcon("res/menu/leader_bar_off.png");
	private final ImageIcon leader_on = new ImageIcon("res/menu/leader_bar_on.png");
	
	private final ImageIcon quit_off = new ImageIcon("res/menu/quit_bar_off.png");
	private final ImageIcon quit_on = new ImageIcon("res/menu/quit_bar_on.png");
	
	
	private final ImageIcon resume_off = new ImageIcon("res/menu/resume_bar_off.png");
	private final ImageIcon resume_on = new ImageIcon("res/menu/resume_bar_on.png");
	
	private final ImageIcon main_menu_off = new ImageIcon("res/menu/main_menu_bar_off.png");
	private final ImageIcon main_menu_on = new ImageIcon("res/menu/main_menu_bar_on.png");
	
	public MenuBar() {
		super(new ImageIcon("res/menu/main_menu.png"));
		setLayout(null);
		setSize(350,300);
	}
	
	public void drawStart() {
		JLabel start = new JLabel(start_off);
		start.setSize(240,40);
		start.setLocation((getWidth()-240)/2, getHeight()*2/5-30);
		add(start);
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
		
	}


	public void drawLeader() {
		JLabel leader = new JLabel(leader_off);
		leader.setSize(240,40);
		leader.setLocation((getWidth()-240)/2, getHeight()*3/5-30);
		add(leader);
		leader.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				leader.setIcon(leader_on);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				leader.setIcon(leader_off);
				
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
	}

	public void drawQuit() {
		JLabel quit = new JLabel(quit_off);
		quit.setSize(240,40);
		quit.setLocation((getWidth()-240)/2, getHeight()*4/5-30);
		add(quit);
		quit.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				quit.setIcon(quit_on);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				quit.setIcon(quit_off);
				
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
	}
	
	public void drawResume() {
		JLabel resume = new JLabel(resume_off);
		resume.setSize(240,40);
		resume.setLocation((getWidth()-240)/2, getHeight()*2/5-30);
		add(resume);
		resume.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				resume.setIcon(resume_on);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				resume.setIcon(resume_off);
				
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
	}
	
}
