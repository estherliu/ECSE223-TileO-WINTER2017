/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.List;
import java.util.*;

// line 15 "../../../../../TileOPersistence.ump"
// line 27 "../../../../../TileO.ump"
public class Player implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Player> playersByNumber = new HashMap<Integer, Player>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int number;
  private int turnsUntilActive;

  //Player State Machines
  public enum Color { RED, BLUE, GREEN, YELLOW }
  private Color color;
  public enum PlayerStatus { Active, InActive }
  private PlayerStatus playerStatus;

  //Player Associations
  private Tile startingTile;
  private Tile currentTile;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(int aNumber, Game aGame)
  {
    turnsUntilActive = 0;
    if (!setNumber(aNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate number");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create player due to game");
    }
    setColor(Color.RED);
    setPlayerStatus(PlayerStatus.Active);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    Integer anOldNumber = getNumber();
    if (hasWithNumber(aNumber)) {
      return wasSet;
    }
    number = aNumber;
    wasSet = true;
    if (anOldNumber != null) {
      playersByNumber.remove(anOldNumber);
    }
    playersByNumber.put(aNumber, this);
    return wasSet;
  }

  public boolean setTurnsUntilActive(int aTurnsUntilActive)
  {
    boolean wasSet = false;
    turnsUntilActive = aTurnsUntilActive;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public static Player getWithNumber(int aNumber)
  {
    return playersByNumber.get(aNumber);
  }

  public static boolean hasWithNumber(int aNumber)
  {
    return getWithNumber(aNumber) != null;
  }

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public String getColorFullName()
  {
    String answer = color.toString();
    return answer;
  }

  public String getPlayerStatusFullName()
  {
    String answer = playerStatus.toString();
    return answer;
  }

  public Color getColor()
  {
    return color;
  }

  public PlayerStatus getPlayerStatus()
  {
    return playerStatus;
  }

  public boolean loseTurns(int n)
  {
    boolean wasEventProcessed = false;
    
    PlayerStatus aPlayerStatus = playerStatus;
    switch (aPlayerStatus)
    {
      case Active:
        if (n>0)
        {
        // line 36 "../../../../../TileO.ump"
          setTurnsUntilActive(getTurnsUntilActive() + n);
          setPlayerStatus(PlayerStatus.InActive);
          wasEventProcessed = true;
          break;
        }
        break;
      case InActive:
        if (n>0)
        {
        // line 50 "../../../../../TileO.ump"
          setTurnsUntilActive(getTurnsUntilActive() + n);
          setPlayerStatus(PlayerStatus.InActive);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean loseTurnsRandom()
  {
    boolean wasEventProcessed = false;
    
    PlayerStatus aPlayerStatus = playerStatus;
    switch (aPlayerStatus)
    {
      case Active:
        // line 39 "../../../../../TileO.ump"
        setTurnsUntilActive(getRandomNum() + getTurnsUntilActive());
        setPlayerStatus(PlayerStatus.InActive);
        wasEventProcessed = true;
        break;
      case InActive:
        // line 53 "../../../../../TileO.ump"
        setTurnsUntilActive(getTurnsUntilActive() + getRandomNum());
        setPlayerStatus(PlayerStatus.InActive);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean takeTurn()
  {
    boolean wasEventProcessed = false;
    
    PlayerStatus aPlayerStatus = playerStatus;
    switch (aPlayerStatus)
    {
      case InActive:
        if (getTurnsUntilActive()>1)
        {
        // line 44 "../../../../../TileO.ump"
          setTurnsUntilActive(getTurnsUntilActive() - 1);
          setPlayerStatus(PlayerStatus.InActive);
          wasEventProcessed = true;
          break;
        }
        if (getTurnsUntilActive()<=1)
        {
        // line 47 "../../../../../TileO.ump"
          setTurnsUntilActive(0);
          setPlayerStatus(PlayerStatus.Active);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean setColor(Color aColor)
  {
    color = aColor;
    return true;
  }

  private void setPlayerStatus(PlayerStatus aPlayerStatus)
  {
    playerStatus = aPlayerStatus;
  }

  public Tile getStartingTile()
  {
    return startingTile;
  }

  public boolean hasStartingTile()
  {
    boolean has = startingTile != null;
    return has;
  }

  public Tile getCurrentTile()
  {
    return currentTile;
  }

  public boolean hasCurrentTile()
  {
    boolean has = currentTile != null;
    return has;
  }

  public Game getGame()
  {
    return game;
  }

  public boolean setStartingTile(Tile aNewStartingTile)
  {
    boolean wasSet = false;
    startingTile = aNewStartingTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentTile(Tile aNewCurrentTile)
  {
    boolean wasSet = false;
    currentTile = aNewCurrentTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to player
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (4)
    if (aGame.numberOfPlayers() >= Game.maximumNumberOfPlayers())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removePlayer(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addPlayer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    playersByNumber.remove(getNumber());
    startingTile = null;
    currentTile = null;
    Game placeholderGame = game;
    this.game = null;
    placeholderGame.removePlayer(this);
  }

  // line 21 "../../../../../TileOPersistence.ump"
   public static  void reinitializeUniqueNumber(List<Player> players){
    playersByNumber = new HashMap<Integer, Player>();
    for (Player player : players) {
      playersByNumber.put(player.getNumber(), player);
    }
  }

  // line 61 "../../../../../TileO.ump"
   public List<Tile> getPossibleMoves(int number){
    return this.getCurrentTile().getNeighbors(number);
  }

  // line 65 "../../../../../TileO.ump"
   public int roll(){
    Random random = new Random();
	  int rndNum = random.nextInt(6) + 1;
	  System.out.println(rndNum);
	  return rndNum;
  }

  // line 72 "../../../../../TileO.ump"
   public int getRandomNum(){
    return (int)System.nanoTime()%6+1;
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startingTile = "+(getStartingTile()!=null?Integer.toHexString(System.identityHashCode(getStartingTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentTile = "+(getCurrentTile()!=null?Integer.toHexString(System.identityHashCode(getCurrentTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 18 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 8896099581655989380L ;

  
}