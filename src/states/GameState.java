package states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ImageLoader.Assets;
import gui.Game;
import inputs.Button;
import inputs.Handler;
import inputs.MouseManager;
import inputs.Ruler;
import tanks.Tank;
import tanks.TurnSetter;
import world.World;

public class GameState extends ScreenState
{
	private TurnSetter tanks;
	private static int enemyTanks, playerTanks, difficulty;
	
	private World world;
	
	private int ticks=0, maxTime=30;
	//private NumberTextBox[] textBoxes;
	
	private Button options;
	private boolean optionsMenu = false, optionsClose = false, gameEnd = false;
	
	private String gameMode;
	
	private ScreenSlider optionsOpener, optionsCloser;
	private GameOverScreen gameOverScreen;
	
	private MouseManager mouseMan;
	
	private Handler handler;
	
	private Ruler rulerButton;
	
	private static BufferedImage background;
	
	private ArrayList<Integer> sounds;
	
	private int deadTanksUser = 0, deadTanksAI = 0, tanksLeft;
	
	private Button optTutorial, optOptions, optQuit;
	private ButtonSlider optionsOptions, optionsQuit;
	
	private OptionsMenu optionsMenuScreen;
	private boolean inOptionsMenuScreen = false;
	
	
	public GameState(Handler handle, String gMode)
	{
		mouseMan = handle.getMouseManager();
		handler = handle;
		gameMode = gMode;
		
		sounds = new ArrayList<>();
		
		world = new World("res/worlds/worldBeach1.txt", this);
		
		buttons();
		initTanks();
		addMenuSound();
	
		optionsMenuScreen = MainState.getOptionsMenuScreen();
	}
	
	public void buttons()
	{
		rulerButton = new Ruler(handler);
		options = new Button(Assets.smallOptions, 1197, 6, 45, 45, handler);
	}
	
	public void initTanks()
	{
		tanks = new TurnSetter(handler);
		if (gameMode.equals("single"))
		{
			playerTanks = 1;
			tanks.createNewUserTank(difficulty);
			for (int i=0; i<enemyTanks; i++)
			{
				tanks.createNewAITank(difficulty);
			}
		}
		if (gameMode.equals("multi"))
		{
			for (int i=0; i<playerTanks; i++)
			{
				tanks.createNewUserTank(difficulty);
			}
			for (int i=0; i<enemyTanks; i++)
			{
				tanks.createNewAITank(difficulty);
			}
		}
		tanksLeft = tanks.tankSize();
		System.out.println("ADDED TANKS!");
	}
	
	public static void setTankAmount(int tanks)
	{
		enemyTanks = tanks;
	}
	
	public static void setFriendlyAmount(int tanks)
	{
		playerTanks = tanks;
	}
	
	public static void setAITankDifficulty(int dif)
	{
		difficulty = dif;
	}
	
	public static void setBackground(BufferedImage image)
	{
		background = image;
	}
	
	public void isGameOver()
	{	
		boolean hit = false;
		for (int i=0; i<tanks.getTanks().size() && !hit; i++)
		{
			if (tanks.getTanks().get(i).getTyping().equals("User"))
			{
				hit = true;
			}
		}
		
		if (!hit || tanks.getTanks().size()<2)
		{
			gameOverScreen = new GameOverScreen(handler, gameMode);
			gameEnd = true;
		}
	}
	
	public void addMenuSound()
	{
		if (background == Assets.background4)
		{
			sounds.add(handler.getSoundManager().addLoopSound("beachMap"));
			handler.getSoundManager().lowerLoopVolume(sounds.get(0), 25.0f);
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(background, 0, 0, Game.getWidth(), Game.getHeight(), null);
		world.render(g);
		tanks.render(g);
		if (gameEnd)
		{
			gameOverScreen.render(g);	
		}
		else
		{			
			rulerButton.render(g);
			
			options.render(g);
			if (optionsMenu)
			{
				//System.out.println("hrere");
				optionsOpener.render(g);
				
				optionsOptions.render(g);
				//optionsTutorial.render(g);
				optionsQuit.render(g);
				
			}
			if (optionsClose)
			{
				optionsCloser.render(g);
				
				optionsOptions.render(g);
				//optionsTutorial.render(g);
				optionsQuit.render(g);
				
			}
			
			if (inOptionsMenuScreen)
			{
				optionsMenuScreen.render(g);
			}
			/*
			//First Velocity
			g.drawRect(188, 640, 98, 14);
			
			//First Angle
			g.drawRect(327, 640, 98, 14);
			
			//Second Velocity
			g.drawRect(828, 640, 98, 14);
			
			//Second Angle
			g.drawRect(967, 640, 98, 14);
			*/
			/*
			for (int i=0; i<textBoxes.length; i++)
			{
				//buttons[i].render(g);
				textBoxes[i].render(g);
			}
			*/
		}
	}
	
	public void optionsScreen()
	{
		if (inOptionsMenuScreen)
		{
			inOptionsMenuScreen = optionsMenuScreen.getOptionsMenuClose();
		}
		else
		{
			if (optionsMenu && options.isClicked())
			{
				optionsClose = true;
				optionsMenu = false;
				closeOptionsMenu();

			}
			else
			{
				if (optionsMenu && mouseMan.isClicked())
				{
					optionsMenu = ((mouseMan.getX()>976 && mouseMan.getX()<1242) && 
							(mouseMan.getY()>6 && mouseMan.getY()<563));
					optionsClose = !optionsMenu;
					//System.out.println(optionsCloser);
					if (optionsClose)
					{
						closeOptionsMenu();
					}
				}
				else
				{
					if (!optionsMenu && options.isClicked())
					{
						optionsClose = false;
						optionsMenu = true;
						openOptionsMenu();
					}
				
				}
			}
		}
		
		if (optionsMenu)
		{
			if (optQuit.isClicked())
			{
				setScreenState("mainMenu");
			}
			if (optOptions.isClicked())
			{
				inOptionsMenuScreen = !inOptionsMenuScreen;
			}
		}	
	}
	
	public void openOptionsMenu()
	{
		optionsOpener = new ScreenSlider(Assets.optionsMenu, 1280, 0, 985, 0, -40, 0, 266, 556);
						
					
		optionsOptions = new ButtonSlider(Assets.optionsMenuOptions, 1301, 50 , 1006, 50, -40, 0, 224, 69, handler);
		//optionsTutorial = new ButtonSlider(Assets.optionsMenuTutorial, 1301, 169, 1006, 169, -40, 0, 224, 69, handler);
		optionsQuit = new ButtonSlider(Assets.optionsMenuQuit, 1301, 288, 1006, 288, -40, 0, 224, 69, handler);
					
		optTutorial = new Button(Assets.optionsMenuTutorial, 1006, 169, 224, 69, handler);
		optOptions = new Button(Assets.optionsMenuOptions, 1006, 50, 224, 69, handler);
		optQuit = new Button(Assets.optionsMenuQuit, 1006, 288, 224, 69, handler);
	}
	
	public void closeOptionsMenu()
	{
		optionsCloser = new ScreenSlider(Assets.optionsMenu, 985, 0, 1280, 0, 40, 0, 266, 556);
		
		
		optionsOptions = new ButtonSlider(Assets.optionsMenuOptions, 1006, 50, 1301, 50, 40, 0, 244, 69, handler);
		//optionsTutorial = new ButtonSlider(Assets.optionsMenuTutorial, 1006, 169, 1301, 169, 40, 0, 244, 69, handler);
		optionsQuit = new ButtonSlider(Assets.optionsMenuQuit, 1006, 288, 1301, 288, 40, 0, 244, 69, handler);
	}
	
	public void tick()
	{
		if (gameEnd)
		{
			gameOverScreen.tick();
		}
		else
		{
			world.tick();
			tanks.tick();
			rulerButton.tick();
			
			
			options.tick();
			optionsScreen();
			if (optionsMenu)
			{
				optionsOpener.tick();
				
				
				optionsOptions.tick();
				//optionsTutorial.tick();
				optionsQuit.tick();
				
				optTutorial.tick();
				optOptions.tick();
				optQuit.tick();
			}
			if (optionsClose)
			{
				optionsCloser.tick();
				
				
				optionsOptions.tick();
				//optionsTutorial.tick();
				optionsQuit.tick();
			}
			
			
			if (inOptionsMenuScreen)
			{
				optionsMenuScreen.tick();
			}
			
			ticks++;
			if (ticks>=maxTime)
			{
				isGameOver();
			}
		}
	}
	
	public ArrayList<Tank> getTanks()
	{
		return tanks.getTanks();
	}
	
	public Ruler getRuler()
	{
		return rulerButton;
	}


}
