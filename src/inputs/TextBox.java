package inputs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class TextBox 
{
	private int width, height, xPos, yPos, posMouse, maxChar, curChar = 0, flashOn = 0, flashOff = 0, flashTime = 10, overFlash = 0, highFlash = 60;
	private KeyManager keyMan;
	private MouseManager mouseMan;
	
	private Rectangle rect;
	private boolean inBounds, textMouse, noMouse, acceptableVal, changeVal, flashRed = false;	
	private String wordsInText, acceptable;
	
	private Handler handler;
	
	public TextBox(int x, int y, int wit, int hit, Handler handle, String acp, int mx)
	{
		width = wit;
		height = hit;
		xPos = x;
		yPos = y;
		handler = handle;
		keyMan = handler.getKeyManager();
		mouseMan = handler.getMouseManager();
		createRect();
		
		inBounds = false;
		textMouse = false;
		noMouse = true;
		changeVal = false;
		wordsInText = "";
		posMouse = -1;
		acceptable = acp;
		maxChar = mx;
		
		resetKey();
	}
	
	public void createRect()
	{
		rect = new Rectangle(xPos, yPos, width, height);
	}
	
	public void resetKey()
	{
		keyMan.setCurrentLetter("");
	}
	
	public void tick()
	{
		if (!inBounds && rect.intersects(mouseMan.getBounds()) && mouseMan.isClicked())
		{
			textMouse = true;
			inBounds = true;
			acceptableVal = true;
		}
		if (inBounds && !rect.intersects(mouseMan.getBounds()) && mouseMan.isClicked())
		{
			closeBox();
		}
		
		if (textMouse)
		{
			if (noMouse)
			{
				noMouse = false;
				posMouse = wordsInText.length();
				wordsInText += "|";
			}
			checkAndDoClick();
		}
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("Dialog BOLD", Font.BOLD, 12));
		g.drawString(wordsInText, xPos+4, yPos+11);
		
		if (flashRed)
		{
			drawFlash(g);
		}
	}
	
	public void checkAndDoClick()
	{	
		//System.out.println("REACHED WORD CHECK "+wordsInText.length());
		if (wordsInText.length()>1)
		{
			if (posMouse>0 && keyMan.getCurrentLetter().equals("left"))
			{
				wordsInText = wordsInText.substring(0, posMouse-1)+"|"+wordsInText.substring(posMouse-1, posMouse)+wordsInText.substring(posMouse+1);
				posMouse--;
				keyMan.setCurrentLetter("");
			}
			if (posMouse<wordsInText.length()-1 && keyMan.getCurrentLetter().equals("right"))
			{
				wordsInText = wordsInText.substring(0, posMouse)+wordsInText.substring(posMouse+1, posMouse+2)+"|"+wordsInText.substring(posMouse+2);
				posMouse++;
				keyMan.setCurrentLetter("");
			}		
			if (posMouse>0 && keyMan.getCurrentLetter().equals("backspace"))
			{
				wordsInText = wordsInText.substring(0, posMouse-1) + wordsInText.substring(posMouse);
				posMouse--;
				keyMan.setCurrentLetter("");
				curChar--;
			}
			if (posMouse<wordsInText.length()-1 && keyMan.getCurrentLetter().equals("delete"))
			{
				wordsInText = wordsInText.substring(0, posMouse+1)+wordsInText.substring(posMouse+2);
				keyMan.setCurrentLetter("");
				curChar--;
			}
		}
		
		if (wordsInText.length()>0 && keyMan.getCurrentLetter().length()>0 && maxChar>=curChar)
		{
			if (acceptable.contains(keyMan.getCurrentLetter()))
			{
				checkAndDrawNums(keyMan.getCurrentLetter());
			}
		}
		
		if (keyMan.getCurrentLetter().equals("enter"))
		{
			closeBox();
			keyMan.setCurrentLetter("");
		}
	}
	
	public void closeBox()
	{
		if (acceptableVal)
		{
			inBounds = false;
			textMouse = false;
			noMouse = true;
			changeVal = true;
			if (wordsInText.length()>1)
			{
				wordsInText = wordsInText.substring(0, posMouse) + wordsInText.substring(posMouse+1);
			}
			else
			{
				wordsInText = "";
			}
			posMouse = -1;
		}
		else
		{
			flashRed = true;
		}
	}
	
	public void checkAndDrawNums(String num)
	{
		wordsInText = wordsInText.substring(0, posMouse)+num+wordsInText.substring(posMouse);
		posMouse++;
		keyMan.setCurrentLetter("");
		curChar++;
	}
	
	public abstract void checkParametersNumbers();
	
	public void drawFlash(Graphics g)
	{
		if (overFlash>highFlash)
		{
			overFlash = 0;
			flashRed = false;			
		}
		if (flashOn>flashTime)
		{
			if (flashOff>flashTime)
			{
				flashOn = 0;
			}
			flashOff++;
		}
		else
		{
			if (flashOn>flashTime)
			{
				flashOff = 0;
			}
			System.out.println("Hit Flash!");
			g.setColor(Color.RED);
			g.fillRect(xPos, yPos, width, height);
			flashOn++;
		}
		overFlash++;
	}
	
	public boolean isAcceptable()
	{
		return acceptableVal;
	}
	
	public void setAcceptable(boolean val)
	{
		acceptableVal = val;
	}
	
	public String getContents()
	{
		return wordsInText;
	}
	
	public int getPosMouse()
	{
		return posMouse;
	}
	
	public boolean getchangeVal()
	{
		return changeVal;
	}
	
	public boolean getinBounds()
	{
		return inBounds;
	}
	
	public void setchangeVal(boolean val)
	{
		changeVal = val;
	}
	
	public void setText(String text)
	{
		if (text.length()>14)
		{
			text = text.substring(0, 14);
		}
		if (textMouse)
		{
			wordsInText = text+"|";
			posMouse = wordsInText.length()-1;
		}
		else
		{
			wordsInText = text;
		}
	}
	
	public void setX(int val)
	{
		xPos = val;
		createRect();
	}
	
	public void setY(int val)
	{
		yPos = val;
		createRect();
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
}

