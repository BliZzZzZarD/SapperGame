package cell;

import java.awt.Color;

import javax.swing.JButton;

import utils.GameAction;
import view.SapperPanel;

//A Common parent class for game cell

public abstract class Cell extends JButton{
	
	//type of cell
	private CellType type;
	
	//a game panel
	private SapperPanel gamePanel;
	
	//boolean variable for definition whether a cell is open or not
	private boolean stateClose;	
	
	public Cell(CellType type, SapperPanel gamePanel){
		
		this.type = type;
		this.gamePanel = gamePanel;
		this.stateClose = true;
		
		setBackground(new Color(238, 238, 238));
		addMouseListener(new GameAction(this));
		
	}
	
	public CellType getType(){
		
		return type;
		
	}
	
	public SapperPanel getGamePanel(){
		
		return gamePanel;
		
	}	
	
	public boolean isStateClose(){
		
		return stateClose;
		
	}
	
	public void setStateClose(boolean state){
		
		stateClose = state;
		
	}
	
}
