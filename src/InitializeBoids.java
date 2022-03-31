import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class InitializeBoids implements Comparable<InitializeBoids>, Iterable<Boid> {
	
	/**
	 * Public parameters
	 */
	public int numBoids;
	public int maxVelocity = 10;
	public double[][][] popData;
	public double[][] fitnessData;
	public ArrayList<Boid> boids = new ArrayList<Boid>();
	public ArrayList<Boid> deadBoids = new ArrayList<Boid>();
	public BoidVisualizer visualizer;
	public boolean simulationRunning = false;
	public  String fitnessType = "";
	public JFrame frame;
	public double totalFitness = 0;
	public double[] resourceLocation = new double[2];
	public Predator predator = null;
	
	/**
	 * Private parameters
	 */
	private static int dimEnvironment = 200;
	private static int boidDistance = 5;
	private static int velBoundChg = 2;
	private static int timeSteps = 1000;
	private static int interactionDistance = 1000;
	private static int maxConnections = 3;
	private static int minGroupSize = 3;
	private static int resourceDistance = 20;
	private static int spreadDistance = 10;
	private static double malaise = 1.5;
	private static double diseaseSpreadChance = 0.02;
	private JPanel buttonPanel;
	private JButton startButton;
	private int curTime = 0;
	private String visualize;
	private double[] predatorVec = {0, 0};
	private double commonInterest = 0.1;
	
	public InitializeBoids(ArrayList<Boid> boids, int seed) {
		this.numBoids = boids.size();
		fitnessType = boids.get(0).fitnessType;
		totalFitness = 0;
		this.frame = new JFrame();
		this.frame.setSize(dimEnvironment * 5, dimEnvironment * 5);
		for(int i = 0; i < boids.size(); i++) {
			
			double[] sensitivity = new double[5];
			for(int j = 0; j < boids.get(i).getSensitivity().size(); j++) {
				sensitivity[j] = boids.get(i).getSensitivity().get(j);
			}
			double fitness = boids.get(i).getFitness();
			
			Random random = MyRandom.getInstanceOfRandom();
			
			Boid newBoid = new Boid(random.nextInt(200), random.nextInt(200), 0.0, 0.0, 0.0, sensitivity, "one", "black");
			newBoid.setFitness(fitness);
			this.boids.add(newBoid);
		}
		
		simulationRunning = true;
		popData = new double[numBoids][3][timeSteps + 1];
		fitnessData = new double[numBoids][timeSteps];
	}
	
	/**
	 * InitializeBoids: Initialize constructor
	 */
	public InitializeBoids(String visualize, int numBoids, String fitnessType, int timeSteps) {
		this.timeSteps = timeSteps;
		this.numBoids = numBoids;
		this.fitnessType = fitnessType;
		this.visualize = visualize;
		this.frame = new JFrame();
		this.frame.setSize(dimEnvironment * 5, dimEnvironment * 5);
		if(visualize.equals("viz")) {
			this.frame.setTitle("Boids!");
			this.buttonPanel = new JPanel();
			this.startButton = new JButton("Start");
			this.startButton.addActionListener(new startListener(this, this.startButton));
			
			this.visualizer = new BoidVisualizer(this);
			this.buttonPanel.add(startButton);
			this.frame.add(this.visualizer);
			this.frame.add(buttonPanel, BorderLayout.NORTH);
			this.frame.addComponentListener(new ComponentAdapter() {
	            public void componentMoved(ComponentEvent e) {
	               // TODO: earthquake
	            }
	        });
			this.frame.addMouseListener(new mouseListener(this.frame));
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.frame.setLocation(50, 50); // might want to play with later
			this.frame.setVisible(true);
		} else {
			simulationRunning = true;
		}

		this.popData = new double[numBoids][3][timeSteps + 1];
		this.fitnessData = new double[numBoids][timeSteps];
		
		if(visualize.equals("viz")) {
			MyRandom.intializeRandom(1);
			if(fitnessType == "predator") {
				Random random = MyRandom.getInstanceOfRandom();
				predator = new Predator(random.nextInt(200), random.nextInt(200), boids);
			}
			Timer t = new Timer(100, new ActionListener() {

				@Override
					
				public void actionPerformed(ActionEvent arg0) {
					
					if(simulationRunning) {
						
						System.out.println(curTime);
						for(Boid b : boids) {
							System.out.println("x: " + b.position[0] + ", y: " + b.position[1]);
						}
						System.out.println("");
						
						if(curTime == 0 && fitnessType.equals("disease")) {
							Random random = MyRandom.getInstanceOfRandom();
							boids.get(random.nextInt(numBoids)).getDisease(true);
						}
						
						update();
						
						
						if(fitnessType == "disease") {
							for(Boid b : boids) {
								if(b.hasDisease) spread(b);
							}
							
							for(Boid b : boids) {
								if(b.hasDisease) {
									b.lifespan--;
									if(b.lifespan == 0) {
										b.toRemove = true;
									}
								}
							}
						}
						
						for(int k = 0; k < boids.size(); k++) {
							if(boids.get(k).toKill) {
								boids.get(k).removeConnections();
								boids.remove(boids.get(k));
							}
						}
						// Set current x, y, fitness, and t for each boid
						for(int i = 0; i < boids.size(); i++) {
							popData[i][0][curTime] = boids.get(i).getPosition()[0];
							popData[i][1][curTime] = boids.get(i).getPosition()[1];
							popData[i][2][curTime] = boids.get(i).getFitness();
						}
						
						
						for(int i = 0; i < 6; i++) {
							visualizer.update(numBoids, boids, i);
							visualizer.repaint();
						}
						
						curTime++;
	
						if(curTime >= timeSteps) {
							simulationRunning = false;
							calculateTotalFitness();
							
							return;
						}
					}
				}

//				private void spread(Boid boid) {
//					for(Boid b : boids) {
//						Random random = MyRandom.getInstanceOfRandom();
//						if(b != boid && distanceBetween(boid.position, b.position) < spreadDistance && random.nextDouble() < diseaseSpreadChance) {
//							System.out.println("diseased");
//							b.hasDisease = true;
//						}
//					}
//				}
			});
			t.start();
		}
	}
	
	public void runSim() throws IOException {
		MyRandom.intializeRandom(1);
		if(fitnessType == "predator") {
			Random random = MyRandom.getInstanceOfRandom();
			predator = new Predator(random.nextInt(200), random.nextInt(200), boids);
		}
		
		this.frame.setVisible(true);
		for(int i = 0; i < timeSteps; i++) {
			if(simulationRunning) {
				
				System.out.println(curTime);
				for(Boid b : boids) {
					System.out.println("x: " + b.position[0] + ", y: " + b.position[1]);
				}
				System.out.println("");
				
				for(int k = 0; k < numBoids; k++) {
					popData[k][0][curTime] = boids.get(k).getPosition()[0];
					popData[k][1][curTime] = boids.get(k).getPosition()[1];
					popData[k][2][curTime] = boids.get(k).getFitness();
				}
				
				if(curTime == 0 && fitnessType.equals("disease")) {
					Random random = MyRandom.getInstanceOfRandom();
					boids.get(random.nextInt(numBoids)).getDisease(true);
				}
				
				update();
				
				if(fitnessType == "disease") {
					for(Boid b : boids) {
						if(b.hasDisease) spread(b);
					}
					
					for(Boid b : boids) {
						if(b.hasDisease) {
							b.lifespan--;
							if(b.lifespan == 0) {
								b.toRemove = true;
							}
						}
					}
				}

				for(int k = 0; k < numBoids; k++) {
					if(boids.get(k).toKill) {
						// penalty for death
						deadBoids.add(boids.get(k));
						boids.get(k).removeConnections();
						boids.remove(boids.get(k));
						numBoids--;
						if(numBoids == 1) {
							curTime = 0;
							calculateTotalFitness();
							return;
						}
					}
				}
				
				for(int k = 0; k < numBoids; k++) {
					fitnessData[k][curTime] = boids.get(k).getFitness();
				}
				curTime++;
			}
			if(curTime >= timeSteps) {
				curTime = 0;
				calculateTotalFitness();
				return;
			}
		}
	}
	
	private void spread(Boid boid) {
		for(Boid b : boids) {
			Random random = MyRandom.getInstanceOfRandom();
			if(b != boid && distanceBetween(boid.position, b.position) < spreadDistance && random.nextDouble() < diseaseSpreadChance) {
				System.out.println("diseased");
				b.hasDisease = true;
			}
		}
	}
	
	private void calculateTotalFitness() {
		double totalSum = 0;
		for(int i = 0; i < numBoids; i++) {
			double sum = 0;
			for(int j = 0; j < timeSteps; j++) {
				sum += fitnessData[i][j];
			}
//			sum /= timeSteps;
			totalSum += sum;
		}
		totalFitness = totalSum;
	}
	
	public int compareTo(InitializeBoids otherPopulation) {
		return (int) Math.ceil((totalFitness - otherPopulation.totalFitness));
	}
	
	/**
	 * setPositions: set each boid at a random position
	 */
//	public void setPositions() {	
//		for(int i = 0; i < numBoids; i++) {
//			Random random = MyRandom.getInstanceOfRandom();
//			boids.add(new Boid(random.nextInt(dimEnvironment * 2 + 1) - dimEnvironment, 
//					random.nextInt(dimEnvironment * 2 + 1) - dimEnvironment, random.nextDouble(), fitnessType, ""));
//		}
//	}
	
	/**
	 * setPositions: set each boid at a random, seeded position
	 * @param: seed
	 */
//	public void setPositions(int seed) {
//		Random random = new Random(seed);
//		for(int i = 0; i < numBoids; i++) {
//			boids.add(new Boid(random.nextInt(dimEnvironment * 2 + 1) - dimEnvironment, 
//					random.nextInt(dimEnvironment * 2 + 1) - dimEnvironment, random.nextDouble(), fitnessType, ""));
//		}
//	}
	
	/**
	 * setPositionsSpecified: set each boid at a position already specified
	 * @param: boidList
	 */
	public void setPositionsSpecified(ArrayList<Boid> boidList) {
		for(int i = 0; i < boidList.size(); i++) {
			boids.add(boidList.get(i));
		}
		
	}
	
	public void update() {
		for(Boid b : boids) {

			this.calculateFitness(b);
			
			if(fitnessType.equals("energy")) {
				if(distanceBetween(b.getPosition(), resourceLocation) > resourceDistance) {
					b.energy -= 0.01;
				} else {
					b.energy += 0.05;
				}
				if(b.energy < 0) {
					b.toRemove = true;
				}
			} else if(fitnessType.equals("predator")) {
				predatorVec[0] = (distanceBetween(predator.position, b.position) < 30) ? (-predator.position[0] + b.position[0]) / 3 : 0;
				predatorVec[1] = (distanceBetween(predator.position, b.position) < 30) ? (-predator.position[1] + b.position[1]) / 3 : 0;
				predator.update();
			}
		
			if(b.getFitness() > b.getBestFitness()) {
				b.setBestFitness(b.getFitness());
				b.setBestPosition(b.getPosition());
			}
			
			// Apply rules
			double[] v1 = rule1(b);
			double[] v2 = rule2(b);
			double[] v3 = rule3(b);
			double[] v4 = rule4(b);
			double[] v5 = rule5(b);
			
			double[][] ruleset = new double[][] { {v1[0], v1[1]}, {v2[0], v2[1]}, {v3[0], v3[1]}, {v4[0], v4[1]}, {v5[0], v5[1]} };
			b.setRules(ruleset);
			
			// Keep boids within lines
			boundPosition(b);
			
			// TODO: ADD BACK LONELINESS COEFFICIENT
			b.velocity[0] += (b.getSensitivity().get(0) * v1[0] + b.getSensitivity().get(1) * v2[0] + b.getSensitivity().get(2) * v3[0] 
					+ b.getSensitivity().get(3) * v4[0] + b.getSensitivity().get(4) * v5[0]) + predatorVec[0];
			b.velocity[1] += (b.getSensitivity().get(0) * v1[1] + b.getSensitivity().get(1) * v2[1] + b.getSensitivity().get(2) * v3[1] 
					+ b.getSensitivity().get(3) * v4[1] + b.getSensitivity().get(4) * v5[1]) + predatorVec[1];
			
			// Restrict oscillations
			if(b.velocity[0] > maxVelocity) {
				b.velocity[0] = maxVelocity;
			} else if(b.velocity[0] < -1 * maxVelocity) {
				b.velocity[0] = -1 * maxVelocity;
			}
			if(b.velocity[1] > maxVelocity) {
				b.velocity[1] = maxVelocity;
			} else if(b.velocity[0] < -1 * maxVelocity) {
				b.velocity[1] = -1 * maxVelocity;
			}
			
			if(b.hasDisease) {
				b.velocity[0] /= malaise;
				b.velocity[1] /= malaise;
			}
			
			b.position[0] += b.velocity[0];
			b.position[1] += b.velocity[1];
			
			if(b.position[0] > frame.getWidth() / 5 + 50 || 
				b.position[1] > frame.getHeight() / 5 + 50) {
				b.toRemove = true;
			}
			
			interaction(b);
			
			if(b.toRemove) {
				b.toKill = true;
			}
//			
			b.perceivedIsolation();
			
		}
	}
		
	// Closer the connections the less likely to interact
	// over expected -> less likely to connect
	
	// cohesion
	private double[] rule1(Boid boid) {
		double[] centerOfMass = new double[2];
		
		for(Boid b : boids) {
			if(b != boid) {
				centerOfMass[0] += b.position[0];
				centerOfMass[1] += b.position[1];
			}
		}
		
		centerOfMass[0] /= numBoids - 1;
		centerOfMass[1] /= numBoids - 1;
		
		centerOfMass[0] = (centerOfMass[0] - boid.position[0]) / 100;
		centerOfMass[1] = (centerOfMass[1] - boid.position[1]) / 100;
		
		return centerOfMass;
	}
	
	// Separation
	private double[] rule2(Boid boid) {
		double[] v = new double[2];
		
		for(Boid b : boids) {
			if(b != boid) {
				if(distanceBetween(boid.getPosition(), b.getPosition()) < boidDistance) {
					v[0] -= (b.position[0] - boid.position[0]) / 20;
					v[1] -= (b.position[1] - boid.position[1]) / 20;
				}
			}
		}
		return v;
	}
	
	// Alignment
	private double[] rule3(Boid boid) {
		double[] v = new double[2];
		
		for(Boid b : boids) {
			if(b != boid) {
				v[0] += b.velocity[0];
				v[1] += b.velocity[1];
			}
		}
		
		v[0] /= numBoids - 1;
		v[1] /= numBoids - 1;
		
		v[0] = (v[0] - boid.velocity[0]) / 8;
		v[1] = (v[1] - boid.velocity[1]) / 8;
			
		return v;
	}
	
	// self
	//for these, not sure how much I should divide by
	private double[] rule4(Boid boid) {
		double[] v = new double[2];
		v[0] += (boid.getBestPosition()[0] - boid.getPosition()[0]) / 5;
		v[1] += (boid.getBestPosition()[1] - boid.getPosition()[1]) / 5;
		
		return v;
	}
	
	// friends
	// AVERAGE TOTAL FRIENDS
	private double[] rule5(Boid boid) {
		if(boid.getConnectionList().size() == 0) {
			double[] empty = {0, 0};
			return empty;
		}
		
		double[] v = new double[2];
		double avgX = 0;
		double avgY = 0;
		
		for(Boid b : boid.getConnectionList()) {
			avgX += b.getPosition()[0];
			avgY += b.getPosition()[1];
		}
		
		avgX /= boid.getConnectionList().size();
		avgY /= boid.getConnectionList().size();
		
		// could potentially be negative at some point?
//		double strongestConnection = Double.NEGATIVE_INFINITY;
//		Boid bestFriend = null;
		
		// based on strength of connection; but still within some range
//		for(Map.Entry element : boid.getConnections().entrySet()) {
//			if((double) element.getValue() > strongestConnection) {
//				strongestConnection = (double) element.getValue();
//				bestFriend = (Boid) element.getKey();
//			}
//		}
		
		
		
		v[0] += ((avgX - boid.position[0]) / 100);
		v[1] += ((avgY - boid.position[1]) / 100);
		
		return v;
	}
	
	// groups
	private double[] rule6(Boid boid) {
		
		if(boid.getGroups().isEmpty()) {
			double[] empty = {0, 0};
			return empty;
		}
		
		double strongestConnection = Double.NEGATIVE_INFINITY;
		ArrayList<Boid> bestGroup = null;
		
		double[] centerOfMass = new double[2];
		
		for(Map.Entry element : boid.getGroups().entrySet()) {
			if((double) element.getValue() > strongestConnection) {
				strongestConnection = (double) element.getValue();
				bestGroup = (ArrayList<Boid>) element.getKey();
			}
		}
		
		for(Boid b : bestGroup) {
			centerOfMass[0] += b.position[0];
			centerOfMass[1] += b.position[1];
		}
		
		centerOfMass[0] /= bestGroup.size() - 1;
		centerOfMass[1] /= bestGroup.size() - 1;
		
		centerOfMass[0] = (centerOfMass[0] - boid.position[0]) / 5;
		centerOfMass[1] = (centerOfMass[1] - boid.position[1]) / 5;
		
		return centerOfMass;
		
	}
	
	public void calculateFitness(Boid b) {
		if(b.fitnessType == "one" || b.fitnessType == "predator") {
			b.setFitness(1 / Math.sqrt(Math.pow(b.position[0] - frame.getWidth() / 10, 2) + (Math.pow(b.position[1] - frame.getHeight() / 10, 2))));
		} else if(b.fitnessType == "two") {
			b.setFitness(1 / (Math.sqrt(Math.pow(b.position[0] - frame.getWidth() / 25, 2) 
									 + (Math.pow(b.position[1] - frame.getHeight() / 25, 2))) 
						    + Math.sqrt(Math.pow(b.position[0] - 4 * frame.getWidth() / 25, 2) 
							         + (Math.pow(b.position[1] - 4 * frame.getHeight() / 25, 2)))));
		} else if(b.fitnessType == "energy") {
			// to start, and with every 50 timesteps, randomize the position of the fitness
			if(curTime % 50 == 0) {
				for(Boid boid : boids) {
					boid.setBestFitness(Double.NEGATIVE_INFINITY);
				}
				Random random = MyRandom.getInstanceOfRandom();
				resourceLocation[0] = random.nextDouble() * 200;
				resourceLocation[1] = random.nextDouble() * 200;
			} 
			b.setFitness(1 / Math.sqrt(Math.pow(b.position[0] - resourceLocation[0], 2) 
					+ (Math.pow(b.position[1] - resourceLocation[1], 2))));
		} else if(b.fitnessType == "disease") {
			b.setFitness(1 / Math.sqrt(Math.pow(b.position[0] - frame.getWidth() / 10, 2) + (Math.pow(b.position[1] - frame.getHeight() / 10, 2))));
		}
	}
	
	/**
	 * Function: Make sure agents do not completely wander outside of bounds (large enough so that they roam around entire screen)
	 * 
	 * @param: boid
	**/
	
	private void boundPosition(Boid boid) {
		if(boid.position[0] < 0) {
			boid.velocity[0] = velBoundChg;
		} else if(boid.position[0] > this.frame.getWidth() / 5) {
			boid.velocity[0] = -velBoundChg;
		}
		
		if(boid.position[1] < 0) {
			boid.velocity[1] = velBoundChg;
		} else if(boid.position[1] > this.frame.getHeight() / 5) {
			boid.velocity[1] = -velBoundChg;
		}
	}
	
	private double distanceBetween(double[] pos1, double[] pos2) {
		return Math.sqrt(Math.pow(pos2[0] - pos1[0], 2) + 
				Math.pow(pos2[1] - pos1[1], 2));
	}
	
	private void interaction(Boid boid) {
		Random random =  MyRandom.getInstanceOfRandom();
		for(Boid b : boids) {
			if(!boid.equals(b)) {
				// if proper distance apart AND within interaction percentage AND connection doesn't already exist THEN form connection
				if(distanceBetween(boid.getPosition(), b.getPosition()) < interactionDistance && random.nextDouble() * 2 < boid.getInteractionChance() 
						&& !boid.getConnections().containsKey(b) && boid.getConnectionList().size() < maxConnections
						&& b.getConnectionList().size() < maxConnections && Math.abs(boid.mutualInterest - b.mutualInterest) < commonInterest ) {
					addConnection(boid, b, 1);
					boid.mutualInterest(b);
					b.mutualInterest(boid);
				} 
				// otherwise, strengthen connection
				else if(distanceBetween(boid.getPosition(), b.getPosition()) < interactionDistance && random.nextDouble() * 2 < boid.getInteractionChance() 
						&& boid.getConnections() != null && boid.getConnections().containsKey(b)) {
					strengthenConnection(boid, b);
					boid.mutualInterest(b);
					b.mutualInterest(boid);
				}
			}
		}
	}
	
	
	private void strengthenConnection(Boid boid, Boid b) {
		boid.strengthenConnection(b);
		b.strengthenConnection(boid);	
	}

	private void addConnection(Boid boid, Boid b, double strength) {
		boid.addConnection(b, strength);
		b.addConnection(boid, strength);
	}
	
	/**
	 * ensures: Looks to see if a boid has shared connections with its existing connections. If so, create a group out of those members
	 * 
	 * @param Boid
	 * 
	 */
	
	private void membership(Boid boid) {
		ArrayList<Boid> members = new ArrayList<>();
		for(Boid friend : boid.getConnectionList()) {
			for(Boid mutual : friend.getConnectionList()) {
				if(boid.getConnectionList().contains(mutual) && !members.contains(mutual)) {
					members.add(mutual);
				}
			}
			if(!members.contains(friend)) {
				members.add(friend);
			}
		}
		members.add(boid);
		if(members.size() >= minGroupSize) {
			boid.addGroup(members, 1);
			for(Boid b : members) {
				boid.mutualInterest(b);
			}
		}
	}
	
	public void writeToCSV(double[][][] arr, String filename) throws IOException {
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\gottlijd\\Desktop\\ABML\\src\\pos_csv\\" + filename));
		StringBuilder sb = new StringBuilder();
		
		sb.append("t,");
		for(int i = 0; i < arr.length; i++) {
			sb.append("x" + i + ",");
			sb.append("y" + i + ",");
			sb.append("fit" + i + ",");
		}
		sb.append("\n");
		
		
		for(int i = 0; i < arr[0][0].length; i++) {
			sb.append(String.valueOf(i) + ",");
			for(int j = 0; j < arr.length; j++) {
				for(int k = 0; k < arr[0].length; k++) {
					sb.append(String.valueOf(Math.round(arr[j][k][i] * 100.0) / 100.0) + ",");
				}
			}
			sb.append("\n");
		}
		pw.write(sb.toString());
		pw.close();
	}
	

	public void setSimulationRunning(boolean b) {
		this.simulationRunning = b;
	}

	public ArrayList<Boid> getBoids() {
		return boids;
	}

	public void setBoidSize(int size) {
		numBoids = size;
	}
	
	public String getViz() {
		return this.visualize;
	}
	
	public double[][][] getData() {
		return this.popData;
	}

	@Override
	public Iterator<Boid> iterator() {
		return boids.iterator();
	}
}