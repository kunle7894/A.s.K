package ImageLoader;

import java.awt.image.BufferedImage;

public class SpriteSheet 
{
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage image)
	{
		sheet = image;
	}
	
	public BufferedImage crop (int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
}
