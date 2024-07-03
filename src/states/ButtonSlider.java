package states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import inputs.Button;
import inputs.Handler;

public class ButtonSlider
{
	private int xStart, yStart, xEnd, yEnd, xMove, yMove, width, height;
	private Button button;
	private Handler handler;
	private BufferedImage[] images;
	
	private int ticks = 0, maxTime = 1;
	
	public ButtonSlider(BufferedImage[] img, int x1, int y1, int x2, int y2, int xRate, int yRate, int wid, int hit, Handler handle)
	{
		images = img;
		xStart = x1;
		yStart = y1;
		xEnd = x2;
		yEnd = y2;
		xMove = xRate;
		yMove = yRate;
		width = wid;
		height = hit;
		handler = handle;
		button = new Button(images, xStart, yStart, 224, 69, handler);
	}
	
	public void render(Graphics g)
	{
		button.render(g);
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
	}
	
	public void tick()
	{
		ticks++;
		if(ticks>=maxTime)
		{
			if (yStart<yEnd || xStart<xEnd || yStart>yEnd || xStart>xEnd)
			{
				mover();
				button = new Button(images, xStart, yStart, images[0].getWidth(), images[0].getHeight(), handler);
				ticks = 0;
			}
			else
			{
				button.tick();
			}
		}
	}
}