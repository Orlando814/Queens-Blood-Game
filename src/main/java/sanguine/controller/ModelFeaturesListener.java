package sanguine.controller;

import sanguine.model.cell.Player;

/**
 * Represents a listener that is listening to the model. Holds reference to specifically whose turn
 * it is.
 */
public interface ModelFeaturesListener {

  /**
   * Listening for whose turn it currently is.
   *
   * @param player is the player whose turn it currently is.
   */
  void whoseTurn(Player player);
}
