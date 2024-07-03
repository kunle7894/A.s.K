package states;

import java.awt.Graphics;


public abstract class ScreenState 
{
	private static String screenState;
	public  abstract void buttons();
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	

	public static String getScreenState() 
	{
		return screenState;
	}

	public void setScreenState(String state) 
	{
		screenState = state;
	}

}

//I ADDED STUFF INTO THE ASSETS CLASS AND MADE GAMESTATE EXTEND SCREENSTATE