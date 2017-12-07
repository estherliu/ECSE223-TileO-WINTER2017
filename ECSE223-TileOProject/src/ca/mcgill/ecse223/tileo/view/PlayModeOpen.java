package ca.mcgill.ecse223.tileo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.Main;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.TileO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PlayModeOpen extends JFrame{

	private JPanel frame;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public PlayModeOpen() {
		setTitle("TileO Game mode");
		setBounds(100, 100, 457, 345);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JPanel();
		frame.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setLayout(null);
		setContentPane(frame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 66, 314, 186);
		frame.add(scrollPane);
		
		TileO tileO = Main.getTileO();
		final List<Game> games = tileO.getGames();
		int numGames = games.size();
		String[] gameNames = new String[numGames];
		
		for (int i = 0; i < numGames; i++) {
			gameNames[i] = "Game " + (i+1);
		}
		
		if (gameNames.length == 0) {
			String[] noGames = {"No recent games"};
			JList<?> list = new JList(noGames);
			scrollPane.setViewportView(list);
		} else {
			JList<?> list = new JList<Object>(gameNames);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JList<?> list = (JList<?>)e.getSource();
			        if (e.getClickCount() == 2) {
			             // Double-click detected
			            int index = list.locationToIndex(e.getPoint());
			            dispose();
			            @SuppressWarnings("unused")
			            StartGame frame = new StartGame(Main.getTileO().getGame(index));
			            frame.setVisible(true);
			        } 
				}
			});
			scrollPane.setViewportView(list);
		}
	
		
		JLabel lblRecentGames = new JLabel("Recent Game Designs");
		lblRecentGames.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 30));
		lblRecentGames.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecentGames.setBounds(68, 10, 314, 47);
		frame.add(lblRecentGames);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChooseHowToDealWithExistGame page = new ChooseHowToDealWithExistGame();
				page.setVisible(true);
			}
		});
		btnNewButton.setBounds(168, 262, 117, 29);
		frame.add(btnNewButton);

	}
}
