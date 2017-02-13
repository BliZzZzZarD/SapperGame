package cell;

import view.SapperPanel;

//A empty cell

public class CellEmpty extends Cell{

	//coordinates of an empty cell
	private int col;
	private int row;
	
	public CellEmpty(CellType type, SapperPanel gamePanel, int col, int row) {
		
		super(type, gamePanel);
	
		this.col = col;
		this.row = row;
		
	}
	
	public int getCol(){
		
		return col;
		
	}
	
	public int getRow(){
		
		return row;
		
	}

}
