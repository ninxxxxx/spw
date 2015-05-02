package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.Timer;

public class GameEngine implements KeyListener,MouseWheelListener, GameReporter{
	GamePanel gp;
	private boolean gameAlive;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Spread> spreads = new ArrayList<Spread>();	
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gameAlive = true;
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		gp.addMouseWheelListener(this);
		gp.addKeyListener(this);
		timer = new Timer(25, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bulletProcess();
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		score = 0;
		gp.sprites.clear();
		v.setLive();
		gp.sprites.add(v);
		enemies.clear();
		bullets.clear();
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	private void generateBullet(){
		Bullet b1 = new Bullet(v.getX(), v.getY());
		Bullet b2 = new Bullet(v.getX() + (v.width) - Bullet.WIDTH, v.getY());
		gp.sprites.add(b1);
		bullets.add(b1);
		gp.sprites.add(b2);
		bullets.add(b2);
	}
	private void generateSpread(){
		Spread sp = new Spread((int)(Math.random()*390), 30);
		gp.sprites.add(sp);
		spreads.add(sp);

	}
	private void bulletProcess(){
		Iterator<Bullet> b_it = bullets.iterator();
		while(b_it.hasNext()){
			Bullet b = b_it.next();
			b.proceed();
			if(!b.isAlive()){
				b_it.remove();
				gp.sprites.remove(b);
			}
		}
	}
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
			generateSpread();
		}
		for(Bullet b : bullets){
			b.proceed();
		}
		Iterator<Spread> sp_iter = spreads.iterator(); 
		while(sp_iter.hasNext()){
			Spread sp = sp_iter.next();
			sp.proceed();
			if(!sp.isAlive()){
				sp_iter.remove();
				gp.sprites.remove(sp);
			}
		}
		Rectangle2D.Double br;
		Rectangle2D.Double er;
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			er = e.getRectangle();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
			}
			Iterator<Bullet> b_iter = bullets.iterator();
			while(b_iter.hasNext()){
				Bullet b = b_iter.next();
				br  = b.getRectangle();
				if(br.intersects(er)){
					score += 100;
					b_iter.remove();
					gp.sprites.remove(b);
					e_iter.remove();
					gp.sprites.remove(e);
				}

			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				v.damaged();
				if(!v.isAlive()){
					die();
					return;
				}
			}
				/*br = b.getRectangle();
				if(er.intersects(br)){
					e.die();
				}
			}*/
		}
	}
	
	public void die(){
		gameAlive = false;
		timer.stop();
	}
	void shot(KeyEvent e){
		System.out.println(e.getKeyCode());
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_SPACE : generateBullet(); break;
			case KeyEvent.VK_A : if(!gameAlive) start(); break; 
		} 
		/*if(e.getKeyCode() == KeyEvent.VK_SPACE){
			System.out.println("55+");
			generateBullet();
		}*/
	}
	void controlVehicle(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		if(notches < 1){
			v.move(-1);
		}
		else{
			v.move(1);
		}
	}


	public long getScore(){
		return score;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		controlVehicle(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		shot(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//do nothing		
	}
}
