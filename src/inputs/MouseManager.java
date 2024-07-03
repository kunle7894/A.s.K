package inputs;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{
	private boolean mouseClicked = false;
	private int xLoc, yLoc;
	
	private Rectangle rect = new Rectangle();

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		xLoc = e.getX();
		yLoc = e.getY();
		createBounds();
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		xLoc = e.getX();
		yLoc = e.getY();
		createBounds();
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if (e.getButton()==MouseEvent.BUTTON1)
		{
			mouseClicked = true;
			//System.out.println("Mouse Clicked");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		mouseClicked = false;
	}
	
	public void createBounds()
	{
		rect.x = xLoc;
		rect.y = yLoc;
		rect.width = 1;
		rect.height = 1;
	}
	
	public boolean isClicked()
	{
		return mouseClicked;
	}
	
	public int getX()
	{
		return xLoc;
	}
	
	public int getY()
	{
		return yLoc;
	}

	public Rectangle getBounds()
	{
		return rect;
	}
}	