package com.hoffnisgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hoffnisgames.main.Game;

public class Tubo extends Entity{
	
	
	public Tubo(double x, double y, int width, int heigth, double speed, BufferedImage sprite) {
		super(x, y, width, heigth, speed, sprite);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		x--;
		if(x+width <= 0){
			Game.entities.remove(this);
			Game.score+=0.5;
			return;
			}
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, width, heigth);
	}

}
