package sanguine.controller;

import sanguine.model.Sanguine;
import sanguine.model.cell.Player;
import sanguine.view.Position;
import sanguine.view.SanguineGuiView;

/**
 * represents the gui controller. This will allow for us to interact with the GUI.
 */
public class BasicSanguineController implements SanguineController, FeaturesListener {

  private final SanguineGuiView view;
  private Sanguine model;

  /**
   * generic constructor that will connect the gui and controller.
   *
   * @param view the relevant view that we are using.
   */
  public BasicSanguineController(SanguineGuiView view) {
    if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    this.view = view;
    this.view.subscribe(this);
  }

  @Override
  public void playGame(Sanguine model) {
    this.model = model;
    this.view.makeVisible();
  }

  @Override
  public void mouseEventBoard(int x, int y) {
    this.view.setPosn(new Position(x, y));
    int col = y - 1;
    if (col == this.model.getCols()
        || col == -1) {
      return;
    }
    System.out.println(x + " " + col);
    this.view.refresh();
  }

  @Override
  public void mouseEventHand(int cardIndex, Player p) {
    this.view.clickCard(cardIndex);
    System.out.println(cardIndex + " Player = " + p.toString());
    this.view.refresh();
  }

  /**
   * will determine what happens depending on what the key pressed is.
   *
   * @param key the key that the user pressed.
   */
  @Override
  public void keyClicked(String key) {
    if (key.equals("m")) {
      System.out.println("played move");
    }
    if (key.equals("p")) {
      System.out.println("passed move");
    }
  }
}
