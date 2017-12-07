
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

public class Game_loseTurnActionCard extends JFrame {

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
	public  Game_loseTurnActionCard(StartGame frame,Game game) {
		controller=frame.getController();
		setTitle("TileO Game mode: lose turn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 553);
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
		Main.getTileO().setCurrentGame(game);
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setLayout(null);
		setContentPane(frame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel colorset = new JLabel("Unfortunately, You have lost your next turn.");
		colorset.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 25));
		colorset.setBounds(68, 50, 600, 47);
		frame.add(colorset);
		
		
		
		
		
		

		JButton button1 = new JButton("CONFIRM");
		button1.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 30));
		button1.setBounds(300, 410, 187, 56);
		button1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					controller.playLoseTurnActionCard(currentgame.getCurrentPlayer());
					f.setVisible(true);	
					f.nextplayer();
					dispose();
				} catch (Exception e1) {

				}
			}
		});
		frame.add(button1);
		
		
	/*	btnTakePlayersTurn = new JButton("Take Player " + ((game.getCurrentPlayer().getNumber()) + 1) + "'s Turn");
		btnTakePlayersTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				btnTakePlayersTurn.setEnabled(false);
			
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
				frame.repaint();
			} else {
				
				JLabel connect = new JLabel();				
				connect.setBounds(40+40*tile1.getX(), 23+40*tile1.getY(), 10, 2);
				connect.setBackground(Color.BLACK);
				connect.setOpaque(true);
				//system.out.println(connect.isDisplayable());
				frame.add(connect);
				//system.out.println(connect);
				frame.repaint();
			}
		}*/
		
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
		/*
		for (Tile tile : game.getTiles()) {
			int x = tile.getX();
			int y = tile.getY();
			 
				
				/*if (tile instanceof ActionTile) {
					ActionTile actionTile = (ActionTile) tile;
					text = "x^" + actionTile.getInactivityPeriod();
				}*/
		/*		board[x][y] = new JButton(status[0]);
				
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
			
		}*/
	
	}
	
	

	


	
}
















/*package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.Main;
import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JLabel;

public class Game_loseTurnActionCard extends JFrame {

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


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game_loseTurnActionCard frame = new Game_loseTurnActionCard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Game_loseTurnActionCard(StartGame f,Game game) {
		currentgame = game;
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setLayout(null);
		setContentPane(frame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		setTitle("TileO Game mode: lose turn ");
		/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnContinue = new JButton("CONTINUE");
		btnContinue.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 30));
		btnContinue.setBounds(73, 400, 187, 56);
		
		

		btnContinue.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
				
					f.setVisible(true);	
					dispose();
				} catch (Exception e1) {

				}
			}
		});
		frame.add(btnContinue);
		
		
		
		JButton button = new JButton("SAVE");
		button.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 30));
		button.setBounds(416, 400, 144, 56);
		contentPane.add(button);
		
		JLabel lblUnfortunatelyYouHave = new JLabel("Unfortunately, You have lost your turn.");
		lblUnfortunatelyYouHave.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 26));
		lblUnfortunatelyYouHave.setBounds(99, 42, 481, 47);
		contentPane.add(lblUnfortunatelyYouHave);
		
		
	}
}
*/