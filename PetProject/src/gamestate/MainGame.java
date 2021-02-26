package gamestate;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import manager.GameManager;
import manager.Pet;

public class MainGame extends GameState {
	
	private Pane canvasPane;
	private Canvas canvas;
	private Canvas deathCanvas;
	private GraphicsContext g;
	private GraphicsContext g2;
	
	private Label deathNameLabel;
	private Label deathAgeLabel;
	private Label deathMessageLabel;
	
	private Image background;
	private Image backgroundDeath;
	private Image petImage;
	private Label nameLabel;
	
	private Label ageLabel;
	private Label weightLabel;
	private Label healthLabel;
	private Label happinessLabel;
	private Label hungerLabel;
	private Label energyLabel;
	
	private Button exitButton;
	
	private Button feedButton;
	private GridPane feedPane;
	private Button meatButton;
	private Button candyButton;
	private Button medicineButton;
	private Button vegetableButton;
	
	private Button playButton;
	private Pane playPane;
	private Button throwBallButton;
	private Button petButton;
	
	private Button sleepButton;
	
	private Pet loadedPet;

	public MainGame(GameManager gm, GameStateManager gsm) {
		super(gm, gsm);
		
		background = new Image("mainGameBackground.png");
		backgroundDeath = new Image("deadPetBackground.png");
		
		canvas = new Canvas(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
		deathCanvas = new Canvas(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
		deathCanvas.setVisible(false);
		g = canvas.getGraphicsContext2D();
		g2 = canvas.getGraphicsContext2D();
		
		canvasPane = new Pane();
		
		deathNameLabel = new Label();
		deathNameLabel.setTextFill(Color.FLORALWHITE);;
		deathNameLabel.setMinHeight(20);
		deathNameLabel.setMinWidth(90);
		deathNameLabel.setLayoutX(204);
		deathNameLabel.setLayoutY(185);
		deathNameLabel.setAlignment(Pos.CENTER);
		deathNameLabel.setVisible(false);
		
		deathAgeLabel = new Label();
		deathAgeLabel.setTextFill(Color.FLORALWHITE);
		deathAgeLabel.setMinHeight(10);
		deathAgeLabel.setMinWidth(90);
		deathAgeLabel.setLayoutX(204);
		deathAgeLabel.setLayoutY(245);
		deathAgeLabel.setAlignment(Pos.CENTER);
		deathAgeLabel.setVisible(false);
		
		deathMessageLabel = new Label();
		deathMessageLabel.setTextFill(Color.FLORALWHITE);
		deathMessageLabel.setMinHeight(10);
		deathMessageLabel.setMinWidth(90);
		deathMessageLabel.setLayoutX(204);
		deathMessageLabel.setLayoutY(215);
		deathMessageLabel.setAlignment(Pos.CENTER);
		deathMessageLabel.setVisible(false);
		
		nameLabel = new Label();
		//nameLabel.setMinWidth(130);
		nameLabel.setMinHeight(20);
		nameLabel.setMinWidth(128);
		nameLabel.setLayoutX(185);
		nameLabel.setLayoutY(10);
		nameLabel.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		nameLabel.setAlignment(Pos.CENTER);
		
		ageLabel = new Label();
		ageLabel.setMinHeight(20);
		ageLabel.setMinWidth(128);
		ageLabel.setLayoutX(185);
		ageLabel.setLayoutY(40);
		ageLabel.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		ageLabel.setAlignment(Pos.CENTER);
		
		weightLabel = new Label();
		weightLabel.setMinHeight(20);
		weightLabel.setLayoutX(249);
		weightLabel.setLayoutY(40);
		weightLabel.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		
		healthLabel = new Label();
		healthLabel.setMinHeight(20);
		healthLabel.setLayoutX(25);
		healthLabel.setLayoutY(70);
		healthLabel.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		
		happinessLabel = new Label();
		happinessLabel.setMinHeight(20);
		happinessLabel.setLayoutX(25);
		happinessLabel.setLayoutY(100);
		happinessLabel.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		
		hungerLabel = new Label();
		hungerLabel.setMinHeight(20);
		hungerLabel.setLayoutX(338);
		hungerLabel.setLayoutY(70);
		hungerLabel.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		
		energyLabel = new Label();
		energyLabel.setMinHeight(20);
		energyLabel.setLayoutX(338);
		energyLabel.setLayoutY(100);
		energyLabel.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		
		exitButton = new Button("EXIT");
		exitButton.setMinWidth(80);
		exitButton.setMinHeight(50);
		exitButton.setLayoutX(35);
		exitButton.setLayoutY(325);
		exitButton.setStyle("-fx-focus-color: transparent;");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				gsm.setState(gsm.MAIN_MENU);
			}
		});
		
		meatButton = new Button("MEAT +25");
		meatButton.setStyle("-fx-focus-color: transparent;");
		meatButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				loadedPet.feed("meat");
				feedPane.setVisible(false);
			}
		});
		
		vegetableButton = new Button("VEGETABLE +15");
		vegetableButton.setStyle("-fx-focus-color: transparent;");
		vegetableButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				loadedPet.feed("vegetable");
				feedPane.setVisible(false);
			}
		});
		
		candyButton = new Button("CANDY +10");
		candyButton.setStyle("-fx-focus-color: transparent;");
		candyButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				loadedPet.feed("candy");
				feedPane.setVisible(false);
			}
		});
		
		medicineButton = new Button("MEDICINE");
		medicineButton.setStyle("-fx-focus-color: transparent;");
		medicineButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				loadedPet.feed("medicine");
				feedPane.setVisible(false);
			}
		});
		
		feedPane = new GridPane();
		feedPane.setPadding(new Insets(5));
		GridPane.setConstraints(meatButton, 0, 0);
		GridPane.setConstraints(vegetableButton, 0, 1);
		GridPane.setConstraints(candyButton, 1, 0);
		GridPane.setConstraints(medicineButton, 1, 1);
		feedPane.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.2, 0.4, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		feedPane.setMinWidth(200);
		feedPane.setMinHeight(100);
		feedPane.setLayoutX(149);
		feedPane.setLayoutY(150);
		feedPane.getChildren().addAll(meatButton, vegetableButton, candyButton, medicineButton);
		feedPane.setVisible(false);
		
		feedButton = new Button("FEED");
		feedButton.setMinWidth(80);
		feedButton.setMinHeight(50);
		feedButton.setLayoutX(151);
		feedButton.setLayoutY(325);
		feedButton.setStyle("-fx-focus-color: transparent;");
		feedButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				playPane.setVisible(false);
				feedPane.setVisible(!feedPane.isVisible());
			}
		});
		
		throwBallButton = new Button("THROW BALL");
		throwBallButton.setStyle("-fx-focus-color: transparent;");
		throwBallButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				loadedPet.play("throwball");
				playPane.setVisible(false);
			}
		});
		
		petButton = new Button("PET");
		petButton.setStyle("-fx-focus-color: transparent;");
		petButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				loadedPet.play("pet");
				playPane.setVisible(false);
			}
		});
		
		playPane = new GridPane();
		playPane.setPadding(new Insets(5));
		GridPane.setConstraints(throwBallButton, 0, 0);
		GridPane.setConstraints(petButton, 1, 0);
		playPane.setBackground(new Background(new BackgroundFill(new Color(0.4, 0.75, 0.64, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		playPane.setMinWidth(200);
		playPane.setMinHeight(100);
		playPane.setLayoutX(149);
		playPane.setLayoutY(150);
		playPane.getChildren().addAll(throwBallButton, petButton);
		playPane.setVisible(false);
		
		playButton = new Button("PLAY");
		playButton.setMinWidth(80);
		playButton.setMinHeight(50);
		playButton.setLayoutX(268);
		playButton.setLayoutY(325);
		playButton.setStyle("-fx-focus-color: transparent;");
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				feedPane.setVisible(false);
				playPane.setVisible(!playPane.isVisible());
			}
		});
		
		sleepButton = new Button("SLEEP");
		sleepButton.setMinWidth(80);
		sleepButton.setMinHeight(50);
		sleepButton.setLayoutX(384);
		sleepButton.setLayoutY(325);
		sleepButton.setStyle("-fx-focus-color: transparent;");
		sleepButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				playPane.setVisible(false);
				feedPane.setVisible(false);
				
				if(loadedPet.isAsleep()) {
					loadedPet.wakeUp();
				} else {
					loadedPet.sleep();
				}
			}
		});
		
		//*REMOVED weightLabel
		canvasPane.getChildren().addAll(canvas, nameLabel, ageLabel, hungerLabel, energyLabel
				, healthLabel, happinessLabel, deathCanvas
				, exitButton, feedButton, playButton
				, sleepButton, feedPane, playPane, deathNameLabel, deathAgeLabel, deathMessageLabel);
		
		this.scene = new Scene(canvasPane);
	}
	
	private void checkDeath() {
		Platform.runLater(()->{
			if (loadedPet.isDead()) {
				nameLabel.setVisible(false);
				//canvas.setVisible(false);
				ageLabel.setVisible(false);
				hungerLabel.setVisible(false);
				energyLabel.setVisible(false);
				weightLabel.setVisible(false);
				healthLabel.setVisible(false);
				happinessLabel.setVisible(false);
				feedButton.setVisible(false);
				playButton.setVisible(false);
				sleepButton.setVisible(false);
				feedPane.setVisible(false);
				playPane.setVisible(false);
				deathCanvas.setVisible(true);
				g2.drawImage(backgroundDeath, 0, 0);
				deathAgeLabel.setText("Died at " + loadedPet.getAge());
				deathMessageLabel.setText(loadedPet.getType());
				deathNameLabel.setVisible(true);
				deathAgeLabel.setVisible(true);
				deathMessageLabel.setVisible(true);
			} else {
				nameLabel.setVisible(true);
				ageLabel.setVisible(true);
				hungerLabel.setVisible(true);
				energyLabel.setVisible(true);
				weightLabel.setVisible(true);
				healthLabel.setVisible(true);
				happinessLabel.setVisible(true);
				feedButton.setVisible(true);
				playButton.setVisible(true);
				sleepButton.setVisible(true);
				deathCanvas.setVisible(false);
				deathNameLabel.setVisible(false);
				deathAgeLabel.setVisible(false);
				deathMessageLabel.setVisible(false);
			}
		});
	}

	@Override
	public void update() {
		//Tick pet after certain amount of time
		//Updates at 10 fps
		loadedPet.tick();
	}

	@Override
	public void render() {
		Platform.runLater(()->{
			if (!loadedPet.isDead()) {
				g.drawImage(background, 0, 0);
				g.drawImage(petImage, 185, 68);
				
				String weightText = "WEIGHT: ";
				int weight = loadedPet.getWeight();
				if (weight == 0) {
					weightText += "Very Underweight";
				} else if (weight == 1) {
					weightText += "Slightly Underweight";
				} else if (weight == 2) {
					weightText += "Light Normal Weight";
				} else if (weight == 3) {
					weightText += "Normal Weight";
				} else if (weight == 4) {
					weightText += "Heavy Normal Weight";
				} else if (weight == 5) {
					weightText += "Slightly Overweight";
				} else if (weight == 6) {
					weightText += "Very Overweight";
				}
				weightLabel.setText(weightText);
				ageLabel.setText("AGE: " + loadedPet.getAge() + " " + weightText);
				
				//ageLabel.setText("AGE: " + loadedPet.getAge());
				
				healthLabel.setText("HEALTH: " + loadedPet.getHealth() + "%");
				happinessLabel.setText("HAPPINESS: " + loadedPet.getHappiness() + "%");
				hungerLabel.setText("HUNGER: " + loadedPet.getHunger() + "%");
				energyLabel.setText("ENERGY: " + loadedPet.getEnergy() + "%");
				petImage = new Image(loadedPet.getType().toLowerCase() + loadedPet.getEmotion() + ".png");
			}
			
			checkDeath();
		});
	}

	@Override
	public void reset() {
		loadedPet = gm.getLoadedPet();
		petImage = new Image(loadedPet.getType().toLowerCase() + loadedPet.getEmotion() + ".png");
		
		nameLabel.setText(loadedPet.getName());
		//nameLabel.setTextAlignment(TextAlignment.CENTER);
		
		deathNameLabel.setText(loadedPet.getName());
		
		checkDeath();
	}
}
