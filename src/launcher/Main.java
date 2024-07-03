package launcher;

import gui.Game;

public class Main 
{
	//width= 1250 height=825
	private static int frameWidh = 1250, frameHeight = 680;
		
	public static void main(String[] args)
	{
		Game game = new Game("K.A.s.", frameWidh, frameHeight);
		game.start();
	}
}