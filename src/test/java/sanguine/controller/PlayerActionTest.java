package sanguine.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import sanguine.model.BasicSanguine;
import sanguine.model.Sanguine;
import sanguine.model.cell.Player;
import sanguine.strategy.FirstMove;
import sanguine.strategy.GreedyMove;
import sanguine.strategy.MoveValues;
import sanguine.strategy.PassMove;

/**
 * This holds all the respective test for any class that implements the PlayerAction interface.
 * This class's intent is to ensure correct behavior and code correctness.
 */
public class PlayerActionTest {

  @Test
  public void testGetPlayer() {
    PlayerAction humanPlayer1 = new PlayerImpl(Player.PLAYER1);
    assertEquals(Player.PLAYER1, humanPlayer1.getPlayer());
    PlayerAction humanPlayer2 = new PlayerImpl(Player.PLAYER2);
    assertEquals(Player.PLAYER2, humanPlayer2.getPlayer());
    PlayerAction machinePlayer1 = new MachineImpl(Player.PLAYER1, new FirstMove());
    assertEquals(Player.PLAYER1, machinePlayer1.getPlayer());
    PlayerAction machinePlayer2 = new MachineImpl(Player.PLAYER2, new FirstMove());
    assertEquals(Player.PLAYER2, machinePlayer2.getPlayer());
  }

  @Test
  public void testImplementMoveHuman() {
    PlayerAction humanPlayer1 = new PlayerImpl(Player.PLAYER1);
    Sanguine model = new BasicSanguine();
    MoveValues humanMove = new MoveValues(-1, -1, null, null);
    assertEquals(humanMove, humanPlayer1.makeMove(model));
    PlayerAction humanPlayer2 = new PlayerImpl(Player.PLAYER2);
    assertEquals(humanMove, humanPlayer2.makeMove(model));
  }

  @Test
  public void testImplementMoveMachine() {
    PlayerAction machinePlayer1 = new MachineImpl(Player.PLAYER1, new PassMove());
    Sanguine model = new BasicSanguine();
    assertEquals("", machinePlayer1.makeMove(model));
    PlayerAction machinePlayer2 = new MachineImpl(Player.PLAYER2, new GreedyMove());
    assertNull("", machinePlayer2.makeMove(model));
  }
}
