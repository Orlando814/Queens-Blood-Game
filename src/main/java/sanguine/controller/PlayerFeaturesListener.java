package sanguine.controller;

/**
 * holds reference to the event when a player makes a move. Represents a listener whose listening
 * to a player.
 */
public interface PlayerFeaturesListener {

  /**
   * Will listen for when the current player has made their move.
   *
   * @param moveMade is the boolean value representing when the current player has made their move.
   */
  void hasPlayerMoved(boolean moveMade);
}
