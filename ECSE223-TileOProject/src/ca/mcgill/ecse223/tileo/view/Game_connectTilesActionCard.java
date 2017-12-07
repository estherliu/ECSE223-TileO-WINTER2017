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
import javax.swing.JOptionPane;

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

public class Game_connectTilesActionCard extends JFrame {

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
	private String status[] = { "", "C" };
	private LinkedList<JLabel> lbl = new LinkedList<JLabel>();

	public Game_connectTilesActionCard(StartGame frame,Game game) {
		controller=frame.getController();
		setTitle("TileO Game mode: connectTiles");
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
		
		JLabel colorset = new JLabel("Please connect two adjacent tiles.");
		colorset.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 25));
		colorset.setBounds(10, 525, 401, 47);
		frame.add(colorset);
		
		
		JButton button = new JButton("APPLY");
		button.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 30));
		button.setBounds(226, 573, 241, 46);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					saveboard(button);
					refreshdata();
					update();
					Main.getTileO().setCurrentGame(currentgame);      //save
					
					
				} catch (Exception e1) {

				}
			}
		});
		frame.add(button);
		
		
		
		

		JButton button1 = new JButton("CONFIRM");
		button1.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		button1.setBounds(509, 582, 117, 29);
		button1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					controller.shuffle(currentgame);
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
		if (board[x][y].getText().equals("C")) {
			board[x][y].setText(status[0]);
			board[x][y].setActionCommand("" + command[0] + command[1] + 0);
		} else {
			board[x][y].setText(status[1]);
			board[x][y].setActionCommand("" + command[0] + command[1] + 0);
		}
		repaint();
	}	
	
	
	public void saveboard(JButton button) throws Exception {
		int k, l, x, y;
		for (int i = 0; i < currentgame.getTiles().size(); i++) {
			k = currentgame.getTile(i).getX();
			l = currentgame.getTile(i).getY();
			switch (board[k][l].getText()) {
			case "":
				break;
			case "C":
				for (int j = i + 1; j < currentgame.getTiles().size(); j++) {
					x = currentgame.getTile(j).getX();
					y = currentgame.getTile(j).getY();
					if (controller.sideBySide(currentgame.getTile(i), currentgame.getTile(j))
							&& board[x][y].getText() == "C") {
						for (Connection Connection : currentgame.getConnections()) {
							if (Connection.getTiles().contains(currentgame.getTile(i))
									&& Connection.getTiles().contains(currentgame.getTile(j))) {
								JOptionPane.showMessageDialog(null, "There is already connection here.");	
							}
						}
						
						controller.playConnectTilesActionCard(currentgame.getTile(i), currentgame.getTile(j));
						controller.connectTiles(currentgame.getTile(i), currentgame.getTile(j), currentgame);
						//system.out.println("connected tiles");
						button.setEnabled(false);
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
			if (board[c][d].getText() == "C" || board[c][d].getText() == "D") {
				board[c][d].setText(status[0]);
			}
		}
		repaint();
	}
	
}
