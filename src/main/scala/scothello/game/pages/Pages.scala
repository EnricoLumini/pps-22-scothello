package scothello.game.pages

import scalafx.scene.Scene
import scothello.controller.end.EndgameController
import scothello.controller.game.GameController
import scothello.controller.home.{HomeController, StartController}
import scothello.game.manager.ScothelloFXApp
import scothello.view.end.EndgameView
import scothello.view.game.GameView
import scothello.view.home.{HomeView, StartView}

given rootScene: Scene = ScothelloFXApp.rootScene

enum Pages(val pageFactory: PageFactory[?, ?]):
  case Home extends Pages(ScalaFXPageFactory(HomeView.apply, HomeController.apply))
  case Start extends Pages(ScalaFXPageFactory(StartView.apply, StartController.apply))
  case Game extends Pages(ScalaFXPageFactory(GameView.apply, GameController.apply))
  case End extends Pages(ScalaFXPageFactory(EndgameView.apply, EndgameController.apply))
