package gamestate;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import manager.GameManager;

public class LoadPet extends GameState {
	
	private Pane canvasPane;
	private Canvas canvas;
	private GraphicsContext g;
	
	private Image background;
	private ObservableList<String> list;
	private ComboBox<String> currentPets;
	private Button cancel;
	private Button delete;
	private Button load;
	
	public LoadPet(GameManager gm, GameStateManager gsm) {
		super(gm, gsm);
		
		background = new Image("loadPetBackground.png");
		canvas = new Canvas(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
		g = canvas.getGraphicsContext2D();
		
		canvasPane = new Pane();
		
		currentPets = new ComboBox<String>();
		currentPets.setMinWidth(150);
		currentPets.setMinHeight(50);
		currentPets.setStyle("-fx-focus-color: transparent;");
		currentPets.setLayoutX(175);
		currentPets.setLayoutY(125);
		
		cancel = new Button("CANCEL");
		cancel.setMinWidth(100);
		cancel.setMinHeight(50);
		cancel.setLayoutX(50);
		cancel.setLayoutY(325);
		cancel.setStyle("-fx-focus-color: transparent;");
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				reset();
				gsm.setState(gsm.MAIN_MENU);
			}
		});
		
		delete = new Button("DELETE");
		delete.setMinWidth(100);
		delete.setMinHeight(50);
		delete.setLayoutX(200);
		delete.setLayoutY(325);
		delete.setStyle("-fx-focus-color: transparent;");
		delete.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				gm.deletePet(currentPets.getValue());
				reset();
			}
		});
		
		load = new Button("LOAD");
		load.setMinWidth(100);
		load.setMinHeight(50);
		load.setLayoutX(350);
		load.setLayoutY(325);
		load.setStyle("-fx-focus-color: transparent;");
		load.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				gm.setLoadedPet(currentPets.getValue());
				gsm.setState(gsm.MAIN_GAME);
			}
		});
		
		canvasPane.getChildren().addAll(canvas, currentPets, cancel, delete, load);
		
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
		list = FXCollections.observableArrayList();
		
		for (int i = 0; i < gm.getNumPets(); i++) {
			list.add(gm.getPet(i).getName());
		}
		currentPets.setItems(list);
	}
}
