package view;


import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private static final String PATHIMAGE  = "/design/spr.png";	
	private BufferedImage image;
	
	public GameFrame(String title) {
		
		super(title);
		
		try{
			image = ImageIO.read(getClass().getResourceAsStream(PATHIMAGE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        setIconImage(image);
		setContentPane(new GeneralPanel());
		
	}
	
}
