package com.hoffnisgames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;




import com.hoffnisgames.entities.Entity;

import com.hoffnisgames.entities.Player;

import com.hoffnisgames.graficos.Spritesheet;
import com.hoffnisgames.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGTH;
	public static final int TILE_SIZE = 16;
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			WIDTH = map.getWidth();
			HEIGTH = map.getHeight();
			tiles = new Tile[map.getWidth()*map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++ ) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy*map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					
					
					
				}
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	
		
	public static void restart() {
		
		Game.score = 0;
		Game.player = new Player(20, 30, 16, 16,1, Game.spritesheet.getSprite(32, 0, 16, 16));
		Game.entities.clear();
		Game.entities.add(Game.player);
		
	}
	
	
	public static boolean isFreeDy(int xnext,int ynext, int width, int heigth) {
		int x1 = xnext  / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+width-1)  / TILE_SIZE;
		int y2 = (ynext) / TILE_SIZE;
		
		int x3 = xnext  / TILE_SIZE;
		int y3 = (ynext+heigth-1)/ TILE_SIZE;
		
		int x4 = (xnext+width-1) / TILE_SIZE;
		int y4 = (ynext+heigth-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static boolean isFreeDyn(int xnext,int ynext, int width, int heigth) {
		int x1 = xnext  / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+width-1)  / TILE_SIZE;
		int y2 = (ynext) / TILE_SIZE;
		
		int x3 = xnext  / TILE_SIZE;
		int y3 = (ynext+heigth-1)/ TILE_SIZE;
		
		int x4 = (xnext+width-1) / TILE_SIZE;
		int y4 = (ynext+heigth-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof Wall) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof Wall) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof Wall) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof Wall));
	}

	
	public static boolean isFree(int xnext,int ynext) {
		int x1 = xnext  / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1)  / TILE_SIZE;
		int y2 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x3 = (xnext+TILE_SIZE-1)  / TILE_SIZE;
		int y3 = ynext / TILE_SIZE;
		
		int x4 = xnext / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(String level) {
	new Game();
	return;
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		int xfinal = xstart + (Game.WIDTH >> 4) + 2;
		int yfinal = ystart + (Game.HEIGHT >> 4) + 2;
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGTH) {
					continue;
				}
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
	public static void renderMinimap() {
		for(int i = 0; i < Game.mapixel.length; i++) {
			Game.mapixel[i] = 0;
		}
		for(int xx = 0; xx < WIDTH; xx ++) {
			for(int yy = 0; yy < HEIGTH; yy++) {
				if(tiles[xx + (yy*WIDTH)] instanceof WallTile) {
					Game.mapixel[xx + (yy*WIDTH)] = 0xff0000;
				}
			}
		}
		
		int xPlayer = Game.player.getX()/16;
		int yPlayer = Game.player.getY()/16;
		
		Game.mapixel[xPlayer + (yPlayer*WIDTH)] = 0x0000ff;
	}
}
