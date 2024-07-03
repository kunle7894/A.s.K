package states;

import java.awt.Graphics;

import ImageLoader.Assets;
import inputs.Button;
import inputs.Handler;

public class GameOverScreen extends ScreenState
{
	private Handler handler;
	private Button retry, quit;
	private int arrowX = 410, arrowY = 181, mouseX, mouseY;
	private String gameMode;
	
	public GameOverScreen(Handler handle, String gMode)
	{
		handler = handle;
		gameMode = gMode;
		
		buttons();
	}
	
	public void buttons()
	{
		retry = new Button(Assets.play, 240+440, 115+175, 150, 40, handler);
		quit = new Button(Assets.exit, 240+440, 115+227, 150, 45, handler);
	}
	
	public void menuFunctions()
	{
		mouseX = handler.getMouseManager().getX();
		mouseY = handler.getMouseManager().getY();
		if (mouseX>=240+440 && mouseX<=240+590)
		{
			if (mouseY>=115+175 && mouseY<=115+215)
			{
				arrowY = 181;
			}
			if (mouseY>=115+227 && mouseY<=115+272)
			{
				arrowY = 234;
			}
			//System.out.println("The Game Over is Working");
		}
		if (retry.isClicked())
		{
			setScreenState("singlePlayerState");
		}
		if (quit.isClicked())
		{
			setScreenState("mainMenu");
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.gameOverMenu, 240, 115, Assets.gameOverMenu.getWidth(), Assets.gameOverMenu.getHeight(), null);
		g.drawImage(Assets.arrow, 240+arrowX, 115+arrowY, Assets.arrow.getWidth(), Assets.arrow.getHeight(), null);
	}
	
	public void tick()
	{
		retry.tick();
		quit.tick();
		
		menuFunctions();
	}
	
	public boolean quitIsClicked()
	{
		return quit.isClicked();
	}
	
	public boolean retyIsClicked()
	{
		return retry.isClicked();
	}
}
