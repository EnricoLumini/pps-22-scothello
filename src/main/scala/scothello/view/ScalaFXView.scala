package scothello.view

import scalafx.beans.property.ObjectProperty
import scalafx.scene.{Parent, Scene}
import scalafx.scene.layout.Pane
import scothello.controller.Controller
import scothello.model.game.state.GameState

/** Represents a ScalaFX view.
  * @param scene
  *   The parent pane.
  */

trait ScalaFXView(
    val scene: Scene
) extends View:

  /** The reactive state of the game. */
  def reactiveState: ObjectProperty[GameState]

  /** The parent pane. */
  def parent: Parent

  override def show(): Unit =
    scene.root = parent

  override def hide(): Unit =
    scene.root = new Pane()

object ScalaFXView:
  type Factory[C <: Controller, V <: View] = (Scene, View.Requirements[C]) => V

/** Represents a base ScalaFX view.
  * @param scene
  *   The parent pane.
  * @param requirements
  *   The requirements of the view.
  * @tparam C
  *   The type of the controller.
  */
abstract class BaseScalaFXView[C <: Controller](
    scene: Scene,
    requirements: View.Requirements[C]
) extends BaseView[C](requirements)
    with ScalaFXView(scene):

  private val _reactiveState: ObjectProperty[GameState] = ObjectProperty(controller.state)

  override def reactiveState: ObjectProperty[GameState] = _reactiveState

  override def updateState(state: GameState): Unit =
    super.updateState(state)
    _reactiveState() = state
