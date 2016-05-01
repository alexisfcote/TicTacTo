package tictacto;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Callback;

/**
 * Model class for the game of tictacto
 * 
 * @author alfoc
 */
public class TicTacTo {
  int[][] grid = new int[3][3];
  int playing = 1;

  List<Callback<Object, Object>> updateCallbacks = new ArrayList<Callback<Object, Object>>(2);

  /**
   * Try a play from the playing player at the location x and y.
   * 
   * @param x
   * @param y
   * @return true if valid play
   */
  public boolean play(int x, int y) {
    if (grid[x][y] != 0) {
      return false;
    }
    // this is a valid play
    grid[x][y] = playing;
    changeplayer();
    updated();
    return true;
  }

  /**
   * Register a callback called when the model has updated
   * 
   * @param callback
   */
  public void registerUpdateCallback(Callback<Object, Object> callback) {
    updateCallbacks.add(callback);
  }

  /**
   * Calls the callbacks to be notified of updated model
   */
  private void updated() {
    for (Callback<Object, Object> callback : updateCallbacks) {
      callback.call(null);
    }
  }

  /**
   * Check is there is a winner for the current grid configuration
   * 
   * @return the winning player id or 0 otherwise
   */
  public int checkWin() {
    for (int i = 0; i < grid.length; i++) {
      // Les lignes horizontales
      if (grid[i][0] == grid[i][1] & grid[i][0] == grid[i][2] & grid[i][0] > 0)
        return grid[i][0];
      // les lignes verticales
      if (grid[0][i] == grid[1][i] & grid[0][i] == grid[2][i] & grid[0][i] > 0)
        return grid[0][i];
    }
    // la diagonale descendante
    if (grid[0][0] == grid[1][1] & grid[0][0] == grid[2][2] & grid[0][0] > 0)
      return grid[0][0];
    // la diagonale ascendante
    if (grid[0][2] == grid[1][1] & grid[0][2] == grid[2][0] & grid[0][2] > 0)
      return grid[0][0];
    return 0;
  }

  public void changeplayer() {
    if (playing == 1) {
      playing = 2;
    } else {
      playing = 1;
    }
  }

  public int getPlaying() {
    return playing;
  }

  public int[][] getgrid() {
    return grid;
  }
}
