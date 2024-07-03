package states;

import java.awt.Graphics;


import ImageLoader.Assets;
import gui.Game;
import inputs.Button;
import inputs.Handler;

public class MainState extends ScreenState
{
	private Handler handler;
	private Button play, tutorial, options, exit;
	private static OptionsMenu optionsMenuScreen;
	private boolean inOptionsScreen = false;
	
	public MainState(Handler handle)
	{
		handler = handle;
		
		play = new Button(Assets.play, 150, 135, 324, 69, handler);
		tutorial = new Button(Assets.tutorial, 150, 275, 324, 69, handler);
		options = new Button(Assets.options, 150, 415, 324, 69, handler);
		exit = new Button(Assets.exit, 150, 571, 324, 69, handler);
		
		optionsMenuScreen = new OptionsMenu(handler);
	}

	
	public void buttons()
	{
		if (!inOptionsScreen)
		{
			if (play.isClicked())
			{
				setScreenState("playerSelectState");
			}

			if (exit.isClicked())
			{
				//Display.getFrame().dispose();
				System.exit(0);
			}
			if (options.isClicked())
			{
				inOptionsScreen = true;
				optionsMenuScreen.setOptionsMenuClose(false);
			}
			if (tutorial.isClicked())
			{
				setScreenState("tutorial");
			}
		}
		if (inOptionsScreen)
		{
			inOptionsScreen = optionsMenuScreen.getOptionsMenuClose();
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.mainMenu, 0, 0, Game.getWidth(), Game.getHeight(), null);
		play.render(g);
		tutorial.render(g);
		options.render(g);
		exit.render(g);
		
		if (inOptionsScreen)
		{
			optionsMenuScreen.render(g);
		}
	}
	
	
	public void tick()
	{
		play.tick();
		tutorial.tick();
		options.tick();
		exit.tick();
		buttons();
		
		if (inOptionsScreen)
		{
			optionsMenuScreen.tick();
		}
	}
	
	public static OptionsMenu getOptionsMenuScreen()
	{
		return optionsMenuScreen;
	}
}


//I ADDED STUFF IN THE ASSESTS CLASS