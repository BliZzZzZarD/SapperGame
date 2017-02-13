package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.ResetAction;

//a dialog windows that appears when a victory is

public class GameWin extends JDialog{

	public GameWin(GeneralPanel generalPanel) {
		
		setSize(new Dimension(200, 150));
		setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowAdapter(){
        	
        	@Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        	
        });
        
        Font font = new Font("Arial", Font.CENTER_BASELINE, 12);
        
        JButton okButton = new JButton();
        okButton.setBackground(new Color(238, 238, 238));
        okButton.setFont(font);
        okButton.setText("OK");
        okButton.setSize(60, 20);
        okButton.setLocation(70, 65);
        okButton.addMouseListener(new ResetAction(generalPanel));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });

        JLabel label = new JLabel();
        label.setOpaque(false);
        label.setFont(font);
        label.setText("Вы одержали победу за " + generalPanel.getLabelTime().getText() +  " с");
        label.setSize(180, 30);
        label.setLocation(7, 20);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(238, 238, 238));
        panel.setLayout(null);
        panel.add(label);
        panel.add(okButton);
        setContentPane(panel);

    }
	
}
