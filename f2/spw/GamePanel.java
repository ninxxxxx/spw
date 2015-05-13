package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	private Image live;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		try{
			File liveImg = new File("f2/spw/images/heart.png");
			live = ImageIO.read(liveImg);
		}
		catch(IOException e){
			e.getMessage();
		}
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big.setColor(Color.WHITE);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		isPause(reporter.getIsRunning());
		updateLive(reporter.getLive());
		for(Sprite s : sprites){
			s.draw(big);
		}
		isOver(reporter.getGameAlive());
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}
	public void isPause(boolean p){
		if(!p){
			big.drawString(String.format("------PAUSE------"), 155, 60 );
			System.out.println("PAUSE");
		}
	}
	public void updateLive(int l){
		int x = 10;
		for(int i = 0; i < l; i++){
			big.drawImage(live, x, 20, 30, 30, null);
			x += 40;
		}
	}
	public void isOver(boolean gameAlive){
		
		if(gameAlive == false){
			System.out.println(">>>>>>>>>>>>>>>>> : " + gameAlive);
			big.clearRect(0, 0, 400, 600);	
			big.drawString(String.format("GameOver"), 170, 150);
			big.drawString(String.format("score :"  ), 160, 170);
			big.drawString(String.format("Press A to Play again"), 140, 200);

		}

	}
 
}