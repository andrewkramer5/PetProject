package gamestate;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import manager.GameManager;

public class MainMenu extends GameState {
	
	@FXML public Pane canvasPane;
	@FXML public Canvas canvas;
	private GraphicsContext g;
	
	private Image background;
	@FXML public Button newPet;
	@FXML public Button loadPet;
	@FXML public Button exit;
	
	//private double mX, mY = 0;
	
	public MainMenu(GameManager gm, GameStateManager gsm) {
		super(gm, gsm);
		
		background = new Image("mainMenuBackground.png");
		
		/*canvas = new Canvas(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
		g = canvas.getGraphicsContext2D();
		canvasPane = new Pane();
		
		newPet = new Button();
		newPet.setText("NEW PET");
		newPet.setStyle("-fx-focus-color: transparent;");
		newPet.setMinWidth(150);
		newPet.setMinHeight(50);
		newPet.setLayoutX(174);
		newPet.setLayoutY(174);
		newPet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				gsm.setState(gsm.PET_CREATION);
			}
		});
		
		loadPet = new Button();
		loadPet.setText("LOAD PET");
		loadPet.setStyle("-fx-focus-color: transparent;");
		loadPet.setMinWidth(150);
		loadPet.setMinHeight(50);
		loadPet.setLayoutX(174);
		loadPet.setLayoutY(174);
		loadPet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				gsm.setState(gsm.LOAD_PET);
			}
		});
		
		exit = new Button();
		exit.setText("EXIT");
		exit.setStyle("-fx-focus-color: transparent;");
		exit.setMinWidth(150);
		exit.setMinHeight(50);
		exit.setLayoutX(174);
		exit.setLayoutY(174);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				gm.exitGame();
			}
		});
		
		canvasPane.getChildren().addAll(canvas, newPet, loadPet, exit);
		loadPet.setTranslateY(75);
		exit.setTranslateY(150);
		
		this.scene = new Scene(canvasPane);
		*/
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		Platform.runLater(()->{
			g.drawImage(background, 0, 0);
		});
	}
	
	@Override
	public void reset() {
		g = canvas.getGraphicsContext2D();
	}
}
