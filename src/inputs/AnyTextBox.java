package inputs;

import java.awt.Graphics;

public class AnyTextBox extends TextBox
{

	public AnyTextBox(int wit, int hit, int x, int y, Handler handle) 
	{
		super(wit, hit, x, y, handle, "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789", 1000);
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
	}

	public void checkParametersNumbers() 
	{
		
	}
}
