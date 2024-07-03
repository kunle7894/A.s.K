package tanks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ImageLoader.Assets;
import gui.Game;
import inputs.Handler;
import projectiles.TankProjectile;

public class TurnSetter 
{
	private int curLocX, curLocY, curTurn, xDist, yDist, curTankNum; 
	//private int ticks, changeAmount = 600;
	private Handler handler;	
	
	private ArrayList<Tank> tanks = new ArrayList<>();
	
	private boolean curTankDead;
	private String curTankType;
	
	public TurnSetter(Handler handle)
	{
		curLocX = Game.getWidth()-1000;
		curLocY = Game.getHeight()-225;
		curTankNum = 0;
		
		handler = handle;
		curTurn = 1;
		//ticks = 0;
	}
	
	public void tick()
	{
		try
		{
			if (!tanks.get(curTurn-1).getChangeTank())
			{
				tanks.get(curTurn-1).tick();
			}
			for (int i=0; i<tanks.size(); i++)
			{
				tanks.get(i).semiTick();
			}
			//ticks++;
			//if (ticks>=changeAmount)
			if (tanks.get(curTurn-1).getChangeTank() && tanks.get(curTurn-1).getProjectiles().size()==0)
			{
				tanks.get(curTurn-1).setchangeTank(false);
				curTurn++;
				
				handler.getRuler().reInit();
				
				if (curTurn-1>=tanks.size())
				{
					curTurn = 1;
				}
				if (tanks.get(curTurn-1).getTyping().equals("AI"))
				{
					findClosestX(tanks.get(curTurn-1).getX(), tanks.get(curTurn-1).getY());
					tanks.get(curTurn-1).setXDist(xDist);
					tanks.get(curTurn-1).setYDist(yDist);
				}
				
				tanks.get(curTurn-1).reinit();
				//ticks = 0;
				System.out.println("Turn: "+ curTurn + " Tank Size: "+tanks.size());
			}
			checkProjCollision();
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Tank: "+(curTurn-1)+" doesn't exist");
		}
	}
	
	public void render(Graphics g)
	{
		try
		{
			if (!tanks.get(curTurn-1).getChangeTank() || tanks.get(curTurn-1).getProjectiles().size()>0)
			{
				tanks.get(curTurn-1).render(g);
			}
			for (int i=0; i<tanks.size(); i++)
			{
				tanks.get(i).semirender(g);
			}
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Projectiles are really fast...");
		}
	}	
	
	public void createNewUserTank(int difficulty)
	{
		BufferedImage imgBody = findImageBody();
		BufferedImage imgHead = findImageHead();
		tanks.add(new UserTank(imgBody, imgHead, curLocX, curLocY, curTankNum, handler));
		tanks.get(curTankNum).setShowShot(difficulty>=300 ? true : false);
		curTankNum++;
		curLocX+=300;
		if (curLocX>1250)
		{
			curLocX/=2;
			curLocX-=400;
		}
	}
	
	public void createNewAITank(int difficult)
	{
		BufferedImage imgBody = findImageBody();
		BufferedImage imgHead = findImageHead();
		tanks.add(new AITank(imgBody, imgHead, curLocX, curLocY, difficult, curTankNum, handler));
		curTankNum++;
		curLocX+=300;
		if (curLocX>1250)
		{
			curLocX/=2;
			curLocX-=400;
		}
	}
	
	public void findClosestX(int curX, int curY)
	{
		if (tanks.size()>0)
		{
			xDist = tanks.get(0).getX()-curX;
			yDist = tanks.get(0).getY()-curY;
			for (int i=1; i<tanks.size(); i++)
			{
				if (curTurn-1!=i && tanks.get(i).getX()-curX<xDist)
				{
					xDist = tanks.get(i).getX()-curX;
					yDist = tanks.get(i).getY()-curY;
				}
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public void checkProjCollision()
	{
		int size = 0;
		boolean found = false;
		
		while (size<tanks.size())
		{
			try
			{
				for (int i=0; i<tanks.get(size).getProjectiles().size(); i++)
				{
					TankProjectile proj = tanks.get(size).getProjectiles().get(i);
					found = false;
					for (int j=0; !found && j<tanks.size(); j++)
					{
						curTankDead = false;
						if (size!=j && tanks.get(j).getBounds().intersects(proj.getBounds()))
						{
							found = true;
							tanks.get(size).getProjectiles().remove(i);
							tanks.get(j).setHealth(tanks.get(j).getHealth()-10);
							if (tanks.get(j).getHealth()<=0)
							{
								curTankDead = true;
								curTankType = tanks.get(j).getTyping();
								tanks.remove(j);
								handler.getSoundManager().addTempSound("tankDestroyed");
							}
							else
							{
								handler.getSoundManager().addTempSound("hitSound1");
							}
							//System.out.println("Health: "+tanks.get(j).getHealth());
						}
					}
					if (proj.getX()<0 || proj.getX()>handler.getGame().getWidth() || proj.getY()>handler.getGame().getHeight())
					{
						tanks.get(size).getProjectiles().remove(i);
					}				
				}
				size++;
			}
			catch (IndexOutOfBoundsException e)
			{
				System.out.println("Those projectiles are too fast...");
				curTurn = curTurn<tanks.size() ? curTurn+1 : 1;
			}
		}
	}
	
	public BufferedImage findImageBody()
	{
		BufferedImage image = Assets.tankYellow;
		if (curTankNum==1)
		{
			image = Assets.tankGreen;
		}
		if (curTankNum==2)
		{
			image = Assets.tankRed;
		}
		if (curTankNum==3)
		{
			image = Assets.tankLBlue;
		}
		if (curTankNum==4)
		{
			image = Assets.tankOrange;
		}
		if (curTankNum==5)
		{
			image = Assets.tankDBlue;
		}
		return image;
	}
	
	public BufferedImage findImageHead()
	{
		BufferedImage image = Assets.tankHeadYellow;
		if (curTankNum==1)
		{
			image = Assets.tankHeadGreen;
		}
		if (curTankNum==2)
		{
			image = Assets.tankHeadRed;
		}
		if (curTankNum==3)
		{
			image = Assets.tankHeadLBlue;
		}
		if (curTankNum==4)
		{
			image = Assets.tankHeadOrange;
		}
		if (curTankNum==5)
		{
			image = Assets.tankHeadDBlue;
		}
		return image;
	}
	
	public boolean isCurrentTankDead()
	{
		return curTankDead;
	}
	
	public int tankSize()
	{
		return tanks.size();
	}
	
	public int getCurTurn()
	{
		return curTurn;
	}
	
	public String tankType()
	{
		return curTankType;
	}
	
	public ArrayList<Tank> getTanks()
	{
		return tanks;
	}
}
