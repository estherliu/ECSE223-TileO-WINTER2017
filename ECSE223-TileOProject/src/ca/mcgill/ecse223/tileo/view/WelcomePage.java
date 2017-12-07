package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WelcomePage extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public WelcomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblWelcomeToTileo = new JLabel("Welcome To Tile-O");
		lblWelcomeToTileo.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 45));
		lblWelcomeToTileo.setBounds(26, 16, 414, 69);
		contentPane.add(lblWelcomeToTileo);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChooseHowToDealWithExistGame pmo = new ChooseHowToDealWithExistGame();
				pmo.setVisible(true);
			}
		});
	
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnContinue.setBounds(138, 171, 178, 57);
		contentPane.add(btnContinue);
		
		JButton btnNew = new JButton("New Design ");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				CreateAGameWithNumberOfPlayer createagame = new CreateAGameWithNumberOfPlayer();
				createagame.setVisible(true);
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNew.setBounds(138, 101, 178, 57);
		contentPane.add(btnNew);
	}

}
