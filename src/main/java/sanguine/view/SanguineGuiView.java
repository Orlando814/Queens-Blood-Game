package sanguine.view;

import sanguine.controller.FeaturesListener;

/**
 * This interface represents a view for the Sanguine game that is a GUI instead of something
 * textual. We are using Swing to implement this GUI.
 */
public interface SanguineGuiView {

  /**
   * Refresh the view to reflect any changes in the game.
   */
  void refresh();

  /**
   * Make the view visible to start the game.
   */
  void makeVisible();

  /**
   * will subscriber to the publisher.
   *
   * @param listener listens and shi to the listener.
   */
  void subscribe(FeaturesListener listener);

  /**
   * This will highlight a card that the user has clicked.
   *
   * @param cardIndex is the index of the card that the user has clicked.
   */
  void clickCard(int cardIndex);

  /**
   * will set the posn in he board class to make sure that there is a way to highlight the location
   * of a given card.
   *
   * @param position the position we are doing this at.
   */
  void setPosn(Position position);
}
