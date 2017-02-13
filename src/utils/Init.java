package utils;

import cell.Cell;
import cell.CellBomb;
import cell.CellEmpty;
import cell.CellNearBomb;
import cell.CellType;
import view.SapperPanel;

//an auxiliary class for initialization of cells

public class Init {

	private Init(){}
	
	public static void initGamePanel(SapperPanel sapperPanel, int countBombs, Cell[][] mapBomb, int colCount, int rowCount){
		
		fillCellBomb(countBombs, mapBomb, colCount, rowCount, sapperPanel);
		
		fillDataCellsNear(mapBomb, sapperPanel);
		
		fillEmptyCells(mapBomb, sapperPanel);
		
		addButtonOnGamePanel(mapBomb, sapperPanel);
	}
	
	//filling of a map by cells with bombs
	
	private static void fillCellBomb(int countBombs, Cell[][] mapBomb, int colCount, int rowCount, SapperPanel sapperPanel) {

		for (int i = 0; i < countBombs; i++){
			
			//a defenition of random coordinates
			int c = 0 + (int)(Math.random() * colCount);
			int r = 0 + (int)(Math.random() * rowCount);
			
			if(mapBomb[c][r] != null){
				
				i--;
			
			//if cell don't define then bomb is planted
			} else {
				
				mapBomb[c][r] = new CellBomb(CellType.cellBomb, sapperPanel);
						
			}
		}
		
	}

	private static void fillDataCellsNear(Cell[][] mapBomb, SapperPanel sapperPanel) {
		
		for (int i = 0; i < mapBomb.length; i++){
			for (int j = 0; j < mapBomb[0].length; j++){
				
				//if cell is with a bomb then it happens a filling of cells near bombs and counts of bombs around it
				if(mapBomb[i][j] != null && mapBomb[i][j].getType() == CellType.cellBomb){
					
					/*each of eight cells around bomb is checked and a count of bombs is entered in a cell. 
					 *it's necessary for painting of image with numbers of bombs near a cell (1, 2 ... 8)
					 *each cells may have from 1 to 8 neighbouring cells with bombs
					 */
					
					if(i - 1 >= 0 && j - 1 >= 0){
						if(mapBomb[i - 1][j - 1] == null){
							mapBomb [i - 1][j - 1] = new CellNearBomb(CellType.cellNearBomb, sapperPanel);
						} else if(mapBomb[i - 1][j - 1].getType() == CellType.cellNearBomb){
							CellNearBomb cnb = (CellNearBomb)mapBomb[i - 1][j - 1];
							cnb.increaseCountBombNear();
							mapBomb[i - 1][j - 1] = cnb;
						}
					}
					
					if(j - 1 >= 0){
						if(mapBomb[i][j - 1] == null){
							mapBomb[i][j - 1] = new CellNearBomb(CellType.cellNearBomb, sapperPanel);
						} else if(mapBomb[i][j - 1].getType() == CellType.cellNearBomb){
							CellNearBomb cnb = (CellNearBomb)mapBomb[i][j - 1];
							cnb.increaseCountBombNear();
							mapBomb[i][j - 1] = cnb;
						}
					}
					
					if(i + 1 < mapBomb.length && j -1 >= 0){
						if(mapBomb[i + 1][j - 1] == null){
							mapBomb[i + 1][j - 1] = new CellNearBomb(CellType.cellNearBomb, sapperPanel);
						} else if(mapBomb[i + 1][j - 1].getType() == CellType.cellNearBomb){
							CellNearBomb cnb = (CellNearBomb)mapBomb[i + 1][j - 1];
							cnb.increaseCountBombNear();
							mapBomb[i + 1][j - 1]= cnb;
						}
					}
					
					if(i + 1 < mapBomb.length){
						if(mapBomb[i + 1][j] == null){
							mapBomb[i + 1][j] = new CellNearBomb(CellType.cellNearBomb, sapperPanel);
						} else if(mapBomb[i + 1][j].getType() == CellType.cellNearBomb){
							CellNearBomb cnb = (CellNearBomb)mapBomb[i + 1][j];
							cnb.increaseCountBombNear();
							mapBomb[i + 1][j] = cnb;
						}
					}
					
					if( i + 1 < mapBomb.length && j + 1 < mapBomb[0].length){
						if(mapBomb[i + 1][j + 1] == null){
							mapBomb[i + 1][j + 1] = new CellNearBomb(CellType.cellNearBomb, sapperPanel);
						} else if(mapBomb[i + 1][j + 1].getType() == CellType.cellNearBomb){
							CellNearBomb cnb = (CellNearBomb)mapBomb[i + 1][j + 1];
							cnb.increaseCountBombNear();
							mapBomb[i + 1][j + 1] = cnb;
						}
					}
					
					if(j + 1 < mapBomb[0].length){
						if(mapBomb[i][j + 1] == null){
							mapBomb[i][j + 1]  = new CellNearBomb(CellType.cellNearBomb, sapperPanel);
						} else if(mapBomb[i][j + 1] .getType() == CellType.cellNearBomb){
							CellNearBomb cnb = (CellNearBomb)mapBomb[i][j + 1] ;
							cnb.increaseCountBombNear();
							mapBomb[i][j + 1]  = cnb;
						}
					}
					
					if(i - 1 >= 0 && j + 1 < mapBomb[0].length){
						if(mapBomb[i - 1][j + 1] == null){
							mapBomb[i - 1][j + 1] = new CellNearBomb(CellType.cellNearBomb, sapperPanel);
						} else if(mapBomb[i - 1][j + 1].getType() == CellType.cellNearBomb){
							CellNearBomb cnb = (CellNearBomb)mapBomb[i - 1][j + 1];
							cnb.increaseCountBombNear();
							mapBomb[i - 1][j + 1] = cnb;
						}
					}
					
					if(i - 1 >= 0){
						if(mapBomb[i - 1][j] == null){
							mapBomb[i - 1][j] = new CellNearBomb(CellType.cellNearBomb, sapperPanel);
						} else if(mapBomb[i - 1][j].getType() == CellType.cellNearBomb){
							CellNearBomb cnb = (CellNearBomb)mapBomb[i - 1][j];
							cnb.increaseCountBombNear();
							mapBomb[i - 1][j] = cnb;
						}
					}
					
				}
								
			}
		}
		
	}
	
	//filling empty cells
	private static void fillEmptyCells(Cell[][] mapBomb, SapperPanel sapperPanel) {
		
		for (int i = 0; i < mapBomb.length; i++){
			for (int j = 0; j < mapBomb[0].length; j++){
				
				if(mapBomb[i][j] == null) {
					mapBomb[i][j] = new CellEmpty(CellType.cellEmpty, sapperPanel, i, j);
				}
			}
		}
		
	}
	
	//adding button-cells in a panel	
	private static void addButtonOnGamePanel(Cell[][] mapBomb, SapperPanel sapperPanel) {

		for(int i = 0; i < mapBomb[0].length; i++){
			for (int j = 0; j < mapBomb.length; j++){
				sapperPanel.add(mapBomb[j][i]);
			}
		}
		
	}
}
