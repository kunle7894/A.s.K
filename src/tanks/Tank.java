package tanks;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ImageLoader.Assets;
import gui.Game;
import health.HealthBar;
import inputs.Handler;
import inputs.HealthTextPlacer;
import inputs.KeyManager;
import projectiles.PathofBullet;
import projectiles.TankProjectile;

public class Tank 
{
	private int locX, locY, defaultSizeW, defaultSizeH, speed, health, stepsMoved, maxSteps, gravity = 1, tankNum;
	
	private double angle = 0, tipLocX, tipLocY, vel = 0, velX = 0, velY = 0;
	private boolean movable = true, gravityDown = true, down = true;
	private boolean changeTank = false, showShot;
	
	private int xDist = 50, yDist = 50, numTnk, arrowX, arrowY;
	//private final double TIP_OFFSETX = 0.19, TIP_OFFSETY = 0.25;  
	
	private String typing;
	
	private BufferedImage tankImage, tankHead, color;
	private ArrayList<TankProjectile> tankProj;
	private KeyManager keyManager;
	private Rectangle bounds;
	
	private AffineTransform rotationHead;
	
	private Handler handler;
	
	private HealthBar focusHealth, smallHealthLoc;
	
	private HealthTextPlacer hpTxtLoc;
	
	private Rectangle[] healthBounds;
	
	protected PathofBullet bulletPath;
	
	public Tank(BufferedImage img, BufferedImage img2, int lcX, int lcY, int tkNum, Handler handle)
	{
		locX = lcX;
		locY = lcY;
		tankImage = img;
		tankHead = img2;
		defaultSizeW = 45;
		defaultSizeH = 60;
		speed = 1;
		health = 100;
		stepsMoved = 0;
		maxSteps = 300;
		tipLocX = lcX+defaultSizeW-10;
		tipLocY = lcY+5;
		handler = handle;
		numTnk = tkNum;
		
		tankProj = new ArrayList<>();
		keyManager = handler.getKeyManager();
		
		rotationHead = AffineTransform.getTranslateInstance(locX+15, locY+16);
		
		ini();
		initHealth();
		
		color = findColor();
		arrowY = locY-40;
		arrowX = locX+defaultSizeW/2-10;
	}
	
	public void ini()
	{
		//BIG Health
		focusHealth = new HealthBar(this, 186, 577, 400, 9, health);
		
		hpTxtLoc = new HealthTextPlacer(this, 290, 605);
		bulletPath = new PathofBullet(this, 0);
	}
	
	public void initHealth()
	{
		healthBounds = new Rectangle[6];
		healthBounds[0] = new Rectangle(742, 574, 201, 9);
		healthBounds[1] = new Rectangle(1014, 574, 201, 9);
		healthBounds[2] = new Rectangle(742, 612, 201, 9);
		healthBounds[3] = new Rectangle(1014, 612, 201, 9);
		healthBounds[4] = new Rectangle(742, 649, 201, 9);
		healthBounds[5] = new Rectangle(1014, 649, 201, 9);
		
		smallHealthLoc = new HealthBar(this, (int) healthBounds[numTnk].getX(), (int) healthBounds[numTnk].getY(), 
											(int) healthBounds[numTnk].getWidth(), (int) healthBounds[numTnk].getHeight(), health);
	}
	
	public void reinit()
	{
		movable = true;
		if (movable)
		{
			stepsMoved = 0;
		}	
	}
	
	public void tick()
	{	
		tipLocX = locX+(int) (defaultSizeW/1.5)/2;
		tipLocY = locY+(int) (defaultSizeH/3)/2;
		
		
		focusHealth.tick();
		
		hpTxtLoc.tick();
		
		checkLoc();
		

		arrowX = locX+defaultSizeW/2-10;
	}
	
	//Things that should always be checked
	public void semiTick()
	{
		locY+= gravityDown ? gravity : 0;
		//shouln't always be checked
		checkandChangeHeadAngle();
		for (int i=0; i<tankProj.size(); i++)
		{
			tankProj.get(i).tick();
		}
		bounds = new Rectangle(locX, locY, (int) (defaultSizeW/1.5), defaultSizeH/3);
		
		smallHealthLoc.tick();
		
	}
		
	public void render(Graphics g)
	{
		
		//Should be changed later
		//Health Bar Location BIG
		//g.drawRect(185, 575, 400, 12);
		
		//g.drawRect(x, y, 1, 1);
		focusHealth.render(g);
		hpTxtLoc.render(g);
		
		g.drawImage(Assets.cats[0], 47, 571, 100, 100, null);
		
		g.drawImage(color, 8, 584, 25, 25, null);
		
		//Small Health locations
		/*
		//1
		g.drawRect(736, 574, 200, 9);
		//2
		g.drawRect(1016, 575, 200, 9);
		//3
		g.drawRect(736, 613, 200, 9);
		//4
		g.fillRect(1016, 613, 200, 9);
		//5
		g.drawRect(736, 652, 200, 9);
		//6
		g.fillRect(1016, 653, 200, 9);
		*/
		animateArrow();
		g.drawImage(Assets.redArrow, arrowX, arrowY, 20, 20, null);
	}
	
	//Things that should always be rendered
	public void semirender(Graphics g)
	{
		// For bounds of Tank g.drawRect(laocX, locY, (int)  (defaultSizeW/1.5), defaultSizeH/3);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(tankHead, rotationHead, null);
		g.drawImage(tankImage, locX, locY, defaultSizeW, defaultSizeH, null);
		
		for (int i=0; i<tankProj.size(); i++)
		{
			tankProj.get(i).render(g);
		}
		smallHealthLoc.render(g);
		
		/*
		//For tip Location
		g.drawRect(locX+(int) (defaultSizeW/1.5)/2, (int) (locY/3)/2, 1, 1);
		for (int i=0; i<=180; i++)
		{
			g.drawRect(locX+defaultSizeW-i, (int) ((i-1.75)*(i-1.75)) + 3, 1, 1);
		}
		*/
		//Fire Button location g.drawRect(565, 600, 125, 68);
	}
	
	public void animateArrow()
	{
		if (down)
		{
			arrowY-=1;
			if (arrowY<(locY-40))
			{
				down = !down;
				//System.out.println("hit. Arrow Y: "+arrowY+"Check: "+(locY-120)+" down: "+down);
			}
		}
		else
		{
			arrowY+=1;
			if (arrowY>=(locY-30))
			{
				down = !down;
				//System.out.println("hit. Arrow Y: "+arrowY+"Check: "+(locY-5)+" down: "+down);
			}
		}
		//System.out.println("Arrow Y: "+(locY-arrowY));
	}
	
	public BufferedImage findColor()
	{
		BufferedImage image = Assets.yellowSquare;
		//System.out.println("Tank Num: "+numTnk);
		if (numTnk==1)
		{
			image = Assets.greenSquare; 
		}
		else
		{
			if (numTnk==2)
			{
				image = Assets.redSquare;
			}
			else
			{
				if (numTnk==3)
				{
					image = Assets.lBlueSquare;
				}
				else
				{
					if (numTnk==4)
					{
						image = Assets.orangeSquare;
					}
					else
					{
						if (numTnk==5)
						{
							image = Assets.dBlueSquare;
						}
					}
				}
			}
		}
		return image;
	}
	
	public void checkAndChangeKeyClk()
	{
		if ((keyManager.getCurrentLetter().toLowerCase().equals("a") || keyManager.getCurrentLetter().toLowerCase().equals("left")) && movable)
		{
			locX -= speed;
			stepsMoved += speed;
			bulletPath = new PathofBullet(this, 20);
			keyManager.setCurrentLetter("");
			gravityDown = true;
		}
		if ((keyManager.getCurrentLetter().toLowerCase().equals("d") || keyManager.getCurrentLetter().toLowerCase().equals("right"))&& movable)
		{
			locX += speed;
			stepsMoved += speed;
			bulletPath = new PathofBullet(this, 20);
			keyManager.setCurrentLetter("");
			gravityDown = true;
		}
		movable = stepsMoved>=maxSteps ? false : movable;
	}
	
	public void checkandChangeHeadAngle()
	{
		rotationHead = AffineTransform.getTranslateInstance(locX+11, locY-1);
				
		rotationHead.rotate(Math.toRadians(angle), (int) (defaultSizeW/4.5), (int) (defaultSizeW/4.5));
		rotationHead.scale(0.8, 0.8);
		//System.out.println("Angle: "+angle);
	}
	
	public void findVelocityComponents()
	{
		System.out.println("Angle: "+angle+" Velocity: "+vel);
		double tempAng = Math.toRadians(-angle);
		velY = vel*Math.sin(tempAng);
		velX = vel*Math.cos(tempAng); 
	}
	
	public void createProj()
	{
		if (velX>=0)
		{
			tankProj.add(new TankProjectile(Assets.tankBulletDef, 3,velY, velX, (int) tipLocX, (int) tipLocY, this));
		}
		else
		{
			tankProj.add(new TankProjectile(Assets.tankBulletDefBack, 3, velY, velX, (int) tipLocX, (int) tipLocY, this));
		}
		keyManager.setCurrentLetter("");
	}
	
	public void checkLoc()
	{
		if (locX<=0)
		{}
		if (locX>(Game.getWidth()-defaultSizeW))
		{
			locX-=2;
		}
		if (locY>Game.getHeight())
		{
			locY = 500;
			gravityDown = true;
		}
	}
	
	public int getX()
	{
		return locX;	
	}
	
	public int getY()
	{
		return locY;
	}
	
	public int getDefaultSize()
	{
		return defaultSizeW;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public void setHealth(int ht)
	{
		health = ht;
	}
	
	public void subtractX(int x)
	{
		locX -= x;
	}
	
	public void subtractY(int x)
	{
		locY -= x;
	}
	
	public ArrayList<TankProjectile> getProjectiles()
	{
		return tankProj;
	}
	
	public boolean getChangeTank()
	{
		return changeTank;
	}
	
	public void setchangeTank(boolean val)
	{
		changeTank = val;
	}
	
	public double getAngle()
	{
		return angle;
	}
	
	public void setChangeTank(boolean val)
	{
		changeTank = val;
	}
	
	public double getVelX()
	{
		return velX;
	}
	
	public double getVelY()
	{
		return velY;
	}
	
	public void chgAngle(double val)
	{
		angle += val;
	}
	
	public void setVel(double val)
	{
		vel = val;
	}
	
	public void setVelX(double val)
	{
		velX = val;
	}
	
	public void setVelY(double val)
	{
		velY = val;
	}
	
	public void setAngle(double val)
	{
		angle = val;
	}
	
	public double getVel()
	{
		return vel;
	}
	
	public void setTyping(String val)
	{
		typing = val;
	}
	
	public String getTyping()
	{
		return typing;
	}
	
	public void setXDist(int val)
	{
		xDist = val;
	}
	
	public void setYDist(int val)
	{
		yDist = val;
	}
	
	public int getXDist()
	{
		return xDist;
	}
	
	public int getYDist()
	{
		return yDist;
	}
	
	public int getTipLocX()
	{
		return (int) tipLocX;
	}
	
	public int getTipLocY()
	{
		return (int) tipLocY;
	}
	
	public int getTankNum()
	{
		return tankNum;
	}
	
	public void setTankNum(int val)
	{
		tankNum = val;
	}
	
	public int getSpeed()
	{
		return speed;
	}

	public int getLocX() 
	{
		return locX;
	}

	public void setLocX(int i) 
	{
		locX = i;
		checkLoc();
	}
	
	public void setGravityDown(boolean val)
	{
		gravityDown = val;
	}
	
	public boolean getShowShot()
	{
		return showShot;
	}
	
	public void setShowShot(boolean val)
	{
		showShot = val;
	}
}