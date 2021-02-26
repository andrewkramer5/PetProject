package manager;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	
	private GameManager g;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Stage window = primaryStage;
		window.setResizable(false);
		window.setTitle("Pet Project");
		window.getIcons().add(new Image("stageIcon.png"));
		
		g = new GameManager(window);
		g.start();
		
		window.show();
	}
	
	@Override
	public void stop() {
		g.saveGameData();
		g.pause();
	}
}
