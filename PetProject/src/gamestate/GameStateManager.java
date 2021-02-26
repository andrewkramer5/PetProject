package gamestate;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.GameManager;

public class GameStateManager {
	
	public final int MAIN_MENU = 0;
	public final int PET_CREATION = 1;
	public final int LOAD_PET = 2;
	public final int MAIN_GAME = 3;
	
	private final int MAX_STATES = 10;
	private int currentState;
	private Scene[] gameStates;
	private GameState[] controllers;
	
	private Stage window;
	
	public GameStateManager(Stage window, GameManager gm) {
		this.window = window;
		//gameStates = new GameState[MAX_STATES];
		gameStates = new Scene[MAX_STATES];
		controllers = new GameState[MAX_STATES];
		
		/*
		gameStates[this.MAIN_MENU] = new MainMenu(gm, this);
		gameStates[this.PET_CREATION] = new PetCreation(gm, this);
		gameStates[this.LOAD_PET] = new LoadPet(gm, this);
		gameStates[this.MAIN_GAME] = new MainGame(gm, this);
		*/
		
		FXMLLoader loader = new FXMLLoader(GameStateManager.class.getResource("/gamestate/MainMenu.fxml"));
		loader.setController(new MainMenu(gm, this));
		controllers[this.MAIN_MENU] = loader.getController();
		Parent root;
		try {
			root = loader.load();
			gameStates[this.MAIN_MENU] = new Scene(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		currentState = this.MAIN_MENU;
		setState(this.MAIN_MENU);
	}
	
	public void setState(int state) {
		this.currentState = state;
		this.controllers[this.currentState].reset();
		window.setScene(this.gameStates[this.currentState]);
		window.sizeToScene();
	}
	
	public Scene getGameState() {
		return gameStates[this.currentState];
	}
	
	public void update() {
		controllers[this.currentState].update();
	}
	
	public void render() {
		controllers[this.currentState].render();
	}
}
