package states;

import java.awt.Graphics;

import ImageLoader.Assets;
import gui.Game;
import inputs.Button;
import inputs.Handler;
import inputs.MouseManager;

public class TutorialState extends ScreenState
{
	private MouseManager mouseMan;
	private Handler handler;
	private Button back, next, exit;
	private boolean needBack, needNext;
	private int cur = 0;
	
	public TutorialState(Handler handle)
	{
		handler = handle;
		mouseMan = handle.getMouseManager();
		
		needBack = false;
		needNext = true;
		buttons();
	}

	public void buttons() 
	{
		
		back = new Button(Assets.backArrow, Game.getWidth()/2-Assets.back[0].getWidth(), 0, Assets.back[0].getWidth(), Assets.back[0].getHeight(), handler);
		next = new Button(Assets.next, Game.getWidth()/2, 0, Assets.back[0].getWidth(), Assets.back[0].getHeight(), handler);
		exit = new Button(Assets.xButton2, 14, 7, 101, 53, handler);
	}

	public void tick() 
	{
		if (back.isClicked())
		{
			needNext = true;
			cur--;
			if (cur==0)
			{
				needBack = false;
			}
			back.reset();
		}
		if (next.isClicked())
		{
			needBack = true;
			cur++;
			if (cur+1>=Assets.tutorialSlides.length)
			{
				needNext = false;
			}
			next.reset();
		}
		
		exit.tick();
		if (exit.isClicked())
		{
			setScreenState("mainMenu");
		}
		
		if (needNext)
		{
			next.tick();
		}
		if (needBack)
		{
			back.tick();
		}
	}
	

	public void render(Graphics g) 
	{
		g.drawImage(Assets.tutorialSlides[cur], 0, 0, Game.getWidth(), Game.getHeight(), null);
		
		exit.render(g);
		if (needNext)
		{
			next.render(g);
		}
		if (needBack)
		{
			back.render(g);
		}
	}
}
