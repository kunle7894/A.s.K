package projectiles;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Projectile 
{
	private double locX, locY, defaultLocY, defaultLocX, velY, velX, velI, grav, t;
	
	public Projectile(double vlY, double vlX, double lcX, double lcY)
	{
		velY = vlY;
		velX = vlX;
		velI = vlY;
		grav = 9.81;
		locY = lcY;
		locX = lcX;
		defaultLocX = locX;
		defaultLocY = locY;
	}
	
	public void tick()
	{
		//height formula
		//h(f) = 1/2gt^2 + vit + h(i)
		locY = (int) (defaultLocY - velI*t + (0.5*grav*(t*t)));
		
		//System.out.println("Loc Proj: "+locY+" time: "+t);
		
		velY = velI + (-grav*t);
		//displace horizontal
		locX = defaultLocX+velX*t;
		
		t+=0.1;
	}
	
	public AffineTransform findAngle()
	{
		AffineTransform tx = AffineTransform.getRotateInstance(0, locX, locY);

		// Drawing the rotated image at the required drawing locations
		return tx;
	}
	
	public void drawImage(BufferedImage img, Graphics2D g2d)
	{
		AffineTransform backup = g2d.getTransform();
	    //rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
	    //is the angle to rotate the image. If you want to rotate around the center of an image,
	    //use the image's center x and y coordinates for rx and ry.
	    AffineTransform a = AffineTransform.getRotateInstance(-Math.atan(velY/velX), locX, locY);
	    //Set our Graphics2D object to the transform
	    g2d.setTransform(a);
	    //Draw our image like normal
	    g2d.drawImage(img, (int) locX, (int) locY, null);
	    //Reset our graphics object so we can draw with it again.
	    g2d.setTransform(backup);	
	}
	
	public abstract void collisionDetec();
	
	public double getX()
	{
		return locX;
	}
	
	public double getY()
	{
		return locY;
	}
	
	public double getVelY()
	{
		return velY;
	}
	
	public double getVelX()
	{
		return velX;
	}
}
