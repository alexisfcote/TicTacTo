package tictacto;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * Main launcher class for the tictacto game
 * 
 * @author alfoc
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("TicTacToView.fxml"));
			Scene scene = new Scene(root, -1, -1);
			scene.getStylesheets().add(getClass().getResource("TicTacToView.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Tic Tac To");
			primaryStage.show();
			primaryStage.setMinWidth(primaryStage.getWidth());
		    primaryStage.setMinHeight(primaryStage.getHeight());
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
