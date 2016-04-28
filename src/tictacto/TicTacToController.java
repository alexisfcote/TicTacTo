package tictacto;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.event.ActionEvent;

public class TicTacToController implements Callback<Object, Object> {

	private TicTacTo tictacto = new TicTacTo();
	private int playing = 1;
	@FXML
	private List<Rectangle> rectangleList;
	
	private List<Node> shapes = new ArrayList<Node>();
	@FXML GridPane gridpane;

	public TicTacToController() {
		// TODO Auto-generated constructor stub

		// add callbacks
		tictacto.registerUpdateCallback(this);
	}

	public void changeplayer() {
		if (playing == 1) {
			playing = 2;
		} else {
			playing = 1;
		}
	}

	@Override
	public Object call(Object param) {
		// TODO Auto-generated method stub
		drawGrid();
		return null;
	}

	private void drawGrid() {
		for (Node shape : shapes) {
			gridpane.getChildren().remove(shape);
		}
		shapes.clear();
		int[][] grid = tictacto.getgrid();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				switch (grid[i][j]) {
				case 1:
					rectangleList.get(3 * i + j).setStroke(Color.BLUE);
					Polygon xshape = new Polygon(new double[]{
							-35.0, -35.0,
						    35.0, 35.0,
						    0.0, 0.0,
						    -35.0, 35.0,
						    35.0, -35.0,
						    0.0, 0.0});
					xshape.setFill(null);
					xshape.setStroke(Color.BLACK);
					xshape.setStrokeWidth(3);
					shapes.add(xshape);
					gridpane.add(xshape, j, i);
					GridPane.setHalignment(xshape, HPos.CENTER);
					break;
				case 2:
					rectangleList.get(3 * i + j).setStroke(Color.RED);
					Circle circle = new Circle(35);
					circle.setFill(null);
					circle.setStroke(Color.BLACK);
					circle.setStrokeWidth(3);
					shapes.add(circle);
					gridpane.add(circle, j, i);
					GridPane.setHalignment(circle, HPos.CENTER);
					break;
				default:
					rectangleList.get(3 * i + j).setStroke(Color.BLACK);
					break;
				}
			}
		}
	}

	@FXML
	public void onRectangleClicked(MouseEvent event) {
		Rectangle rect = (Rectangle) event.getSource();
		int y = GridPane.getColumnIndex(rect);
		int x = GridPane.getRowIndex(rect);
		if (tictacto.play(x, y, playing)) {
			changeplayer();
		}
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
		tictacto = new TicTacTo();
		tictacto.registerUpdateCallback(this);
		drawGrid();
	}
}
