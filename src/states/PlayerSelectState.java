package states;

import java.awt.Graphics;

import ImageLoader.Assets;
import gui.Game;
import inputs.Button;
import inputs.Handler;
import inputs.SpecialTextBox;

public class PlayerSelectState extends ScreenState
{
	private Handler handler;
	private Button singlePlayer, multiPlayer, back, level1Button, level2Button, level3Button, level4Button, map1Button, map2Button;
	
	private ScreenSlider multiplayerSelect;
	private boolean inMultiplayer = false;
	private String lvlButtonClicked = "";
	private String mapButtonClicked = "";
	
	private SpecialTextBox playerTextBox, aiTextBox;
	private int playerCount, aiCount;
	
	 public PlayerSelectState(Handler handle)
	{
		handler = handle;
		
		singlePlayer = new Button(Assets.singlePlayer, 150, 157, 324, 138, handler);
		multiPlayer = new Button(Assets.multiPlayer, 150, 454, 324, 138, handler);
		back = new Button(Assets.back, 14, 6, 100, 51, handler);
	}
	
	public void buttons()
	{
		if (singlePlayer.isClicked())
		{
			setScreenState("singlePlayerState");
		}
		if (multiPlayer.isClicked())
		{
			inMultiplayer = true;
			multiplayerSelect = new ScreenSlider(Assets.multiPlayerSelectScreen, 498, 680, 498, 107, 0, 10, 350, 415);
			initMultiplayerButtons();
		}
		if (back.isClicked())
		{
			setScreenState("mainMenu");
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.mainMenu, 0, 0, Game.getWidth(), Game.getHeight(), null);
		singlePlayer.render(g);
		multiPlayer.render(g);
		back.render(g);
		if (inMultiplayer)
		{
			multiplayerSelect.render(g);
			renderMultiPlayerSelections(g);
		}
	}
	
	public void tick()
	{
		singlePlayer.tick();
		multiPlayer.tick();
		back.tick();
		buttons();
		if (inMultiplayer)
		{
			multiplayerSelect.tick();
			tickMultiplayerSelections();
			checkStateChange();
		}
	}
	
	//renders the changeable things in the Multiplayer slider
	public void renderMultiPlayerSelections(Graphics g)
	{
		level1Button.render(g);
		level2Button.render(g);
		level3Button.render(g);
		level4Button.render(g);
		map1Button.render(g);
		map2Button.render(g);
		playerTextBox.render(g);
		aiTextBox.render(g);
		//temporary Buttons
		g.drawImage(Assets.tempMap3Button, multiplayerSelect.getX()+247, multiplayerSelect.getY()+309, 45, 45, null);
		g.drawImage(Assets.tempMap4Button, multiplayerSelect.getX()+125, multiplayerSelect.getY()+309, 45, 45, null);
	}
	
	//ticks the changeable this the Multiplayer slider can use
	public void tickMultiplayerSelections()
	{
		if (multiplayerSelect.isMoving())
		{
			int multiplayerX = multiplayerSelect.getX();
			int multiPlayerY = multiplayerSelect.getY();
			
			//offsets used to put buttons in the right location relative to multiplayer slider
			level1Button.setX(multiplayerX+56);
			level1Button.setY(multiPlayerY+212);
			
			level2Button.setX(multiplayerX+191);
			level2Button.setY(multiPlayerY+212);
			
			level3Button.setX(multiplayerX+56);
			level3Button.setY(multiPlayerY+244);
			
			level4Button.setX(multiplayerX+191);
			level4Button.setY(multiPlayerY+244);
			
			map1Button.setX(multiplayerX+64);
			map1Button.setY(multiPlayerY+309);
			
			map2Button.setX(multiplayerX+186);
			map2Button.setY(multiPlayerY+309);
			
			playerTextBox.setX(multiplayerX+248);
			playerTextBox.setY(multiPlayerY+88);
			aiTextBox.setX(multiplayerX+248);
			aiTextBox.setY(multiPlayerY+124);
			//System.out.println("Multiplayer Slider Moving");
		}
		level1Button.tick();
		level2Button.tick();
		level3Button.tick();
		level4Button.tick();
		map1Button.tick();
		map2Button.tick();
		
		playerTextBox.tick();
		aiTextBox.tick();
		playerTextBox.checkParametersNumbers();
		aiTextBox.checkParametersNumbers();
		playerCount = playerTextBox.getVal();
		aiCount = aiTextBox.getVal();
		//Makes the levelButtons radio like
		makeButtonsRadios();		
	}
	
	//intializes MultiPlayer buttons
	public void initMultiplayerButtons()
	{
		int multiplayerX = multiplayerSelect.getX();
		int multiplayerY = multiplayerSelect.getY();
		
		//offsets used to put buttons in the right location relative to multiplayer slider 80 and 20 represent the width and height of the level button respectively
		level1Button = new Button(Assets.level1Button, multiplayerX+56, multiplayerY+212, 80, 20, handler);
		level2Button = new Button(Assets.level2Button, multiplayerX+191, multiplayerY+212, 80, 20, handler);
		level3Button = new Button(Assets.level3Button, multiplayerX+56, multiplayerY+244, 80, 20, handler);
		level4Button = new Button(Assets.levelXButton, multiplayerX+191, multiplayerY+244, 80, 20, handler);
		
		//offsets used to put buttons in the right location relative to multiplayer slider 45 represents the width and height of the map buttons
		map1Button = new Button(Assets.map1Button, multiplayerX+64, multiplayerY+309, 45, 45, handler);
		map2Button = new Button(Assets.map2Button, multiplayerX+125, multiplayerY+309, 45, 45, handler);
		
		playerTextBox = new SpecialTextBox(multiplayerX+248, multiplayerY+88, 83, 20, handler, "0123456", 6);
		aiTextBox = new SpecialTextBox(multiplayerX+248, multiplayerY+124, 83, 20, handler, "0123456", 6);
		
		lvlButtonClicked = "";
		mapButtonClicked = "";
		playerCount= 0; 
		aiCount = 0;
	}
	
	//Check to see if requirements are meant to change state
	/*
	 * Players and AI need to have at least 2 players together
	 * Can't have greater than 6
	 * can't have a value less than 0
	 * Must have a map and a level.
	 * 
	 */
	public void checkStateChange()
	{
		if (aiCount+playerCount<=6 && aiCount+playerCount>=2 && aiCount>=0 && playerCount>=0)
		{
			if (lvlButtonClicked.length()>0 && mapButtonClicked.length()>0)
			{
				setScreenState("multilimboState");
				
				//This is used Eclipse is just retarded
				@SuppressWarnings("unused")
				MultiPlayerState multi = new MultiPlayerState(handler, lvlButtonClicked, mapButtonClicked, playerCount, aiCount);
			}
		}
	}
	
	public void makeButtonsRadios()
	{
		if (level1Button.isClicked() && !lvlButtonClicked.equals("level 1"))
		{
			lvlButtonClicked = "level 1";
			level2Button.reset();
			level3Button.reset();
			level4Button.reset();
		}
		else
		{
			if (level2Button.isClicked() && !lvlButtonClicked.equals("level 2"))
			{
				lvlButtonClicked = "level 2";
				level1Button.reset();
				level3Button.reset();
				level4Button.reset();
			}
			else
			{	
				if (level3Button.isClicked() && !lvlButtonClicked.equals("level 3"))
				{
					lvlButtonClicked = "level 3";
					level1Button.reset();
					level2Button.reset();
					level4Button.reset();
				}
				else
				{
					if (level4Button.isClicked() && !lvlButtonClicked.equals("level 4"))
					{
						lvlButtonClicked = "level 4";
						level1Button.reset();
						level2Button.reset();
						level3Button.reset();
					}
				}
			}
		}
		//Makes map buttons radio like
		if (map1Button.isClicked() && !mapButtonClicked.equals("map 1"))
		{
			mapButtonClicked = "map 1";
			map2Button.reset();
		}
		else
		{
			if (map2Button.isClicked() && !mapButtonClicked.equals("map 2"))
			{
				mapButtonClicked = "map 2";
				map1Button.reset();
			}
		}
	}
}
