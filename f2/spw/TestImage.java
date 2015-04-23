import java.awt.*;
import javax.swing.*;
 
 public class TestImage extends JFrame{
 	private ImageIcon img1;
 	private JLabel label1;
 	public TestImage(){
 		setLayout(new FlowLayout());
 		 img1 = new ImageIcon(getClass().getResource("/images/spaceship.png"));
 		 label1 = new JLabel(img1);
 		 add(label1); 
 	} 
 	public static void main(String[] args) {
 		TestImage gui = new TestImage();
 		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		gui.setVisible(true);
 		gui.pack();
 		gui.setTitle("Test Images");
 	}
 } 