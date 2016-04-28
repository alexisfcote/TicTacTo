package tictacto;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Callback;

public class TicTacTo {
	int[][] grid = new int[3][3];
	int lastplayertoplay = 0;
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
}


