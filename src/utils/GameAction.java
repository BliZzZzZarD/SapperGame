package utils;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import cell.Cell;
import cell.CellBomb;
import cell.CellEmpty;
import cell.CellNearBomb;
import cell.CellType;
import view.GeneralPanel;
import view.SapperPanel;

//an auxiliary class-listener for opening cells of game plane

public class GameAction extends MouseAdapter{

	private Cell cell;
	
	public GameAction(Cell cell){
		
		this.cell = cell;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		
		if(event.getButton() == MouseEvent.BUTTON1){
			
			if(!cell.getGamePanel().getGeneralPanel().isGameRunning() &&
					!cell.getGamePanel().getGeneralPanel().isGameOver()){
				
				cell.getGamePanel().getGeneralPanel().setGameRunning(true);
				
			}
				
			
			if(cell.getGamePanel().getGeneralPanel().isGameRunning()){
				
				openCell();
				checkNeutralizedBomb();
				
			}
		}	
	}
	
	//opening cells
	private void openCell(){
		
		if(cell.isStateClose()){
			
			setOpenView();
					
			if (cell.getType().equals(CellType.cellBomb)){
			
				cell.setBackground(Color.RED);
				openBomb();
				openAllCellBomb();
				
				if(cell.getGamePanel().getGeneralPanel().isGameRunning())
					cell.getGamePanel().getGeneralPanel().setGameRunning(false);
					cell.getGamePanel().getGeneralPanel().setGameOver(true);
					cell.getGamePanel().getGeneralPanel().getButtonReset().setIcon(new ImageIcon(
							cell.getGamePanel().getGeneralPanel().getImageOver()));
			
			} else if (cell.getType().equals(CellType.cellNearBomb)){
			
				openCellNearBomb();
				
						
			} else if (cell.getType().equals(CellType.cellEmpty)){
			
				openCellEmpty();
						
			}
		}
		
		cell.setStateClose(false);
		
	}
	
	//opening a cell with a bomb
	
	private void openBomb(){
		
		CellBomb cb = (CellBomb) cell;
		cell.setIcon(cb.getImage());
		
	}
	
	private void openBomb(Cell cell){
		
		CellBomb cb = (CellBomb) cell;
		cell.setIcon(cb.getImage());
		
	}
	
	//opening a cell near a bomb
	
	private void openCellNearBomb(){
		
		CellNearBomb cnb = (CellNearBomb) cell;
		cell.setIcon(cnb.getImage());
		
	}
	
	
	private void openCellNearBomb(Cell cell){
		
		CellNearBomb cnb = (CellNearBomb) cell;
		cell.setIcon(cnb.getImage());
		
	}
	
	//open an empty cell
	
	private void openCellEmpty(){
		
		CellEmpty ce = (CellEmpty) cell;
		Cell[][] mapBomb = cell.getGamePanel().getMapBomb();
		
		openEmtyArea(mapBomb, ce.getCol(), ce.getRow());
		checkNeutralizedBomb();
		
	}
	
	//open an area with empty cells
	
	private void openEmtyArea(Cell[][] mapBomb, int col, int row){
		
			if(mapBomb[col][row].isStateClose()){
					
				if(mapBomb[col][row].getType().equals(CellType.cellEmpty)){
					setOpenView(mapBomb[col][row]);
					
					if(col - 1 >= 0 && row - 1 >= 0){
						openEmtyArea(mapBomb, col - 1, row - 1);
					}
					
					if(row - 1 >= 0){
						openEmtyArea(mapBomb, col, row - 1);
					}
					
					if(col + 1 < SapperPanel.COLCOUNT && row - 1 >= 0){
						openEmtyArea(mapBomb, col + 1, row - 1);
					}
					
					if(col + 1 < SapperPanel.COLCOUNT){
						openEmtyArea(mapBomb, col + 1, row);
					}
					
					if(col + 1 < SapperPanel.COLCOUNT && row + 1 < SapperPanel.ROWCOUNT){
						openEmtyArea(mapBomb, col + 1, row + 1);
					}
					
					if(row + 1 < SapperPanel.ROWCOUNT){
						openEmtyArea(mapBomb, col, row + 1);
					}
					
					if(col - 1 >= 0 && row + 1 < SapperPanel.ROWCOUNT){
						openEmtyArea(mapBomb, col - 1, row + 1);
					}
					
					if(col - 1 >= 0){
						openEmtyArea(mapBomb, col - 1, row);
					}
					
				} else if(mapBomb[col][row].getType().equals(CellType.cellNearBomb)){
					setOpenView(mapBomb[col][row]);
					openCellNearBomb(mapBomb[col][row]);
				}
				
			}	
			
			
		
	}
	
	//setting of an open cell
	
	private void setOpenView(){
		
		cell.setBorderPainted(false);
		cell.setFocusPainted(false);
		
	}
	
	private void setOpenView(Cell cell){
		
		cell.setBorderPainted(false);
		cell.setFocusPainted(false);
		cell.setStateClose(false);
		
	}
	
	//opening cells with bombs if game is over
	
	private void openAllCellBomb(){
		
		Cell[][] mapBomb = cell.getGamePanel().getMapBomb();
		
		for(int i = 0; i < mapBomb[0].length; i++){
			for (int j = 0; j < mapBomb.length; j++){
				
				if(mapBomb[j][i].isStateClose()){
					
					if (mapBomb[j][i].getType().equals(CellType.cellBomb)){
					
						setOpenView(mapBomb[j][i]);						
						openBomb(mapBomb[j][i]);
						mapBomb[j][i].setStateClose(false);
					
					}
				
				}	
			}
		}
		
	}
	
	//a check of neutralization of a bomb. if cells with a bomb are opened then a bomb is "neutralized".
	
	private void checkNeutralizedBomb(){
		
		Cell[][] mapBomb = cell.getGamePanel().getMapBomb();
		
		for (int i = 0; i < mapBomb.length; i++){
			for (int j = 0; j < mapBomb[0].length; j++){
				
				int checkNeutralizedBomb = 0;
				
				if(mapBomb[i][j].getType() == CellType.cellBomb){
					
					CellBomb cb = (CellBomb) mapBomb[i][j];
					
					if(!cb.isNeutralized()){
						
						if(i - 1 >= 0 && j - 1 >= 0){
							
							if(mapBomb[i - 1][j - 1].getType() == CellType.cellBomb || 
									(mapBomb[i - 1][j - 1].getType() == CellType.cellNearBomb && 
									!mapBomb[i - 1][j - 1].isStateClose())){
								checkNeutralizedBomb++;
							}
						}
						
						if(j - 1 >= 0){
							
							if(mapBomb[i][j - 1].getType() == CellType.cellBomb || 
									(mapBomb[i][j - 1].getType() == CellType.cellNearBomb && 
									!mapBomb[i][j - 1].isStateClose())){
								checkNeutralizedBomb++;
							}

						}
						
						if(i + 1 < mapBomb.length && j -1 >= 0){
							
							if(mapBomb[i + 1][j - 1].getType() == CellType.cellBomb || 
									(mapBomb[i + 1][j - 1].getType() == CellType.cellNearBomb && 
									!mapBomb[i + 1][j - 1].isStateClose())){
								checkNeutralizedBomb++;
							}

						}
						
						if(i + 1 < mapBomb.length){
							
							if(mapBomb[i + 1][j].getType() == CellType.cellBomb || 
									(mapBomb[i + 1][j].getType() == CellType.cellNearBomb && 
									!mapBomb[i + 1][j].isStateClose())){
								checkNeutralizedBomb++;
							}

						}
						
						if( i + 1 < mapBomb.length && j + 1 < mapBomb[0].length){
							
							if(mapBomb[i + 1][j + 1].getType() == CellType.cellBomb || 
									(mapBomb[i + 1][j + 1].getType() == CellType.cellNearBomb && 
									!mapBomb[i + 1][j + 1].isStateClose())){
								checkNeutralizedBomb++;
							}
							
						}
						
						if(j + 1 < mapBomb[0].length){
							
							if(mapBomb[i][j + 1].getType() == CellType.cellBomb || 
									(mapBomb[i][j + 1].getType() == CellType.cellNearBomb && 
									!mapBomb[i][j + 1].isStateClose())){
								checkNeutralizedBomb++;
							}

						}
						
						if(i - 1 >= 0 && j + 1 < mapBomb[0].length){
							
							if(mapBomb[i - 1][j + 1].getType() == CellType.cellBomb || 
									(mapBomb[i - 1][j + 1].getType() == CellType.cellNearBomb && 
									!mapBomb[i - 1][j + 1].isStateClose())){
								checkNeutralizedBomb++;
							}

						}
						
						if(i - 1 >= 0){
							
							if(mapBomb[i - 1][j].getType() == CellType.cellBomb || 
									(mapBomb[i - 1][j].getType() == CellType.cellNearBomb && 
									!mapBomb[i - 1][j].isStateClose())){
								checkNeutralizedBomb++;
							}

						}
						
						if(i != 0 || j != 0 || i != mapBomb.length - 1 || j != mapBomb[0].length - 1){
							
							if(checkNeutralizedBomb == 8){
								
								neutralizedBomb(cell.getGamePanel().getGeneralPanel());
								cb.setNeutralized(true);
								
							} 
						}
						
						if(i > 0 && i < mapBomb.length - 1 && j == 0 ||
								i > 0 && i < mapBomb.length - 1 && j == mapBomb[0].length - 1 ||
								j > 0 && j < mapBomb[0].length - 1 && i == 0 || 
								j > 0 && j < mapBomb[0].length - 1 && i == mapBomb.length - 1){
							
							if(checkNeutralizedBomb == 5){
								
								neutralizedBomb(cell.getGamePanel().getGeneralPanel());
								cb.setNeutralized(true);
								
							} 
						}
						
						if(i == 0 && j == 0 ||
								i == mapBomb.length - 1 && j == mapBomb[0].length - 1 ||
								i == 0 && j == mapBomb[0].length - 1 ||
								i == mapBomb.length - 1 && j == 0){
							
							if(checkNeutralizedBomb == 3){
								
								neutralizedBomb(cell.getGamePanel().getGeneralPanel());
								cb.setNeutralized(true);
								
							} 
						}
						
					}
				}
			}	
		}
	}
	
	//if cells with a bomb are opened then a bomb is "neutralized". Variable "countBombInGame" decreases
	
	private void neutralizedBomb(GeneralPanel generalPanel) {
		
		Integer newCountBomb = generalPanel.getCountBombInGame() - 1;
		
		generalPanel.setCountBombInGame(newCountBomb);
		generalPanel.getLabelBombs().setText(newCountBomb.toString());
		
		if(newCountBomb == 0){
			
			generalPanel.setGameWin(true);
			
		}
		
	}
	
}
