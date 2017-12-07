/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 82 "../../../../../TileOPersistence.ump"
// line 287 "../../../../../TileO.ump"
public class RemoveConnectionActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RemoveConnectionActionCard(String aInstructions, Deck aDeck)
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

  // line 291 "../../../../../TileO.ump"
   public void play(Connection connection){
    // need the 5th feature in design mode

		connection.getGame().removeConnection(connection);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 85 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -74454467836L ;

  
}