package levelpac;

import javax.swing.JLabel;

public class RecordField extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String number, points, level,  name;

	public RecordField(String name, String level, String score) {
		super();
		this.points = score;
		this.level = level;
		this.name = name;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public String geyName() {
		return name;
	}

	public String getLevel() {
		return level;
	}

}
