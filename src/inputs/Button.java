package inputs;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import inputs.MouseManager;

public class Button 
{
	private int xPos, yPos, width, height, current, maxTimer = 20, curTimer = 0;
	private boolean clicked = false, changeVal = true;
	private MouseManager mouseManager;
	private Rectangle rect;
	private BufferedImage[] image;
	
	private Handler handler;
	
	public Button(BufferedImage[] img, int lcX, int lcY, int wit, int hit, Handler handle)
	{
		xPos = lcX;
		yPos = lcY;
		width = wit;
		height = hit;
		handler = handle;
		mouseManager = handler.getMouseManager();
		image = img;
		
		/*The Current bufferedImage is set to 0
		current = 0 //Means not hovered or clicked
		current = 1 //Means mouse hovered if img available
		current = 2 //Means mouse clicked if img available
		*/
		current = 0;
		
		makeRect();
	}
	
	public void tick()
	{
		if (changeVal)
		{
			clicked = false;
			checkOver();
		}
		curTimer = curTimer<=maxTimer ? curTimer+1 : curTimer;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(image[current], xPos, yPos, width, height, null);
	}
	
	public void checkOver()
	{
		current = 0;
		if (mouseManager.getBounds().intersects(rect))
		{
			if (image.length>1)
			{
				current = 1;
			}
			checkClicked();
		}
	}
	
	public void checkClicked()
	{
		if (mouseManager.isClicked() && curTimer>maxTimer)
		{
			curTimer = 0;
			clicked = true;
			if (image.length>2)
			{
				current = 2;
				changeVal = false;
			}
		}
	}
	
	public void reset()
	{
		clicked = false;
		changeVal = true;
		current = 0;
	}
	
	public void makeRect()
	{
		rect = new Rectangle(xPos, yPos, width, height);
	}
	
	public boolean isClicked()
	{
		return clicked;
	}
	
	public void setClicked(boolean val)
	{
		clicked = val;
	}
	
	public void setX(int val)
	{
		xPos = val;
		makeRect();
	}
	
	public void setY(int val)
	{
		yPos = val;
		makeRect();
	}
}
