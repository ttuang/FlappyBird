/*
 * Thang Tuang
 * 08/11/2014
 * Bird.java
 * making Flappy Bird for Final project
 */

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;


public class Bird extends GCompound

{
	private GOval body, wing, face, mouth, mouth1, eye;

	public void assemble()
	{	         
		drawBird();
	}

	public void drawBird()
	{
		//bg GOval
		body = new GOval(20, 40, 60, 50);
		add(body);
		body.setFilled(true);
		body.setFillColor(Color.YELLOW);

		//left GOval
		wing = new GOval(10, 55, 35, 20);
		add(wing);
		wing.setFilled(true);
		wing.setFillColor(Color.YELLOW);

		//face
		face = new GOval(45, 40, 35, 40);
		add(face);
		face.setFilled(true);
		face.setFillColor(Color.WHITE);

		//MOUTH
		mouth = new GOval(45, 60, 40, 10);
		add(mouth);
		mouth.setFilled(true);
		mouth.setFillColor(Color.RED);

		//MOUTH
		mouth1 = new GOval(45, 70, 35, 10);
		add(mouth1);
		mouth1.setFilled(true);
		mouth1.setFillColor(Color.RED);

		//EYE
		eye = new GOval(65, 50, 5, 10);
		add(eye);
		eye.setFilled(true);
		eye.setFillColor(Color.BLACK);
	}
}