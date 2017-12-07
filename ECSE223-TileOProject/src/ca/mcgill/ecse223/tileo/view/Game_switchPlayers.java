package ca.mcgill.ecse223.tileo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Player;

public class Game_switchPlayers extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7129874070357101327L;

	private JLabel explanation;
	private JComboBox<Integer> playerNums;
	private JButton apply;
	private Game currentgame;
	private	StartGame parent;
	private TileOController controller;
	
	public Game_switchPlayers(StartGame parent, Game game, TileOController controller){
		this.currentgame = game;
		this.controller = controller;
		this.parent = parent;
		initComponents();
	}
	
	public void initComponents(){
		setSize(300, 150);
		setTitle("Switch player turns");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		explanation = new JLabel("Choose player to switch turns with.");
		playerNums = new JComboBox<Integer>();
		apply = new JButton("Switch turns");
		
		for (int i=0 ; i<currentgame.getPlayers().size();i++){
			if (i != currentgame.getCurrentPlayer().getNumber()){
				playerNums.addItem(i+1);
			}
		}
		GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
		    layout.setHorizontalGroup(
	    		layout.createParallelGroup()
	    		.addComponent(explanation)
	    		.addComponent(playerNums)
	    		.addComponent(apply)
	    		);
	    
	    layout.setVerticalGroup(
	    		layout.createSequentialGroup()
	    		.addComponent(explanation)
	    		.addComponent(playerNums)
	    		.addComponent(apply)
	    		);
		apply.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					int p = (int) playerNums.getSelectedItem();
					controller.playSwitchPlayersActionCard(p-1);
					parent.nextplayer();
					dispose();
				}catch (Exception e2){
					//system.out.println("Unable to switch turns");
				}
			}
		});
	}
}
