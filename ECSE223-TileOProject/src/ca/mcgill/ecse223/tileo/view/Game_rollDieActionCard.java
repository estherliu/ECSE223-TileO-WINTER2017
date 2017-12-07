package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Game_rollDieActionCard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game_rollDieActionCard frame = new Game_rollDieActionCard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game_rollDieActionCard() {
		setTitle("TileO Game mode");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		
		JButton btnRollDie = new JButton("Roll Die!");
		btnRollDie.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 26));
		btnRollDie.setBounds(30, 424, 345, 46);
		btnRollDie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		btnRollDie.setForeground(Color.BLACK);
		btnRollDie.setBackground(Color.GRAY);
		contentPane.add(btnRollDie);
		
		JLabel lblYouCanRoll = new JLabel("You can roll one more time!");
		lblYouCanRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouCanRoll.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 27));
		lblYouCanRoll.setBounds(136, 42, 328, 39);
		contentPane.add(lblYouCanRoll);
		
		JLabel label = new JLabel("PlayerX' Turn");
		label.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 24));
		label.setBounds(15, 0, 144, 46);
		contentPane.add(label);
		
		JButton button = new JButton("SAVE");
		button.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 30));
		button.setBounds(435, 423, 164, 48);
		contentPane.add(button);
	}

}
