package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ca.mcgill.ecse223.tileo.application.Main;
import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Die;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.TileO;;

public class CreateAGameWithNumberOfPlayer extends JFrame {

	private JPanel contentPane;
	private Game currentgame;
	/**
	 * Create the frame.
	 */
	public CreateAGameWithNumberOfPlayer() {
		currentgame = new Game(0, Main.getTileO());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblSelectNumberOf = new JLabel("Select number of players :");
		lblSelectNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSelectNumberOf.setBounds(23, 40, 310, 53);
		contentPane.add(lblSelectNumberOf);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(318, 62, 75, 20);
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 36));
		btnNewButton.setBounds(23, 172, 115, 53);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				WelcomePage frame = new WelcomePage();
				frame.setVisible(true);
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNext = new JButton("Create");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TileOController controller = new TileOController();;
				for (int i = 0; i < Integer.parseInt(comboBox.getSelectedItem().toString()); i++) {
					try {
						
						currentgame.addPlayer(i);
					} catch (Exception e2) {
						//system.out.println(e2);
					}
				}/*
				for (int i = 3; i >= Integer.parseInt(comboBox.getSelectedItem().toString()); i--) {
					try {
						Main.getTileO().getCurrentGame().getPlayer(i).delete();
					} catch (IndexOutOfBoundsException e2) {
						//system.out.println(e2);
					}
					
				}*/
				
				GameBoardDesign gameboard = new GameBoardDesign(currentgame);
				
				dispose();				
				gameboard.setVisible(true);
			}
		});
		btnNext.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 36));
		btnNext.setBounds(272, 172, 141, 53);
		contentPane.add(btnNext);
		
		/*
		JButton btnLoad = new JButton("Load");
		btnLoad.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 36));
		btnLoad.setBounds(147, 172, 115, 53);
		contentPane.add(btnLoad);*/
	}
}
