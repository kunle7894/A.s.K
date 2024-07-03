package states;

import java.awt.Graphics;

import ImageLoader.Assets;
import inputs.Handler;

public class MultiPlayerState extends ScreenState
{
	
	public MultiPlayerState(Handler handle, String difficulty, String map, int numplayer, int numAI)
	{
		setUpGame(difficulty, map, numplayer, numAI);
	}
	
	public void setUpGame(String diff, String map, int numPlay, int numAI)
	{
		setScreenState("multiPlayerGame");
		
		if (diff.equals("level 1"))
		{
			GameState.setAITankDifficulty(300);
		}
		else
		{
			if (diff.equals("level 2"))
			{
				GameState.setAITankDifficulty(200);
			}
			else
			{		
				if (diff.equals("level 3"))
				{
					GameState.setAITankDifficulty(100);
				}
				else
				{	
					GameState.setAITankDifficulty(1);
				}
			}
		}
		if (map.equals("map 1"))
		{
			GameState.setBackground(Assets.background4);
		}
		else
		{
			if (map.equals("map 2"))
			{
				GameState.setBackground(Assets.darkMap);
			}
			else
			{
				GameState.setBackground(Assets.background4);
			}
		}
		GameState.setFriendlyAmount(numPlay);
		GameState.setTankAmount(numAI);
	}
	
	public void buttons() 
	{
		
		
	}
	public void tick() 
	{
		
	}
	
	public void render(Graphics g)
	{
		
	}
}
