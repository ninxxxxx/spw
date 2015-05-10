package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{
	private static final int INITIAL_LIVE = 3;
	int step = 8;
	int live = INITIAL_LIVE;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}
//i love u Aj.suthon :D
	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public void damaged(){
		live--;
	}
	public boolean isAlive(){
		return live != 0;

	}
	public void setLive(){
		if(!isAlive())
			live = INITIAL_LIVE; 
	}
	public int getLive(){
		return live;
	}
}
