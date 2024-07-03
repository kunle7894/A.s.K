package tanks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import inputs.Handler;

public class AITank extends Tank
{
	private double velNeed = 0, angNeed=180, accuracy;
	private final int mxVel = 100;
	private boolean possible = false;
	private int neededX, lastHealth, movePositive, moved = 0;
	//Lower Acc is better since there is less spread in points.
	public AITank(BufferedImage img, BufferedImage img2, int lcX, int lcY, int acc, int tnkNum, Handler handle) 
	{
		super(img, img2, lcX, lcY, tnkNum, handle);
		accuracy = acc>0 ? acc : 1;
		super.setTyping("AI");
		neededX = chooseValOnAccuracy((int) accuracy)+super.getXDist();
		super.setAngle(180);
		lastHealth = 100;
	}
	
	public void reinit()
	{
		possible = false;
		angNeed = 180;
		velNeed = 0;
		neededX = chooseValOnAccuracy((int) accuracy)+super.getXDist();
	}
	
	public int chooseValOnAccuracy(int acc)
	{
		Random val = new Random();
		int valObt = val.nextInt(acc);
		//System.out.println("Random Val: "+valObt);
		return valObt;
	}
	
	public void tick()
	{
		if (lastHealth!=super.getHealth())
		{
			moveTank();
		}
		else
		{
			if (velNeed==0 && !possible)
			{
				findAngle();
			}
			else
			{
				super.setChangeTank(true);
				createProj();
			}
		}
		super.tick();
		
		/*
		if (velNeed==0)
		{
			super.setChangeTank(true);
			createProj();
		}
		*/
	}
	
	public void findAngle()
	{
		int xDist = 0;
		while (angNeed>=0 && !possible)
		{
			angNeed -= 0.1;
			super.setAngle(-angNeed);
			checkandChangeHeadAngle();
			for (double i=0.0; i<=mxVel && !possible; i+=0.5f)
			{
				//Parabolic projectile y = tan(ang)*xPos - (g/(2*vi^2*cos^2(ang)))*xPos^2
				
				/*
				 * Not Working Round 2
				*/
				//Horizontal Distance = (Vi^2*sin(2*ang))/g
				xDist = (int) (i*i*Math.sin(2*Math.toRadians(angNeed))/9.81);
				//System.out.println("Dist: "+ xDist+" Need: "+super.getXDist());
				
				if (neededX==xDist)
				{
					velNeed = i;
					super.setVel(velNeed);
					findVelocityComponents();
					//System.out.println("Ang: "+angNeed+ " Vel: "+i + " Velx: "+xVel + " Vely: "+yVel);
					possible = true;
				}
				
				//*/
				
				/* Not Working...
				
				yVel = i*Math.sin(Math.toRadians(angNeed));
				xVel = i*Math.cos(Math.toRadians(angNeed));
				//System.out.println("Ang: "+angNeed+ " Vel: "+i + " Velx: "+xVel + " Vely: "+yVel);
								
				//used to see if velocity and angle meet requirements
				//h(fy) = 1/2gt^2 + velt + h(i); t = h(fx)/xVel
				double t = super.getXDist()/xVel;
				if ((int) super.getYDist()==(int) (4.905*(t*t) + yVel*t))
				{
					velNeed = i;
					System.out.println("Ang: "+angNeed+ " Vel: "+i + " Velx: "+xVel + " Vely: "+yVel);
					possible = true;
					super.setVelX(xVel);
					super.setVelY(yVel);
				}
				*/
			}
		}
		if (!possible)
		{
			possible = true;
			super.setVelX(0);
			super.setVelY(0);
		}
			
	}
	
	public void render(Graphics g)
	{
		super.render(g);
	}
	
	public void moveTank()
	{
		if (moved%30==0)
		{
			movePositive = chooseValOnAccuracy(100)<50 ? (int) super.getSpeed() : (int) -super.getSpeed();
		}
		super.setLocX(super.getLocX()+movePositive);
		neededX += movePositive;
		moved++;
		if (moved>100)
		{
			moved = 0;
			lastHealth = super.getHealth();
		}
		super.setGravityDown(true);
	}
}
