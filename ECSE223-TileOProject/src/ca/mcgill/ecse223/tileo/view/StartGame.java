package ca.mcgill.ecse223.tileo.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

//import com.sun.org.apache.bcel.internal.generic.GOTO;
//import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import ca.mcgill.ecse223.tileo.application.Main;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.controller.TileOController.Mode;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.WinTile;

import javax.swing.JLabel;

import java.awt.Font;



public class StartGame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5119423141014850078L;
	
	private JPanel frame;
	private LinkedList<Tile> tiles;
	private List<Player> players;
	private int[] numRolled = {0};
	public JButton board[][] = new JButton[15][13];
	private TileOController controller = new TileOController();
	public Game currentgame = Main.getTileO().getCurrentGame();
	private JButton btnTakePlayersTurn;
	private LinkedList<JLabel> lbl = new LinkedList<JLabel>();
	/**
	 * Create the application.
	 * @param game 
	 */
	public StartGame(Game game) {
		try {
			////system.out.println(game);
			controller.startGame(game);
			
			setBounds(100, 100, 657, 675);
			initialize(game);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Game game) {
		currentgame = game;
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setLayout(null);
		setContentPane(frame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel colorset = new JLabel("pl: red  p2: blue  p3: green  p4: yellow");
		colorset.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 25));
		colorset.setBounds(10, 525, 401, 47);
		frame.add(colorset);
		
		
		btnTakePlayersTurn = new JButton("Take Player " + ((game.getCurrentPlayer().getNumber()) + 1) + "'s Turn");
		btnTakePlayersTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
			
				if (controller.mode!=Mode.Roll) {
					Exceptions frame = new Exceptions("wait a sec computer is not ready yet");
					frame.setVisible(true);
					controller.mode=Mode.Roll;
					return;
				}
				btnTakePlayersTurn.setEnabled(false);
				moveplayer();
			
			}
		});
		btnTakePlayersTurn.setBounds(226, 573, 241, 46);
		frame.add(btnTakePlayersTurn);
		
		JButton btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.save();
			}
		});
		btnSaveGame.setBounds(509, 582, 117, 29);
		frame.add(btnSaveGame);
		
		players = game.getPlayers();
		
		for (Connection connection : game.getConnections()) {
			Tile tile1 = connection.getTile(0);
			Tile tile2 = connection.getTile(1);
			if (tile1.getX() == tile2.getX()) {
				JLabel connect = new JLabel();
				connect.setBounds(22+40*tile1.getX(), 40+40*tile1.getY(), 2, 10);
				connect.setBackground(Color.BLACK);
				connect.setOpaque(true);
				frame.add(connect);
				////system.out.println(connect);
				lbl.add(connect);
				frame.repaint();
			} else {
				
				JLabel connect = new JLabel();				
				connect.setBounds(40+40*tile1.getX(), 23+40*tile1.getY(), 10, 2);
				connect.setBackground(Color.BLACK);
				connect.setOpaque(true);
				////system.out.println(connect.isDisplayable());
				frame.add(connect);
				////system.out.println(connect);
				lbl.add(connect);
				frame.repaint();
			}
		}
		
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			setPlayerColor(player,i);
			////system.out.println("this time" +i);
			Tile startTile = player.getStartingTile();
			int x = startTile.getX();
			int y = startTile.getY();
			board[x][y] = new JButton();
			board[x][y].setBounds(10+x*40, 10+y*40, 30, 30);
			board[x][y].setMargin(new Insets(0, 0, 0, 0));
			board[x][y].setBackground(switchcolor(player));
			board[x][y].setOpaque(true);
			frame.add(board[x][y]);
			frame.repaint();
		}
		
		for (Tile tile : game.getTiles()) {
			int x = tile.getX();
			int y = tile.getY();
			if (board[x][y] == null) {
				String text = "";
				if (tile instanceof ActionTile) {
					ActionTile actionTile = (ActionTile) tile;
				//	text = "x^" + actionTile.getInactivityPeriod();
				}
				board[x][y] = new JButton(text);
				
				////system.out.println("x="+x);
				////system.out.println("y="+y+"\n");
				board[x][y].setBounds(10+x*40, 10+y*40, 30, 30);
				board[x][y].setMargin(new Insets(0, 0, 0, 0));
				////system.out.println("board" + board[x][y]);
				board[x][y].setBackground(Color.WHITE);
				board[x][y].setOpaque(true);
				frame.add(board[x][y]);
			}
		}
	
	}
	
	
	
	
	
	private void moveplayer() {
		System.out.println("moveplayer()");
		findPossibleX(true);
				
	
        
		/*if (nTile.getClass().equals(NormalTile.class)) {
			return;
		}*/
	}


	private void findPossibleX(boolean set) {
		System.out.println("findpossiblex "+set);
		tiles = new LinkedList<Tile>();
		
		int dx = currentgame.getCurrentPlayer().getCurrentTile().getX();
		int dy = currentgame.getCurrentPlayer().getCurrentTile().getY();
		//controller.shuffle(currentgame);
		if (set) {
			
			controller.rollDie(tiles, numRolled,currentgame,0);
			LinkedList<Tile> tempTiles=tiles;
			
			for(Player player : players){
				for(Tile tile : tempTiles){
					if(player.getCurrentTile()==tile){
						tiles.remove(tile);
					}
				}
			}
			if (tiles.size()!=0) {
				JOptionPane.showMessageDialog(null, "You rolled an " + numRolled[0] + " and your possible final spots are shown with X's! Please pick the one you wish to land on.");
			}else {
				JOptionPane.showMessageDialog(null, "no moves are available");
				controller.mode = Mode.Roll;
				nextplayer();
				return;
				
			}
			
			for (Tile tile : tiles) {
				int x = tile.getX();
				int y = tile.getY();
				ActionListener a;
				if (board[x][y].getBackground().equals(Color.WHITE)) {
					a=new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							controller.doLandTile(tile);
							
						//	board[x][y].setText("P" + (currentgame.getCurrentPlayer().getNumber() + 1));
							board[x][y].setBackground(switchcolor(currentgame.getCurrentPlayer()));
							board[x][y].setOpaque(true);
							board[dx][dy].setBackground(Color.WHITE);
							checktile();
						
							cleanX(true);
						   
							repaint();
							
						};
					};
				}
				else {
					a=new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Exceptions frame = new Exceptions("you are not allowed to land on a player, you lose your turn");
							frame.setVisible(true);
							nextplayer();
						}
					};
				}
				
				
				
				board[x][y].setText("X");	
				board[x][y].setOpaque(true);
				//system.out.println("checking");		
				board[x][y].addActionListener(a);			
			}
		/*	String a="";
			for (final Tile tile : tiles) {	
				int x = tile.getX();
				int y = tile.getY();
				a=a+board[x][y].getText();	
			}
			if (a.equals("")){
				btnTakePlayersTurn.setEnabled(true);
				btnTakePlayersTurn.setText("Invalid roll.Try again.");
			}*/
		} 

	}	
	
	
	
	private void cleanX(boolean set) {
		System.out.println("cleanx "+set);
		tiles = new LinkedList<Tile>();
		//system.out.println("here?");
		//system.out.println(tiles + "\n");	
		if (set) {
			for (JButton[] btn : board) {
				for (JButton btnn : btn) {
					try {
						if (btnn.getText().equals("X")) {
							btnn.setText("");
							for (ActionListener list : btnn.getActionListeners()) {
								btnn.removeActionListener(list);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}
		
		}

	}	
	
	public void nextplayer(){
	System.out.println("nextplayer");
	  controller.determineNextPlayer();
	  btnTakePlayersTurn.setEnabled(true);
	  btnTakePlayersTurn.setText("Take Player " + ((currentgame.getCurrentPlayer().getNumber()) + 1) + "'s Turn");
	  update();
}
	
	
	
	private void checktile()  {
		System.out.println("checktile");
        Main.getTileO().setCurrentGame(currentgame);
		Tile currenttile = currentgame.getCurrentPlayer().getCurrentTile();
		Player currentplayer = currentgame.getCurrentPlayer();
		
		if (currenttile.getClass().equals(NormalTile.class)) {	
			nextplayer();
			repaint();
			controller.mode=Mode.Roll;
			return;
		}
		else if (currenttile.getClass().equals(WinTile.class)) {
			if (currentgame.getWinTile().equals(currenttile)) {
				dispose();
				Game_won frame = new Game_won();
				frame.setVisible(true);
			}
			else {
				nextplayer();
				repaint();
				return;
			}
		}
		else if (currenttile.getClass().equals(ActionTile.class)) {
			controller.mode = Mode.ActionCard;
			ActionCard picked = currentgame.getDeck().getCurrentCard();
			switch (picked.getInstructions()) {
			case "Show Action Tiles":
				//system.out.println("sat");
				Game_showActionTilesActionCard showActionTiles= new Game_showActionTilesActionCard(this,currentgame);
				currentgame.setMode(Game.Mode.GAME_SHOWACTIONTILESACTIONCARD);
				showActionTiles.setVisible(true);
				this.setVisible(false);
				controller.shuffle(currentgame);
				return;
			case "Move Win Tile":
				//system.out.println("mwt");
				Game_MoveWinTile moveWinTile = new Game_MoveWinTile(this);				
				currentgame.setMode(Game.Mode.GAME_MOVEWINTILEACTIONCARD);
				this.setVisible(false);
				moveWinTile.setVisible(true);
				nextplayer();
				controller.shuffle(currentgame);
				return;
			case "Lose Random Turns":
				//system.out.println("lrt");
				JOptionPane.showMessageDialog(null, "all players lose random number of times.");								
				controller.playLoseTurnRandomActionCard();
				nextplayer();
				repaint();	
				controller.shuffle(currentgame);
				return;
			case "Connect Tiles":
			
				Game_connectTilesActionCard connectTilesActionCard = new Game_connectTilesActionCard(this,currentgame);
				currentgame.setMode(Game.Mode.GAME_CONNECTTILESACTIONCARD);
				connectTilesActionCard.setVisible(true);
				this.setVisible(false);	
				
				repaint();
				controller.shuffle(currentgame);
				return;
			case "Lose Turn":
				//system.out.println("lt");
				currentgame.setMode(Game.Mode.GAME_LOSETURNACTIONCARD);
				Game_loseTurnActionCard frame2 = new Game_loseTurnActionCard(this,currentgame);	
				controller.playLoseTurnActionCard(currentplayer);
				frame2.setVisible(true);
				this.setVisible(false);					   
				repaint();
				controller.shuffle(currentgame);
							
				return;
			case "Remove Connection":
				//system.out.println("rt");
				Game_removeConnectionActionCard framee = new Game_removeConnectionActionCard(this,currentgame);
				currentgame.setMode(Game.Mode.GAME_REMOVECONNECTIONACTIONCARD);
				framee.setVisible(true);
				this.setVisible(false);
				controller.shuffle(currentgame);
				return;
			case "Teleport":
				
				currentgame.setMode(Game.Mode.GAME_TELEPORTACTIONCARD);
				controller.shuffle(currentgame);
				enableTele();
				return;
			case "Roll Again":
				//system.out.println("ra");
				currentgame.setMode(Game.Mode.GAME_ROLLDIEACTIONCARD);
				try {
					controller.playRollDieActionCard(tiles);
				} catch (InvalidInputException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "You can roll again.");
				rollagain();
				controller.shuffle(currentgame);
				
				
				return;
			case "Change Tile":
				//system.out.println("ct");
				currentgame.setMode(Game.Mode.GAME_CHANGETILEACTIONCARD);
				List<Tile> tiles = currentgame.getTiles();
				for (int i = 0; i < tiles.size(); i++) {
					Tile oneTile = tiles.get(i);
					//system.out.println(oneTile.getClass());
					board[oneTile.getX()][oneTile.getY()].setText("" + i + "");
				}
				JOptionPane.showMessageDialog(null, "You can change any tile to an action tile.\n" + "Please choose the tile and then the inactivity period you wish to apply to it");
				int tileNum = Integer.parseInt(JOptionPane.showInputDialog("Please choose the tile by number"));
				while (tileNum < 0 || tileNum >= tiles.size()) {
					tileNum = Integer.parseInt(JOptionPane.showInputDialog("Please choose the tile by number"));
				}
				int inactivityPer = Integer.parseInt(JOptionPane.showInputDialog("Please choose the inactivity period"));
				controller.playChangeTileActionCard(tiles.get(tileNum), inactivityPer);
				for (int i = 0; i < tiles.size(); i++) {
					Tile oneTile = tiles.get(i);
					//system.out.println(oneTile.getClass());
					board[oneTile.getX()][oneTile.getY()].setText("");
				}
				nextplayer();
				controller.shuffle(currentgame);
				return;
			case "Switch Player Turns":
				//system.out.println("spt");
				JOptionPane.showMessageDialog(null, "You drew a switch players action card!");
				Game_switchPlayers sw = new Game_switchPlayers(this,currentgame,controller);
				currentgame.setMode(Game.Mode.GAME_SWITCHPLAYERSACTIONCARD);
				sw.setVisible(true);
				controller.shuffle(currentgame);
				return;
			case "Swap Position":
				//system.out.println("sp");
				JOptionPane.showMessageDialog(null, "You drew a swap position action card!");
				Game_swapPositionActionCard sp = new Game_swapPositionActionCard(this,currentgame,controller);
				currentgame.setMode(Game.Mode.GAME_SWAPPOSITIONACTIONCARD);
				sp.setVisible(true);
				controller.shuffle(currentgame);
				return;
				
			default:
				break;
			}
		}else {
			return;
		}
		
	}

	
	


java.awt.Color switchcolor(Player player){
	System.out.println("switchlocor "+"player "+player.getColor());
	java.awt.Color co=new java.awt.Color(0);
	if(player.getColor().equals(Player.Color.RED))
		co=Color.RED;
	else if(player.getColor().equals(Player.Color.BLUE))
		co=Color.BLUE;
	else if(player.getColor().equals(Player.Color.GREEN))
		co=Color.GREEN;
	else if(player.getColor().equals(Player.Color.YELLOW))
		co=Color.YELLOW;
	else;
		//system.out.println("no color");
	return co;
	}



private void setPlayerColor(Player player,int i){
	switch(i){
	case 0:
		player.setColor(Player.Color.RED);
		break;
	case 1:
		player.setColor(Player.Color.BLUE);
		break;
	case 2:
		player.setColor(Player.Color.GREEN);
		break;
	case 3:
		player.setColor(Player.Color.YELLOW);
		break;
	}
	return;
}


public void update() {
	while (!lbl.isEmpty()) {
		lbl.remove().setVisible(false);
	}
	for (Connection connection : currentgame.getConnections()) {
		Tile tile1 = connection.getTile(0);
		Tile tile2 = connection.getTile(1);
		if (tile1.getX() == tile2.getX()) {
			JLabel connect = new JLabel();
			connect.setBounds(22 + 40 * tile1.getX(), 40 + 40 * tile1.getY(), 2, 10);
			connect.setBackground(Color.BLACK);
			connect.setOpaque(true);
			frame.add(connect);
			lbl.add(connect);
			////system.out.println(connect);
			repaint();
		} else {

			JLabel connect = new JLabel();
			connect.setBounds(40 + 40 * tile1.getX(), 23 + 40 * tile1.getY(), 10, 2);
			connect.setBackground(Color.BLACK);
			connect.setOpaque(true);
			frame.add(connect);
			////system.out.println(connect);
			lbl.add(connect);
			repaint();
		}
	}
	  for (Tile tile : currentgame.getTiles()) {
		   int x = tile.getX();
		   int y = tile.getY();		  
		   board[x][y].setEnabled(true);
	  }
}





public TileOController getController(){
	return controller;
}




private void rollagain() {
	  controller.mode = Mode.Roll;
	  btnTakePlayersTurn.setEnabled(true);
	  btnTakePlayersTurn.setText("Take Player " + ((currentgame.getCurrentPlayer().getNumber()) + 1) + "'s Turn");
	  update();

}	

private void enableTele(){
	cleanX(true);
	JOptionPane.showMessageDialog(null,"You may teleport your tile to locations marked with 't'.");
	List<Player> players = currentgame.getPlayers();
	int dx = currentgame.getCurrentPlayer().getCurrentTile().getX();
	int dy = currentgame.getCurrentPlayer().getCurrentTile().getY();
	Player cplayer=currentgame.getCurrentPlayer();
    for (Tile tile : currentgame.getTiles()) {
	   int x = tile.getX();
	   int y = tile.getY();
	   board[x][y].setText("t");
	   board[x][y].setBackground(Color.WHITE);
	   board[x][y].setEnabled(true);

	   
	   
	   ActionListener ac=new ActionListener() {
		public void actionPerformed(ActionEvent e) { 
			Main.getTileO().setCurrentGame(currentgame);		
			try {
				controller.playTeleportActionCard(tile);
				//when still action tile, keep Actioncard mode
			} catch (InvalidInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			board[x][y].setBackground(switchcolor(cplayer));
			board[x][y].setOpaque(true);
			board[dx][dy].setBackground(Color.WHITE);	
			 for (Tile tile : currentgame.getTiles()) {
				   int x = tile.getX();
				   int y = tile.getY();
				   board[x][y].setText("");
		 
			 }
			 for (JButton[] btn : board) {
					for (JButton btnn : btn) {
						try {
							
								for (ActionListener list : btnn.getActionListeners()) {
									btnn.removeActionListener(list);
								}
							}
						catch (Exception e1) {
							// TODO: handle exception
						}
					}
			 }			
			 //system.out.println("sese");
			 checktile();
			 controller.mode = Mode.Roll;
			repaint();				
		};	
	};
	
	
	
	   board[x][y].addActionListener(ac);
		
    }
	 
		for (int i = 0; i < players.size(); i++) {
				Player player = players.get(i);
				Tile playertile= player.getCurrentTile();
				int a = playertile.getX();
			    int b = playertile.getY();
			    board[a][b].setBackground(switchcolor(player));	
			
			    	 board[a][b].setText("");
			    	board[a][b].setEnabled(false);;
			   		    	
	      }
		

 }





}