/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.List;
import ca.mcgill.ecse223.tileo.application.Main;

// line 70 "../../../../../TileOPersistence.ump"
// line 241 "../../../../../TileO.ump"
public class RollDieActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RollDieActionCard(String aInstructions, Deck aDeck)
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

  // line 248 "../../../../../TileO.ump"
   public List<Tile> play(){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Die die = currentGame.getDie();
		int rolledNum = die.roll();
		Player currentPlayer = currentGame.getCurrentPlayer();
		return currentPlayer.getPossibleMoves(rolledNum);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 73 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -74037744555367836L ;

  
}