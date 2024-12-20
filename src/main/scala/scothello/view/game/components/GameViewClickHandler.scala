package scothello.view.game.components

import scothello.controller.game.GameController
import scothello.model.board.{AllowedTiles, Tile}
import scothello.model.game.state.GameState

/** Handles the click on the game view
  */
trait GameViewClickHandler:

  /** Handles the click on a tile
    *
    * @param tile
    *   the tile clicked
    */
  def onTileClick(tile: Tile): Unit

  /** Handles the click on the stop game button
    */
  def onStopGameButtonClick(): Unit

  /** Handles the click on the stop game cancel button
    */
  def onStopGameCancelClick(): Unit

object GameViewClickHandler:

  def apply(gameController: GameController): GameViewClickHandler =
    new GameViewClickHandler:

      def state: GameState = gameController.state

      override def onTileClick(tile: Tile): Unit =
        if AllowedTiles.checkIfTileIsAllowed(state.allowedTiles, state.turn.player, tile) then
          gameController.placePawn(tile)
          gameController.flipPawns(tile)
          gameController.nextTurn()
        else ()

      override def onStopGameButtonClick(): Unit =
        gameController.pauseGame()

      override def onStopGameCancelClick(): Unit =
        gameController.resumeGame()
