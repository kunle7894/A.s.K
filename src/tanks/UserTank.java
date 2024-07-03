package tanks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ImageLoader.Assets;
import inputs.Button;
import inputs.Handler;
import inputs.KeyManager;
import inputs.NumberTextBox;
import projectiles.PathofBullet;

public class UserTank extends Tank
{
	private int velAndAngleTextW = 98, velAndAngleTextH = 14, velAndAngleTextY = 640, velOneTextX = 188, offsetTextBy = 141;
	
	private double angleMov = 1;
	
	private NumberTextBox[] textBoxes;
	
	private Button fireButton;
	
	private KeyManager keyManager;
	
	private Handler handler;
	
	public UserTank(BufferedImage img, BufferedImage img2, int lcX, int lcY, int tnkNum, Handler handle) 
	{
		super(img, img2, lcX, lcY, tnkNum, handle);
		keyManager = handle.getKeyManager();
		handler = handle;
		init();
		super.setTyping("User");
		super.setTankNum(tnkNum);
		// TODO Auto-generated constructor stub
	}
	
	public void init()
	{
		textBoxes = new NumberTextBox[2];
		//firstVelocity
		textBoxes[0] = new NumberTextBox(velOneTextX, velAndAngleTextY, velAndAngleTextW, velAndAngleTextH, handler, 100);
		//firstAngle
		textBoxes[1] = new NumberTextBox(velOneTextX+offsetTextBy, velAndAngleTextY, velAndAngleTextW, velAndAngleTextH, handler, 180);
		
		//Fire Button
		fireButton = new Button(Assets.fire, 565, 600, 125, 68, handler);
		
	}
	
	public void tick()
	{
		super.tick();
		checkAndChangeKeyClk();
		checkAndChangeKeyClick();
		
		for (int i=0; i<textBoxes.length; i++)
		{
			textBoxes[i].tick();
			textBoxes[i].runCheck();
			if (!textBoxes[i].getinBounds() && textBoxes[i].getchangeVal())
			{
				//health -= 10;
				if (i==0)
				{
					super.setVel(textBoxes[i].getTextContents());
				}
				if (i==1)
				{
					super.setAngle(-textBoxes[i].getTextContents());
				}
				findVelocityComponents();
				textBoxes[i].setchangeVal(false);
				bulletPath = new PathofBullet(this, 20);
			}
		}
		fireButton.tick();
		
		if (super.getShowShot())
		{
			bulletPath.tick();
		}
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		for (int i=0; i<textBoxes.length; i++)
		{
			//buttons[i].render(g);
			textBoxes[i].render(g);
		}
		fireButton.render(g);
		
		if (super.getShowShot())
		{
			bulletPath.render(g);
		}
	}
	
	public void checkAndChangeKeyClick()
	{
		if ((keyManager.getCurrentLetter().toLowerCase().equals("w") || keyManager.getCurrentLetter().toLowerCase().equals("up")) && super.getAngle()>-180)
		{
			super.chgAngle(-angleMov);
			textBoxes[1].setText(""+(-super.getAngle()));
			findVelocityComponents();
			/*
			 //TIp Location
			adderTipX--;
			if (angle>=-90)
			{
				adderTipY--;
			}
			else
			{
				adderTipY++;
			}
			*/

			bulletPath = new PathofBullet(this, 20);
			keyManager.setCurrentLetter("");
		}
		if ((keyManager.getCurrentLetter().toLowerCase().equals("s") || keyManager.getCurrentLetter().toLowerCase().equals("down")) && super.getAngle()<0)
		{
			super.chgAngle(angleMov);
			textBoxes[1].setText(""+(-super.getAngle()));
			findVelocityComponents();
			/*
			adderTipX++;
			if (angle<=-90)
			{
				adderTipY--;
			}
			else
			{
				adderTipY++;
			}
			*/

			bulletPath = new PathofBullet(this, 20);
			keyManager.setCurrentLetter("");
		}
		
		if (((keyManager.getCurrentLetter().equals(" ") || fireButton.isClicked())) && super.getVel()>0)
		{
			super.createProj();
			super.setChangeTank(true);
		}
	}
}
