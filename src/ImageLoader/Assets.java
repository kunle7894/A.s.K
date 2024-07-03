package ImageLoader;

import java.awt.image.BufferedImage;

public class Assets 
{
	public static BufferedImage background1, background2, background3, background4, tankYellow, tankHeadYellow, tankBulletDef, 
									tankBulletDefBack, beachTile, invisibleTile, triangularTile, green, startMenu, mainMenu, optionsMenu,
										tankRed, tankHeadRed, tankLBlue, tankHeadLBlue, tankHeadDBlue, tankDBlue, tankGreen, tankHeadGreen, tankOrange, tankHeadOrange,
										multiPlayerSelectScreen, tempMap3Button, tempMap4Button, yellowSquare, orangeSquare, redSquare, redArrow, greenSquare, lBlueSquare, dBlueSquare, gameOverMenu, startImg,
										soundSlider, arrow, musicMenu, darkMap;
	public static BufferedImage[] start, fire, ruler, play, tutorial, options, exit, back, singlePlayer,
	  								easy, medium, hard, extreme, multiPlayer, smallOptions, cats, level1Button, level2Button, level3Button, levelXButton, map1Button, map2Button, map3Button, map4Button,	  								
	  										optionsMenuResume, optionsMenuTutorial, optionsMenuOptions, optionsMenuQuit, muteButton, xButton2, soundButton, sound2Button, xButton, tutorialSlides, next, backArrow;

	private int widthSlides = 60, heightSlides = 60, widthCatSlides = 100, heightCatSlides = 100,
					butWidthSlides = 324, butHeightSlides = 69, widthFire = 126, litWidth = 45, litHeight = 45, backWid = 100, 
					backHigh = 53, sizeMapButton = 45, xTempMap = 1422, yTempMap = 48, levelButtonWidth = 80, optionsButWidth = 224, levelButtonHeight = 20, offsetLvlButton = 0, xButtonSize = 30;

	public void init()
	{
		//Creates a new SpriteSheet
		SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/res/spriteSheet.png"));
		SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("/res/ButtonSheet.png"));
		SpriteSheet tankSheet = new SpriteSheet(ImageLoader.loadImage("/res/tankSheet.png"));
		SpriteSheet catSheet = new SpriteSheet(ImageLoader.loadImage("/res/cats.png"));
		//I ADDED THIS
		//SpriteSheet menuButtons = new SpriteSheet(ImageLoader.loadImage("/mainMenuSheet.png"));
		//SpriteSheet numbers = new SpriteSheet(ImageLoader.loadImage("/"))
		
		
		//I ADDED THIS
		startMenu = ImageLoader.loadImage("/res/startMenu.png");
		mainMenu = ImageLoader.loadImage("/res/mainMenu.png");
		optionsMenu = ImageLoader.loadImage("/res/optionsMenuFull.png");
		musicMenu = ImageLoader.loadImage("/res/musicMenu.png");
		gameOverMenu = ImageLoader.loadImage("/res/gameover.png");
		darkMap = ImageLoader.loadImage("/res/nightMap.png");
		

		tutorialSlides = new BufferedImage[4];
		tutorialSlides[0] = ImageLoader.loadImage("/res/tutorial/EasyMediumHardScreen.PNG");
		tutorialSlides[1] = ImageLoader.loadImage("/res/tutorial/SinglePlayerorMultiplayer.PNG");
		tutorialSlides[2] = ImageLoader.loadImage("/res/tutorial/Multiplayer.PNG");
		tutorialSlides[3] = ImageLoader.loadImage("/res/tutorial/GameState.PNG");
		
		
		soundSlider = sheet2.crop(widthSlides*3, heightSlides, 7, 22);
		arrow = sheet2.crop(widthSlides*3, heightSlides*2, widthSlides, heightSlides);
		
		startImg = sheet2.crop(widthSlides*2, 0, widthSlides, heightSlides);
		
		background1 = ImageLoader.loadImage("/res/tankystest.png");
		//background2 = ImageLoader.loadImage("/tankysEasytest.png");
		//background3 = ImageLoader.loadImage("/backgroundAttack.png");
		background4 = ImageLoader.loadImage("/res/map.png");
		multiPlayerSelectScreen = ImageLoader.loadImage("/res/multiplayerSelect.png");
		
		
		tankHeadYellow = tankSheet.crop(widthSlides*2, 0, widthSlides, heightSlides);
		tankYellow = tankSheet.crop(widthSlides*2, heightSlides, widthSlides, heightSlides);
		tankHeadRed = tankSheet.crop(widthSlides, 0, widthSlides, heightSlides);
		tankRed = tankSheet.crop(widthSlides, heightSlides, widthSlides, heightSlides);
		tankHeadDBlue = tankSheet.crop(0, heightSlides*2, widthSlides, heightSlides);
		tankDBlue = tankSheet.crop(0, heightSlides*3, widthSlides, heightSlides);
		tankHeadLBlue = tankSheet.crop(widthSlides*2, heightSlides*2, widthSlides, heightSlides);
		tankLBlue = tankSheet.crop(widthSlides*2, heightSlides*3, widthSlides, heightSlides);
		tankHeadOrange = tankSheet.crop(widthSlides, heightSlides*2, widthSlides, heightSlides);
		tankOrange = tankSheet.crop(widthSlides, heightSlides*3, widthSlides, heightSlides);
		tankHeadGreen = tankSheet.crop(0, 0, widthSlides, heightSlides);
		tankGreen = tankSheet.crop(0, heightSlides, widthSlides, heightSlides);
		
		tankBulletDef = sheet2.crop(0, heightSlides*3, widthSlides, heightSlides);
		tankBulletDefBack = sheet2.crop(0, heightSlides*4, widthSlides, heightSlides);
		
		beachTile = sheet2.crop(0, heightSlides*2, widthSlides, heightSlides);
		invisibleTile = sheet2.crop(widthSlides, heightSlides*2, widthSlides, heightSlides);
		triangularTile = sheet2.crop(widthSlides*2, heightSlides*2, widthSlides, heightSlides);
		
		green = sheet2.crop(widthSlides, heightSlides*3, widthSlides, heightSlides);
		
		yellowSquare = sheet2.crop(0, 0, widthSlides, heightSlides);
		redSquare = sheet2.crop(widthSlides, 0, widthSlides, heightSlides);
		dBlueSquare = sheet2.crop(widthSlides*2, 0, widthSlides, heightSlides);
		greenSquare = sheet2.crop(0, heightSlides, widthSlides, heightSlides);
		orangeSquare = sheet2.crop(widthSlides, heightSlides, widthSlides, heightSlides);
		lBlueSquare = sheet2.crop(widthSlides*2, heightSlides, widthSlides, heightSlides);
		redArrow = sheet2.crop(widthSlides*3, 0, widthSlides, heightSlides);
		
		start = new BufferedImage[2];
		start[0] = sheet2.crop(widthSlides*2, 0, widthSlides, heightSlides);
		start[1] = sheet2.crop(widthSlides*2, heightSlides, widthSlides, heightSlides);
		
		//I CHANGED OR ADDED THESE
		fire = new BufferedImage[2];
		fire[0] = buttons.crop(butWidthSlides*4, 0, widthFire, butHeightSlides);
		fire[1] = buttons.crop(butWidthSlides*4, butHeightSlides, widthFire, butHeightSlides);
		
		xButton2 = new BufferedImage[2];
		xButton2[0]= buttons.crop(butWidthSlides*3+optionsButWidth+backWid*2, butHeightSlides*6, backWid, backHigh);
		xButton2[1] = buttons.crop(butWidthSlides*3+optionsButWidth+backWid*2, butHeightSlides*6+backHigh, backWid, backHigh);
		
		xButton = new BufferedImage[2];
		xButton[0] = buttons.crop(butWidthSlides*4+widthFire, 0, xButtonSize, xButtonSize);
		xButton[1] = buttons.crop(butWidthSlides*4+widthFire+xButtonSize, 0, xButtonSize, xButtonSize);
		
		ruler = new BufferedImage[2];
		ruler[0] = buttons.crop(butWidthSlides*4, butHeightSlides*2, litWidth, litHeight);
		ruler[1] = buttons.crop((butWidthSlides*4)+litWidth, butHeightSlides*2, litWidth, litHeight);
		
		smallOptions = new BufferedImage[2];
		smallOptions[0] = buttons.crop(butWidthSlides*4, butHeightSlides*2+litHeight, litWidth, litHeight);
		smallOptions[1] = buttons.crop((butWidthSlides*4)+litWidth, butHeightSlides*2+litHeight, litWidth, litHeight);
		
		play = new BufferedImage[2];
		play[0] = buttons.crop(0, 0, butWidthSlides, butHeightSlides);
		play[1] = buttons.crop(butWidthSlides, 0, butWidthSlides, butHeightSlides);
		
		tutorial = new BufferedImage[2];
		tutorial[0] = buttons.crop(0, butHeightSlides, butWidthSlides, butHeightSlides);
		tutorial[1] = buttons.crop(butWidthSlides, butHeightSlides, butWidthSlides, butHeightSlides);
		
		options = new BufferedImage[2];
		options[0] = buttons.crop(0, butHeightSlides*2, butWidthSlides, butHeightSlides);
		options[1] = buttons.crop(butWidthSlides, butHeightSlides*2, butWidthSlides, butHeightSlides);
		
		exit = new BufferedImage[2];
		exit[0] = buttons.crop(0, butHeightSlides*3, butWidthSlides, butHeightSlides);
		exit[1] = buttons.crop(butWidthSlides, butHeightSlides*3, butWidthSlides, butHeightSlides);
		
		back = new BufferedImage[2];
		back[0] = buttons.crop(butWidthSlides*4, (butHeightSlides*2)+(litHeight*2), backWid, backHigh);
		back[1] = buttons.crop(butWidthSlides*4, (butHeightSlides*2)+(litHeight*2)+backHigh, backWid, backHigh);
		
		singlePlayer = new BufferedImage[2];
		singlePlayer[0] = buttons.crop(butWidthSlides*2, butHeightSlides*2, butWidthSlides, butHeightSlides*2);
		singlePlayer[1] = buttons.crop(butWidthSlides*3, butHeightSlides*2, butWidthSlides, butHeightSlides*2);
		
		easy = new BufferedImage[2];
		easy[0] = buttons.crop(0, butHeightSlides*4, butWidthSlides, butHeightSlides);
		easy[1] = buttons.crop(butWidthSlides, butHeightSlides*4, butWidthSlides, butHeightSlides);
		
		medium = new BufferedImage[2];
		medium[0] = buttons.crop(0, butHeightSlides*5, butWidthSlides, butHeightSlides);
		medium[1] = buttons.crop(butWidthSlides, butHeightSlides*5, butWidthSlides, butHeightSlides);
		
		hard = new BufferedImage[2];
		hard[0] = buttons.crop(butWidthSlides*2, 0, butWidthSlides, butHeightSlides);
		hard[1] = buttons.crop(butWidthSlides*3, 0, butWidthSlides, butHeightSlides);
		
		extreme = new BufferedImage[2];
		extreme[0] = buttons.crop(butWidthSlides*2, butHeightSlides, butWidthSlides, butHeightSlides);
		extreme[1] = buttons.crop(butWidthSlides*3, butHeightSlides, butWidthSlides, butHeightSlides);
		
		multiPlayer = new BufferedImage[2];
		multiPlayer[0] = buttons.crop(butWidthSlides*2, butHeightSlides*4, butWidthSlides, butHeightSlides*2);
		multiPlayer[1] = buttons.crop(butWidthSlides*3, butHeightSlides*4, butWidthSlides, butHeightSlides*2);
		
		cats = new BufferedImage[6];
		cats[0] = catSheet.crop(0, 0, widthCatSlides, heightCatSlides);
		
		tempMap3Button = buttons.crop(xTempMap, yTempMap, sizeMapButton, sizeMapButton);
		tempMap4Button = buttons.crop(xTempMap, yTempMap+sizeMapButton, sizeMapButton, sizeMapButton);
		
		optionsMenuResume = new BufferedImage[2];
		optionsMenuResume[0] = buttons.crop(0, butHeightSlides*7, optionsButWidth, butHeightSlides);
		optionsMenuResume[1] = buttons.crop(butWidthSlides, butHeightSlides*7, optionsButWidth, butHeightSlides);
		
		optionsMenuTutorial = new BufferedImage[2];
		optionsMenuTutorial[0] = buttons.crop(butWidthSlides*2, butHeightSlides*7, optionsButWidth, butHeightSlides);
		optionsMenuTutorial[1] = buttons.crop(butWidthSlides*3, butHeightSlides*7, optionsButWidth, butHeightSlides);
		
		optionsMenuOptions = new BufferedImage[2];
		optionsMenuOptions[0] = buttons.crop(0, butHeightSlides*6, optionsButWidth, butHeightSlides);
		optionsMenuOptions[1] = buttons.crop(butWidthSlides, butHeightSlides*6, optionsButWidth, butHeightSlides);
		
		optionsMenuQuit = new BufferedImage[2];
		optionsMenuQuit[0] = buttons.crop(butWidthSlides*2, butHeightSlides*6, optionsButWidth, butHeightSlides);
		optionsMenuQuit[1] = buttons.crop(butWidthSlides*3, butHeightSlides*6, optionsButWidth, butHeightSlides);
		
		// +4 is added to level Buttons to fix offset problems...
		level1Button = new BufferedImage[3];
		level1Button[0] = buttons.crop(butWidthSlides*4, (butHeightSlides*2)+(litHeight*2)+backHigh*2+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		level1Button[1] = buttons.crop(butWidthSlides*4, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		level1Button[2] = buttons.crop(butWidthSlides*4, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		
		level2Button = new BufferedImage[3];
		level2Button[0] = buttons.crop(butWidthSlides*4, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight*2+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		level2Button[1] = buttons.crop(butWidthSlides*4, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight*3+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		level2Button[2] = buttons.crop(butWidthSlides*4, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight*3+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		
		level3Button = new BufferedImage[3];
		level3Button[0] = buttons.crop(butWidthSlides*4+levelButtonWidth, (butHeightSlides*2)+(litHeight*2)+backHigh*2+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		level3Button[1] = buttons.crop(butWidthSlides*4+levelButtonWidth, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		level3Button[2] = buttons.crop(butWidthSlides*4+levelButtonWidth, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		
		levelXButton = new BufferedImage[3];
		levelXButton[0] = buttons.crop(butWidthSlides*4+levelButtonWidth, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight*2+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		levelXButton[1] = buttons.crop(butWidthSlides*4+levelButtonWidth, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight*3+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		levelXButton[2] = buttons.crop(butWidthSlides*4+levelButtonWidth, (butHeightSlides*2)+(litHeight*2)+backHigh*2+levelButtonHeight*3+offsetLvlButton, levelButtonWidth, levelButtonHeight);
		
		map1Button = new BufferedImage[3];
		map1Button[0] = buttons.crop(butWidthSlides*4+sizeMapButton*2, butHeightSlides*2+sizeMapButton, sizeMapButton, sizeMapButton);
		map1Button[1] = buttons.crop(butWidthSlides*4+sizeMapButton*3, butHeightSlides*2+sizeMapButton, sizeMapButton, sizeMapButton);
		map1Button[2] = buttons.crop(butWidthSlides*4+sizeMapButton*3, butHeightSlides*2+sizeMapButton, sizeMapButton, sizeMapButton);
		
		map2Button = new BufferedImage[3];
		map2Button[0] = buttons.crop(butWidthSlides*4+sizeMapButton*2, butHeightSlides*2, sizeMapButton, sizeMapButton);
		map2Button[1] = buttons.crop(butWidthSlides*4+sizeMapButton*3, butHeightSlides*2, sizeMapButton, sizeMapButton);
		map2Button[2] = buttons.crop(butWidthSlides*4+sizeMapButton*3, butHeightSlides*2, sizeMapButton, sizeMapButton);
		
		map3Button = new BufferedImage[3];
		map3Button[0] = buttons.crop(butWidthSlides*4+backWid, (butHeightSlides*2)+(litHeight*2), sizeMapButton, sizeMapButton);
		map3Button[1] = buttons.crop(butWidthSlides*4+backWid+sizeMapButton, (butHeightSlides*2)+(litHeight*2), sizeMapButton, sizeMapButton);
		map3Button[2] = buttons.crop(butWidthSlides*4+backWid+sizeMapButton, (butHeightSlides*2)+(litHeight*2), sizeMapButton, sizeMapButton);
		
		map4Button = new BufferedImage[3];
		map4Button[0] = buttons.crop(butWidthSlides*4+backWid, (butHeightSlides*2)+(litHeight*2)+sizeMapButton, sizeMapButton, sizeMapButton);
		map4Button[1] = buttons.crop(butWidthSlides*4+backWid+sizeMapButton, (butHeightSlides*2)+(litHeight*2)+sizeMapButton, sizeMapButton, sizeMapButton);
		map4Button[2] = buttons.crop(butWidthSlides*4+backWid+sizeMapButton, (butHeightSlides*2)+(litHeight*2)+sizeMapButton, sizeMapButton, sizeMapButton);

		muteButton = new BufferedImage[3];
		//buttons.crop(butWidthSlides*2, butHeightSlides*6, optionsButWidth, butHeightSlides);
		muteButton[0] = buttons.crop(butWidthSlides*2+optionsButWidth, butHeightSlides*6+sizeMapButton, sizeMapButton, sizeMapButton);
		muteButton[1] = buttons.crop(butWidthSlides*2+optionsButWidth+sizeMapButton, butHeightSlides*6+sizeMapButton*2, sizeMapButton, sizeMapButton);
		muteButton[2] = buttons.crop(butWidthSlides*2+optionsButWidth+sizeMapButton, butHeightSlides*6+sizeMapButton*2, sizeMapButton, sizeMapButton);
		
		soundButton = new BufferedImage[3];
		soundButton[0] = buttons.crop(butWidthSlides*2+optionsButWidth+sizeMapButton, butHeightSlides*6, sizeMapButton, sizeMapButton);
		soundButton[1] = buttons.crop(butWidthSlides*2+optionsButWidth, butHeightSlides*6+sizeMapButton*2, sizeMapButton, sizeMapButton);
		soundButton[2] = buttons.crop(butWidthSlides*2+optionsButWidth, butHeightSlides*6+sizeMapButton*2, sizeMapButton, sizeMapButton);
		
		sound2Button = new BufferedImage[3];
		sound2Button[0] = buttons.crop(butWidthSlides*2+optionsButWidth, butHeightSlides*6, sizeMapButton, sizeMapButton);
		sound2Button[1] = buttons.crop(butWidthSlides*2+optionsButWidth+sizeMapButton, butHeightSlides*6+sizeMapButton*1, sizeMapButton, sizeMapButton);
		sound2Button[2] = buttons.crop(butWidthSlides*2+optionsButWidth+sizeMapButton, butHeightSlides*6+sizeMapButton*1, sizeMapButton, sizeMapButton);
	
		next = new BufferedImage[2];
		next[0] = buttons.crop(butWidthSlides*3+optionsButWidth+backWid, butHeightSlides*6, backWid, backHigh);
		next[1] = buttons.crop(butWidthSlides*3+optionsButWidth+backWid, butHeightSlides*6+backHigh, backWid, backHigh);
		
		backArrow = new BufferedImage[2];
		backArrow[0]= buttons.crop(butWidthSlides*3+optionsButWidth, butHeightSlides*6, backWid, backHigh);
		backArrow[1] = buttons.crop(butWidthSlides*3+optionsButWidth, butHeightSlides*6+backHigh, backWid, backHigh);
	}
}
