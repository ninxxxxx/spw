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
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();	
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		gp.addMouseWheelListener(this);
		gp.addKeyListener(this);
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bulletProcess();
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	private void generateBullet(){
		Bullet b = new Bullet(v.getX() + (v.width / 2) - 2, v.getY());
		gp.sprites.add(b);
		bullets.add(b);
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
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
	void shot(KeyEvent e){
		System.out.println(e.getKeyCode());
		System.out.println(KeyEvent.VK_BACK_SPACE);
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			System.out.println("55+");
			generateBullet();
		}
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
