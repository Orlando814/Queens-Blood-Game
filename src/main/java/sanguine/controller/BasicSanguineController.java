package sanguine.controller;

import sanguine.model.Sanguine;
import sanguine.model.card.BasicCard;
import sanguine.model.card.Card;
import sanguine.model.cell.Player;
import sanguine.strategy.MoveValues;
import sanguine.strategy.Strategy;
import sanguine.view.Position;
import sanguine.view.SanguineGuiView;

/**
 * represents the gui controller. This will allow for us to interact with the GUI.
 */
public class BasicSanguineController implements SanguineController, FeaturesListener {
  //TODO: Subscribe to model, view, and player / change constructor arguments

  private final SanguineGuiView view;
  private Sanguine model;
  private Position posn; //represents the values for the given move
  private Integer cardPos; //represents the posn for the given
  private final Player player; //the player for this given controller

  /**
   * generic constructor that will connect the gui and controller.
   *
   * @param view     the relevant view that we are using.
   * @param player   is the player that this controller represents.
   * @param strategy is the current strategy this controller is using (human or machine).
   * @throws IllegalArgumentException if any of the given arguments are null.
   */
  public BasicSanguineController(SanguineGuiView view, Player player) {
    if (view == null || player == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }
    this.view = view;
    this.player = player;
    this.view.subscribe(this);
  }

  @Override
  public void playGame(Sanguine model) {
    this.model = model;
    this.view.makeVisible();
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

  private void makeMove(MoveValues move) {
    if (move == null) {
      model.passMove();
    } else {
      this.model.placeCard(move.getCard(), move.getRow(), move.getCol());
    }
  }
  */

  @Override
  public void mouseEventBoard(int x, int y) {
    if (this.model.getPlayer() == this.player) {
      this.view.setPosn(new Position(x, y));
      int col = y - 1;
      if (col == this.model.getCols()
          || col == -1) {
        return;
      }
      this.posn = new Position(x, col);
      System.out.println(x + " " + col);
      this.view.refresh();
    }
  }

  @Override
  public void mouseEventHand(int cardIndex, Player p) {
    if (this.model.getPlayer() == this.player) {
      this.cardPos = cardIndex;
      this.view.clickCard(cardIndex);
      System.out.println(cardIndex + " Player = " + p.toString());
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
    if (this.model.getPlayer() == this.player) {
      if (key.equals("m")) {
        if (this.cardPos != null && this.cardPos >= 0
            && this.cardPos < this.model.getHandSize(this.player)) {
          System.out.println("Card pos: " + this.cardPos);
          Card card = this.model.getCardInHand(this.player, this.cardPos);
          BasicCard bc = (BasicCard) card;
          if (bc != null) {
            if (this.model.isValidMove(bc, this.posn.getX(), this.posn.getY(), this.player)) {
              System.out.println("Valid");
              this.model.placeCard(bc, this.posn.getX(), this.posn.getY());
            } else {
              this.view.showInvalidMove();
              System.out.println("Invalid");
            }
          }
        }
      } else if (key.equals("p")) {
        this.model.passMove();
      }
    }
    this.posn = null;
    this.cardPos = null;
    this.view.refresh();
  }
}
