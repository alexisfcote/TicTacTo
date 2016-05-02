package tictacto;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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

  TicTacTo tictacto = new TicTacTo();
  Drawer drawer = new Drawer();
  @FXML
  List<Rectangle> rectangleList;
  List<Node> shapes = new ArrayList<Node>();
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
    drawer.drawGrid(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javafx.util.Callback#call(java.lang.Object)
   */
  @Override
  public Object call(Object param) {
    // Gets called when the tictacto model gets updated
    drawer.drawGrid(this);
    return null;
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
      drawer.drawGrid(this);
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
    drawer.drawGrid(this);
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
