package inputs;

import java.awt.Graphics;

import tanks.Tank;

public class HealthTextPlacer extends TextPlacer
{
	public int mxHealth, curHealth;
	
	public Tank tank;
	
	public HealthTextPlacer(Tank tk, int lcX, int lcY)
	{
		super (tk.getHealth()+" / "+tk.getHealth(), lcX, lcY);
		mxHealth = tk.getHealth();
		curHealth = tk.getHealth();
		
		tank = tk;
	}
	
	public void tick()
	{
		if (!(tank.getHealth()==curHealth))
		{
			curHealth = tank.getHealth();
			super.setText(curHealth+" / "+mxHealth);
		}
	}
	
	public void render(Graphics g)
	{
		super.render(g);
	}
}
