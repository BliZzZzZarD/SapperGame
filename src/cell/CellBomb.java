package cell;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import view.SapperPanel;

//A cell with a bomb

public class CellBomb extends Cell{

	//image for an open cell with a bomb
	private static final String PATHIMAGE  = "/bomb/bomb.png";	
	private BufferedImage image;
	
	//boolean variable for definition whether a cell with a bomb is neutralized or not
	private boolean neutralized;
	
	public CellBomb(CellType type, SapperPanel gamePanel) {
		
		super(type, gamePanel);
		this.neutralized = false;
		
		try{
			image = ImageIO.read(getClass().getResourceAsStream(PATHIMAGE));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public ImageIcon getImage(){
		
		return new ImageIcon(image);
		
	}
	
	public boolean isNeutralized(){
		
		return neutralized;
		
	}
	
	public void setNeutralized(boolean state){
		
		neutralized = state;
		
	}

}
