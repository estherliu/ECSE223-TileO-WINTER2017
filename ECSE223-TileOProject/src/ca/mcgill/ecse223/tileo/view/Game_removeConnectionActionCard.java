
	package ca.mcgill.ecse223.tileo.view;

	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

	import ca.mcgill.ecse223.tileo.application.Main;
	import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
	import ca.mcgill.ecse223.tileo.controller.TileOController;
	import ca.mcgill.ecse223.tileo.model.ActionTile;
	import ca.mcgill.ecse223.tileo.model.Connection;
	import ca.mcgill.ecse223.tileo.model.Game;
	import ca.mcgill.ecse223.tileo.model.Player;
	import ca.mcgill.ecse223.tileo.model.Tile;

	import javax.swing.JLabel;
	import java.awt.Font;
	import javax.swing.SwingConstants;
	import javax.swing.JButton;
	import java.awt.Component;
	import java.awt.Rectangle;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.LinkedList;
	import java.util.List;
	import java.awt.Point;
	import java.awt.Insets;

	public class  Game_removeConnectionActionCard extends JFrame {

		private JPanel contentPane;
		private JPanel frame;
		private LinkedList<Tile> tiles;
		private int numRolled;
		private JButton board[][] = new JButton[15][13];
		private TileOController controller = new TileOController();
		private Game currentgame = Main.getTileO().getCurrentGame();
		private JButton btnTakePlayersTurn;
		private int cardnum = 0;
		private Tile temp;
		private Color color;
		private int Cnum = 0;
		private String status[] = { "", "R" };
		private LinkedList<JLabel> lbl = new LinkedList<JLabel>();

		/**
		 * Launch the application.
		 */
	/*	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Game_connectTilesActionCard frame = new Game_connectTilesActionCard();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	*/
		/**
		 * Create the frame.
		 */
		public  Game_removeConnectionActionCard(StartGame frame,Game game) {
			controller=frame.getController();
			setTitle("TileO Game mode: removeTile");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 657, 657);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			
			
		/*	JButton btnSave = new JButton("SAVE");
			btnSave.setBounds(368, 410, 189, 59);
			btnSave.setAlignmentX(Component.RIGHT_ALIGNMENT);
			btnSave.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 27));
			contentPane.add(btnSave);
			
			*/
		
			
			initialize(frame,game);
		}
		
		
		private void initialize(StartGame f,Game game) {
			currentgame = game;
			frame = new JPanel();
			frame.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setLayout(null);
			setContentPane(frame);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JLabel colorset = new JLabel("Please remove a connection.");
			colorset.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 25));
			colorset.setBounds(81, 571, 482, 47);
			frame.add(colorset);
			
			
			JButton button = new JButton("APPLY");
			button.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 30));
			button.setBounds(100, 584, 187, 56);
			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						saveboard();
						refreshdata();
						update();
						Main.getTileO().setCurrentGame(currentgame);      //save
						button.setEnabled(false);
						
					} catch (Exception e1) {

					}
				}
			});
			frame.add(button);
			
			
			
			

			JButton button1 = new JButton("CONFIRM");
			button1.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 30));
			button1.setBounds(300, 584, 187, 56);
			button1.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
					    f.update();
						f.setVisible(true);	
						f.nextplayer();
						dispose();
					} catch (Exception e1) {

					}
				}
			});
			frame.add(button1);
			
			

			

			
			List<Player> players = game.getPlayers();
			
			for (Connection connection : game.getConnections()) {
				//system.out.print("adding connections");
				Tile tile1 = connection.getTile(0);
				Tile tile2 = connection.getTile(1);
				if (tile1.getX() == tile2.getX()) {
					JLabel connect = new JLabel();
					connect.setBounds(22+40*tile1.getX(), 40+40*tile1.getY(), 2, 10);
					connect.setBackground(Color.BLACK);
					connect.setOpaque(true);
					frame.add(connect);
					//system.out.println(connect);
					lbl.add(connect);
					frame.repaint();
				} else {
					
					JLabel connect = new JLabel();				
					connect.setBounds(40+40*tile1.getX(), 23+40*tile1.getY(), 10, 2);
					connect.setBackground(Color.BLACK);
					connect.setOpaque(true);
					//system.out.println(connect.isDisplayable());
					frame.add(connect);
					//system.out.println(connect);
					lbl.add(connect);
					frame.repaint();
				}
			}
			
			/*for (int i = 0; i < players.size(); i++) {
				Player player = players.get(i);
				setPlayerColor(player,i);
				//system.out.println("this time" +i);
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
			}*/
			
			for (Tile tile : game.getTiles()) {
				int x = tile.getX();
				int y = tile.getY();
				 
					
					/*if (tile instanceof ActionTile) {
						ActionTile actionTile = (ActionTile) tile;
						text = "x^" + actionTile.getInactivityPeriod();
					}*/
					board[x][y] = new JButton(status[0]);
					
					//system.out.println("x="+x);
					//system.out.println("y="+y+"\n");
					board[x][y].setBounds(10+x*40, 10+y*40, 30, 30);
					board[x][y].setMargin(new Insets(0, 0, 0, 0));
					//system.out.println("board" + board[x][y]);
					board[x][y].setActionCommand("" + (char) x + (char) y + 0);
					board[x][y].setBackground(Color.WHITE);
					board[x][y].setOpaque(true);
					board[x][y].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							switchstate(e.getActionCommand());
						}
					});
					frame.add(board[x][y]);
				
			}
		
		}
		
		
		
		
		
		private void switchstate(String actioncommand) {

			char command[] = actioncommand.toCharArray();
			int x = (int) command[0];
			int y = (int) command[1];

			if (Cnum > 1) {
				Cnum = 0;
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						try {
							board[i][j].setText("");
						} catch (IndexOutOfBoundsException e) {
							Cnum = 0;
						} catch (NullPointerException e) {
							Cnum = 0;
						}
					}
				}
				repaint();
				return;
			}
			Cnum++;
			if (board[x][y].getText().equals("R")) {
				board[x][y].setText(status[0]);
				board[x][y].setActionCommand("" + command[0] + command[1] + 0);
			} else {
				board[x][y].setText(status[1]);
				board[x][y].setActionCommand("" + command[0] + command[1] + 0);
			}
			repaint();
		}	
		
		
		public void saveboard() throws InvalidInputException {
			int k, l, x, y;
			for (int i = 0; i < currentgame.getTiles().size(); i++) {
				k = currentgame.getTile(i).getX();
				l = currentgame.getTile(i).getY();
				switch (board[k][l].getText()) {
				case "":
					break;
				case "R":
					for (int j = i + 1; j < currentgame.getTiles().size(); j++) {
						x = currentgame.getTile(j).getX();
						y = currentgame.getTile(j).getY();
						
						if (controller.sideBySide(currentgame.getTile(i), currentgame.getTile(j))
								&& board[x][y].getText() == "R") {
							for (Connection Connection : currentgame.getConnections()) {
								if (Connection.getTiles().contains(currentgame.getTile(i))
										&& Connection.getTiles().contains(currentgame.getTile(j))) {
									try {
										
										controller.removeConnect(currentgame.getTile(i), currentgame.getTile(j),
												currentgame);
										//system.out.println("succcc");
										Main.getTileO().setCurrentGame(currentgame);
										controller.playRemoveConnectionActionCard(Connection);
										//system.out.println("disconnected");
									} catch (Exception e) {
										// TODO: handle exception
									}

									
									return;
								}
							}
							
							
						}
					}
					break;
				}
			}
		}
		private void update() {
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
					//system.out.println(connect);
					repaint();
				} else {

					JLabel connect = new JLabel();
					connect.setBounds(40 + 40 * tile1.getX(), 23 + 40 * tile1.getY(), 10, 2);
					connect.setBackground(Color.BLACK);
					connect.setOpaque(true);
					frame.add(connect);
					//system.out.println(connect);
					lbl.add(connect);
					repaint();
				}
			}
		}
		
		
		public void refreshdata() {
			int c, d;
			for (Tile tile : currentgame.getTiles()) {
				c = tile.getX();
				d = tile.getY();
				if (board[c][d].getText() == "R" || board[c][d].getText() == "D") {
					board[c][d].setText(status[0]);
				}
			}
			repaint();
		}
		
		
		
		
	}
