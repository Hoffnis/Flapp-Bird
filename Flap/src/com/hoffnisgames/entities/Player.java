package com.hoffnisgames.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.Camera;
import com.hoffnisgames.world.World;

public class Player extends Entity{

	public boolean press = false;
	
	public Player(int x, int y, int width, int heigth, double speed, BufferedImage sprite) {
		super(x, y, width, heigth, speed, sprite);
	
		
	}
	public void tick() {
		
		if(!press) {
			y+=1;
		}else {
			y-=1;
		}
		
		if(y > Game.HEIGHT || y < 0) {
			World.restart();
		}
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e != this) {
				if(Entity.isCollinding(this, e)) {
					World.restart();
				}
			}
		}
	}
	
	
	
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		if(!press) {
			
			g2.rotate(Math.toRadians(10), this.getX() + width /2, this.getY() + heigth / 2);
			g2.drawImage(sprite, this.getX(), this.getY(), null);
			g2.rotate(Math.toRadians(-10), this.getX() + width /2, this.getY() + heigth / 2);
			
		}else {
			
			g2.rotate(Math.toRadians(-10), this.getX() + width /2, this.getY() + heigth / 2);
			g2.drawImage(sprite, this.getX(), this.getY(), null);
			g2.rotate(Math.toRadians(10), this.getX() + width /2, this.getY() + heigth / 2);
			
		}
	}
	
	
	
	
	
	
	


}
