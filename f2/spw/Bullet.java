package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 0;
	public static final int WIDTH = 2;
	
	private int step = 15;
	private boolean alive = true;
	private boolean visible = true;
	
	public Bullet(int x, int y) {
		super(x, y, WIDTH, 5);
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		y -= step;
	}
	public boolean isAlive(){
		return alive;
	}
	public void die(){
		this.visible = false;
	}
}