package tiles;

import ImageLoader.Assets;

public class InvisibleTile extends Tile
{

	public InvisibleTile(int id) 
	{
		super(Assets.invisibleTile, id, false, false);
	}

}