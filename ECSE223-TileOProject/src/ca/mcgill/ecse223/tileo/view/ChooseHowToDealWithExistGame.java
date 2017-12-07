package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.Main;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class ChooseHowToDealWithExistGame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public ChooseHowToDealWithExistGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStartnewgame = new JButton("Start New Game");
		btnStartnewgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main.Load();
				PlayModeOpen pmo = new PlayModeOpen();
				pmo.setVisible(true);
			}
		});
		btnStartnewgame.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnStartnewgame.setBounds(78, 153, 291, 50);
		contentPane.add(btnStartnewgame);
		
		JLabel label = new JLabel("Welcome To Tile-O");
		label.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 45));
		label.setBounds(15, 28, 414, 69);
		contentPane.add(label);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				WelcomePage pmo = new WelcomePage();
				pmo.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBack.setBounds(78, 260, 291, 50);
		contentPane.add(btnBack);
	}
}
