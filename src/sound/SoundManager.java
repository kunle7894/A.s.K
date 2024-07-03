package sound;

import java.util.ArrayList;

import inputs.Handler;

public class SoundManager 
{
	private Handler handler;
	private int level;
	
	private ArrayList<LoopSound> loopSounds;
	private ArrayList<TempSound> tempSounds;
	
	public SoundManager(Handler handle)
	{
		setHandler(handle);
		
		level = -10;
		loopSounds = new ArrayList<>();
		tempSounds = new ArrayList<>();
	}
	
	public int addLoopSound(String location)
	{
		loopSounds.add(new LoopSound(location));
		loopSounds.get(loopSounds.size()-1).playMusic();
		if (level>0)
		{
			lowerLoopVolume(loopSounds.size()-1, level);
		}
		if (level==-1)
		{
			loopSounds.get(loopSounds.size()-1).stopMusic();
		}
		return loopSounds.size()-1;
	}
	
	public void lowerLoopVolume(int loc, float amount)
	{
		loopSounds.get(loc).lowerMusic(amount);
	}
	
	public int addTempSound(String location)
	{
		if (level!=-1)
		{
			tempSounds.add(new TempSound(location));
			tempSounds.get(tempSounds.size()-1).playMusic();
		}
		if (level>0)
		{	
			lowerTempVolume(tempSounds.size()-1, level);
		}
		return tempSounds.size()-1;
	}
	
	public void lowerTempVolume(int loc, float amount)
	{
		loopSounds.get(loc).lowerMusic(amount);
	}
	
	public void stopLoopSound(int loc)
	{
		loopSounds.get(loc).stopMusic();
	}
	
	public void stopTempSounds(int loc)
	{
		tempSounds.get(loc).stopMusic();
	}

	public Handler getHandler() 
	{
		return handler;
	}

	public void setLevel(int lvl)
	{
		level = lvl;
	}
	
	/* Unused for a previous version of sound
	public void setLevel()
	{
		level = 
		double lvl = level<50 ? (double) (level)/50.0 : (double) (level-50)/50.0;
		int curChange = 0;
		if (level==0)
		{
			muteAllSound();
		}
		if (level<50)
		{	
			lowerAllSound((float) (range*lvl));
			
			/*
			lvl = 1-lvl;
			curChange = (int) (range*lvl);
			//lowers the level of all the sounds
			System.out.println("Lowered By: "+ (float) (-lastChange-curChange));
			lowerAllSound((float) (lastChange-curChange));
			lastChange = curChange;
			
		}
		if (level>=50)
		{	
			lowerAllSound(-(float) (range*lvl));
			/*
			curChange = (int) (range*lvl);
			//lowers the level of all the sounds
			System.out.println("Lowered By: "+ (float) (lastChange-curChange));
			lowerAllSound((float) (lastChange-curChange));
			lastChange = curChange;
			
		}
		System.out.println("Sound Level: "+curChange);
	}
	*/
	
	public void lowerAllSound(float val)
	{
		for (int i=0; i<tempSounds.size(); i++)
		{
			lowerTempVolume(i, val);
		}
		for (int i=0; i<loopSounds.size(); i++)
		{
			lowerLoopVolume(i, val);
		}
	}
	
	public void muteAllSound()
	{
		for (int i=0; i<tempSounds.size(); i++)
		{
			tempSounds.get(i).stopMusic();
		}
		for (int i=0; i<loopSounds.size(); i++)
		{
			loopSounds.get(i).stopMusic();
		}
	}
	
	public void rePlayAllSound()
	{
		for (int i=0; i<tempSounds.size(); i++)
		{
			tempSounds.get(i).rePlay();
		}
		for (int i=0; i<loopSounds.size(); i++)
		{
			loopSounds.get(i).rePlay();
		}
	}
	
	public void setHandler(Handler handle) 
	{
		handler = handle;
	}
}
