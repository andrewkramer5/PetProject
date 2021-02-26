package manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gamestate.GameStateManager;
import javafx.stage.Stage;

public class GameManager implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	private double UPDATE_CAP = 1.0/10.0;
	
	private GameStateManager gsm;
	private Stage window;
	private FileOutputStream outFile;
	private ObjectOutputStream outObj;
	private FileInputStream inFile;
	private ObjectInputStream inObj;
	private boolean firstStartup = false;
	
	private String saveFolderPath;
	private ArrayList<Pet> createdPets = new ArrayList<Pet>();
	private int currentPetIndex;
	private Pet loadedPet;
	
	@SuppressWarnings("unchecked")
	public GameManager(Stage window) {
		this.window = window;
		loadedPet = null;
		currentPetIndex = 0;
		
		//saveFolderPath = System.getProperty("user.home") + File.separator + "Documents" + "/PetProjectSaves";
		saveFolderPath = "PetProjectSaves";
		
		File saveFolder = new File(saveFolderPath);
		saveFolder.mkdir();
		File game = new File(saveFolder, "game.data");
		firstStartup = !game.exists();
		
		if (!firstStartup) {
			try {
				inFile = new FileInputStream(game);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				inObj = new ObjectInputStream(inFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				createdPets = (ArrayList<Pet>) inObj.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				inObj.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			if (firstStartup) {
				game.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			outFile = new FileOutputStream(game);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			outObj = new ObjectOutputStream(outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Size of loaded array list: " + createdPets.size());
		
		gsm = new GameStateManager(window, this);
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public void pause() {
		running = false;
	}
	
	@Override
	public void run() {
		running = true;
		
		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		//For frame rate
		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		
		while (running) {
			
			render = false;
			
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime; //time between loop runs
			lastTime = firstTime;
			
			unprocessedTime += passedTime; //add time between loop runs to the time that hasn't updated
			frameTime += passedTime;
			
			while (unprocessedTime >= this.UPDATE_CAP) {
				unprocessedTime -= this.UPDATE_CAP;
				render = true;
				
				//UPDATE GAME
				gsm.update();
				
				if (frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
					
					//System.out.println("FPS: " + fps);
				}
			}
			
			if (render) {
				
				//RENDER GAME
				gsm.render();
				
				frames++;
				
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addPet(Pet p) {
		this.createdPets.add(p);
		
		System.out.println("\nPets:");
		for (int i = 0; i < this.createdPets.size(); i++) {
			System.out.println(this.createdPets.get(i).toString());
		}
	}
	
	public void deletePet(String name) {
		for (int i = 0; i < this.createdPets.size(); i++) {
			if (name.compareTo(this.createdPets.get(i).getName()) == 0) {
				this.createdPets.remove(i);
				i--;
			}
		}
		
		System.out.println("\nPets:");
		for (int i = 0; i < this.createdPets.size(); i++) {
			System.out.println(this.createdPets.get(i).toString());
		}
	}
	
	public void setLoadedPet(String name) {
		for (int i = 0; i < this.createdPets.size(); i++) {
			if (name.compareTo(this.createdPets.get(i).getName()) == 0) {
				this.loadedPet = this.createdPets.get(i);
				this.currentPetIndex = i;
				return;
			}
		}
	}
	
	public Pet getLoadedPet() {
		return this.loadedPet;
	}
	
	public Pet getPet (int i) {
		return this.createdPets.get(i);
	}
	
	public int getNumPets() {
		return this.createdPets.size();
	}
	
	public void saveGameData() {
		try {
			outObj.writeObject(createdPets);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			outObj.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exitGame() {
		this.window.close();
	}
}
