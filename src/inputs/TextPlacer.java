package inputs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextPlacer 
{
	private String toDraw;
	private int xPos, yPos;
	
	public TextPlacer(String vals, int xP, int yP)
	{
		toDraw = vals;
		xPos = xP;
		yPos = yP;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("Dialog BOLD", Font.BOLD, 15));
		g.drawString(toDraw, xPos, yPos);
	}
	
	public void setText(String val)
	{
		toDraw = val;
	}
}
