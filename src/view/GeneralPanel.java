package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import utils.ResetAction;
import utils.TimeThread;

public class GeneralPanel extends JPanel {

	//dimensions of a general panel
	public static final int WIDTH          = 340;
	public static final int HEIGHT         = 300;
	
	//counts of bombs
	public static final Integer COUNTBOMBS = 75;
	
	//a counter of bombs for a check in a game 
	private Integer countBombInGame;
	
	//image for a button of reset
	private static final String PATHIMAGEGAME  = "/design/gr.png";	
	private static final String PATHIMAGEOVER = "/design/go.png";	
	
	private BufferedImage imageGame;
	private BufferedImage imageOver;
	
	//elements of an upper panel
	private JLabel  labelBombs;
	private JButton buttonReset;
	private JLabel  labelTime;
	
	//statuses of game for diferent checks
	private boolean gameRunning;
	private boolean gameWin;
	private boolean gameOver;
	
	//a separated thread for a stop-watch
	private static TimeThread timeThread;
	
	public GeneralPanel() {
		
		this.gameRunning 	 = false;
		this.gameWin 	 	 = false;
		this.gameOver 	 	 = false;
		this.countBombInGame = COUNTBOMBS;
				
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		
		try{
			imageGame = ImageIO.read(getClass().getResourceAsStream(PATHIMAGEGAME));
			imageOver = ImageIO.read(getClass().getResourceAsStream(PATHIMAGEOVER));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		add(createUpperPanel());
		
		add(new SapperPanel(this));

		
	}

	//creating of upper panel
	private JPanel createUpperPanel() {
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(WIDTH - 10, HEIGHT / 6));
		
		panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder()));
		
		Font font = new Font("Arial", Font.BOLD, 25);
		
		JPanel panelBomb = new JPanel();
		panelBomb.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 8));
		panelBomb.setBackground(Color.BLACK);
		
		JLabel labelBombs = new JLabel();
		labelBombs.setFont(font);
		labelBombs.setForeground(Color.RED);
		labelBombs.setText(COUNTBOMBS.toString());
		
		panelBomb.add(labelBombs);
				
		JButton buttonReset = new JButton();
		buttonReset.setPreferredSize(new Dimension(WIDTH / 4, HEIGHT / 8));
		buttonReset.setIcon(new ImageIcon(imageGame));
		buttonReset.setBackground(new Color(238, 238, 238));
		buttonReset.addMouseListener(new ResetAction(this));
				
		JPanel panelTime = new JPanel();
		panelTime.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 8));
		panelTime.setBackground(Color.BLACK);
		
		JLabel labelTime = new JLabel();
		labelTime.setFont(font);
		labelTime.setForeground(Color.RED);
		labelTime.setText("000");
		
		panelTime.add(labelTime);
		
		panel.add(panelBomb);
		panel.add(buttonReset);
		panel.add(panelTime);
		
		this.labelBombs  = labelBombs;
		this.buttonReset = buttonReset;
		this.labelTime   = labelTime;
		
		return panel;
	}
	
	public JLabel getLabelBombs(){
		
		return labelBombs;
		
	}

	public JButton getButtonReset(){
	
		return buttonReset;
	
	}

	public JLabel getLabelTime(){
	
		return labelTime;
	
	}
	
	public BufferedImage getImageOver(){
		
		return imageOver;
	
	}	
	
	public BufferedImage getImageGame(){
		
		return imageGame;
	
	}
	
	public boolean isGameRunning(){
		
		return gameRunning;
		
	}
	
	public boolean isGameOver() {

		return gameOver;
		
	}
	
	public Integer getCountBombInGame(){
		
		return countBombInGame;
	
	}
	
	public void setCountBombInGame(Integer newCountBomb){
		
		countBombInGame = newCountBomb;
	
	}
	
	public void resetCountBombInGame(){
		
		countBombInGame = COUNTBOMBS;
		labelBombs.setText(COUNTBOMBS.toString());
	
	}
	
	public void setGameRunning(boolean state){
		
		gameRunning = state;
		
		if(gameRunning){

			timeThread = new TimeThread(this);
			timeThread.start();
			
		} else {
			
			if(timeThread != null){
				
				timeThread.setNoBreak(false);
				
			}
		}
	}

	public void setGameWin(boolean state) {
		
		gameWin = state;
		
		if(gameWin){

			setGameRunning(false);
			GameWin gameWin = new GameWin(this);
			gameWin.setVisible(true);
						
		}	
		
	}

	public void setGameOver(boolean state) {

		gameOver = state;
		
	}

}
