package sanguine;

import java.util.List;
import sanguine.controller.BasicSanguineController;
import sanguine.controller.SanguineController;
import sanguine.model.BasicSanguine;
import sanguine.model.ReadOnlyBasicSanguine;
import sanguine.model.ReadOnlySanguine;
import sanguine.model.Sanguine;
import sanguine.model.card.Card;
import sanguine.model.cell.Player;
import sanguine.model.deck.DeckCreator;
import sanguine.model.deck.DeckCreatorImpl;
import sanguine.view.SanguineGuiFrame;
import sanguine.view.SanguineGuiView;

/**
 * This class contains the main method which launches the GUI version of the game.
 */
public final class SanguineGame {

  /**
   * This is the main method that runs the GUI version of the game.
   *
   * @param args are the provided user arguments.
   */
  public static void main(String[] args) {
    Sanguine model = new BasicSanguine();
    DeckCreator createDeck = new DeckCreatorImpl();
    List<Card> cardsPlayer1 = createDeck.createDeck(Player.PLAYER1, "config/red.deck");
    List<Card> cardsPlayer2 = createDeck.createDeck(Player.PLAYER2, "config/red.deck");
    model.startGame(5, 7, cardsPlayer1, cardsPlayer2);
    ReadOnlySanguine readOnlyModel = new ReadOnlyBasicSanguine(model);
    SanguineGuiView viewP1 = new SanguineGuiFrame(readOnlyModel, Player.PLAYER1);
    SanguineGuiView viewP2 = new SanguineGuiFrame(readOnlyModel, Player.PLAYER2);
    SanguineController controllerP1 = new BasicSanguineController(viewP1);
    SanguineController controllerP2 = new BasicSanguineController(viewP2);
    controllerP1.playGame(model);
    controllerP2.playGame(model);
    model.placeCard(model.getHand(Player.PLAYER1).getFirst(), 1, 0);
    model.placeCard(model.getHand(Player.PLAYER2).getFirst(), 0, 6);
    model.placeCard(model.getHand(Player.PLAYER1).getFirst(), 0, 0);
    model.placeCard(model.getHand(Player.PLAYER2).get(3), 1, 6);
    model.placeCard(model.getHand(Player.PLAYER1).get(4), 2, 0);
    model.placeCard(model.getHand(Player.PLAYER2).getFirst(), 2, 6);
    model.placeCard(model.getHand(Player.PLAYER1).get(3), 1, 1);
    model.placeCard(model.getHand(Player.PLAYER2).get(3), 3, 6);
    model.placeCard(model.getHand(Player.PLAYER1).get(1), 0, 1);
    model.placeCard(model.getHand(Player.PLAYER2).get(3), 4, 6);
  }
}