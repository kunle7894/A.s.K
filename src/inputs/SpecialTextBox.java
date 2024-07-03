package inputs;

import java.awt.Color;
import java.awt.Graphics;

public class SpecialTextBox extends TextBox
{
	private int maxNum, valInside = -1;
	
	public SpecialTextBox(int wit, int hit, int x, int y, Handler handle, String charac, int max) 
	{
		super(wit, hit, x, y, handle, charac, max);
		maxNum = max;
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.orange);
		g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		super.render(g);
	}
	
	public void checkParametersNumbers() 
	{
		super.setAcceptable(false);
		valInside = getPosMouse()>0 ? Integer.parseInt(super.getContents().substring(0, getPosMouse())+super.getContents().substring(getPosMouse()+1)) : valInside;
		//System.out.println("TextBox Value: "+valInside);
		if (valInside>=0 && valInside<=maxNum)
		{
			super.setAcceptable(true);
		}
	}
	
	public int getVal()
	{
		int val = valInside;
		if (getinBounds())
		{
			val = -1;
		}
		return val;
	}
}
