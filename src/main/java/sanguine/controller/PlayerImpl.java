package sanguine.controller;

import sanguine.model.ReadOnlySanguine;
import sanguine.model.cell.Player;
import sanguine.strategy.MoveValues;

/**
 * This represents the implementation of a human player. Since a human is making all the moves
 * there will not be a lot of implementation made.
 */
public class PlayerImpl implements PlayerAction {
  private final Player player;

  /**
   * This is the generic constructor for this class. Takes in which player this implementation is
   * tied to.
   *
   * @param player is the player this implementation is tied to.
   * @throws IllegalArgumentException if one of the given arguments are null.
   */
  public PlayerImpl(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("An argument is null");
    }
    this.player = player;
  }

  @Override
  public Player getPlayer() {
    return this.player;
  }

  @Override
  public MoveValues makeMove(ReadOnlySanguine model) {
    return null;
  }

  @Override
  public void subscribe(PlayerFeaturesListener listener) {
  }
}
