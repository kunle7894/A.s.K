package states;

import java.awt.Graphics;

import ImageLoader.Assets;
import gui.Game;
import inputs.Button;
import inputs.Handler;
import inputs.KeyManager;

public class StartState extends ScreenState
{
	private Handler handler;
	private Button screenButton;
	private KeyManager keyMan;

	public StartState(Handler handle)
	{
		handler = handle;
		keyMan = handler.getKeyManager();
		screenButton = new Button(Assets.start, 0, 0, Game.getWidth(), Game.getHeight(), handler);
	
		buttons();
	}
	public void buttons() 
	{
		if (screenButton.isClicked())
		{
			setScreenState("mainMenu");
		}
		if (!keyMan.getCurrentLetter().equals(""))
		{
			setScreenState("mainMenu");
			keyMan.setCurrentLetter("");
		}
	}


	public void tick() 
	{
		screenButton.tick();
		buttons();
	}
	
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.startMenu, 0, 0, Game.getWidth(), Game.getHeight(), null);
	}

}
