package gamestate;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import manager.GameManager;
import manager.Pet;

public class PetCreation extends GameState {
	
	private Pane canvasPane;
	private HBox box;
	private Canvas canvas;
	private GraphicsContext g;
	
	private Image background;
	private TextField nameField;
	private Button create;
	private Button cancel;
	private ComboBox<String> animalTypes;
	
	public PetCreation(GameManager gm, GameStateManager gsm) {
		super(gm, gsm);
		
		background = new Image("petCreationBackground.png");
		canvas = new Canvas(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
		g = canvas.getGraphicsContext2D();
		
		canvasPane = new Pane();
		box = new HBox();
		box.setLayoutX(50);
		box.setLayoutY(125);
		box.setSpacing(25);
		
		String[] animals = {"Basic"};
		animalTypes = new ComboBox<String>(FXCollections.observableArrayList(animals));
		
		nameField = new TextField("");
		nameField.setMinWidth(150);
		nameField.setMinHeight(50);
		nameField.setPromptText("Pet Name");
		nameField.setStyle("-fx-focus-color: transparent;");
		
		animalTypes.setMinWidth(150);
		animalTypes.setMinHeight(50);
		animalTypes.setStyle("-fx-focus-color: transparent;");
		
		create = new Button("CREATE");
		create.setMinWidth(100);
		create.setMinHeight(50);
		create.setStyle("-fx-focus-color: transparent;");
		create.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Pet p = new Pet(animalTypes.getValue(), nameField.getText());
				gm.addPet(p);
				gsm.setState(gsm.MAIN_MENU);
			}
		});
		
		cancel = new Button("CANCEL");
		cancel.setMinWidth(100);
		cancel.setMinHeight(50);
		cancel.setStyle("-fx-focus-color: transparent;");
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				gsm.setState(gsm.MAIN_MENU);
			}
		});
		
		box.getChildren().addAll(nameField, animalTypes);
		canvasPane.getChildren().addAll(canvas, box, create, cancel);
		create.requestFocus();
		create.setTranslateX(300);
		create.setTranslateY(325);
		cancel.setTranslateX(100);
		cancel.setTranslateY(325);
		box.setTranslateX(20);
		
		this.scene = new Scene(canvasPane);
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
		nameField.setText("");
		animalTypes.setValue("");
	}
}
