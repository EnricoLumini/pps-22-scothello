package scothello.game

import scothello.game.pages.{GamePage, PageFactory, Pages}
import scothello.model.Model
import scothello.model.game.state.GameState

trait ScothelloGame:
  val model: Model
  val pages: Map[Pages, GamePage[?, ?]]

object ScothelloGame:
  def apply(
      pagesFactories: Map[Pages, PageFactory[?, ?]]
  ): ScothelloGame =
    new ScothelloGame:
      override val model: Model = Model(GameState())
      override val pages: Map[Pages, GamePage[?, ?]] =
        pagesFactories.map((page, pageFactory) => page -> GamePage(model, pageFactory))
