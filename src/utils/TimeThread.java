package utils;

import view.GeneralPanel;

//a separated thread for a stop-watch

public class TimeThread extends Thread{

	private GeneralPanel generalPanel;
	private boolean noBreak;
	
	public TimeThread(GeneralPanel generalPanel) {
		
		this.generalPanel = generalPanel;
		this.noBreak = true;
		
	}

	public void run() {
        
		int timer = 0;
		
		while(isNoBreak()){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			timer++;
			generalPanel.getLabelTime().setText(String.format("%03d", timer));
			
		}
		
		generalPanel.getLabelTime().setText("000");
		
    }
	
	public boolean isNoBreak(){
		
		return noBreak;
		
	}
	
	public void setNoBreak(boolean state){
		
		noBreak = state;
		
	}
	
}
