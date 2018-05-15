package smthTipaProgi;


import java.lang.reflect.InvocationTargetException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Game {
	static JFrame frame;

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				drawBackGrounds();
			}
		});
	}

	private static void drawBackGrounds() {
		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setVisible(true);
		frame.add(new JLabel(new ImageIcon("res/background.png")));
	}
}
