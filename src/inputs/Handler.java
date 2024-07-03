package inputs;

import gui.Game;
import sound.SoundManager;
import states.GameState;

public class Handler 
{
	private Game game;
	
	public Handler(Game gme)
	{
		game = gme;
	}
	
	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}
	
	public KeyManager getKeyManager()
	{
		return game.getKeyManager();
	}
	
	public Game getGame()
	{
		return game;
	}

	public Ruler getRuler() 
	{
		// TODO Auto-generated method stub
		return game.getGameState().getRuler();
	}
	
	public GameState getGameState()
	{
		return game.getGameState();
	}
	
	public SoundManager getSoundManager()
	{
		return game.getSoundManager();
	}
}
