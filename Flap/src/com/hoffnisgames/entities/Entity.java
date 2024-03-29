package com.hoffnisgames.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.Camera;
import com.hoffnisgames.world.Node;
import com.hoffnisgames.world.Vector2i;
import com.hoffnisgames.world.World;

public class Entity {
	
	protected double x;
	protected double y;
	protected int width;
	protected int heigth;
	protected double speed;
	public int depth;
	
	protected List<Node> path;
	
	protected BufferedImage sprite;
	public static BufferedImage COMIDA = Game.spritesheet.getSprite(0, 32, 16, 16);
	public static BufferedImage ENEMY = Game.spritesheet.getSprite(0, 16, 16, 16);
	public static BufferedImage EN = Game.spritesheet.getSprite(16, 16, 16, 16);
	
	public static Random rand =  new Random();;
	
	public int maskx, masky, mwidth, mheigth;
	
	public Entity(double x, double y, int width, int heigth, double speed, BufferedImage sprite){
		
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.heigth = heigth;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheigth = heigth;
	}
	
public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
		
		@Override	
		public int compare(Entity n0, Entity n1) {
			if(n1.depth < n0.depth) 
				return + 1;
			if(n1.depth >n0.depth)
			return -1;
			return 0;
		}
	};
	
	public void setX(int newX) {
		this.x = newX;
		
	}
	
	
	public void setY(int newY) {
		
		this.y = newY;
		
	}
	
	public int getX(){
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
		
	}
	
	public int getWidth() {
		return this.width;
		
	}
	
	public int getHeigth() {
		
		return this.heigth;
	}
	
	public void tick() {
		
		
	}
	

	public void updatecamera() {
	Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.WIDTH*16 - Game.WIDTH) ;
	Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.HEIGTH*16 - Game.HEIGHT) ;
	}
	
	public double calculateDistance(int x1, int y1, int x2, int y2) {
		
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	
	public void followPath(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() -1 ).tile;
				
				if(x< target.x * 16) {
					x++;
				}else if(x > target.x *16) {
					x--;
				}
				
				if(y < target.y * 16) {
					y++;
				}
				else if(y> target.y *16) {
					y--;
				}
				
				if(x == target.x* 16 && y == target.y * 16) {
					path.remove(path.size()-1);
				}
			}
		}
	}
	
	public static boolean isCollinding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mwidth, e1.mheigth);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mwidth, e2.mheigth);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		
	}


}
