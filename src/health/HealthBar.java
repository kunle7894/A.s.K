package health;

import java.awt.Graphics;

import ImageLoader.Assets;
import tanks.Tank;

public class HealthBar 
{
	private Tank tank;
	private int locX, locY, maxHealth;
	private double width, widthOrig, height;
	
	public HealthBar(Tank tk, int x, int y, int wth, int ht, int maxH)
	{
		tank = tk;
		locX = x;
		locY = y;
		widthOrig = wth;
		width = widthOrig;
		height = ht;
		maxHealth = maxH;
	}
	
	public void tick()
	{
		
		width = widthOrig*((double) tank.getHealth()/maxHealth);
		//System.out.println("Health: "+tank.getHealth());
		//System.out.println("Width: "+width);
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.green, locX, locY, (int) width, (int) height, null);
	}
	
}
