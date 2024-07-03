package states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ScreenSlider
{
	private int xStart, yStart, xEnd, yEnd, xMove, yMove, width, height;
	private BufferedImage image;
	
	private int ticks = 0, maxTime = 1;
	
	private boolean moving = true;
	
	public ScreenSlider(BufferedImage img, int x1, int y1, int x2, int y2, int xRate, int yRate, int wid, int hit)
	{
		xStart = x1;
		yStart = y1;
		xEnd = x2;
		yEnd = y2;
		xMove = xRate;
		yMove = yRate;
		image = img;
		width = wid;
		height = hit;
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(image, xStart, yStart, width, height, null);
	}
	
	public void mover()
	{
		if (yMove>0 && yEnd<yStart)
		{
			yStart -= yMove;
		}
		else
		{
			if (yMove<0 && yEnd>yStart)
			{
				yStart -= yMove;
			}
		}
	if ((yMove>0 && (yStart<yEnd)) || (yMove<0 && (yStart>yEnd)))
		{
			yStart = yEnd;
		}
		
		if (xMove<0 && xEnd<xStart)
		{
			xStart += xMove;
		}
		else
		{
			if (xMove>0 && xEnd>xStart)
			{
				xStart += xMove;
			}
		}
		if ((xMove<0 && (xStart<xEnd)) || (xMove>0 && (xStart>xEnd)))
		{
			xStart = xEnd;
		}
		moving = true;
		//System.out.println("xStart: "+xStart+" yMove: "+yStart);
	}
	
	public void tick()
	{
		moving = false;
		ticks++;
		if(ticks>=maxTime)
		{
			//System.out.println("checking");
			if (yStart<yEnd || xStart<xEnd || yStart>yEnd || xStart>xEnd)
			{
				//System.out.println("hrererer");
				mover();
				ticks = 0;
			}
		}
	}

	public int getX()
	{
		return xStart;
	}
	
	public int getY()
	{
		return yStart;
	}
	
	public boolean isMoving()
	{
		return moving;
	}
}