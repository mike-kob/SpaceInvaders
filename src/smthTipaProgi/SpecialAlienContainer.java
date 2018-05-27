package smthTipaProgi;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SpecialAlienContainer extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel label;
	static Mp3Player mp = new Mp3Player(Const.SOUND_OF_EXPLOSION);
	
	public static void draw() {
		label = new JLabel();
		ImageIcon im = new ImageIcon(Const.UFO_SPECIAL_PATH);
		label.setIcon(im);
		label.setLocation(-64, 50);
		label.setSize(64, 44);
		Game.lp.add(label, Const.ALIEN_LAYER);
	}

	public static void update() {

		if (isChecker(label.getX())) {
			delete();
			draw();
		}

		label.setLocation(label.getX() + 1, label.getY());
		Game.lp.repaint();
	

		for (Bomb bomb : BombContainer.getBombs()) {
			if (isHit(bomb)) {
				PointsContainer.change(Const.POINTS_FOR_SPECIAL);
				BombContainer.remove(bomb);
				new Thread() {
					public void run() {
						mp.play();
					}
				}.start();
				delete();
				draw();
			}
		}
		pause(10);
	}
	
	
	private static void delete() {
		Game.lp.remove(label);
		Game.lp.repaint();
		pause(Const.PAUSE_FOR_SPECIAL_UFO);
	}

	private static boolean isHit(Bomb bomb) {
		int x = label.getX();
		int y = label.getY();

		if (x-20  < bomb.getX() && bomb.getX() + bomb.getWidth() < x + label.getWidth()+20) {
			if (y + label.getHeight() >= bomb.getY()) {
				return true;
			
		}}
		return false;
		
	}

	private static boolean isChecker(int x) {
		return x >= Game.frame.getWidth();
	}

	private static void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
