package states;

import java.awt.Graphics;

import ImageLoader.Assets;
import gui.Game;
import inputs.Button;
import inputs.Handler;

public class SinglePlayerState extends ScreenState
{
	private Handler handler;
	//private GameState gameState;
	private Button easy, medium, hard, extreme, back;
	
	public SinglePlayerState(Handler handle)
	{
		handler = handle;
		//gameState = handler.getGameState();
		easy = new Button(Assets.easy, 150, 135, 324, 69, handler);
		medium = new Button(Assets.medium, 150, 275, 324, 69, handler);
		hard = new Button(Assets.hard, 150, 415, 324, 69, handler);
		extreme = new Button(Assets.extreme, 150, 571, 324, 69, handler);
		back = new Button(Assets.back, 14, 6, 100, 51, handler);
	}
	
	public void buttons()
	{
		if (easy.isClicked())
		{
			setScreenState("singlePlayerGame");
			/*Take notes Andrew
				*Never use static like
				*GameState.setAITankDifficulty(300);
			*/
			GameState.setAITankDifficulty(300);
			GameState.setTankAmount(1);
			GameState.setBackground(Assets.background4);
		}
		if (medium.isClicked())
		{
			setScreenState("singlePlayerGame");
			GameState.setAITankDifficulty(200);
			GameState.setTankAmount(2);
			GameState.setBackground(Assets.background4);
		}
		if (hard.isClicked())
		{
			setScreenState("singlePlayerGame");
			GameState.setAITankDifficulty(100);
			GameState.setTankAmount(4);
			GameState.setBackground(Assets.background4);
		}
		if (extreme.isClicked())
		{
			setScreenState("singlePlayerGame");
			GameState.setAITankDifficulty(1);
			GameState.setTankAmount(5);
			GameState.setBackground(Assets.background4);
		}
		if (back.isClicked())
		{
			setScreenState("playerSelectState");
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.mainMenu, 0, 0, Game.getWidth(), Game.getHeight(), null);
		easy.render(g);
		medium.render(g);
		hard.render(g);
		extreme.render(g);
		back.render(g);
	}
	
	public void tick()
	{
		easy.tick();
		medium.tick();
		hard.tick();
		extreme.tick();
		back.tick();
		buttons();
	}
	
}
