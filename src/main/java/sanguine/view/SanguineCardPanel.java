package sanguine.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import sanguine.model.card.Card;
import sanguine.model.cell.Player;

/**
 * This is a card panel. It represents a card that is in a player hand. Is also a subpanel of the
 * hand panel.
 */
public class SanguineCardPanel extends JPanel {
  private final Card card;
  private final Player player;

  /**
   * Will create a new board panel for our game and does some error checking.
   *
   * @param card   is the current card in this panel. Card only has toString so it's immutable.
   * @param player is the player this card panel belongs too.
   */
  public SanguineCardPanel(Card card, Player player) {
    super();
    if (card == null || player == null) {
      throw new IllegalArgumentException("Model or player cannot be null");
    }
    this.player = player;
    this.card = card;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    drawCards(g2d);
    g2d.setColor(Color.BLACK);
    g2d.drawLine(0, 0, 0, this.getHeight());
    g2d.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
    g2d.drawLine(this.getWidth(), this.getHeight(), this.getWidth(), 0);
    g2d.drawLine(this.getWidth(), 0, 0, 0);
    this.drawCards(g2d);
  }

  /**
   * will draw the given card, name, cost, influence, value.
   *
   * @param g2d the graphics object.
   */
  private void drawCards(Graphics2D g2d) {
    int fontSize = this.getHeight() / 10;
    Font font = new Font("Arial", Font.BOLD, fontSize);
    g2d.setFont(font);
    String[] strCard = this.card.toString().split(System.lineSeparator());
    g2d.drawString(strCard[0], 0, fontSize);
    g2d.drawString(strCard[1], 0, fontSize * 2);
    g2d.drawString(strCard[2], 0, fontSize * 3);
    for (int influenceRow = 3; influenceRow < strCard.length; influenceRow++) {
      g2d.drawString(strCard[influenceRow], 0,
          this.getHeight() / 2 + (fontSize * (-3 + influenceRow)));
    }
  }
}
