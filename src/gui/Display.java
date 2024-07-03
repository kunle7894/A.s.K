package gui;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display 
{
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	//Creates a Display
	public Display(String tit, int with, int hight)
	{
		title = tit;
		width = with;
		height = hight;
		
		createDisplay();
	}
	
	private void createDisplay()
	{
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
				
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}

	public JFrame getFrame() 
	{
		return frame;
	}

	public Canvas getCanvas() 
	{
		return canvas;
	}
}

