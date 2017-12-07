/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.application.Main;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import java.util.*;

// line 46 "../../../../../TileOPersistence.ump"
// line 202 "../../../../../TileO.ump"
public class WinTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WinTile(int aX, int aY, Game aGame)
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

  // line 209 "../../../../../TileO.ump"
   public void land(){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		this.setHasBeenVisited(true);
		currentGame.setMode(Mode.GAME_WON);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 49 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -7403802774454467836L ;

  
}