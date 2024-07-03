package sound;

import javax.sound.sampled.Clip;

public class LoopSound extends Sound
{
	public LoopSound(String fileName)
	{
		super(fileName);
	}
	
	public void playMusic()
	{
		super.playMusic();
		try
		{
			getAudio().loop(Clip.LOOP_CONTINUOUSLY);
			System.out.println("Music Playing!");
		}
		catch (Exception e)
		{
			System.out.println("Loop can't happen. File doesn't exist");
		}
	}
}
