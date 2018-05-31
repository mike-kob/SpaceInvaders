package smthTipaProgi;

import java.awt.Color;

public class Const {
	public static final Integer BACKGROUND_LAYER = 1;
	public static final Integer ALIEN_LAYER = 3;
	public static final Integer DEFENCE_LAYER = 2;
	public static final Integer BOMB_LAYER = 4;
	public static final Integer ROCKET_LAYER = 5;
	public static final Integer DYNAMITE_LAYER = 6;
	public static final Integer LIVES_LAYER = 7;
	public static final Integer FINAL_MSG_LAYER = 8;
	public static final Integer MENU_LAYER = 9;
	
	public static final int DEFENCE_Y = 600;
	public static final int HEIGHT_OF_BAR = 10;
	public static final int WEIGHT_OF_BAR = 140;
	public static final int NUM_OF_DEF = 4;
	public static final int LIFE_OF_ROCK = 9;
	public static final int INTERVAL_X = 35;
	public static final int INTERVAL_Y = 25;
	
	
	public static final int BOMB_SPEED=5;
	public static final int BOMB_FREQUENCY = 3000; //--> max number of millis+1000 Thread waits to launch next bomb
	public static final int DYN_SPEED= 2;
	public static final int BOMB_STARTING_LEVEL = 760;
	public static final long BOMB_TIME_INTERVAL = 500; //-->frequency of bombs
	public static final double HEAL_PROBABILITY= 0.05;
	
	public static final int ALIEN_STEP = 50;
	public static final long ALIEN_TIME_STEP = 1000; //--> time in millis of Thread.sleep in alien update method
	public static final int ALIENT_START_Y = 60;
	public static final int ALIEN_ROWS = 5;
	public static final int ALIEN_COLUMNS = 7;
	public static final int POINTS_FOR_SPECIAL = 50;
	protected static final int PAUSE_FOR_SPECIAL_UFO = 10000;
	
	public static final String BACKGROUND_PATH = "res/background.png";
	public static final String UFO_HIGH_PATH = "res/ufo/rowhigh.png";
	public static final String UFO_MID_PATH = "res/ufo/rowmid.png";
	public static final String UFO_LOW_PATH = "res/ufo/rowlow.png";
	public static final String BOMB_PATH = "res/bomb.png";
    public static final String ASTEROID_PATH = "res/rock/камень_1.png";
	public static final String DYNAMITE_PATH = "res/dynamite.png";
	public static final String ROCKET_PATH = "res/rocket.png";
	public static final String ROCKET_GLOW_PATH = "res/rocket_glow.png";
	public static final String HEART_PATH = "res/heart.png";
	public static final String UFO_SPECIAL_PATH = "res/ufo/ufo_special.png";
	public static final String BOOM_PATH = "res/boom.gif";
	public static final String ROCK_BUBUH_PATH = "res/bubuh.gif";
	public static final String LEADER_PATH = "res/leaderboard/leaderboard2.png";
	public static final String SOUND_OF_EXPLOSION = "res/Explosion.mp3";
	public static final String MUSIC_PATH = "res/Hiding Your Reality.mp3";
	
	public static final Color TRANSPARENT = new Color(0,0,0,1);

	public static final int POINTS_FOR_HIGHT = 50;
	public static final int POINTS_FOR_MID = 40;
	public static final int POINTS_FOR_LOW = 30;
	
	public static final int TIME_NOT_ATTACK = 3000;	
}

