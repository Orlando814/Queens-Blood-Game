package sanguine.controller;

import sanguine.model.Sanguine;

/**
 * This is the controller for the Sanguine game. Is responsible for listening to the view and then
 * changing the model to represent the user's actions.
 */
public interface SanguineController {

  /**
   * will play a full gamme of sanguine from start until end.
   *
   * @param model the relevant model that is associated with this game.
   */
  void playGame(Sanguine model);
}
