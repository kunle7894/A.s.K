package inputs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import ImageLoader.Assets;
import gui.Game;

public class Ruler 
{
	private MouseManager mouseManager;
	public Button rulerButton, wholeScreenButton;
	private int step = 0;
	private int ticks = 0, maxTime = 5;
	private int[] lineCoords = new int[4];
	
	public Ruler(Handler handler)
	{
		mouseManager = handler.getMouseManager();
		
		rulerButton = new Button(Assets.ruler, 1197, 65,45, 45, handler); //the ruler button
		wholeScreenButton = new Button(Assets.start, 0, 0, Game.getWidth(), Game.getHeight(), handler); //screen button for placing ruler end point(image doesn't matter)
	}
	
	public void rulerWorks()
	{
		if (step==2 && wholeScreenButton.isClicked())
		{
			step = 3;
			lineCoords[2] = mouseManager.getX();
			lineCoords[3] = mouseManager.getY();
		}
		
		if (step==1 && wholeScreenButton.isClicked())
		{
			step = 2;
			lineCoords[0] = mouseManager.getX();
			lineCoords[1] = mouseManager.getY();
		}
		if (rulerButton.isClicked())
		{
			step = step==3 ? 0 : 1;
		}
		if ((rulerButton.isClicked() || wholeScreenButton.isClicked()) && step>0)
		{
			ticks = 0;
		}
	}
	
	
	private double distance()
	{ 
		return (double)((int)((Math.sqrt(Math.abs(Math.pow(lineCoords[2]-lineCoords[0], 2) + Math.pow(lineCoords[3]-lineCoords[1], 2))))*1000))/1000;
	}


	public void render(Graphics g)
	{
		String str1, str2, str3, str4, str5;
		
		//This is used. Eclipse is just retarded.
		@SuppressWarnings("unused")
		int str1Length, str2Length, str3Length, str4Length, str5Length;
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier PLAIN", Font.PLAIN, 15));
		{
			str4 = "Horizontal Distance: " + Math.abs((lineCoords[2]-lineCoords[0]));
			str4Length = g.getFontMetrics().stringWidth(str4);
			str5 = "Vertical Distance: " + -(lineCoords[3]-lineCoords[1]);
			str5Length = g.getFontMetrics().stringWidth(str5);
		}
		g.setFont(new Font("Courier PLAIN", Font.PLAIN, 30));
		{
			str1 = "Place First Ruler Endpoint";
			str1Length = g.getFontMetrics().stringWidth(str1);
			str2 = "Place Second Ruler Endpoint";
			str2Length = g.getFontMetrics().stringWidth(str2);
			str3 = "Distance: "+distance()+" meters";
			str3Length = g.getFontMetrics().stringWidth(str3);
			
			switch (step)
			{
				case 1: g.drawString(str1, 625-str1Length/2, 75); break;
				case 2: g.fillOval(lineCoords[0], lineCoords[1], 5, 5);
								g.drawString(str2, 625-str2Length/2, 75); break;
				case 3: g.drawLine(lineCoords[0], lineCoords[1], lineCoords[2], lineCoords[3]); 
								//g.drawString(str3, 625-str3Length/2, 75);
								g.setFont(new Font("Courier PLAIN", Font.PLAIN, 15));
								{
									g.drawString(str4, Game.getWidth()-str4Length-15, 130);
									g.drawString(str5, Game.getWidth()-str5Length-15, 145);
								}
			}
		
		}
		rulerButton.render(g);
	}
	
	public void tick()
	{
		timer();
	}
	
	public void timer()
	{
		ticks++;
		if (ticks<maxTime)
		{
			rulerButton.reset();
			wholeScreenButton.reset();
		}
		else
		{
			rulerButton.tick();
			wholeScreenButton.tick();
			rulerWorks();
		}
	}
	
	public void reInit()
	{
		step = 0;
	}
}