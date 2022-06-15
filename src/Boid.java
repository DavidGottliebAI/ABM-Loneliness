
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Author: David Gottlieb
 */

public class Boid {
	
	/**
	 * Hyperparameters
	 */
	private static int initialVelocity = 3;
	private static double socializationFactor = 0.1;
	private static double desocializationFactor = -0.02;
	private static double maxSocial = 2;
	
	/**
	 * Public parameters
	 */
	public double[] position = new double[2];
	public double[] velocity = {initialVelocity, initialVelocity};
	public String fitnessType;
	public double energy = 1.0;
	public boolean hasDisease = false;
	public boolean toRemove = false;
	public int lifespan = 50;
	public int diseaseResistance = 40;
	private int lossEffect = 100;
	
	/**
	 * Private parameters: 
	 * @socialExpected: the amount of **meaningful** social interaction an agent expects to receive
	 * @socialPerceived: the amount of **meaningful** social interaction an agent perceives
	 * @interactionChance: the probability that an agent will interact with another agent
	 * @mutualInterest: a value representing the values and interests of an agent, to compare against others
	 * @loneliness: the difference between expected and perceived social interaction
	 * @sensitivity: how much an agent is affected by loneliness
	 */
	private double[] bestPosition;
	private double socialExpected = 1.0;
	public double socialPerceived = maxSocial;
	private double connectionChance = 0.05;
	private double loneliness;
	private double fitness = 0;
	private double bestFitness;
	private double[][] rules = new double[4][2];
	
	private ArrayList<Double> sensitivity = new ArrayList<Double>();
	private ArrayList<Double> defaultBehavior = new ArrayList<Double>();
	private HashMap<Boid, Double> connections = new HashMap<Boid, Double>();
	private ArrayList<Boid> connectionList = new  ArrayList<Boid>();
	private HashMap<ArrayList<Boid>, Double> groups = new HashMap<ArrayList<Boid>, Double>();
	private String color;
	public boolean toKill = false;
	
	/**
	 * Boid: Random boid constructor; based on @value
	 * @params: x, y, value, fitness, color
	 */
//	public Boid(int x, int y, double value, String fitness, String color) {
//		this.color = color;
//		Random random = new Random();
//		this.position[0] = x;
//		this.position[1] = y;
//		this.fitnessType = fitness;
//		this.bestFitness = Double.NEGATIVE_INFINITY;
//		this.personality = value;
//		this.mutualInterest = this.personality * random.nextDouble();
//		this.socialExpected = this.personality * random.nextDouble();
//		this.interactionChance = this.personality * random.nextDouble();
//		for(int i = 0; i < 5; i++) this.sensitivity.add(-1 + 2 * random.nextDouble());
//	}
//	
	/**
	 * Boid: Boid constructor
	 * @param sensitivity 
	 * @params: x, y, mutualInterest, socialExpected, interactionChance, sensitivity, fitness, color
	 */
	public Boid(int x, int y, double socialExpected, double[] defaultBehavior, double[] sensitivity, String fitness, String color) {
		this.color = color;
		this.position[0] = x;
		this.position[1] = y;
		this.bestPosition = new double[2];
		this.bestPosition[0] = x;
		this.bestPosition[1] = y;
		this.fitnessType = fitness;
		this.bestFitness = Double.NEGATIVE_INFINITY;
		this.socialExpected = socialExpected;
		for(int i = 0; i < sensitivity.length; i++) this.sensitivity.add(sensitivity[i]);
		for(int i = 0; i < defaultBehavior.length; i++) this.defaultBehavior.add(defaultBehavior[i]);
	}
	
//	@Override
//	public int compareTo(Boid otherBoid) {
//		return (int) Math.ceil((otherBoid.getFitness() - this.getFitness()));
//	}
	
	/**
	 * addConnection: add connection between two agents
	 * @params: boid, strength
	 */
	public void addConnection(Boid boid) {
//		this.connections.put(boid);
		this.connectionList.add(boid);
	}
	
	/**
	 * strengthenConnection: strengthen connection between two agents
	 * @params: boid
	 */
//	public void strengthenConnection(Boid b) {
//		this.connections.put(b, connections.get(b) + connectionStrength);
//	}
	
	public void removeConnections() {
		for(Boid b : connectionList) {
			b.connectionList.remove(this);
			b.perceivedIsolation(lossEffect);
		}
	}
	
	/**
	 * addGroup: create group between group of agents
	 * @params: members, strength
	 */
	public void addGroup(ArrayList<Boid> members, double strength) {
		this.groups.put(members, strength);
	}
	
	/**
	 * perceivedSocialization: adjust an agent's perceived social interaction positively
	 */
	public void perceivedSocialization() {
		this.socialPerceived += socializationFactor;
		if(this.socialPerceived > maxSocial) {
			this.socialPerceived = maxSocial;
		}
	}
	
	/**
	 * perceivedSocialization: adjust an agent's perceived social interaction negatively
	 * @param effect 
	 */
	public void perceivedIsolation(int effect) {
		this.socialPerceived += effect * desocializationFactor;
		if(this.socialPerceived < -maxSocial) {
			this.socialPerceived = -maxSocial;
		}
	}
	
	/**
	 * mutualInterest: calculate if two agents are compatible in respect to common interest
	 */
//	public void mutualInterest(Boid b) {
//		if(this.mutualInterest - b.mutualInterest < interestDifference) {
//			this.perceivedSocialization();
//		}
//	}
	

	public void calculateLoneliness() {
		this.loneliness = this.socialExpected - this.socialPerceived;
	}
	
	/**
	 * List of getters
	 */
	
	public double[] getVelocity() {
		return this.velocity;
	}

	public double getFitness() {
		return this.fitness;
	}
	
	public double getBestFitness() {
		return this.bestFitness;
	}

	public double[] getPosition() {
		return this.position;
	}

	public double[] getBestPosition() {
		return this.bestPosition;
	}
	
	public HashMap<Boid, Double> getConnections(){
		return this.connections;
	}

	public ArrayList<Boid> getConnectionList() {
		return this.connectionList;
	}
	
	public HashMap<ArrayList<Boid>, Double> getGroups() {
		return this.groups;
	}

	public double getLoneliness() {
		return this.loneliness;
	}

	public ArrayList<Double> getSensitivity() {
		return this.sensitivity;
	}

	public double getConnectionChance() {
		return this.connectionChance;
	}

//	public double getInterest() {
//		return this.mutualInterest;
//	}

	public double getSocialExpected() {
		return this.socialExpected;
	}

	public String getColor() {
		return this.color;
	}
	
	public double[][] getRules(){
		return this.rules;
	}
	
	public double getExpected(){
		return this.socialExpected;
	}

	
	/**
	 * List of setters
	 */

	public void setRules(double[][] ruleset) {
		this.rules = ruleset;
	}
	
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public void setBestFitness(double currentFitness) {
		this.bestFitness = currentFitness;
	}
	
	public void setBestPosition(double[] position) {
		this.bestPosition[0] = position[0];
		this.bestPosition[1] = position[1];
	}

//	public void setInterest(double newInterest) {
//		this.mutualInterest = newInterest;
//	}

	public void setExpected(double newExpected) {
		this.socialExpected = newExpected;
	}

	public void setInteractChance(double newInteractionChance) {
		this.connectionChance = newInteractionChance;
	}

	public void setSensitivity(int index, double value) {
		sensitivity.set(index, value);
	}

	public void getDisease(boolean b) {
		this.hasDisease = b;
	}

	public void setPosition(int newX, int newY) {
		double[] newPos = {newX, newY};
		this.position = newPos;
	}

	public ArrayList<Double> getDefaultBehavior() {
		return this.defaultBehavior;
	}

	public double getSocialPercieved() {
		return this.socialPerceived;
	}
}
