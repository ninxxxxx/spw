package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{
	private static final int INITIAL_HP = 3;
	int step = 8;
	int hp = INITIAL_HP;
	
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
		hp--;
	}
	public boolean isAlive(){
		return hp != 0;

	}
	public void setLive(){
		if(!isAlive())
			hp = INITIAL_HP; 
	}
}
