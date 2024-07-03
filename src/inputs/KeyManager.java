package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyManager implements KeyListener
{
	public String currentLetter = "";
	
	public KeyManager()
	{
		
	}
	
	
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode()==KeyEvent.VK_UP)
		{
			currentLetter = "up";
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			currentLetter = "down";
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			currentLetter = "right";
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			currentLetter = "left";
		}
		//System.out.println("Pressed: "+currentLetter);
	}

	public void keyReleased(KeyEvent e)
	{
		
	}

	public void keyTyped(KeyEvent e) 
	{
		currentLetter = ""+e.getKeyChar();
		//for backspace
		if (e.getKeyChar() == 8)
		{
			currentLetter = "backspace";
		}
		//for delete
		if (e.getKeyChar() == 127)
		{
			currentLetter = "delete";
		}
		//for enter
		if (e.getKeyChar() == 10)
		{
			currentLetter = "enter";
		}
		
		//System.out.println((int) e.getKeyChar());
		//System.out.println("Pressed: "+currentLetter);
	}
	
	public String getCurrentLetter()
	{
		return currentLetter;
	}
	
	public void setCurrentLetter(String val)
	{
		currentLetter = "";
	}
}

