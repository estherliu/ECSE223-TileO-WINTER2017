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
import javax.swing.Timer;
import javax.sound.midi.ControllerEventListener;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.Point;
import java.awt.Insets;

public class Game_showActionTilesActionCard extends JFrame {

	private JPanel contentPane;
	private JPanel frame;
	private Game currentgame = Main.getTileO().getCurrentGame();
	private TileOController controller = new TileOController();
	private JButton board[][] = new JButton[15][13];
	
	private String status[] = { "", "C" };
	


	/**
	 * Create the frame.
	 */
	public  Game_showActionTilesActionCard(StartGame frame,Game game) {
		controller=frame.getController();
		setTitle("TileO Game mode: show action tiles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 553);
		
		initialize(frame,game);
	}
	
	
	
	private void initialize(StartGame f,Game game) {
		currentgame = game;
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setLayout(null);
		setContentPane(frame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel colorset = new JLabel("actioncard appears for 5 sec after clicking Confirm");
		colorset.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 25));
		colorset.setBounds(68, 50, 600, 47);
		frame.add(colorset);
		
		
		ActionListener taskperformer= (new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
				     controller.playShowActionTilesActionCard();
				     f.update();					
					f.nextplayer();
					dispose();
				    f.setVisible(true);
				} catch (Exception e1) {

				}
			}
		});
		
		Timer timer=new Timer(5000,taskperformer);
		timer.setRepeats(false);
		
		
		
		

		JButton button1 = new JButton("CONFIRM");
		button1.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 30));
		button1.setBounds(300, 410, 187, 56);
		button1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
				     button1.setEnabled(false);
					timer.start();
					show(f,game);
				} catch (Exception e1) {

				}
			}
		});
		frame.add(button1);
				
	
	}
	
	
	
	private void show(StartGame f,Game game) {

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
		}
		for (Tile tile : game.getTiles()) {
			int x = tile.getX();
			int y = tile.getY();
			 
				String text="";
				if (tile instanceof ActionTile) {
					ActionTile actionTile = (ActionTile) tile;
					text = "x^" + actionTile.getInactivityPeriod();
				}
				board[x][y] = new JButton(status[0]);
				
				//system.out.println("x="+x);
				//system.out.println("y="+y+"\n");
				board[x][y].setBounds(10+x*40, 10+y*40, 30, 30);
				board[x][y].setMargin(new Insets(0, 0, 0, 0));
				//system.out.println("board" + board[x][y]);
				board[x][y].setActionCommand("" + (char) x + (char) y + 0);
				board[x][y].setText(text);
				board[x][y].setBackground(Color.WHITE);
				board[x][y].setOpaque(true);
				
				frame.add(board[x][y]);
			
		}


	
	}
}



