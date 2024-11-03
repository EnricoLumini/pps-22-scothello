package scothello.controller.game

import scothello.controller.{BaseController, Controller}
import scothello.view.game.GameView
import scothello.model.ModelOps.updateState
import scothello.model.board.Tile
import scothello.model.game.state.ops.GameOps.{nextTurn, placePawn, calculateAllowedPos, flipPawns}

/** Controller for the game page */
trait GameController extends Controller:

  /** Increases the turn */
  def nextTurn(): Unit

  def placePawn(tile: Tile): Unit

  def calculateAllowedPos(): Unit

  def flipPawns(tile: Tile): Unit

object GameController:

  def apply(requirements: Controller.Requirements[GameView]): GameController =
    new GameControllerImpl(requirements)

private class GameControllerImpl(requirements: Controller.Requirements[GameView])
    extends BaseController(requirements)
    with GameController:

  override def nextTurn(): Unit =
    this.model.updateState(_.nextTurn())

  override def placePawn(tile: Tile): Unit =
    this.model.updateState(_.placePawn(tile))

  override def calculateAllowedPos(): Unit =
    this.model.updateState(_.calculateAllowedPos())

  override def flipPawns(tile: Tile): Unit =
    this.model.updateState(_.flipPawns(tile))
