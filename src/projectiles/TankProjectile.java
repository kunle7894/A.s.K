package projectiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import tanks.Tank;

public class TankProjectile extends Projectile
{
	private int attack, defaultSize;
	
	private Rectangle bounds;
	
	private BufferedImage attackImage;
	
	private Tank tank;
	
	private AffineTransform rotationHead;
	
	public TankProjectile(BufferedImage img, int atk, double velY, double velX, int locX, int locY, Tank tk)
	{
		super(velY, velX, locX, locY);
		
		attack = atk;
		attackImage = img;
		tank = tk;
		defaultSize = tank.getDefaultSize()/3;
				
		bounds = new Rectangle((int) getX(),(int) getY(), defaultSize, defaultSize);
		
		rotationHead = AffineTransform.getTranslateInstance(locX+15, locY+16);
	}
	
	public void tick()
	{
		super.tick();
		
		bounds = new Rectangle((int) getX(),(int) getY(), defaultSize/2, defaultSize/4);
		checkandChangeHeadAngle();
	}
	
	public void render(Graphics g)
	{		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(attackImage, rotationHead, null);
		
		//Part of the resize system
		//drawImage(resize(attackImage, defaultSize, defaultSize), (Graphics2D) g);
		//g.drawRect((int) getX(), (int) getY(), defaultSize/2, defaultSize/4);
		
	}
	
	//Previous system that didn't work on some higher resolution screens... I wonder why
	public BufferedImage resize(BufferedImage img, int width, int height)
	{
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        //THIS NEEEDS TO BE CHANGED LATER
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
	}
	
	public void checkandChangeHeadAngle()
	{
		rotationHead = AffineTransform.getTranslateInstance(getX(), getY());
				
		rotationHead.rotate(-Math.atan(super.getVelY()/super.getVelX()), (int) (defaultSize/6), (int) (defaultSize/6));
		rotationHead.scale(0.35, 0.35); //Scale = 0.35 of original size
		//System.out.println("Angle: "+angle);
	}
	
	
	public Rectangle getBounds()
	{
		return bounds;
	}

	public void collisionDetec() 
	{
		
	}
	
	public int getAttack()
	{
		return attack;
	}
}
