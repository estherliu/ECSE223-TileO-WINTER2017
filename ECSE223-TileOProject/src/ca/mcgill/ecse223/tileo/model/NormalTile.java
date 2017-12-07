/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.application.Main;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import java.util.*;

// line 40 "../../../../../TileOPersistence.ump"
// line 168 "../../../../../TileO.ump"
public class NormalTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NormalTile(int aX, int aY, Game aGame)
  {
    super(aX, aY, aGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 177 "../../../../../TileO.ump"
   public void land(){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Player currentPlayer = currentGame.getCurrentPlayer();
		currentPlayer.setCurrentTile(this);
		boolean wasSet = false;
		// if currentPlayer is last player
		// assumes players will be numbered starting from 0
		/*if (currentGame.indexOfPlayer(currentPlayer) == (currentGame.numberOfPlayers() - 1)) {
			// first player is player
			wasSet = currentGame.setCurrentPlayer(currentGame.getPlayer(0));
		} else {
			wasSet = currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer) + 1));
		}*/
		if (wasSet == false) {
			try {
				throw new InvalidInputException("Not set");
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
		}
		this.setHasBeenVisited(true);
		currentGame.setMode(Mode.GAME);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 43 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -7403802774454467836L ;

  
}