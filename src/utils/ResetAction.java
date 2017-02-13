package utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import view.GeneralPanel;
import view.SapperPanel;

//an auxiliary class-listener for button "RESET"

public class ResetAction extends MouseAdapter{
	
	private GeneralPanel generalPanel;
	
	public ResetAction(GeneralPanel generalPanel) {
		
		this.generalPanel = generalPanel;
		
	}

	@Override
	 public void mouseClicked(MouseEvent event) {
		
		if(event.getButton() == MouseEvent.BUTTON1){
			
			//dumping of statuses
			generalPanel.setGameRunning(false);
			generalPanel.setGameWin(false);
			generalPanel.setGameOver(false);
			
			//dumping of the counter of bombs for check in a game
			generalPanel.resetCountBombInGame();
				
			//removing an old game panel and setting a new one
			setNewSaperPanel();
		
		}	
				
	}

	private void setNewSaperPanel(){
		
		generalPanel.getButtonReset().setIcon(new ImageIcon(generalPanel.getImageGame()));
		generalPanel.remove(1);
		generalPanel.add(new SapperPanel(generalPanel));
		generalPanel.repaint();
		generalPanel.revalidate();
 	
	}
	
}
