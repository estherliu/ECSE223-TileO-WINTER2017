package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.Main;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Game_won extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public Game_won() {
		setTitle("TileO Game mode");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBackToMain = new JButton("Exit");
		btnBackToMain.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 22));
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnBackToMain.setBounds(173, 441, 299, 29);
		contentPane.add(btnBackToMain);
		
		JLabel lblPlayerx = new JLabel("");
		lblPlayerx.setBounds(5, 5, 575, 0);
		lblPlayerx.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
		lblPlayerx.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPlayerx);
		
		JLabel lblCongratulationsPlayerx = new JLabel("Congratulations Player1 !!  You won the game!!");
		lblCongratulationsPlayerx.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 28));
		lblCongratulationsPlayerx.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongratulationsPlayerx.setBounds(17, 108, 588, 122);
		contentPane.add(lblCongratulationsPlayerx);
	}

}
