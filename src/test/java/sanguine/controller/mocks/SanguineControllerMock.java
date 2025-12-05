package sanguine.controller.mocks;

import java.io.IOException;
import sanguine.controller.ModelFeaturesListener;
import sanguine.controller.SanguineController;
import sanguine.controller.ViewFeaturesListener;
import sanguine.model.Sanguine;
import sanguine.model.cell.Player;

/**
 * mock for our sanguine controller, will just go through the steps and make sure that everything
 * works as intended while unit testing.
 */
public class SanguineControllerMock implements SanguineController, ViewFeaturesListener,
    ModelFeaturesListener {

  Appendable log;

  public SanguineControllerMock(StringBuilder log) {
    if (this.log == null) {
      throw new IllegalArgumentException("Log has to like not be null pookie");
    }
    this.log = log;
  }

  @Override
  public void playGame(Sanguine model) {
    try {
      log.append("Started Game with model");
    } catch (IOException e) {
      int sixSeven = 67;
    }
  }

  @Override
  public void whoseTurn(Player player) {
    try {
      log.append("Called whose turn with Player: " + player);
    } catch (IOException e) {
      int sixSeven = 67;
    }
  }

  @Override
  public void gameOver(Player player) {
    try {
      log.append("Called gameOver with player: " + player);
    } catch (IOException e) {
      int sixSeven = 67;
    }
  }

  @Override
  public void mouseEventBoard(int x, int y) {
    try {
      log.append("Called mouseEventBoard with X: " + x + " Y: " + y);
    } catch (IOException e) {
      int sixSeven = 67;
    }
  }

  @Override
  public void mouseEventHand(int cardIndex, Player player) {
    try {
      log.append("Called mouseEventHand with cardIndex: " + cardIndex + "and player: "
          + player);
    } catch (IOException e) {
      int sixSeven = 67;
    }
  }

  @Override
  public void keyClicked(String key) {
    try {
      log.append("Called keyClicked with key: " + key);
    } catch (IOException e) {
      int sixSeven = 67;
    }
  }
}
