package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Game_MoveWinTile extends JFrame {

	private JPanel contentPane;
	private JButton board[][] = new JButton[15][13];

	/**
	 * Create the frame.
	 */
	public Game_MoveWinTile(StartGame fr) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblSelectTheNew = new JLabel("select the new wintile location");
		lblSelectTheNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTheNew.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblSelectTheNew.setBounds(81, 571, 482, 47);
		contentPane.add(lblSelectTheNew);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new JButton();
				try {
					board[i][j].setBounds(fr.board[i][j].getBounds());
					board[i][j].setOpaque(true);
					board[i][j].setActionCommand(""+(char)i+(char)j);
					board[i][j].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							char[] arr = e.getActionCommand().toCharArray();
							
							fr.getController().playMoveWinTileActionCard((int)arr[0],(int)arr[1],fr.currentgame);
							fr.setVisible(true);
							//system.out.println("wintile set to "+(int)arr[0]+" "+(int)arr[1]);
							dispose();
						}
					});
					contentPane.add(board[i][j]);
				} catch (Exception e) {
					
				}
			}
		};
	}
}
