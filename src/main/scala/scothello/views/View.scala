package scothello.views

import scothello.model.game.state.GameState
import scothello.controllers.Controller

/** Represents a view of the game.
  */
trait View:

  /** The state of the game.
    */
  def state: GameState

  /** Shows the view.
    */
  def show(): Unit

  /** Hides the view.
    */
  def hide(): Unit

  /** Updates the state of the view.
    * @param state
    *   The new state of the game.
    */
  def updateState(state: GameState): Unit = ()

/** The view object.
  */
object View:

  type Factory[C <: Controller, V <: View] = Requirements[C] => V

  trait Requirements[C <: Controller] extends Controller.Provider[C]

  trait Dependencies[C <: Controller](requirements: Requirements[C]) extends View:
    protected def controller: C = requirements.controller

  trait Provider[V <: View]:
    def view: V

//trait NavigatorView extends View:
//def navigateTo()

/** Represents a base view.
  * @param requirements
  *   The requirements of the view.
  * @tparam C
  *   The type of the controller.
  */
abstract class BaseView[C <: Controller](
    val requirements: View.Requirements[C]
) extends View
    with View.Dependencies(requirements):

  override def state: GameState = _state

  private var _state: GameState = controller.state

  override def updateState(state: GameState): Unit =
    _state = state
