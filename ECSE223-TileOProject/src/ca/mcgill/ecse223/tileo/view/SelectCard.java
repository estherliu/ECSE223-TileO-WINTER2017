package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.Main;
import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.Game;

import java.awt.event.ActionListener;
import java.lang.Thread.State;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class SelectCard extends JFrame {

	private JPanel contentPane;
	private String names[];
	private JLabel str[] = new JLabel[32];
	private JButton status[] = new JButton[32];
	private int dy = 25;
	private String ActionType[] = {"","Connect Tiles","Lose Turn","Remove Connection","Teleport","Roll Again","Lose Random Turns", "Change Tile","Switch Player Turns","Move Win Tile","Show Action Tiles","Swap Position"};
	private Game currentgame;
	/**
	 * Create the frame.
	 */
	public SelectCard(final Game game) {
		currentgame = game;
		setnames();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		

	/*	JButton btnNewButton1 = new JButton("check");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < status.length; i++) {
			
				//system.out.println(status[i].getText());
				}
			}
		});	
			
				btnNewButton1.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 24));
				btnNewButton1.setBounds(167, 380, 189, 34);
				contentPane.add(btnNewButton1);
				*/
		
		JButton btnNewButton = new JButton("Done and Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TileOController controller = new TileOController();
					
					controller.setcards(status,game);
					Main.save();
					dispose();
					WelcomePage wel = new WelcomePage();
					wel.setVisible(true);
					
				} catch (Exception e2) {
					Exceptions frame = new Exceptions(e2.getMessage());
					frame.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 24));
		btnNewButton.setBounds(167, 443, 189, 34);
		contentPane.add(btnNewButton);
		
		
		for (int i = 0; i < 16; i++) {
			str[i] = new JLabel(names[i]);
			str[i].setBounds(25, 11+i*dy, 60, 14);
			contentPane.add(str[i]);
			
			status[i] = new JButton(ActionType[0]);
			status[i].setBounds(75, 7+i*dy, 150, 23);
			status[i].setActionCommand(""+(char)i+(char)0);
			status[i].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					switchstate(e.getActionCommand());					
				}
			});
			contentPane.add(status[i]);
			
			str[i+16] = new JLabel(names[i+16]);
			str[i+16].setBounds(250, 11+(i)*dy, 60, 14);
			str[i+16].setText(names[i+16]);
			contentPane.add(str[i+16]);
			
			status[i+16] = new JButton(ActionType[0]);
			status[i+16].setBounds(305, 7+(i)*dy, 150, 23);
			status[i+16].setActionCommand(""+(char)(i+16)+(char)0);
			status[i+16].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					switchstate(e.getActionCommand());					
				}
			});
			contentPane.add(status[i+16]);
		}
	}
	private void switchstate(String actioncommand) {
		char command[] = actioncommand.toCharArray();
		int index = (int)command[0];
		int current = (int)command[1];
		current++;
		current=current%ActionType.length;	
		if (current==(ActionType.length)) {
			status[index].setText(ActionType[0]);
			status[index].setActionCommand(""+command[0]+0);
		} else {
			status[index].setText(ActionType[current]);
			status[index].setActionCommand(""+command[0]+(char)current);
		}
	}
	public void setnames() {
		names = new String[32];
		for (int i = 0; i < names.length; i++) {
			names[i] = "Card : "+String.valueOf(i+1);
		}		
	}
}
