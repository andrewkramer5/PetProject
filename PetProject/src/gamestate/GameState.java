package gamestate;
import javafx.scene.Scene;
import manager.GameManager;

public abstract class GameState {
	
	protected Scene scene;
	protected GameManager gm;
	protected GameStateManager gsm;
	public final int DEFAULT_WIDTH = 500;
	public final int DEFAULT_HEIGHT = 400;
	
	public GameState(GameManager gm, GameStateManager gsm) {
		this.gm = gm;
		this.gsm = gsm;
	}
	
	public abstract void update();
	
	public abstract void render();
	
	public Scene getScene() {
		return scene;
	}
	
	public abstract void reset();
}
