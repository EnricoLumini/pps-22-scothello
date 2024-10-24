package scothello.view.home

import scalafx.geometry.Pos.{Center, CenterRight, TopCenter}
import scalafx.scene.{Node, Parent, Scene}
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{Background, VBox}
import scothello.controller.home.{HomeController, StartController}
import scothello.game.pages.Pages
import scothello.model.game.config.Player
import scothello.utils.Pair
import scothello.view.{BaseScalaFXView, View}

trait StartView extends View

object StartView:
  def apply(scene: Scene, requirements: View.Requirements[StartController]): StartView =
    BaseScalaFXStartView(scene, requirements)

/** Represents the home view.
  * @param scene
  *   The parent pane.
  * @param requirements
  *   The requirements of the view.
  */
private class BaseScalaFXStartView(scene: Scene, requirements: View.Requirements[StartController])
    extends BaseScalaFXView(scene, requirements)
    with StartView:

  override def parent: Parent = new VBox:
    spacing = 30
    alignment = Center
    stylesheets = List("file:style/startpage.css")

    val player1Label = new Label("Player 1:")
    val player1Field: TextField = new TextField:
      promptText = "Enter Player 1 name"
    children += player1Label += player1Field

    val player2Label = new Label("Player 2:")
    val player2Field: TextField = new TextField:
      promptText = "Enter Player 2 name"
    children += player2Label += player2Field

    val button: Button = new Button("Start game"):
      id = "startButton"
      text = "Start"
      alignment = Center
      onAction = _ =>
        val player1Name = player1Field.text.value.trim
        val player2Name = player2Field.text.value.trim

        var validInput = true
        if player1Name.isEmpty then
          markFieldInvalid(player1Field)
          validInput = false

        if player2Name.isEmpty then
          markFieldInvalid(player2Field)
          validInput = false

        if validInput then
          val players: Pair[Player] = (
            Player(player1Name),
            Player(player2Name)
          )

          controller.startGame(players)
    children += button

    private def resetFieldStyle(field: TextField): Unit =
      field.setStyle("")

    private def markFieldInvalid(field: TextField): Unit =
      field.setStyle("-fx-border-color: red;")

    private def addTextFieldListener(field: TextField): Unit =
      field.text.onChange { (_, _, newValue) =>
        if newValue.trim.nonEmpty then resetFieldStyle(field)
      }

    addTextFieldListener(player1Field)
    addTextFieldListener(player2Field)
