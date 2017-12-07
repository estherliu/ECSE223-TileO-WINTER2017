package ca.mcgill.ecse223.tileo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Tile;

public class Game_swapPositionActionCard extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -529378947471059392L;
	private Game currentgame;
	private TileOController controller;
	private StartGame parent;
	private JLabel explanation;
	private JComboBox<Integer> playerNums;
	private JButton apply;



	public Game_swapPositionActionCard (StartGame parent, Game game, TileOController controller){
		this.currentgame = game;
		this.controller = controller;
		this.parent = parent;
		initComponents();
	}
	private void initComponents(){
		setSize(300, 150);
		setTitle("Swap positions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		explanation = new JLabel("Choose player to swap positions with.");
		playerNums = new JComboBox<Integer>();
		apply = new JButton("Swap Positions");
		
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
					controller.playSwapPositionActionCard(p-1);
					parent.nextplayer();
					List<Player> allPlayers = currentgame.getPlayers();
					for (Player player : allPlayers) {
						Tile tile = player.getCurrentTile();
						int x =tile.getX();
						int y =tile.getY();
						parent.board[x][y].setBackground(parent.switchcolor(player));
					}
					dispose();
				}catch (Exception e2){
					//system.out.println("Unable to swap positions");
				}
			}
		});
	}
}
