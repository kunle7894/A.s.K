package states;

import java.awt.Graphics;

import ImageLoader.Assets;
import gui.Game;
import inputs.Button;
import inputs.Handler;
import sound.SoundManager;

public class OptionsMenu
{
	private Handler handler;
	private Button exit, muteButton, soundButton, sound2Button;
	private boolean closeMenu;

	private int soundBarX, soundBarY;
	
	private int soundSliderXPos;
	
	private int soundPercentage, soundPercentBefore;
	
	private int mouseXPos, mouseYPos, xPosScreen, yPosScreen, constantChanger, constantChangerY, startPercent;
	
	private String prevSoundVal, curSoundVal;
	
	private SoundManager soundManager;
	
	public OptionsMenu(Handler handle)
	{
		handler = handle;
		
		closeMenu = false;
		
		constantChanger = 153;
		constantChangerY = 14;
		
		xPosScreen = Game.getWidth()/2-((Assets.musicMenu.getWidth())/2)-2;
		yPosScreen = Game.getHeight()/2-((Assets.musicMenu.getHeight())/2);
		soundBarX = xPosScreen-110;
		soundBarY = yPosScreen+125;
		
		startPercent = constantChanger/2+1; //To start at 50%
		soundPercentage = startPercent;
		soundPercentBefore = soundPercentage;
		
		soundSliderXPos = soundBarX+constantChanger+startPercent;
				
		buttons();
		
		prevSoundVal = "";
		curSoundVal = "";
		
		soundManager = handler.getSoundManager();
	}
	
	public void buttons()
	{
		exit = new Button(Assets.xButton, xPosScreen+Assets.musicMenu.getWidth()/2+94, yPosScreen, (int) (Assets.xButton[0].getWidth()), (int) (Assets.xButton[0].getHeight()), handler);
		
		int buttonSize = 40;
		muteButton = new Button(Assets.muteButton, xPosScreen+35, yPosScreen+120, buttonSize, buttonSize, handler);
		soundButton = new Button(Assets.soundButton, xPosScreen+105, yPosScreen+120, buttonSize, buttonSize, handler);
		sound2Button = new Button(Assets.sound2Button, xPosScreen+175, yPosScreen+120, buttonSize, buttonSize, handler);
	}
	
	public void closeOptionsMenu()
	{
		closeMenu = exit.isClicked();
	}
	
	public void soundAdjustments()
	{
		mouseXPos = handler.getMouseManager().getX();
		mouseYPos = handler.getMouseManager().getY();
		
		if (handler.getMouseManager().isClicked())
		{
			if (mouseXPos>=soundBarX+constantChanger && mouseXPos<=soundBarX+constantChanger*2 &&
				mouseYPos>=soundBarY && mouseYPos<=soundBarY+constantChangerY)
			{
				soundSliderXPos = mouseXPos;
			}
		}
	}
	
	public void volumePercent()
	{
		soundPercentage = Math.abs((int)((soundSliderXPos-soundBarX-constantChanger)*(100.0/constantChanger)));
	
		if (soundPercentage!=soundPercentBefore)
		{
			//System.out.println("Sound Percentage: "+soundPercentage);
			handler.getSoundManager().setLevel(soundPercentage);
			soundPercentBefore = soundPercentage;
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.musicMenu, xPosScreen, yPosScreen, Assets.musicMenu.getWidth(), Assets.musicMenu.getHeight(), null);
		
		/*
		 * For a slider that didn't go through
		//g.drawImage(Assets.soundBar, soundBarX,  soundBarY, Assets.soundBar.getWidth(), Assets.soundBar.getHeight(), null);
		g.drawImage(Assets.soundSlider, soundSliderXPos, soundBarY, Assets.soundSlider.getWidth(), Assets.soundSlider.getHeight(), null);
		g.setFont(new Font("Courier PLAIN", Font.PLAIN, 15));
		{
			
			g.drawString(""+soundPercentage, soundSliderXPos-(g.getFontMetrics().stringWidth(""+soundPercentage)/2)+2, soundBarY+10);
		}
		*/
		exit.render(g);
		
		muteButton.render(g);
		soundButton.render(g);
		sound2Button.render(g);
	}
	
	public void tick()
	{
		exit.tick();
		
		muteButton.tick();
		soundButton.tick();
		sound2Button.tick();
		makeRadioandAdjust();
		
		closeOptionsMenu();
		soundAdjustments();
		volumePercent();
	}
	
	public void makeRadioandAdjust()
	{
		if (muteButton.isClicked() && !curSoundVal.equals("mute"))
		{
			soundButton.reset();
			sound2Button.reset();
			soundManager.muteAllSound();
			
			//-1 is the value for mute. Assigned by the coder.
			
			soundManager.setLevel(-1);
			//soundManager.lowerAllSound(50);
			prevSoundVal = curSoundVal;
			curSoundVal = "mute";
		}
		else
		{
			if (soundButton.isClicked() && !curSoundVal.equals("sound1"))
			{
				muteButton.reset();
				sound2Button.reset();
				if (prevSoundVal.equals("mute"))
				{
					soundManager.rePlayAllSound();
					//soundManager.lowerAllSound(5);
				}
				else
				{
					soundManager.lowerAllSound(5);
				}
				soundManager.setLevel(5);
				prevSoundVal = curSoundVal;
				curSoundVal = "sound1";
			}
			else
			{
				if (sound2Button.isClicked() && !curSoundVal.equals("sound2"))
				{
					muteButton.reset();
					soundButton.reset();
					if (prevSoundVal.equals("mute"))
					{
						soundManager.rePlayAllSound();
						/*
						soundManager.lowerAllSound(-40);
						soundManager.lowerAllSound(-4);
						*/
					}
					else
					{
						soundManager.lowerAllSound(-4);
					}
					soundManager.setLevel(0);
					prevSoundVal = curSoundVal;
					curSoundVal = "sound2";
				}
			}
		}
	}
	
	public boolean getOptionsMenuClose()
	{
		return !closeMenu;
	}
	
	public void setOptionsMenuClose(boolean close)
	{
		closeMenu = close;
	}
	
	public void setSoundLevel(int vol)
	{
		soundPercentage = vol;
	}
	
	public int getSoundLevel()
	{
		return soundPercentage;
	}
}
