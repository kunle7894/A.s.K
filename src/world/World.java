package world;

import java.awt.Graphics;
import java.awt.Rectangle;

import states.GameState;
import tanks.TurnSetter;
import tiles.Tile;

public class World 
{
	private int width, height;
	private int spawnX, spawnY;
	private int locTank, locProj, locTankProj;
	private int[][] tiles;
	private GameState gameState;
	
	public boolean stopChecking;
	
	public World(String path, GameState state)
	{
		loadWorld(path); 
		gameState = state;
		stopChecking = false;
	}
	
	public void tick()
	
	{
		
	}
	
	public void render(Graphics g)
	{
		int xStart = 0;
		int xEnd = width;
		int yStart = 0;
		int yEnd = height;
		
		for (int y=yStart; y<yEnd; y++)
		{
			for (int x=xStart; x<xEnd; x++)
			{
				getTile(x, y).render(g, (int) (x*Tile.TILE_WIDTH+spawnX),
										(int) (y*Tile.TILE_HEIGHT+spawnY));
				checkTankCollision(x, y);
				if (!stopChecking)
				{
					//System.out.println("Solid ? "+getTile(x,y).isSolid());
					if (locTank>-1)
					{
						//TurnSetter.tanks.get(locTank).subtractX(1);
						//TurnSetter.tanks.get(locTank).subtractY(1);
						//System.out.println("Collision: Tank and Terrain!");
						//So the tanks don't giggle
						//stopChecking = true;
					}
				}
				if (locProj>-1)
				{
					setZerosAround(x, y);
					gameState.getTanks().get(locTankProj).getProjectiles().remove(locProj);
				}
			}
			stopChecking = false;
		}
	}
	
	public void checkTankCollision(int x, int y)
	{
		locTank = -1;
		locProj = -1;
		locTankProj = -1;
		
		Rectangle curBounds;
		Rectangle projBounds;
		Rectangle tileBounds = getTile(x, y).getBounds();
		for (int i=0; i<gameState.getTanks().size(); i++)
		{
			curBounds =  gameState.getTanks().get(i).getBounds();
			if (locTank==-1 && curBounds!=null && getTile(x, y).isSolid() && tileBounds.intersects(curBounds))
			{
				locTank = i;
				gameState.getTanks().get(locTank).subtractY(1);
				gameState.getTanks().get(locTank).setGravityDown(false);
			}
			
			for (int j=0; j<gameState.getTanks().get(i).getProjectiles().size() && locProj==-1; j++)
			{
				projBounds = gameState.getTanks().get(i).getProjectiles().get(j).getBounds();
				if (locTank==-1 && curBounds!=null && getTile(x, y).isSolid() && tileBounds.intersects(projBounds))
				{
					locProj = j;
					locTankProj = i;
				}
			}
		}
	}
		
	public Tile getTile(int x, int y)
	{
		Tile t = Tile.tiles[tiles[x][y]];
		Tile tileReturned = t;
		if (x<0 || y<0 || x>=width || y>=height)
		{
			tileReturned = Tile.beachyTile;
		}
		if (t==null)
		{
			tileReturned = Tile.beachyTile;
		}
		return tileReturned;
		
	}
	
	private void loadWorld(String path)
	{
		String file = Utils.loadFilesAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for (int y=0; y<height; y++)
		{
			for (int x=0; x<width; x++)
			{
				tiles[x][y] = Utils.parseInt(tokens[(x+y*width)+4]);
			}
		}
	}
	
	public void setZerosAround(int locX, int locY)
	{
		int start = locX-1>0 ? locX-1 : locX;
		int end = locX+1<tiles.length ? locX+1 : locX;
		for (int i=start; i<=end; i++)
		{
			if (getTile(i, locY).isBreakable() && getTile(i, locY).isSolid())
			{
				tiles[i][locY] = 0;
			}
		}
		start = locY;
		end = locY+1<tiles[locY].length ? locY+1 : locY;
		for (int i=start; i<=end; i++)
		{
			if (getTile(locX, i).isBreakable() && getTile(locX, i).isSolid())
			{	
				tiles[locX][i] = 0;
			}
		}
		gravitizeBlocks();
		
		//Makes sure the tanks aren't in the air
		for (int i=0; i<gameState.getTanks().size(); i++)
		{
			gameState.getTanks().get(i).setGravityDown(true);	
		}
	}
	
	public void gravitizeBlocks()
	{
		int temp = 0;
		System.out.println("World Height"+tiles.length);
		for (int i=0; i<tiles[0].length-1; i++)
		{
			for (int j=0; j<tiles.length; j++)
			{
				if (tiles[j][i]!=0 && tiles[j][i+1]==0)
				{
					temp = tiles[j][i];
					tiles[j][i] = tiles[j][i+1];
					tiles[j][i+1] = temp;
				}
			}
		}
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
