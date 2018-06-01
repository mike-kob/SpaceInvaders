package levelpac;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class RecordField extends JLabel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String probel = "                                                 ";
	  private String number;
	  private String points;
	  private String level;
	  private String name;
	  private final int PAD = 4;
	private final Font recFont = new Font("Courier", Font.BOLD, 25);
	  

	public RecordField( String name, String level, String score) {
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
		// TODO Auto-generated method stub
		return name;
	}


	public String getLevel() {
		// TODO Auto-generated method stub
		return level;
	}


	


	
}
