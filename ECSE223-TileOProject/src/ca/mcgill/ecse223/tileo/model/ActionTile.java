/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.application.Main;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import java.util.*;

// line 34 "../../../../../TileOPersistence.ump"
// line 125 "../../../../../TileO.ump"
public class ActionTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionTile Attributes
  private int inactivityPeriod;
  private int turnsUntilActive;

  //ActionTile State Machines
  public enum ActionTileStatus { Active, InActive }
  private ActionTileStatus actionTileStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(int aX, int aY, Game aGame, int aInactivityPeriod)
  {
    super(aX, aY, aGame);
    inactivityPeriod = aInactivityPeriod;
    turnsUntilActive = 0;
    setActionTileStatus(ActionTileStatus.Active);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTurnsUntilActive(int aTurnsUntilActive)
  {
    boolean wasSet = false;
    turnsUntilActive = aTurnsUntilActive;
    wasSet = true;
    return wasSet;
  }

  public int getInactivityPeriod()
  {
    return inactivityPeriod;
  }

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public String getActionTileStatusFullName()
  {
    String answer = actionTileStatus.toString();
    return answer;
  }

  public ActionTileStatus getActionTileStatus()
  {
    return actionTileStatus;
  }

  public boolean deactivate()
  {
    boolean wasEventProcessed = false;
    
    ActionTileStatus aActionTileStatus = actionTileStatus;
    switch (aActionTileStatus)
    {
      case Active:
        if (getInactivityPeriod()>0)
        {
        // line 134 "../../../../../TileO.ump"
          setTurnsUntilActive(getInactivityPeriod() + 1 );
          setActionTileStatus(ActionTileStatus.InActive);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean takeTurn()
  {
    boolean wasEventProcessed = false;
    
    ActionTileStatus aActionTileStatus = actionTileStatus;
    switch (aActionTileStatus)
    {
      case InActive:
        if (getTurnsUntilActive()>1)
        {
        // line 139 "../../../../../TileO.ump"
          setTurnsUntilActive(getTurnsUntilActive() - 1);
          setActionTileStatus(ActionTileStatus.InActive);
          wasEventProcessed = true;
          break;
        }
        if (getTurnsUntilActive()<=1)
        {
        // line 142 "../../../../../TileO.ump"
          setTurnsUntilActive(0);
          setActionTileStatus(ActionTileStatus.Active);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setActionTileStatus(ActionTileStatus aActionTileStatus)
  {
    actionTileStatus = aActionTileStatus;
  }

  public void delete()
  {
    super.delete();
  }

  // line 150 "../../../../../TileO.ump"
   public void land(){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Player currentPlayer = currentGame.getCurrentPlayer();
		currentPlayer.setCurrentTile(this);
		this.setHasBeenVisited(true);
		Deck deck = currentGame.getDeck();
		ActionCard currentCard;
		if (deck.hasCurrentCard()) {
			currentCard = deck.getCurrentCard();
		} else {
			deck.setCurrentCard(deck.getCard(0));
			currentCard = deck.getCurrentCard();
		}
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "inactivityPeriod" + ":" + getInactivityPeriod()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]"
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 37 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 2045406856025012133L ;

  
}