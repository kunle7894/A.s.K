package projectiles;

import java.awt.Graphics;

import tanks.Tank;

public class PathofBullet 
{
	private int locXSrt, locYSrt, curLocX, curLocY, howMany, time;
	private int[][] locDraw;
	private double velX, velY, velYI, grav = 9.81;
	private Tank tank;
	
	public PathofBullet(Tank tnk, int dist)
	{
		tank = tnk;
		time = 0;
		velX = tank.getVelX();
		velY = tank.getVelY();
		velYI = velY;
		//ang = tank.getAngle();
		locXSrt = tank.getTipLocX();
		locYSrt = tank.getTipLocY();
		howMany = dist;
		locDraw = new int[howMany][2];
	}
	
	public void tick()
	{
		if (time<howMany)
		{
			curLocY = (int) (locYSrt - velYI*time + (0.5*grav*(time*time)));
			
			velY = velYI + (-grav*time);
			//displace horizontal
			curLocX = (int) (locXSrt+velX*time);
			
			locDraw[(int) time][0] = curLocX;
			locDraw[(int) time][1] = curLocY;
			time+=1;
		}
	}
	
	public void render(Graphics g)
	{
		for (int i=0; i<time; i++)
		{
			g.drawOval(locDraw[i][0], locDraw[i][1], 5, 5);
			//System.out.println("Time: "+i+" LocX: "+locDraw[i][0]+" LocY: "+locDraw[i][1]);
		}
	}

}
