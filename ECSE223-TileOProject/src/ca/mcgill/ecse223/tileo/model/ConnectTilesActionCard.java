/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 76 "../../../../../TileOPersistence.ump"
// line 257 "../../../../../TileO.ump"
public class ConnectTilesActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ConnectTilesActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 262 "../../../../../TileO.ump"
   public void play(Tile tile1, Tile tile2) throws InvalidInputException{
    // need the 4th feature in design mode
		if (tile1.getGame().getTileO().indexOfGame(tile1.getGame()) == tile2.getGame().getTileO()
				.indexOfGame(tile2.getGame())) {
			tile1.getGame().connectTiles(tile1, tile2);
		} else
			throw new InvalidInputException("Tiles in different Games");
  }

  // line 270 "../../../../../TileO.ump"
   public Mode getActionCardGameMode(String instruction){
    String ActionType[] = { "", "Connect Tiles", "Lose Turn", "Remove Connection", "Teleport", "Roll Again",
				"Lose Random Turns", "Change Tile", "Switch Player Turns", "Swap Positions","Choose Addtional Move" };
		Mode[] gamemode = { null, Mode.GAME_CONNECTTILESACTIONCARD, Mode.GAME_LOSETURNACTIONCARD,
				Mode.GAME_REMOVECONNECTIONACTIONCARD, Mode.GAME_TELEPORTACTIONCARD, Mode.GAME_ROLLDIEACTIONCARD,
				Mode.GAME_LOSETURNRANDOMACTIONCARD, Mode.GAME_CHANGETILEACTIONCARD, Mode.GAME_SWITCHPLAYERSACTIONCARD,
				Mode.GAME_SWAPPOSITIONACTIONCARD, Mode.GAME_CHOOSEADDITIONALMOVEACTIONCARD };

		for (int i = 0; i < ActionType.length; i++) {
			if (instruction.equals(ActionType[i])) {

				return gamemode[i];
			}
		}
		return null;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 79 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -1409832774467836L ;

  
}