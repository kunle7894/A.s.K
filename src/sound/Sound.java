package sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class Sound
{
	private String fileName;
	
	private URL musicFile;
	private AudioInputStream audioInput;
	private Clip audio;
	
	public Sound(String file)
	{
		fileName = "/res/music/"+file+".wav";
		System.out.println("Music Name: "+fileName);
		attachMusic();
	}
	
	
	public void attachMusic()
	{
		try
		{ 
			//music = new File(fileName);
			musicFile  = Sound.class.getResource(fileName);
			audioInput = AudioSystem.getAudioInputStream(musicFile);
			audio = AudioSystem.getClip();
		}
		catch (Exception e)
		{
			System.out.println("ERROR: File not found");
			System.exit(0);
		}
	}
	
	public void playMusic()
	{
		try
		{
			audio.open(audioInput);
			audio.start();
			//lowerMusic(-20.0f);
			//audio.getMicrosecondPosition() gets position of music
		}
		catch (Exception e)
		{
			System.out.println("AUDIO doesn't exist");
		}
	}
	
	public void lowerMusic(float val)
	{
		FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
		try
		{
			System.out.println("Music Val: "+gainControl.getValue());
			gainControl.setValue(-val);
		}
		catch (IllegalArgumentException e)
		{
			System.out.println("Value Taken: "+(-val)+" not allowable");
			//e.printStackTrace();
		}
	}
	
	public void stopMusic()
	{
		try
		{
			audio.stop();
		}
		catch (Exception e)
		{
			System.out.println("No music is playing!");
		}
	}
	
	public void rePlay()
	{
		try
		{
			audio.start();
		}
		catch (Exception e)
		{
			System.out.println("No music is playing!");
		}
	}
	
	public boolean isPlaying()
	{
		return audio!=null && audio.isActive() ? true : false;
	}
	
	public Clip getAudio()
	{
		return audio;
	}
}	
	
	
/*	
private int recordTime = 10000; //Default 10 seconds
private String soundFileName = "SoundFiles/";
private String keyID;
    private AudioFormat recordingFormat;
    private AudioFileFormat.Type fileType;
private TargetDataLine line;

	//recording
	private float sampleRate = 16000;
    private int sampleSizeInBits = 16;
    private int channels = 2;
    private boolean signed = true;
    private boolean bigEndian = true;
   
    //playing
    private final int BUFFER_SIZE = 128000;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
       
    private File wavFile;
    private Clip clip;
    private Thread stopper;
   
    public Sound(String key)
    {
    	keyID = key;

    	soundFileName += key+".wav";

    	wavFile = new File(soundFileName);

        recordingFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);

        fileType = AudioFileFormat.Type.WAVE;        
    }


	public boolean fileExists()
	{
		return wavFile.exists();
	}
	
	
	//getters
	public String getKeyID()
	{
		return keyID;
	}
	
	public String getRecordTime()
	{
		int recordTTime = recordTime / 1000;
		return ""+recordTTime;
	}
	
	public boolean isPlaying()  
	{
		return clip !=null ? clip.isActive() : false;
	}
	
	public boolean isOpen()
	{
		return clip !=null ? clip.isOpen() : false;
	}
	
	
	//setters
	public void setRecordTime(int desiredRecordTime)
	{
		recordTime = desiredRecordTime*1000;
	}

	//Recorder and Player
	public void record()
	{
		stopper = new Thread(new Runnable()
	    {        
			public void run()
	        {
	            try
	            {
	                 Thread.sleep(recordTime);
	            }
	            catch (InterruptedException ex)
	            {
	                ex.printStackTrace();
	            }
	            recordFinish();
	        }
	    });
	
			stopper.start();
	
			recordStart();
	}
	
	public void recordStart()
	{
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, recordingFormat);
	
		if (!AudioSystem.isLineSupported(info))
		{
			System.out.println("Line not supported");
	        System.exit(0);
		}
		try
		{
			line = (TargetDataLine) AudioSystem.getLine(info);
	   
			line.open(recordingFormat);
			line.start();
	   
			System.out.println("Starting");    
			AudioInputStream ais = new AudioInputStream(line);
	   
			System.out.println("Recording");
			AudioSystem.write(ais, fileType, wavFile);
		}
		catch (LineUnavailableException ex)
		{
			ex.printStackTrace();
		}
	    catch (IOException ioe)
	    {
	        ioe.printStackTrace();
	    }
	}

	public void recordFinish()
    {
        line.stop();
        line.close();
        System.out.println("Finished Recording");
    }

	public void play()
	{
		if (clip!=null)
		{
			clip.close();
		}
		System.out.println("Playing...");
		try
		{
	      // Open an audio input stream.
	       AudioInputStream audioIn = AudioSystem.getAudioInputStream(wavFile);          
	       
	       
	      // Get a sound clip resource.
	      clip = AudioSystem.getClip();
	     
	     
	      // Open audio clip and load samples from the audio input stream.
	      clip.open(audioIn);
	      clip.start();
	   }
	   catch (UnsupportedAudioFileException e)
	   {
	      e.printStackTrace();
	   }
	   catch (IOException e)
	   {
	      e.printStackTrace();
	   }
	   catch (LineUnavailableException e)
	   {
	      e.printStackTrace();
	   }
	}

	public void delayedPlay()
	{
		System.out.println("Playing");
	
		try
		{
	            audioStream = AudioSystem.getAudioInputStream(wavFile);
	       }
		catch (Exception e)
		{
	            e.printStackTrace();
	            System.exit(1);
	        }
	
	        audioFormat = audioStream.getFormat();
	
	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	        try
	        {
	            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
	            sourceLine.open(audioFormat);
	        }
	        catch (LineUnavailableException e)
	        {
	            e.printStackTrace();
	            System.exit(1);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            System.exit(1);
	        }
	
	        sourceLine.start();
	
	        int nBytesRead = 0;
	        byte[] abData = new byte[BUFFER_SIZE];
	        while (nBytesRead != -1)
	        {
	            try
	            {
	                nBytesRead = audioStream.read(abData, 0, abData.length);
	            }
	            catch (IOException e)
	            {
	                e.printStackTrace();
	            }
	            if (nBytesRead >= 0)
	            {
	                @SuppressWarnings("unused")
	                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
	            }
	        }
	
	        sourceLine.drain();
	        sourceLine.close();
	        System.out.println("Finished Playing");
	}

	public void resume()
	{
		if (clip != null)	
		{
			clip.start();
		}
	}

	public void stop()
	{
		if (clip != null)
		{
			clip.stop();
		}
	}


}
*/