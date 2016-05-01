package tictacto;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

/**
 * Model class for the game of tictacto
 * 
 * @author alfoc
 *
 */
public class TicTacToController implements Callback<Object, Object> {

  private TicTacTo tictacto = new TicTacTo();
  @FXML
  private List<Rectangle> rectangleList;
  private List<Node> shapes = new ArrayList<Node>();
  @FXML
  GridPane gridpane;
  @FXML
  Text currentPlayer;

  public TicTacToController() {
    // add callbacks
    tictacto.registerUpdateCallback(this);

  }

  @FXML
  public void initialize() {
    drawGrid();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javafx.util.Callback#call(java.lang.Object)
   */
  @Override
  public Object call(Object param) {
    // Gets called when the tictacto model gets updated
    drawGrid();
    return null;
  }

  /**
   * Redraw the gamescreen from scratch
   */
  private void drawGrid() {
    currentPlayer.setText("Player " + tictacto.getPlaying() + " is playing");
    if (tictacto.getPlaying() == 1) {
      currentPlayer.setFill(Color.BLUE);
    } else {
      currentPlayer.setFill(Color.RED);
    }

    for (Node shape : shapes) {
      gridpane.getChildren().remove(shape);
    }
    shapes.clear();
    int[][] grid = tictacto.getgrid();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        switch (grid[i][j]) {
          case 1:
            drawX(i, j);
            break;
          case 2:
            drawO(i, j);
            break;
          default:
            rectangleList.get(3 * i + j).setStroke(Color.BLACK);
            break;
        }
      }
    }
  }

  /**
   * Draw a rectangle at the designated location on the grid
   * 
   * @param i
   * @param j
   */
  private void drawO(int i, int j) {
    rectangleList.get(3 * i + j).setStroke(Color.RED);
    Circle circle = new Circle(35);
    circle.setFill(null);
    circle.setStroke(Color.BLACK);
    circle.setStrokeWidth(3);
    shapes.add(circle);
    gridpane.add(circle, j, i);
    GridPane.setHalignment(circle, HPos.CENTER);
  }

  /**
   * Draw an X at the designated location on the grid
   * 
   * @param i
   * @param j
   */
  private void drawX(int i, int j) {
    rectangleList.get(3 * i + j).setStroke(Color.BLUE);
    Polygon xshape = new Polygon(
        new double[] {-35.0, -35.0, 35.0, 35.0, 0.0, 0.0, -35.0, 35.0, 35.0, -35.0, 0.0, 0.0});
    xshape.setFill(null);
    xshape.setStroke(Color.BLACK);
    xshape.setStrokeWidth(3);
    shapes.add(xshape);
    gridpane.add(xshape, j, i);
    GridPane.setHalignment(xshape, HPos.CENTER);
  }

  /**
   * Try a to play at the clicked rectangle
   * 
   * @param event
   */
  @FXML
  public void onRectangleClicked(MouseEvent event) {
    Rectangle rect = (Rectangle) event.getSource();
    int y = GridPane.getColumnIndex(rect);
    int x = GridPane.getRowIndex(rect);
    if (tictacto.play(x, y)) {
      int winnigplayer = tictacto.checkWin();
      if (winnigplayer > 0) {
        winscreen(winnigplayer);
        return;
      }
      drawGrid();
    }
  }

  /**
   * Launch a dialog box informing the winnigplayer that he won Also reset the game.
   * 
   * @param winnigplayer
   */
  private void winscreen(int winnigplayer) {
    //
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Congratulations");
    alert.setHeaderText("Player " + winnigplayer + " won");
    alert.setContentText("Congratulations!");

    alert.showAndWait();
    resetGame();
  }

  private void resetGame() {
    tictacto = new TicTacTo();
    tictacto.registerUpdateCallback(this);
    drawGrid();
  }

  @FXML
  public void onMouseEntered(MouseEvent event) {
    Rectangle rect = (Rectangle) event.getSource();
    rect.setFill(Color.BISQUE);
  }

  @FXML
  public void onMouseExited(MouseEvent event) {
    Rectangle rect = (Rectangle) event.getSource();
    rect.setFill(Color.web("#ffffff"));
  }

  @FXML
  public void onBtnReset(ActionEvent event) {
    resetGame();
  }

}
