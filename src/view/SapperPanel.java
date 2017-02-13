package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import cell.Cell;
import utils.Init;

//A panel of a game field

public class SapperPanel extends JPanel{

	//dimensions of game panel
	private static final int WIDTH  = GeneralPanel.WIDTH - GeneralPanel.WIDTH / 20;
	private static final int HEIGHT = GeneralPanel.HEIGHT - GeneralPanel.HEIGHT / 4;
	
	//counts of bombs
	private static final int COUNTBOMBS = GeneralPanel.COUNTBOMBS;
	
	//counts of columns and rows on a game field
	public static final int COLCOUNT = SapperPanel.WIDTH / 16;
	public static final int ROWCOUNT = SapperPanel.HEIGHT / 16;
	
	//a general panel
	private GeneralPanel generalPanel;
	
	//a map of cells
	private Cell[][]mapBomb = new Cell[COLCOUNT][ROWCOUNT];
	
	public SapperPanel(GeneralPanel generalPanel){
		
		this.generalPanel = generalPanel;
				
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new GridLayout(ROWCOUNT, COLCOUNT));
		
		setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		
		//all logic is separated from viewing elements in a special class
		Init.initGamePanel(this, COUNTBOMBS, mapBomb, COLCOUNT, ROWCOUNT);
		
	}
	
	public Cell[][] getMapBomb(){
		
		return mapBomb;
		
	}
	
	public GeneralPanel getGeneralPanel(){
		
		return generalPanel;
		
	}
	
}

