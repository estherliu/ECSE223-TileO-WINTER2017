package ca.mcgill.ecse223.tileo.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.model.Game;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SetInactivityPeriod extends JFrame {

	private JPanel contentPane;
	JButton[][] board = new JButton[15][13];
	JButton currentbutton;
	private JTextField txtEnterANumber;
	GameBoardDesign org;
	

	/**
	 * Create the frame.
	 */
	public SetInactivityPeriod(GameBoardDesign originalpage) {
		
		org = originalpage;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton button = new JButton("Next");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				org.setVisible(true);
				org.inactivityperiodset = true;
			}
		});
		button.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 36));
		button.setBounds(470, 554, 136, 61);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Set");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setperiod();
			}
		});
		button_1.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 36));
		button_1.setBounds(314, 554, 136, 61);
		contentPane.add(button_1);
		
		JLabel lblInactivityPeriod = new JLabel("Inactivity Period:");
		lblInactivityPeriod.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 26));
		lblInactivityPeriod.setBounds(21, 543, 188, 74);
		contentPane.add(lblInactivityPeriod);
		
		txtEnterANumber = new JTextField();
		txtEnterANumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterANumber.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 40));
		txtEnterANumber.setText("0");
		txtEnterANumber.setBounds(218, 556, 86, 59);
		contentPane.add(txtEnterANumber);
		txtEnterANumber.setColumns(10);
		
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (originalpage.board[i][j].getText().equals("A")) {
					board[i][j] = new JButton("0");
					board[i][j].setBounds(10+i*40, 10+j*40, 30, 30);
					board[i][j].setMargin(new Insets(0, 0, 0, 0));
					board[i][j].setActionCommand(""+(char)i+(char)j);
					board[i][j].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {							
							setcurrentbutton(e.getActionCommand());
							
						}
					});
					contentPane.add(board[i][j]);
				}
			}
		}
		
		
	}
	private void setcurrentbutton(String e) {
		int x = e.toCharArray()[0];
		int y = e.toCharArray()[1];
		try {
			currentbutton.setBackground(Color.white);
			currentbutton = board[x][y];
			currentbutton.setBackground(Color.red);		
		} catch (Exception e2) {
			currentbutton = board[x][y];
			currentbutton.setBackground(Color.red);	
		}
	}
	private void setperiod() {
		try {
			char[] temp = currentbutton.getActionCommand().toCharArray();
			int x = temp[0];
			int y = temp[1];
			int period = Integer.parseInt(txtEnterANumber.getText());
			if (period>=0) {
				org.inactperiod[x][y] = period;
				currentbutton.setText(""+period);
			}else {
				Exceptions frame = new Exceptions("Please enter a valid number");
				frame.setVisible(true);
			}
			
		} catch (NullPointerException e) {
			Exceptions frame = new Exceptions("No ActionTile selected");
			frame.setVisible(true);
		}catch (NumberFormatException e) {
			Exceptions frame = new Exceptions("Please enter a valid number");
			frame.setVisible(true);
		}		
	}
}
