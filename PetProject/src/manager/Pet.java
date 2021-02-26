package manager;

import java.io.Serializable;

public class Pet implements Serializable{
	
	private String emotion;
	public final String ANGRY = "Angry";
	public final String HAPPY = "Happy";
	public final String NEUTRAL = "Neutral";
	public final String SAD = "Sad";
	public final String SICK = "Sick";
	public final String ASLEEP = "Sleeping";
	public final String DEAD = "Dead";
	
	private String name;
	private String type;
	
	public final int MAX_AGE = 100;
	private final int TIME_PER_AGE = 1; //Age every 1 minute
	private final int GAME_FPS = 10;
	private int ticks;
	
	private int age; //0 - 100
	private int weight; //0 - 6
	public final int MAX_WEIGHT = 6;
	private int health; //1 - 100
	public final int MAX_HEALTH = 100;
	private int happiness; //1 - 100
	public final int MAX_HAPPINESS = 100;
	private int hunger; //1 - 100
	public final int MAX_HUNGER = 100;
	private int energy; //1 - 100
	public final int MAX_ENERGY = 100;
	private boolean asleep;
	private boolean overFed;
	private boolean sick;
	private boolean dead;
	
	public Pet(String type, String name) {
		this.type = type;
		this.name = name;
		this.emotion = this.HAPPY;
		this.asleep = false;
		this.sick = false;
		this.dead = false;
		this.overFed = false;
		
		this.age = 0;
		this.weight = 3;
		this.ticks = 0;
		this.health = 100;
		this.happiness = 100;
		this.hunger = 100;
		this.energy = 100;
	}
	
	public void tick() {
		//Ticks 10 times per second, at frame rate
		//600 ticks per minute
		if (!dead) {
			this.ticks++;
			
			//10 times per minute
			if (ticks % 60 == 0) {
				System.out.println("BASIC TICK");
				
				//Change hunger
				subHunger(1);
				if (sick) {
					subHunger(2);
				}
				
				if (weight <= 1) {
					subHunger(4);
				}
				
				if (energy < 50) {
					subHunger(2);
					addEnergy(2);
				} else if (energy < 75) {
					addEnergy(1);
				}
				
				//Change happiness
				if (hunger < this.MAX_HUNGER / 2) {
					subHappiness(4);
				} else {
					addHappiness(1);
				}
				if (asleep) {
					addHappiness(1);
				}
				if (sick) {
					subHappiness(5);
				}
				if (weight <= 1 || weight >= 5) {
					subHappiness(2);
				}
				if (energy < 50) {
					subHappiness(1);
				} else {
					addHappiness(1);
				}
				
				//Change energy
				energy-= 1;
				if (asleep) {
					addEnergy(10);
				}
				if (sick) {
					subEnergy(3);
				}
				
				//Change health
				if (weight < 1 || weight > 5) {
					subHealth(4);
				}
				if (happiness < this.MAX_HAPPINESS / 2) {
					subHealth(1);
				}
				if (hunger < this.MAX_HUNGER / 4) {
					subHealth(2);
				}
				
				//Change asleep
				if (energy < 10) {
					asleep = true;
				}
				if (energy > 90) {
					asleep = false;
				}
				
				//Change dead
				if (health <= 0) {
					dead = true;
				}
				
				//Change emotion
				this.emotion = this.HAPPY;
				if (happiness < 75 || energy < 50) {
					this.emotion = this.NEUTRAL;
				}
				if (happiness < 50) {
					this.emotion = this.SAD;
				}
				if (hunger < 50) {
					this.emotion = this.ANGRY;
				}
				if (sick || health < 50) {
					this.emotion = this.SICK;
				}
				if (asleep) {
					this.emotion = this.ASLEEP;
				}
				if (dead) {
					this.emotion = this.DEAD;
				}
			}
			
			//AGE - every minute
			if (ticks >= (GAME_FPS * 60)) {
				System.out.println("AGE TICK");
				ticks = 0;
				age += TIME_PER_AGE;
				
				int r = randomInRange(1, 8);
				if (r == 1) {
					this.sick = true;
				} else if (r == 2 && this.happiness < this.MAX_HAPPINESS / 2) {
					this.sick = true;
				}
				
				if (hunger < MAX_HUNGER / 4) {
					subWeight(1);
					addHunger(20);
				}
				
				if (overFed) {
					overFed = false;
					subHealth(5);
					addWeight(1);
				}
				
				if (sick) {
					subHealth(10);
				}
			}
		}
	}
	
	public void feed(String foodType) {
		if (foodType.compareTo("meat") == 0) {
			addHunger(25);
			addHappiness(10);
		} else if (foodType.compareTo("vegetable") == 0) {
			addHunger(15);
			addHealth(2);
			addEnergy(5);
		} else if (foodType.compareTo("candy") == 0) {
			addHappiness(15);
			addHunger(10);
			addEnergy(10);
		} else if (foodType.compareTo("medicine") == 0) {
			sick = false;
			addHealth(5);
			subHappiness(5);
			subEnergy(5);
		}
	}
	
	public void sleep() {
		this.asleep = true;
	}
	
	public void wakeUp() {
		if (energy > 50) {
			this.asleep = false;
		}
	}
	
	public boolean isAsleep() {
		return this.asleep;
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public void play(String playType) {
		if (playType.compareTo("throwball") == 0) {
			addHappiness(10);
			subEnergy(10);
			subHunger(2);
			if (!sick) {
				addHealth(2);
			} else {
				subHealth(4);
			}
		} else if (playType.compareTo("pet") == 0) {
			addHappiness(15);
		}
	}
	
	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
	
	public String getEmotion() {
		return this.emotion;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getHappiness() {
		return this.happiness;
	}
	
	public int getHunger() {
		return this.hunger;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	private void addHunger(int h) {
		this.hunger += h;
		
		if (this.hunger > this.MAX_HUNGER) {
			overFed = true;
			this.hunger = this.MAX_HUNGER;
		}
	}
	
	private void subHunger(int h) {
		this.hunger -= h;
		
		if (this.hunger < 0) {
			this.hunger = 0;
		}
	}
	
	private void addHappiness(int h) {
		this.happiness += h;
		
		if (this.happiness > this.MAX_HAPPINESS) {
			this.happiness = this.MAX_HAPPINESS;
		}
	}
	
	private void subHappiness(int h) {
		this.happiness -= h;
		
		if (this.happiness < 0) {
			this.happiness = 0;
		}
	}
	
	private void addHealth(int h) {
		this.health += h;
		
		if (this.health > this.MAX_HEALTH) {
			this.health = this.MAX_HEALTH;
		}
	}
	
	private void subHealth(int h) {
		this.health -= h;
		
		if (this.health < 0) {
			this.health = 0;
		}
	}
	
	private void addEnergy(int h) {
		this.energy += h;
		
		if (this.energy > this.MAX_ENERGY) {
			this.energy = this.MAX_ENERGY;
		}
	}
	
	private void subEnergy(int h) {
		this.energy -= h;
		
		if (this.energy < 0) {
			this.energy = 0;
		}
	}
	
	private void addWeight(int h) {
		this.weight += h;
		
		if (this.weight > this.MAX_WEIGHT) {
			this.weight = this.MAX_WEIGHT;
		}
	}
	
	private void subWeight(int h) {
		this.weight -= h;
		
		if (this.weight < 0) {
			this.weight = 0;
		}
	}
	
	public int randomInRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
	
	@Override
	public String toString() {
		return type + " - " + name;
	}
}
