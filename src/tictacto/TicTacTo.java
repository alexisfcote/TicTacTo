package tictacto;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Callback;

public class TicTacTo {
	int[][] grid = new int[3][3];
	int lastplayertoplay = 2;
	List<Callback<Object, Object>> updateCallbacks = new  ArrayList<Callback<Object, Object>>(2);
	
	public boolean play(int x, int y, int player){
		if (grid[x][y] != 0){
			return false;
		}
		if (player == lastplayertoplay){
			throw new IllegalArgumentException("Same player trying to play twice");
		}
		//  this is a valid play
		grid[x][y] = player;
		lastplayertoplay = player;
		updated();
		return true;
	}
	
	public void registerUpdateCallback(Callback<Object, Object> callback){
		updateCallbacks.add(callback);
	}
	
	private void updated(){
		for (Callback<Object, Object> callback : updateCallbacks) {
			callback.call(null);
		}
	}
	
	public int[][] getgrid(){
		return grid;
	}
	
	public int checkWin(){
		for (int i = 0; i < grid.length; i++) {
			// Les lignes horizontales
			if (grid[i][0] == grid[i][1] & grid[i][0] == grid[i][2] & grid[i][0] > 0) return grid[i][0];
			// les lignes verticales
			if (grid[0][i] == grid[1][i] & grid[0][i] == grid[2][i] & grid[0][i] > 0) return grid[0][i];
		}
		// la diagonale descendante
		if (grid[0][0] == grid[1][1] & grid[0][0] == grid[2][2] & grid[0][0] > 0) return grid[0][0];
		// la diagonale ascendante
		if (grid[0][2] == grid[1][1] & grid[0][2] == grid[2][0] & grid[0][2] > 0) return grid[0][0];
		return 0;
	}
}


