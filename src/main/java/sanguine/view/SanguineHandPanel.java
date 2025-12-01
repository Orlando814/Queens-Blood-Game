package sanguine.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import sanguine.controller.FeaturesListener;
import sanguine.model.ReadOnlySanguine;
import sanguine.model.cell.Player;

/**
 * represents the panel for the cards at the bottom of the screen. Will allow for us to have
 * different behaviors for the cards and the boards without making a disgustingly long class.
 */
public class SanguineHandPanel extends JPanel {
  private final ReadOnlySanguine model;
  private final Player player;
  private int clickedCard;

  /**
   * This is the constructor for this class. It first does some error checking and then adds
   * several frames equal to the number of cards in the player's hand to this panel.
   *
   * @param model  is the read-only version of the model.
   * @param player is the player that this hand belongs too.
   */
  public SanguineHandPanel(ReadOnlySanguine model, Player player) {
    super();
    if (model == null || player == null) {
      throw new IllegalArgumentException("Model or player cannot be null.");
    }
    this.model = model;
    this.player = player;
    this.clickedCard = -1;
    this.setLayout(new GridLayout(1, 0, 0, 0));
    for (int cardIndex = 0; cardIndex < model.getHandSize(player); cardIndex++) {
      JPanel card = new SanguineCardPanel(model.getCardInHand(player, cardIndex), player);
      card.setOpaque(false);
      this.add(card);
    }
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    for (int cardIndex = 0; cardIndex < model.getHandSize(player); cardIndex++) {
      if (this.clickedCard == cardIndex) {
        g2d.setColor(Color.MAGENTA);
      } else {
        if (this.player == Player.PLAYER1) {
          g2d.setColor(new Color(255, 100, 105));
        } else {
          g2d.setColor(new Color(137, 207, 240));
        }
      }
      int cardWidth = this.getWidth() / model.getHandSize(this.player);
      g2d.fillRect(cardWidth * cardIndex, 0,
          cardWidth * cardIndex + cardWidth, this.getHeight());
    }
  }

  /**
   * Subscribes a mouse listener to this frame that reports back the index of the card the user
   * clicked to the controller.
   *
   * @param listener is the controller which is listening to the input from this panel.
   */
  public void subscribe(FeaturesListener listener) {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        double cardIndex = e.getX() * ((double) (model.getHandSize(player)) / getWidth());
        listener.mouseEventHand((int) cardIndex, player);
      }
    });
  }

  /**
   * This will highlight a card that the user has clicked.
   *
   * @param cardIndex is the index of the card that the user has clicked.
   */
  public void clickCard(int cardIndex) {
    this.clickedCard = cardIndex;
  }
}
