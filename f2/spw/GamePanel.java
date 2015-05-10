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
		int x = 0;
		for(int i = 0; i < l; i++){
			x += 42	;
			big.drawImage(live, x, 20, 40, 40, null);
		}
	}
 
}
