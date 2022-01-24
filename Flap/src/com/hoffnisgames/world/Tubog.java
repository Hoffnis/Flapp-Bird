package com.hoffnisgames.world;

import com.hoffnisgames.entities.Entity;
import com.hoffnisgames.entities.Tubo;
import com.hoffnisgames.main.Game;

public class Tubog {
	
	public static int time = 0;
	
	public static int tag = 120;
	
	
	public static void tick() {
		
		time ++;
		
		if(time >= tag) {
			int altura1 = Entity.rand.nextInt(75 - 45) + 45;
			Tubo tubo1 = new Tubo(Game.WIDTH, 0, 20, altura1,1, null);
			
			
			int altura2 = Entity.rand.nextInt(60 - 22) + 22;
			Tubo tubo2 = new Tubo(Game.WIDTH, Game.HEIGHT - altura2, 20, altura2,1, null);
			
			Game.entities.add(tubo1);
			Game.entities.add(tubo2);
			time = 0;
		}
		
		ch();

		
	}
	
	public static void ch() {
		if(Game.score >= 5 && Game.score < 10 ) {
			tag = 105;
		}
		else if(Game.score >= 10 && Game.score <25) {
			tag =90;
		}
		else if(Game.score >= 25 && Game.score < 50) {
			tag =75;
		}

		else if(Game.score >= 50 && Game.score < 100) {
			tag =60;
		}

		else if(Game.score >= 100 && Game.score < 250) {
	
			tag = 40;

		}

		else if(Game.score >= 250 && Game.score < 600) {
			tag = 30;
		}

		else if(Game.score >= 600) {
			tag = 20;
		} else
			tag = 120;
	}
	
	

}
