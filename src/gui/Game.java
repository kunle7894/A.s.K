package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import ImageLoader.Assets;
import inputs.Handler;
import inputs.KeyManager;
import inputs.MouseManager;
import sound.SoundManager;
import states.GameState;
import states.MainState;
import states.PlayerSelectState;
import states.ScreenState;
import states.SinglePlayerState;
import states.StartState;
import states.TutorialState;

public class Game implements Runnable
{
	private String title;
	private static int height;
	private static int width;
	private boolean running = false;
	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Display display;
	
	private Assets assets;
	
	private MouseManager mouseManager;
	private KeyManager keyManager;
		
	private ScreenState gState;
	private String scrState;
	
	private Handler handler;
	
	private SoundManager sound;
	
	private int soundIndex;
	
	public Game(String tit, int with, int hight)
	{
		title = tit;
		height = hight;
		width = with;
	}
	
	public void init()
	{
		display = new Display(title, width, height);
		handler = new Handler(this);
		
		assets = new Assets();
		assets.init();
		
		mouseManager = new MouseManager();
		keyManager = new KeyManager();
		
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
	
		//move later
		gState = new StartState(handler);
		gState.setScreenState("startMenu");
		scrState = "startMenu";
		
		sound = new SoundManager(handler);
		soundIndex = sound.addLoopSound("newMenu");
	}
	
	public void render() 
	{
		boolean needToRender = true;
		bs = display.getCanvas().getBufferStrategy();
		
		if  (bs==null)
		{
			display.getCanvas().createBufferStrategy(3);
			needToRender = false;
		}
		if (needToRender)
		{
			g = bs.getDrawGraphics();
			//Clears Screen
	
			//Draw HERE
			/*
			y = (int) ((50 - (10*t)) +(0.5*9.81*(t*t)));
			x = 10*t;
			System.out.println("y: "+y+" x: "+x+" t: "+t);
			g.drawRect(x, y, 1, 1);
		
				
			*/
			g.setColor(Color.RED);
			g.fillRect(0, 0, width, height);

			gState.render(g);
			//Tile.tiles[0].render(g, 20, 20);
			//End Draw
			bs.show();
			g.dispose();
		}
	}

	public void tick() 
	{
		if (!ScreenState.getScreenState().equals(scrState))
		{
			switch (ScreenState.getScreenState())
			{
				case "startMenu": gState = new StartState(handler);
											scrState = "startMenu"; break;
				case "mainMenu": gState = new MainState(handler); 
											scrState = "mainMenu"; break;
				case "playerSelectState": gState= new PlayerSelectState(handler);
											scrState = "playerSelectState"; break;
				case "singlePlayerState": gState = new SinglePlayerState(handler);
											scrState = "singlePlayerState"; break;
				case "singlePlayerGame": gState = new GameState(handler, "single");
											scrState = "singlePlayerGame"; 
											sound.stopLoopSound(soundIndex); break;
				case "multiPlayerGame" : gState = new GameState(handler, "multi");
											scrState = "multiPlayerGame"; 
											sound.stopLoopSound(soundIndex); break;
				case "tutorial": gState = new TutorialState(handler);
											scrState = "tutorial"; break;
			}
		}
		
		gState.tick();
	}
	
	//More efficient game to have 60 frames and 60 ticks
	public void run()
	{
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while (running)
		{
			now = System.nanoTime();
			delta += (now-lastTime)/timePerTick;
			timer += now-lastTime;
			lastTime = now;
			
			if (delta>=1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if (timer>=1000000000)
			{
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}	
		stop();
	}
	
	public synchronized void start()
	{
		if (!running)
		{
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop()
	{
		if (running)
		{
			try 
			{
				thread.join();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public static int getWidth()
	{
		return width;
	}
	
	public static int getHeight()
	{
		return height;
	}
	
	public GameState getGameState()
	{
		return (GameState) gState;
	}
	
	public SoundManager getSoundManager()
	{
		return sound;
	}
}