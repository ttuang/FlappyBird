/*
 * Thang Tuang
 * 08/11/2014
 * FlappyBird.java
 * making Flappy Bird for Final project
 */

import java.awt.Color;
import java.awt.Font; 
import java.awt.event.KeyEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel; 
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram; 
import acm.util.RandomGenerator; 

public class FlappyBird extends GraphicsProgram 
{ 
	private static final int APPLET_WIDTH = 800;
	private static final int APPLET_HEIGHT= 500;
	private static final int OBSTACLES_SPEED = -2; 
	private static final int NUM_OBSTACLES = 9; 
	private static final int RECT1_WIDTH= 50, RECT2_WIDTH =60,
			RECT3_WIDTH =50, RECT4_WIDTH =40,
			RECT5_WIDTH =70,RECT6_WIDTH =70,
			RECT7_WIDTH = 70, RECT8_WIDTH =60;
	private static final int RECT1_HEIGHT= 230, RECT2_HEIGHT= 180,
			RECT3_HEIGHT= 200, RECT4_HEIGHT= 100,
			RECT5_HEIGHT= 300, RECT6_HEIGHT= 230,
			RECT7_HEIGHT= 100,RECT8_HEIGHT= 300;	
	private static final int RECT1_X = 60,RECT2_X = 130,RECT3_X = 300,
			RECT4_X = 400,RECT5_X = 520,RECT6_X = 570,
			RECT7_X = 770,RECT8_X = 750;	
	private static final int RECT1_Y = 350,RECT2_Y = -30,RECT3_Y = 310,
			RECT4_Y = -10,RECT5_Y = 300,RECT6_Y = -100,
			RECT7_Y = -30,RECT8_Y = 200;
	private RandomGenerator random = new RandomGenerator(); 
	private GRect[] obstacles = new GRect[NUM_OBSTACLES];
	private GRect grass;
	private GLabel score, gameOver;
	private boolean done = false, game = true;
	private double playerScore = 0;
	private double flappyGravity = .3;
	private Bird  myBird;
	public void init() 
	{ 
		setSize(APPLET_WIDTH, APPLET_HEIGHT); 
		addKeyListeners();
		setBackground(Color.GREEN);
	} 

	public void run() 
	{ 
		myBird = new Bird();
		add(myBird,5, 200);
		myBird.assemble();

		//		addGraphics();
		drawObstacles(); 
		dirt();
		grass();
		waitForClick(); 

		//adding score on our game
		score = new GLabel("score:" +myBird.getX(),330,490 );
		score.setFont (new Font("Arial", Font.BOLD, 20));
		score.setColor(Color.WHITE);

		//game Over
		gameOver = new GLabel("");
		gameOver.setFont(new Font("Arial", Font.BOLD, 30));
		gameOver.setColor(Color.GREEN);
		add(gameOver);
		add(score);

		//adding player score        
		score.setLabel("Score :" + playerScore);

		// making flappy bird and obstacles move
		while(!done)
		{
			for (int i = 1; i < obstacles.length; i++) 
			{
				obstacles[i].move (OBSTACLES_SPEED,0);
				myBird.move(0,flappyGravity);

				//ask whether it's game over!
				//flappy bird collides with obstacles
				if (myBird.getBounds().intersects(obstacles[i].getBounds()))
				{
					remove(myBird);
					done = game;
				}	
				else
					if (myBird.getBounds().intersects(grass.getBounds()))
					{
						remove(myBird);
						done = game;
					}

				//repeat the obstacles
				if (obstacles[i].getX()<= 0) 
				{
					obstacles[i].setLocation(APPLET_WIDTH,obstacles[i].getY() );

					//scoring each time the obstacle pass the applet_width left
					playerScore++;
					score.setLabel("Score: " + playerScore);
				}				
			}
			pause(20);
		}
	} 

	// drawing OBSTACLES with GRect
	public void drawObstacles() 
	{ 
		obstacles[1] = new GRect (RECT1_X, RECT1_Y, RECT1_WIDTH,RECT1_HEIGHT);
		obstacles[1].setFilled(true);
		obstacles[1].setFillColor(random.nextColor());
		add(obstacles[1]); 

		obstacles[2] = new GRect (RECT2_X, RECT2_Y, RECT2_WIDTH,RECT2_HEIGHT);
		obstacles[2].setFilled(true);
		obstacles[2].setFillColor(random.nextColor());
		add(obstacles[2]);  

		obstacles[3] = new GRect (RECT3_X, RECT3_Y, RECT3_WIDTH,RECT3_HEIGHT);
		obstacles[3].setFilled(true);
		obstacles[3].setFillColor(random.nextColor());
		add(obstacles[3]);  

		obstacles[4] = new GRect (RECT4_X, RECT4_Y, RECT4_WIDTH,RECT4_HEIGHT);
		obstacles[4].setFilled(true);
		obstacles[4].setFillColor(random.nextColor());
		add(obstacles[4]);

		obstacles[5] = new GRect (RECT5_X, RECT5_Y, RECT5_WIDTH,RECT5_HEIGHT);
		obstacles[5].setFilled(true);
		obstacles[5].setFillColor(random.nextColor());
		add(obstacles[5]);

		obstacles[6] = new GRect (RECT6_X, RECT6_Y, RECT6_WIDTH,RECT6_HEIGHT);
		obstacles[6].setFilled(true);
		obstacles[6].setFillColor(random.nextColor());
		add(obstacles[6]);

		obstacles[7] = new GRect (RECT7_X, RECT7_Y, RECT7_WIDTH,RECT7_HEIGHT);
		obstacles[7].setFilled(true);
		obstacles[7].setFillColor(random.nextColor());
		add(obstacles[7]);

		obstacles[8] = new GRect (RECT8_X, RECT8_Y, RECT8_WIDTH,RECT8_HEIGHT);
		obstacles[8].setFilled(true);
		obstacles[8].setFillColor(random.nextColor());
		add(obstacles[8]);
	} 

	//Adding dirt
	public void dirt() 
	{
		//dirt
		int dirtX = 0;
		int dirtY = 460;
		int dirtHeight = 40;
		GRect dirt = new GRect(dirtX, dirtY, APPLET_WIDTH, dirtHeight);
		add(dirt); 
		dirt.setFilled(true);
		dirt.setFillColor(Color. GREEN);
	}

	//Adding the grass
	public void grass() 
	{
		//grass
		int grassX = 0;
		int grassY = 440;
		int grassHeight = 20;
		grass = new GRect(grassX, grassY, APPLET_WIDTH, grassHeight);
		add(grass); 
		grass.setFilled(true);
		grass.setFillColor(Color. PINK);
	}

	//controlling the FLAPPY BIRD WITH UP ARROW
	public void keyPressed(KeyEvent event)
	{
		int key = event.getKeyCode();

		switch (key)
		{
		case KeyEvent.VK_UP:
			//make sure we aren't moving off screen
			if (myBird.getY() >= 0)
			{
				myBird.move(0, -20);
			}
			break;
		}
	}   
} 