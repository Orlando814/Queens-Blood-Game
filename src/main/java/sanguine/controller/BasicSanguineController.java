package sanguine.controller;

import sanguine.model.Sanguine;
import sanguine.model.card.BasicCard;
import sanguine.model.card.Card;
import sanguine.model.cell.Player;
import sanguine.strategy.MoveValues;
import sanguine.view.Position;
import sanguine.view.SanguineGuiView;

/**
 * represents the gui controller. This will allow for us to interact with the GUI.
 */
public class BasicSanguineController implements SanguineController, ViewFeaturesListener,
    ModelFeaturesListener, PlayerFeaturesListener {

  private final SanguineGuiView view;
  private Sanguine model;
  private Position posn; //represents the values for the given move
  private Integer cardPos; //represents the posn for the given
  private final PlayerAction player; //the player for this given controller
  private Player currentTurnPlayer; //the player whose current turn it is

  /**
   * generic constructor that will connect the gui and controller.
   *
   * @param view   the relevant view that we are using.
   * @param player is the player that this controller represents.
   * @throws IllegalArgumentException if any of the given arguments are null.
   */
  public BasicSanguineController(SanguineGuiView view, PlayerAction player) {
    if (view == null || player == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.view = view;
    this.player = player;
    this.view.subscribe(this);
    this.player.subscribe(this);
  }

  @Override
  public void playGame(Sanguine model) {
    this.model = model;
    this.currentTurnPlayer = this.player.getPlayer();
    this.model.subscribe(this);
    this.view.makeVisible();
    //this.makeMove();
  }
    /*//while loop for the game to continue while the game is not over
    while (!(model.isGameOver())) {
      if (this.model.getPlayer() == this.player) {
        this.makeMove(strategy.implementMove(model, player));
      }
    }
    this.view.showGameOver(this.model.whoWon(), this.model.totalScore(this.model.whoWon()));
    System.out.println("game over lol");
  }
  */

  //TODO: Don't know how to do this (maybe recusion???) not sure
  private void makeMove() {
    MoveValues move = this.player.makeMove(this.model);
    if (move == null) {
      model.passMove();
    } else {
      this.model.placeCard(move.getCard(), move.getRow(), move.getCol());
    }
  }

  @Override
  public void mouseEventBoard(int x, int y) {
    if (this.player.getPlayer() == this.currentTurnPlayer) {
      this.view.setPosn(new Position(x, y));
      int col = y - 1;
      if (col == this.model.getCols()
          || col == -1) {
        return;
      }
      this.posn = new Position(x, col);
      this.view.refresh();
    }
  }

  @Override
  public void mouseEventHand(int cardIndex, Player p) {
    if (this.player.getPlayer() == this.currentTurnPlayer) {
      this.cardPos = cardIndex;
      this.view.clickCard(cardIndex);
      this.view.refresh();
    }
  }

  /**
   * will determine what happens depending on what the key pressed is.
   *
   * @param key the key that the user pressed.
   */
  @Override
  public void keyClicked(String key) {
    if (this.player.getPlayer() == this.currentTurnPlayer) {
      if (key.equals("m")) {
        if (this.cardPos != null && this.cardPos >= 0
            && this.cardPos < this.model.getHandSize(this.currentTurnPlayer)) {
          Card card = this.model.getCardInHand(this.currentTurnPlayer, this.cardPos);
          BasicCard bc = (BasicCard) card;
          if (bc != null) {
            if (this.model.isValidMove(bc, this.posn.getX(), this.posn.getY(),
                this.currentTurnPlayer)) {
              this.model.placeCard(bc, this.posn.getX(), this.posn.getY());
              this.view.clickCard(-1);
            } else {
              this.view.showInvalidMove();
            }
          } else {
            this.view.showInvalidMove();
          }
        } else {
          this.view.showInvalidMove();
        }
      } else if (key.equals("p")) {
        this.model.passMove();
        this.view.clickCard(-1);
      }
    }
    this.posn = null;
    this.cardPos = null;
    this.view.refresh();
  }

  @Override
  public void whoseTurn(Player player) {
    this.currentTurnPlayer = player;
  }

  @Override
  public void hasPlayerMoved(boolean moveMade) {

  }
}
