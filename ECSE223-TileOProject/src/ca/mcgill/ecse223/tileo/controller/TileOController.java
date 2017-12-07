/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.controller;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import ca.mcgill.ecse223.tileo.application.Main;
import ca.mcgill.ecse223.tileo.controller.TileOController.Mode;
import ca.mcgill.ecse223.tileo.model.SwapPositionActionCard;
import ca.mcgill.ecse223.tileo.model.MoveWintileActionCard;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ShowActionTilesActionCard;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.ChangeTileActionCard;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Die;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.LoseTurnActionCard;
import ca.mcgill.ecse223.tileo.model.LoseTurnRandomActionCard;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Player.PlayerStatus;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.SwitchPlayersActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.ChooseAdditionalMoveActionCard;

// line 3 "../../../../../TileOController.ump"
public class TileOController
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TileOController State Machines
  public enum Mode { Ready, Roll, Move, ActionCard, Won }
  public Mode mode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TileOController()
  {
    setMode(Mode.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getModeFullName()
  {
    String answer = mode.toString();
    return answer;
  }

  public Mode getMode()
  {
    return mode;
  }

  public boolean StartGame()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Ready:
        // line 37 "../../../../../TileOController.ump"
        StartGame();
        setMode(Mode.Roll);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean load(Game selectedGame)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Ready:
        if (isInGameMode(selectedGame))
        {
        // line 41 "../../../../../TileOController.ump"
          doLoad(selectedGame);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        if (isInWonMode(selectedGame))
        {
        // line 45 "../../../../../TileOController.ump"
          doLoad(selectedGame);
          setMode(Mode.Won);
          wasEventProcessed = true;
          break;
        }
        if (isNotInGameOrWonMode(selectedGame))
        {
        // line 49 "../../../../../TileOController.ump"
          doLoad(selectedGame);
          setMode(Mode.ActionCard);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean rollDie(LinkedList<Tile> tiles,int [] numrolled,Game agame,int times)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Roll:
        // line 55 "../../../../../TileOController.ump"
        doRollDie(tiles,numrolled,agame,times);
        setMode(Mode.Move);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean land(Tile tile)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Move:
        if (isNormalTile(tile))
        {
        // line 61 "../../../../../TileOController.ump"
          doLandTile(tile);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        if (isWinTile(tile))
        {
        // line 65 "../../../../../TileOController.ump"
          doLandTile(tile);
          setMode(Mode.Won);
          wasEventProcessed = true;
          break;
        }
        if (isActionTile(tile))
        {
        // line 69 "../../../../../TileOController.ump"
          doLandTile(tile);
          setMode(Mode.ActionCard);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playRollDieActionCard(LinkedList<Tile> tiles) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isRollDieActionCard())
        {
        // line 75 "../../../../../TileOController.ump"
          doPlayRollDieActionCard(tiles);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playConnectTilesActionCard(Tile tile1,Tile tile2) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isConnectTilesActionCard())
        {
        // line 78 "../../../../../TileOController.ump"
          doPlayConnectTilesActionCard(tile1, tile2);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playRemoveConnectionActionCard(Connection c) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isRemoveConnectionActionCard())
        {
        // line 81 "../../../../../TileOController.ump"
          doPlayRemoveConnectionActionCard(c);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playTeleportActionCard(Tile tile) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isTeleportAndNormalTile(tile))
        {
        // line 84 "../../../../../TileOController.ump"
          doPlayTeleportActionCard(tile);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        if (isTeleportAndWinTile(tile))
        {
        // line 89 "../../../../../TileOController.ump"
          doPlayTeleportActionCard(tile);
          setMode(Mode.Won);
          wasEventProcessed = true;
          break;
        }
        if (isTeleportAndActionTile(tile))
        {
        // line 93 "../../../../../TileOController.ump"
          doPlayTeleportActionCard(tile);
          setMode(Mode.ActionCard);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playLoseTurnActionCard(Player p)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isLoseTurnActionCard())
        {
        // line 97 "../../../../../TileOController.ump"
          doPlayLoseTurnActionCard(p);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playLoseTurnRandomActionCard()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isLoseTurnRandomActionCard())
        {
        // line 101 "../../../../../TileOController.ump"
          doPlayLoseTurnRandomActionCard();
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playChangeTileActionCard(Tile atile,int num)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isChangeTileActionCard())
        {
        // line 105 "../../../../../TileOController.ump"
          doPlayChangeTileActionCard(atile,num);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playSwitchPlayersActionCard(int num)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isSwitchPlayersActionCard())
        {
        // line 109 "../../../../../TileOController.ump"
          doPlaySwitchPlayersActionCard(num);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }
  public boolean playSwapPositionActionCard(int num)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isSwapPositionActionCard())
        {
        // line 112 "../../../../../TileOController.ump"
          doPlaySwapPositionActionCard(num);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playMoveWinTileActionCard(int x,int y,Game current)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isMoveWinTileActionCard())
        {
        // line 112 "../../../../../TileOController.ump"
          doMoveWinTileActionCard(x,y,current);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playShowActionTilesActionCard()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isShowActionTilesActionCard())
        {
        // line 115 "../../../../../TileOController.ump"
          doPlayShowActionTilesActionCard();
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playChooseAdditionalMoveActionCard(LinkedList<Tile> tiles,int steps)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isChooseAdditionalMoveActionCard())
        {
        // line 118 "../../../../../TileOController.ump"
          doChooseAdditionalMoveActionCard(tiles,steps);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean win()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Won:
        // line 123 "../../../../../TileOController.ump"
        
        setMode(Mode.Ready);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setMode(Mode aMode)
  {
    mode = aMode;
  }

  public void delete()
  {}

  // line 130 "../../../../../TileOController.ump"
   public void CreateAGame(TileO tileO){
    tileO.addGame(new Game(0, tileO));
		tileO.setCurrentGame(tileO.getGame(tileO.numberOfGames() - 1));
  }

  // line 136 "../../../../../TileOController.ump"
   public void CreateATile(int x, int y){
    
  }

  // line 139 "../../../../../TileOController.ump"
   public Tile addNormalTile(int i, int j, Game currentgame){
    Tile temp = new NormalTile(i, j, currentgame);
		currentgame.addTile(temp);
		return temp;
  }

  // line 146 "../../../../../TileOController.ump"
   public void addActionTile(int i, int j, Game currentgame, int period){
    currentgame.addTile(new ActionTile(i, j, currentgame, period));
  }

  // line 151 "../../../../../TileOController.ump"
   private boolean isMoveWinTileActionCard(){
    TileO tileo = Main.getTileO();
		ActionCard currentCard = tileo.getCurrentGame().getDeck().getCurrentCard();

		if (currentCard instanceof MoveWintileActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 162 "../../../../../TileOController.ump"
   public void addHiddenTile(int i, int j, Game currentgame){
    WinTile wintile = new WinTile(i, j, currentgame);
		currentgame.addTile(wintile);
		currentgame.setWinTile(wintile);
  }

  // line 168 "../../../../../TileOController.ump"
   public boolean sideBySide(Tile tile1, Tile tile2){
    if (tile1.getX() == tile2.getX()) {
			if (tile1.getY() == tile2.getY() + 1 || tile1.getY() == tile2.getY() - 1) {
				return true;
			} else {
				return false;
			}
		} else if (tile1.getY() == tile2.getY()) {
			if (tile1.getX() == tile2.getX() + 1 || tile1.getX() == tile2.getX() - 1) {
				return true;
			} else {
				return false;
			}
		} else
			return false;
  }

  // line 185 "../../../../../TileOController.ump"
   public void connectTiles(Tile tile1, Tile tile2, Game selectedGame) throws InvalidInputException{
    if (this.sideBySide(tile1, tile2) == false) {
			throw new InvalidInputException("These tiles are not side by side");
		}

		for (int i = 0; i < tile1.numberOfConnections(); i++) {
			if (tile1.getConnection(i).getTile(0) == tile2 || tile1.getConnection(i).getTile(1) == tile2) {
				throw new InvalidInputException("These tiles are already connected");
			}
		}
		Connection newConnect = new Connection(selectedGame);
		newConnect.addTile(tile1);
		newConnect.addTile(tile2);
  }

  // line 200 "../../../../../TileOController.ump"
   public void removeConnect(Tile tile1, Tile tile2, Game selectedGame) throws InvalidInputException{
    for (int i = 0; i < tile1.numberOfConnections(); i++) {
			if (tile1.getConnection(i).getTile(0) == tile2 || tile1.getConnection(i).getTile(1) == tile2) {
				tile1.getConnection(i).delete();
				return;
			}
		}
    
		throw new InvalidInputException("These tiles were not connected");
  }

  // line 260 "../../../../../TileOController.ump"
   public void startGame(Game selectedGame) throws InvalidInputException{
    TileO tileO = Main.getTileO();
		tileO.setCurrentGame(selectedGame);
		if (!selectedGame.getDeck().hasCards()) {
			throw new InvalidInputException("No Action Cards");
		} else if (!selectedGame.hasWinTile()) {
			throw new InvalidInputException("No Win Tile");
		}

		for (Player player : selectedGame.getPlayers()) {
			if (!player.hasStartingTile()) {
				throw new InvalidInputException("No Starting Tile");
			}
		}
		Deck deck = selectedGame.getDeck();
		deck.shuffle();

		for (Tile tile : selectedGame.getTiles()) {
			tile.setHasBeenVisited(false);
		}

		for (Player player : selectedGame.getPlayers()) {
			Tile startingTile = player.getStartingTile();
			player.setCurrentTile(startingTile);
			startingTile.setHasBeenVisited(true);
		}

		selectedGame.setCurrentPlayer(selectedGame.getPlayers().get(0));

		selectedGame.setCurrentConnectionPieces(Game.SpareConnectionPieces);

		selectedGame.setMode(selectedGame.getMode().GAME);
  }

  // line 293 "../../../../../TileOController.ump"
   private void doMoveWinTileActionCard(int i, int j, Game currentgame){
    for (Tile tile : currentgame.getTiles()) {
		if (tile.getX()==i && tile.getY()==j) {
			WinTile wt = new WinTile(i, j, currentgame);
			for (Tile t : tile.getNeighbors(1)) {
				try {
					removeConnect(t, tile, currentgame);
					connectTiles(wt, t, currentgame);
				} catch (InvalidInputException e) {
					e.printStackTrace();
				}
			}
			currentgame.setWinTile(wt);
			tile.delete();
			
			Deck deck = currentgame.getDeck();
			ActionCard currentCard = currentgame.getDeck().getCurrentCard();
			if (deck.indexOfCard(currentCard) == (deck.numberOfCards() - 1)) {
				deck.shuffle();
				deck.setCurrentCard(deck.getCard(0));
			} else {
				deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard) + 1));
			}
			currentgame.setMode(currentgame.getMode().GAME);
			return;
		}
	}
  }

  // line 313 "../../../../../TileOController.ump"
   public void doLandTile(Tile tile){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		List<Tile> tiles = currentGame.getTiles();
		if (tiles.contains(tile)) {
			tile.land();
			currentGame.getCurrentPlayer().setCurrentTile(tile);
		}
  }

  // line 323 "../../../../../TileOController.ump"
   private boolean isRollDieActionCard(){
    TileO tileo = Main.getTileO();
		ActionCard currentCard = tileo.getCurrentGame().getDeck().getCurrentCard();
		if (currentCard instanceof RollDieActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 333 "../../../../../TileOController.ump"
   public void determineNextPlayer(){
    TileO tileo = Main.getTileO();
		boolean found = false;
		Player player = tileo.getCurrentGame().getCurrentPlayer();
		Player nextPlayer;

		while (!found) {
			try {
				nextPlayer = tileo.getCurrentGame().getPlayer(tileo.getCurrentGame().indexOfPlayer(player) + 1);
				//system.out.println("player" + nextPlayer.getNumber());
			} catch (IndexOutOfBoundsException e) {
				nextPlayer = tileo.getCurrentGame().getPlayer(0);
				//system.out.println("new round");
			}
			if (nextPlayer.getPlayerStatus() == PlayerStatus.Active) {
				found = true;
			} else {
				nextPlayer.takeTurn();
			}
			player = nextPlayer;
		}
		tileo.getCurrentGame().setCurrentPlayer(player);
		//system.out.println("Current player now" + player.getNumber());
  }

  // line 358 "../../../../../TileOController.ump"
   public void doPlayRollDieActionCard(LinkedList<Tile> tiles) throws InvalidInputException{
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Deck deck = currentGame.getDeck();
		ActionCard currentCard = deck.getCurrentCard();
		if (currentCard instanceof RollDieActionCard) {
			tiles = (LinkedList<Tile>) ((RollDieActionCard) currentCard).play();
		} else {
			throw new InvalidInputException("Current card is not a RollDieActionCard");
		}
		if (deck.indexOfCard(currentCard) == (deck.numberOfCards() - 1)) {
			deck.shuffle();
			deck.setCurrentCard(deck.getCard(0));
		} else {
			deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard) + 1));
		}
		currentGame.setMode(currentGame.getMode().GAME);
  }

  // line 378 "../../../../../TileOController.ump"
   public void doRollDie(LinkedList<Tile> tiles, int [] numrolled, Game agame, int times){
    Game currentGame = agame;
		Die die = currentGame.getDie();
		numrolled[0] = die.roll();
		Player currentPlayer = currentGame.getCurrentPlayer();
		while (!tiles.isEmpty()) {
			tiles.remove();
			
		}
		for (Tile tile : currentPlayer.getPossibleMoves(numrolled[0])) {
			tiles.add(tile);
		}
		if (tiles.size()==0&&times<500) {
			doRollDie(tiles, numrolled, agame,times+1);
		}
  }

  // line 394 "../../../../../TileOController.ump"
   private boolean isSwapPositionActionCard(){
    TileO tileO = Main.getTileO();
		ActionCard currentCard = tileO.getCurrentGame().getDeck().getCurrentCard();
		if (currentCard instanceof SwapPositionActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 404 "../../../../../TileOController.ump"
   private void doSwapPositionActionCard(Player player){

    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Player currentPlayer = currentGame.getCurrentPlayer();
		boolean worked = player.setCurrentTile(currentPlayer.getCurrentTile());
		boolean worked2 = currentPlayer.setCurrentTile(player.getCurrentTile());
		currentGame.setMode(currentGame.getMode().GAME);

  }

  // line 416 "../../../../../TileOController.ump"
   public void doPlayConnectTilesActionCard(Tile tile1, Tile tile2) throws InvalidInputException{
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Deck deck = currentGame.getDeck();
		Player currentPlayer = currentGame.getCurrentPlayer();
		List<Tile> tiles = currentGame.getTiles();

		// check:1.tile1,tile2 exist 2.they are adjacent
		// 3.currentConnectionPieces greater than 0

		if (tiles.contains(tile1) || tiles.contains(tile2) || tile1.isAdjacent(tile2)
				|| currentGame.getCurrentConnectionPieces() > 0) {
			ActionCard currentCard = currentGame.getDeck().getCurrentCard();
			if (currentCard instanceof ConnectTilesActionCard) {
				ConnectTilesActionCard currentConnectCard = (ConnectTilesActionCard) currentCard;
				currentConnectCard.play(tile1, tile2);
				// check
				if (currentPlayer.getNumber() == currentGame.numberOfPlayers())
					currentGame.setCurrentPlayer(currentGame.getPlayer(1));
				else
					currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer) + 1));
				// check
				if (deck.indexOfCard(deck.getCurrentCard()) == Deck.maximumNumberOfCards()) {
					deck.shuffle();
					deck.setCurrentCard(deck.getCard(1));
				} else {
					deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard) + 1));
				}
				currentGame.setMode(currentGame.getMode().GAME);
			}

			else
				throw new InvalidInputException("Not ConnectTilesActionCard");
		}

		else
			throw new InvalidInputException("Not Valid Tiles");
  }

  // line 455 "../../../../../TileOController.ump"
   private boolean isRemoveConnectionActionCard(){
    TileO tileo = Main.getTileO();
		ActionCard currentCard = tileo.getCurrentGame().getDeck().getCurrentCard();

		if (currentCard instanceof RemoveConnectionActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 466 "../../../../../TileOController.ump"
   private boolean isTeleportAndWinTile(Tile tile){
    if (tile instanceof WinTile) {
			return true;
		} else {
			return false;
		}
  }

  // line 474 "../../../../../TileOController.ump"
   private boolean isLoseTurnActionCard(){
    TileO tileo = Main.getTileO();
		ActionCard currentCard = tileo.getCurrentGame().getDeck().getCurrentCard();

		if (currentCard instanceof LoseTurnActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 485 "../../../../../TileOController.ump"
   private boolean isLoseTurnRandomActionCard(){
    TileO tileo = Main.getTileO();
		ActionCard currentCard = tileo.getCurrentGame().getDeck().getCurrentCard();
		if (currentCard instanceof LoseTurnRandomActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 496 "../../../../../TileOController.ump"
   private boolean isTeleportAndActionTile(Tile tile){
    if (tile instanceof ActionTile) {
			return true;
		} else {
			return false;
		}
  }

  // line 504 "../../../../../TileOController.ump"
   private boolean isTeleportAndNormalTile(Tile tile){
    if (tile instanceof NormalTile) {
			return true;
		} else {
			return false;
		}
  }

  // line 512 "../../../../../TileOController.ump"
   public void doPlayRemoveConnectionActionCard(Connection connection) throws InvalidInputException{
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Deck deck = currentGame.getDeck();
		Player currentPlayer = currentGame.getCurrentPlayer();
		List<Connection> connections = currentGame.getConnections();
		// check if connection exists
		if (connections.contains(connection)) {
			ActionCard currentCard = currentGame.getDeck().getCurrentCard();
			if (currentCard instanceof RemoveConnectionActionCard) {
				RemoveConnectionActionCard currentRemoveConnectionCard = (RemoveConnectionActionCard) currentCard;
				currentRemoveConnectionCard.play(connection);

				// check
				if (currentPlayer.getNumber() == currentGame.numberOfPlayers())
					currentGame.setCurrentPlayer(currentGame.getPlayer(1));
				else
					currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer) + 1));
				// check
				if (deck.indexOfCard(deck.getCurrentCard()) == Deck.maximumNumberOfCards()) {
					deck.shuffle();
					deck.setCurrentCard(deck.getCard(1));
				} else {
					deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard) + 1));
				}
				currentGame.setMode(currentGame.getMode().GAME);
			} else
				throw new InvalidInputException("Not RemoveConnectionActionCard");
		} else
			throw new InvalidInputException("Not Valid Connection");
  }

  // line 544 "../../../../../TileOController.ump"
   public void doPlayTeleportActionCard(Tile tile) throws InvalidInputException{
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Deck deck = currentGame.getDeck();
		Player currentPlayer = currentGame.getCurrentPlayer();
		List<Tile> tiles = currentGame.getTiles();
		Tile currentTile = currentPlayer.getCurrentTile();
		// check 1.if tile exists 2.tile is not currentTile
		if (tiles.contains(tile) || currentTile.equals(tile) == false) {
			ActionCard currentCard = currentGame.getDeck().getCurrentCard();
			// check
			if (deck.indexOfCard(deck.getCurrentCard()) == Deck.maximumNumberOfCards()) {
				deck.shuffle();
				deck.setCurrentCard(deck.getCard(1));
			} else {
				deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard) + 1));
			}

			if (currentCard instanceof TeleportActionCard) {
				TeleportActionCard currentTeleportCard = (TeleportActionCard) currentCard;
				currentTeleportCard.play(tile);
			} else
				throw new InvalidInputException("Not TeleportActionCard");
		} else
			throw new InvalidInputException("Not Valid Tile");
  }

  // line 571 "../../../../../TileOController.ump"
   public void RemoveTile(int i, int j, Game currentgame){
    Tile pointer;
		for (int k = 0; k < currentgame.numberOfTiles(); k++) {
			pointer = currentgame.getTile(k);
			if (pointer.getX() == i && pointer.getY() == j) {
				currentgame.removeTile(pointer);
			}
		}
  }

  // line 582 "../../../../../TileOController.ump"
   private boolean isInWonMode(Game selectedGame){
    return (selectedGame.getMode() == selectedGame.getMode().GAME_WON);
  }


  /**
   * line 504 "../../../../../TileOController.ump"
   */
  // line 587 "../../../../../TileOController.ump"
   private boolean isInGameMode(Game selectedGame){
    return (selectedGame.getMode() == selectedGame.getMode().GAME);
  }

  // line 591 "../../../../../TileOController.ump"
   private boolean isNotInGameOrWonMode(Game selectedGame){
    if ((!isInWonMode(selectedGame)) && (!isInGameMode(selectedGame))) {
			return true;
		}
		return false;
  }

  // line 598 "../../../../../TileOController.ump"
   private boolean isActionTile(Tile tile){
    if (tile instanceof ActionTile) {
			return true;
		} else {
			return false;
		}
  }

  // line 606 "../../../../../TileOController.ump"
   private boolean isWinTile(Tile tile){
    if (tile instanceof WinTile) {
			return true;
		} else {
			return false;
		}
  }

  // line 614 "../../../../../TileOController.ump"
   private boolean isNormalTile(Tile tile){
    if (tile instanceof NormalTile) {
			return true;
		} else {
			return false;
		}
  }

  // line 622 "../../../../../TileOController.ump"
   private boolean isConnectTilesActionCard(){
    TileO tileo = Main.getTileO();
		ActionCard currentCard = tileo.getCurrentGame().getDeck().getCurrentCard();

		if (currentCard instanceof ConnectTilesActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 633 "../../../../../TileOController.ump"
   private void doPlayLoseTurnRandomActionCard(){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Deck deck = currentGame.getDeck();
		List<Player> players = currentGame.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			players.get(i).loseTurnsRandom();
		}

		if (deck.indexOfCard(deck.getCurrentCard()) == deck.getCards().size() - 1) {
			deck.shuffle();
			deck.setCurrentCard(deck.getCard(0));
		} else {
			deck.setCurrentCard(deck.getCard(deck.indexOfCard(deck.getCurrentCard()) + 1));
		}
		currentGame.setMode(currentGame.getMode().GAME);
  }

  // line 652 "../../../../../TileOController.ump"
   private boolean isChangeTileActionCard(){
    TileO tileO = Main.getTileO();
		ActionCard currentCard = tileO.getCurrentGame().getDeck().getCurrentCard();
		if (currentCard instanceof ChangeTileActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 662 "../../../../../TileOController.ump"
   private void doPlayChangeTileActionCard(Tile tile, int inactivityPeriod){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		if (tile instanceof NormalTile) {
			ActionTile newTile = new ActionTile(tile.getX(), tile.getY(), tile.getGame(), inactivityPeriod);
			currentGame.removeTile(tile);
			currentGame.addTile(newTile);
			tile.delete();
		}
		Deck deck = currentGame.getDeck();
		ActionCard currentCard = currentGame.getDeck().getCurrentCard();
		if (deck.indexOfCard(currentCard) == (deck.numberOfCards() - 1)) {
			deck.shuffle();
			deck.setCurrentCard(deck.getCard(0));
		} else {
			deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard) + 1));
		}
		currentGame.setMode(currentGame.getMode().GAME);
		currentGame.setMode(currentGame.getMode().GAME);
  }

  // line 673 "../../../../../TileOController.ump"
   private boolean isSwitchPlayersActionCard(){
    TileO tileO = Main.getTileO();
		ActionCard currentCard = tileO.getCurrentGame().getDeck().getCurrentCard();
		if (currentCard instanceof SwitchPlayersActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 683 "../../../../../TileOController.ump"
   private void doPlaySwitchPlayersActionCard(int p){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Player currentPlayer = currentGame.getCurrentPlayer();
		int i = currentGame.indexOfPlayer(currentGame.getCurrentPlayer());
		currentGame.addOrMovePlayerAt(currentGame.getPlayer(p), i);
		currentGame.addOrMovePlayerAt(currentPlayer, p);
		currentGame.setCurrentPlayer(currentGame.getPlayer(i));
		Deck deck = currentGame.getDeck();
		ActionCard currentCard = currentGame.getDeck().getCurrentCard();
		if (deck.indexOfCard(currentCard) == (deck.numberOfCards() - 1)) {
			deck.shuffle();
			deck.setCurrentCard(deck.getCard(0));
		} else {
			deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard) + 1));
		}
		currentGame.setMode(currentGame.getMode().GAME);
  }

  // line 694 "../../../../../TileOController.ump"
   private boolean isChooseAdditionalMoveActionCard(){
    TileO tileO =Main.getTileO();
        ActionCard currentCard = tileO.getCurrentGame().getDeck().getCurrentCard();
        if (currentCard instanceof ChooseAdditionalMoveActionCard) {
			return true;
		} else {
			return false;
		}
  }

  // line 703 "../../../../../TileOController.ump"
   private void doChooseAdditionalMoveActionCard(LinkedList<Tile> tiles, int steps){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Player currentPlayer = currentGame.getCurrentPlayer();
		while (!tiles.isEmpty()) {
			tiles.remove();
			
		}
		for (Tile tile : currentPlayer.getPossibleMoves(steps)) {
			tiles.add(tile);
		}
		currentGame.setMode(currentGame.getMode().GAME);
  }
   
   // line 695 "../../../../../TileOController.ump"
	private void doPlaySwapPositionActionCard(int p) {
		TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Player player = currentGame.getPlayer(p);
		Player currentPlayer = currentGame.getCurrentPlayer();
		Tile temp = currentPlayer.getCurrentTile();
		boolean worked2 = currentPlayer.setCurrentTile(player.getCurrentTile());
		boolean worked = player.setCurrentTile(temp);
		Deck deck = currentGame.getDeck();
		ActionCard currentCard = currentGame.getDeck().getCurrentCard();
		if (deck.indexOfCard(currentCard) == (deck.numberOfCards() - 1)) {
			deck.shuffle();
			deck.setCurrentCard(deck.getCard(0));
		} else {
			deck.setCurrentCard(deck.getCard(deck.indexOfCard(currentCard) + 1));
		}
		currentGame.setMode(currentGame.getMode().GAME);
		currentGame.setMode(currentGame.getMode().GAME);

	}

  // line 716 "../../../../../TileOController.ump"
   private void doLoad(Game selectedGame){
    TileO tileo = Main.getTileO();
		tileo.setCurrentGame(selectedGame);
  }

  // line 724 "../../../../../TileOController.ump"
   private void doPlayShowActionTilesActionCard(){
    Game currentGame = Main.getTileO().getCurrentGame();
		Deck deck = currentGame.getDeck();
					
			if (deck.indexOfCard(deck.getCurrentCard()) == deck.getCards().size()-1) {
				deck.shuffle();
				deck.setCurrentCard(deck.getCard(0));
			} else {
				deck.setCurrentCard(deck.getCard(deck.indexOfCard(deck.getCurrentCard()) + 1));
			}
			currentGame.setMode(Game.Mode.GAME);
  }

  // line 738 "../../../../../TileOController.ump"
   private boolean isShowActionTilesActionCard(){
    TileO tileo = Main.getTileO();
			ActionCard currentCard = tileo.getCurrentGame().getDeck().getCurrentCard();

			if (currentCard instanceof ShowActionTilesActionCard) {
				return true;
			} else {
				return false;
			}
  }

  // line 749 "../../../../../TileOController.ump"
   public void shuffle(Game game){
    Deck deck=game.getDeck();
	    deck.shuffle();
  }

  // line 755 "../../../../../TileOController.ump"
   private void doPlayLoseTurnActionCard(Player aPlayer){
    TileO tileO = Main.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Deck deck = currentGame.getDeck();

		aPlayer.loseTurns(1);

		if (deck.indexOfCard(deck.getCurrentCard()) == deck.getCards().size() - 1) {
			deck.shuffle();
			deck.setCurrentCard(deck.getCard(0));
		} else {
			deck.setCurrentCard(deck.getCard(deck.indexOfCard(deck.getCurrentCard()) + 1));
		}
		currentGame.setMode(currentGame.getMode().GAME);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 209 ../../../../../TileOController.ump
  public void setcards (JButton instructionsbuttons[], Game current) throws InvalidInputException 
  {
    Deck dk = current.getDeck();
		String[] type = { "", "Connect Tiles", "Lose Turn", "Remove Connection", "Teleport", "Roll Again",
				"Lose Random Turns", "Change Tile", "Switch Player Turns","Show Action Tiles", "Choose A Move","Swap Position"};
		for (ActionCard card : dk.getCards()) {
			card.delete();
		}
		for (int i = 0; i < instructionsbuttons.length; i++) {
			switch (instructionsbuttons[i].getText()) {
			case "Connect Tiles":
				dk.addCard(new ConnectTilesActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Lose Turn":
				dk.addCard(new LoseTurnActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Remove Connection":
				dk.addCard(new RemoveConnectionActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Teleport":
				dk.addCard(new TeleportActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Roll Again":
				dk.addCard(new RollDieActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Lose Random Turns":
				dk.addCard(new LoseTurnRandomActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Change Tile":
				dk.addCard(new ChangeTileActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Switch Player Turns":
				dk.addCard(new SwitchPlayersActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Move Win Tile":
				dk.addCard(new MoveWintileActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Swap Position":
				dk.addCard(new SwapPositionActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Show Action Tiles":
				dk.addCard(new ShowActionTilesActionCard(instructionsbuttons[i].getText(), dk));
				break;
			case "Choose A Move":
			    dk.addCard(new ChooseAdditionalMoveActionCard(instructionsbuttons[i].getText(), dk));
			    break;
			default:
				break;
			}
		}
  }

  
}