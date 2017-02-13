package cell;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import view.SapperPanel;

//A empty cell near a cell with bomb

public class CellNearBomb extends Cell{

	//image for an open cell that is near a cell with a bomb
	private static final String PATHIMAGE  = "/countbombs/countBombs.png";	
	private static BufferedImage image;
	
	//a count of bombs that are near with a cell
	private Integer countBombNear = 0;
	
	public CellNearBomb(CellType type, SapperPanel gamePanel) {
		
		super(type, gamePanel);
		countBombNear += 1;
		
		try{
			image = ImageIO.read(getClass().getResourceAsStream(PATHIMAGE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void increaseCountBombNear(){
		
		countBombNear += 1;
		
	}
	
	public String getCountBombNear(){
		
		return countBombNear.toString();
		
	}
	
	public ImageIcon getImage(){
		
		return new ImageIcon(image.getSubimage(0 + 10 * (countBombNear - 1), 0, 10, 10));
		
	}
	
}
