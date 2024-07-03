package inputs;

import java.awt.Graphics;

public class NumberTextBox extends TextBox
{
	private int maxNum;
	private double valOfText, tempText;
	
	public NumberTextBox(int wit, int hit, int x, int y, Handler handle, int max) 
	{
		super(wit, hit, x, y, handle, "1234567890.", 12);
		maxNum = max;
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
	}
	
	public boolean checkParametersNumsWithDotUnder()
	{
		setAcceptable(false);
		String contents = getContents();
		int numDots = 0;
		boolean failing = false;
		boolean changed = false;
		double calcVal = 0;
		int currentDivMult = 1;
		for (int i=contents.length()-1; i>=0 && !failing; i--)
		{
			changed = false;
			if (i==contents.length()-1 && contents.charAt(i)=='.')
			{
				failing = true;
			}
			else
			{
				if (contents.charAt(i)=='.')
				{
					numDots++;
					if (numDots==1)
					{
						changed = true;
						calcVal /= currentDivMult;
						currentDivMult = 1;
					}
					else
					{
						failing = true;
					}
				}
				else
				{
					if (contents.charAt(i)=='|')
					{
						changed = true;
					}
					else
					{
						calcVal += Character.getNumericValue(contents.charAt(i))*currentDivMult;
					}
				}
			}
			currentDivMult *= changed ? 1 : 10;
			
		}
		//System.out.println(calcVal);
		tempText = calcVal;
		return !failing && calcVal<=maxNum;
	}

	public void runCheck()
	{
		if (checkParametersNumsWithDotUnder())
		{
			valOfText = tempText;
			setAcceptable(true);
		}
		
	}
	@Override
	public void checkParametersNumbers() {
		// TODO Auto-generated method stub
		
	}
	
	public double getTextContents()
	{
		return valOfText;
	}
	
	public double getTempContents()
	{
		return tempText;
	}
	
	public void setVal(Double val)
	{
		valOfText = val;
	}
}
