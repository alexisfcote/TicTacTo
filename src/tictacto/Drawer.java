package tictacto;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Drawer {

  /**
   * Redraw the gamescreen from scratch
   * @param ticTacToController TODO
   */
  void drawGrid(TicTacToController ticTacToController) {
    ticTacToController.currentPlayer.setText("Player " + ticTacToController.tictacto.getPlaying() + " is playing");
    if (ticTacToController.tictacto.getPlaying() == 1) {
      ticTacToController.currentPlayer.setFill(Color.BLUE);
    } else {
      ticTacToController.currentPlayer.setFill(Color.RED);
    }
  
    for (Node shape : ticTacToController.shapes) {
      ticTacToController.gridpane.getChildren().remove(shape);
    }
    ticTacToController.shapes.clear();
    int[][] grid = ticTacToController.tictacto.getgrid();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        switch (grid[i][j]) {
          case 1:
            ticTacToController.drawer.drawX(ticTacToController, i, j);
            break;
          case 2:
            ticTacToController.drawer.drawO(ticTacToController, i, j);
            break;
          default:
            ticTacToController.rectangleList.get(3 * i + j).setStroke(Color.BLACK);
            break;
        }
      }
    }
  }

  /**
   * Draw a rectangle at the designated location on the grid
   * 
   * @param ticTacToController TODO
   * @param x
   * @param y
   */
  void drawO(TicTacToController ticTacToController, int x, int y) {
    ticTacToController.rectangleList.get(3 * x + y).setStroke(Color.RED);
    Circle circle = new Circle(35);
    circle.setFill(null);
    circle.setStroke(Color.BLACK);
    circle.setStrokeWidth(3);
    ticTacToController.shapes.add(circle);
    ticTacToController.gridpane.add(circle, y, x);
    GridPane.setHalignment(circle, HPos.CENTER);
  }

  /**
   * Draw an X at the designated location on the grid
   * 
   * @param ticTacToController TODO
   * @param i
   * @param j
   */
  void drawX(TicTacToController ticTacToController, int i, int j) {
    ticTacToController.rectangleList.get(3 * i + j).setStroke(Color.BLUE);
    Polygon xshape = new Polygon(
        new double[] {-35.0, -35.0, 35.0, 35.0, 0.0, 0.0, -35.0, 35.0, 35.0, -35.0, 0.0, 0.0});
    xshape.setFill(null);
    xshape.setStroke(Color.BLACK);
    xshape.setStrokeWidth(3);
    ticTacToController.shapes.add(xshape);
    ticTacToController.gridpane.add(xshape, j, i);
    GridPane.setHalignment(xshape, HPos.CENTER);
  }

  /**
   * Launch a dialog box informing the winnigplayer that he won Also reset the game.
   * 
   * @param ticTacToController TODO
   * @param winnigplayer
   */
  void winscreen(TicTacToController ticTacToController, int winnigplayer) {
    //
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Congratulations");
    alert.setHeaderText("Player " + winnigplayer + " won");
    alert.setContentText("Congratulations!");
  
    alert.showAndWait();
    ticTacToController.resetGame();
  }

}
