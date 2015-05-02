package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
public class Spread extends Sprite{
	private static final int Y_TO_DIE = 600;
	int step = new Random().nextInt(4) + 1;
	boolean alive = true;

	public Spread(int x, int y){
		super(x, y, 1, 2);
	}
	@Override
	public void draw(Graphics2D g) {	
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	public boolean isAlive(){
		return alive;
	}
	public void die(){
		visible = false;
	}
} 