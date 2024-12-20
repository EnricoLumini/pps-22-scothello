package scothello.view.game.components

import scalafx.geometry.Insets
import scalafx.geometry.Pos.BottomRight
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, ButtonType}
import scalafx.scene.layout.HBox
import scothello.controller.game.GameController
import scothello.game.pages.Pages
import scothello.model.game.state.GameState
import scothello.view.BaseScalaFXView
import scothello.view.utils.ResettableObjectProperty

object StopButtonComponent:

  private val buttonRadius: Int = 20
  private val paddingSize: Int = 10

  def stopButtonComponent(reactiveGameState: ResettableObjectProperty[GameState])(using
      displayScene: Scene,
      gameController: GameController,
      clickHandler: GameViewClickHandler,
      gameView: BaseScalaFXView[GameController]
  ): HBox =
    new HBox:
      alignment = BottomRight
      padding = Insets(0, paddingSize, paddingSize, 0)
      val stopButton: Button = new Button:
        style = s"""
          -fx-background-image: url('/imgs/stop-button.png');
          -fx-background-radius: ${buttonRadius}px;
          -fx-background-repeat: no-repeat;
          -fx-background-position: center;
          -fx-background-size: contain;
          -fx-background-color: transparent;
          -fx-border-color: transparent;
          -fx-pref-width: ${buttonRadius * 2}px;
          -fx-pref-height: ${buttonRadius * 2}px;
          -fx-cursor: hand;
        """
        prefHeight = buttonRadius * 2
        prefWidth = buttonRadius * 2

        onAction = _ =>
          clickHandler.onStopGameButtonClick()
          val alert = new Alert(AlertType.Confirmation):
            title = "Stopping the game"
            headerText = "Action Confirmation"
            contentText = "Are you sure you want to stop the game?"
            buttonTypes = Seq(ButtonType.Yes, ButtonType.No)
          alert.showAndWait() match
            case Some(ButtonType.Yes) =>
              reactiveGameState.removeListeners()
              gameView.navigateTo(Pages.End)
            case _ =>
              clickHandler.onStopGameCancelClick()

      children.add(stopButton)
