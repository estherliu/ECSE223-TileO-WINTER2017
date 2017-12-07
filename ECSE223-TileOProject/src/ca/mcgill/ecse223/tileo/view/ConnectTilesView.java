package ca.mcgill.ecse223.tileo.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Tile;

public class ConnectTilesView extends JFrame {

	private JPanel contentPane;
	private JButton board[][] = new JButton[15][13];
	private String status[] = { "", "C" };
	private TileOController controller;
	private Game currentgame;
	private int Cnum = 0;
	private LinkedList<JLabel> lbl = new LinkedList<JLabel>();

	public ConnectTilesView(final Game game) {
		setTitle("TileO Design mode - Add & Remove Connections");
		currentgame = game;
		controller = new TileOController();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					saveboard();
					refreshdata();
					update();
					Cnum=0;
				} catch (Exception e1) {

				}
			}
		});
		applyButton.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 25));
		applyButton.setBounds(298, 545, 136, 61);
		contentPane.add(applyButton);

		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					dispose();
					SelectCard frame = new SelectCard(currentgame);
					frame.setVisible(true);
				} catch (Exception e2) {
					Exceptions frame = new Exceptions(e2.getMessage());
					frame.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 36));
		btnNewButton.setBounds(456, 545, 136, 61);
		contentPane.add(btnNewButton);

		JLabel lblC = new JLabel("C: Switch connection status for those 2 tiles");
		lblC.setBounds(25, 545, 420, 34);
		contentPane.add(lblC);

		int i, j;
		for (Tile tile : currentgame.getTiles()) {
		//	if (tile.getClass().equals(ActionTile.class)) {
            if (false){
			} else {
				i = tile.getX();
				j = tile.getY();
				board[i][j] = new JButton(status[0]);
				board[i][j].setBounds(10 + i * 40, 10 + j * 40, 30, 30);
				board[i][j].setActionCommand("" + (char) i + (char) j + 0);
				board[i][j].setMargin(new Insets(0, 0, 0, 0));
				board[i][j].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						switchstate(e.getActionCommand());
					}
				});
				contentPane.add(board[i][j]);
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

	public void saveboard() throws InvalidInputException {
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
								try {
									controller.removeConnect(currentgame.getTile(i), currentgame.getTile(j),
											currentgame);
								} catch (Exception e) {
									// TODO: handle exception
								}

								//system.out.println("disconnected");
								return;
							}
						}
						controller.connectTiles(currentgame.getTile(i), currentgame.getTile(j), currentgame);
						//system.out.println("connected tiles");
					}
				}
				break;
			}
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
				contentPane.add(connect);
				lbl.add(connect);
				//system.out.println(connect);
				repaint();
			} else {

				JLabel connect = new JLabel();
				connect.setBounds(40 + 40 * tile1.getX(), 23 + 40 * tile1.getY(), 10, 2);
				connect.setBackground(Color.BLACK);
				connect.setOpaque(true);
				contentPane.add(connect);
				//system.out.println(connect);
				lbl.add(connect);
				repaint();
			}
		}
	}

}
