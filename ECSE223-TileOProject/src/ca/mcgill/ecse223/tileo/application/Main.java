package ca.mcgill.ecse223.tileo.application;

import java.awt.EventQueue;


import java.util.LinkedList;
import java.util.List;

import ca.mcgill.ecse223.tileo.view.WelcomePage;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Die;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.LoseTurnActionCard;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.persistence.PersistenceObjectStream;


public class Main {
	
	
	private static TileO tileO;
	private static String filename = "data.tileo";
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static TileO getTileO() {
		if (tileO == null) {
			// load model
			tileO = Load();
		}
 		return tileO;
	}
	
	public static void save() {
		PersistenceObjectStream.serialize(tileO);
	}
	
	public static TileO Load() {
		PersistenceObjectStream.setFilename(filename);
		tileO = (TileO) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty TileO
		if (tileO == null) {
			tileO = new TileO();
		}
		else {
			List<Game> games = tileO.getGames();
			/*
			List<Player> players = new LinkedList<Player>();
			for (Game game : games) {
				List<Player> oneGamePlayers = game.getPlayers();
				for (Player oneGamePlayer : oneGamePlayers) {
					players.add(oneGamePlayer);
				}
			}
			Player.reinitializeUniqueNumber(players);*/
		}
		return tileO;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}
