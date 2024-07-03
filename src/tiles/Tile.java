package tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Tile 
{
	//Static things
	
	public static Tile[] tiles = new Tile[256];
	public static Tile invisibleTile = new InvisibleTile(0);
	public static Tile beachyTile = new BeachyTile(1);
	public static Tile hardbeachyTile = new HardBeachyTile(2);
	public static Tile triangularTile = new TriangularTile(3);
	
	//CLASS
	
	public static final int TILE_WIDTH = 10, TILE_HEIGHT = 10;
	protected BufferedImage texture;
	
	private int id;
	private boolean breakable, solid;
	private Rectangle bounds;
	
	public Tile(BufferedImage tex, int idi, boolean breakab, boolean sol)
	{
		texture = tex;
		id = idi;
		
		tiles[id] = this;
		
		breakable = breakab;
		solid = sol;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
		bounds = new Rectangle(x, y, TILE_WIDTH, TILE_HEIGHT);
		//g.drawRect(x, y, TILE_WIDTH, TILE_HEIGHT);
	}
	
	public boolean isSolid()
	{
		return solid;
	}
	
	public boolean isBreakable()
	{
		return breakable;
	}
	
	public int getId()
	{
		return id;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public void setSolid(boolean set)
	{
		solid = set;
	}
	
	public void setBreakable(boolean isBreak)
	{
		breakable = isBreak;
	}
}
