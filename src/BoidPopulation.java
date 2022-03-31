import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BoidPopulation {
	
	private int mutationRate = 1;
	private int generations = 500;
	private int populationSize;
	public int sizeMultiplier = 5;
	public int numLoops = 0;
	public int valueBoundsLower;
	public int valueBoundsUpper;
	public boolean evolutionRunning = true;
	public int curTime = 0;
	public ArrayList<InitializeBoids> allBoids = new ArrayList<InitializeBoids>();
	public double[][][][] genData;
	public double[][] boxData;
	private int metaPopulationSize;
	private String fitness;
	
	public BoidPopulation(int seed, boolean[] genome, String fitness, int populationSize, int metaPopulationSize) throws IOException {
		this.fitness = fitness;
		this.populationSize = populationSize;
		this.metaPopulationSize = metaPopulationSize;
		MyRandom.intializeRandom(seed);
		Random random = MyRandom.getInstanceOfRandom();
		for(int i = 0; i < metaPopulationSize ; i++) {
			InitializeBoids boidsInitial = new InitializeBoids("", populationSize, fitness, 1000);
			boidsInitial.setBoidSize(populationSize);
			ArrayList<Boid> boidsPopulation = new ArrayList<Boid>();
			for (int j = 0; j < boidsInitial.numBoids; j++) {
				double[] sensitivity = {0, 0, 0, 0, 0};
				Boid b = new Boid(random.nextInt(100), random.nextInt(100), 1, 0, 1, sensitivity, "one", "black");
				if(genome[0]) b.setInterest(random.nextDouble());
				if(genome[1]) b.setExpected(random.nextDouble());
				if(genome[2]) b.setInteractChance(random.nextDouble());
				if(genome[3]) b.setSensitivity(0, random.nextDouble() * 2 - 1);
				if(genome[4]) b.setSensitivity(1, random.nextDouble() * 2 - 1);
				if(genome[5]) b.setSensitivity(2, random.nextDouble() * 2 - 1);
				if(genome[6]) b.setSensitivity(3, random.nextDouble() * 2 - 1);
				if(genome[7]) b.setSensitivity(4, random.nextDouble() * 2 - 1);
				boidsPopulation.add(b);
			}
			boidsInitial.setPositionsSpecified(boidsPopulation);
			allBoids.add(boidsInitial);
		}
		
		this.genData = new double[3][populationSize][genome.length-1][generations];
		this.boxData = new double[genome.length-2][metaPopulationSize * populationSize];
		
		evolutionLoop(genome, seed);
//		for(int i = 0; i < allBoids.size(); i++) {
//			allBoids.get(i).writeToCSV(allBoids.get(i).popData, "pop_data.csv (" + i + ")");
//		}
//		allBoids.get(0).writeToCSV(allBoids.get(0).popData, "myfile.csv");
		writeToCSV(genData, "gen_data.csv");
		writeToBoxCSV(boxData, "box_data.csv");
		
	}

	public void evolutionLoop(boolean[] genome, int seed) throws IOException {
		for(int k = 0; k < generations; k++) {
			
			// Collecting the worst data
			for(int i = 0; i < populationSize; i++) {
				genData[0][i][0][k] = allBoids.get(0).boids.get(i).getSensitivity().get(0);
				genData[0][i][1][k] = allBoids.get(0).boids.get(i).getSensitivity().get(1);
				genData[0][i][2][k] = allBoids.get(0).boids.get(i).getSensitivity().get(2);
				genData[0][i][3][k] = allBoids.get(0).boids.get(i).getSensitivity().get(3);
				genData[0][i][4][k] = allBoids.get(0).boids.get(i).getSensitivity().get(4);
				genData[0][i][5][k] = allBoids.get(0).boids.get(i).getExpected();
				genData[0][i][6][k] = allBoids.get(0).boids.get(i).getFitness();
			}
			
			// Collecting average and distributional data
			double[] x = new double[genome.length-1];
			int sum = 0;
			for(int j = 0; j < metaPopulationSize; j++) {
				for(int i = 0; i < populationSize; i++) {
					x[0] += boxData[0][sum] = allBoids.get(j).boids.get(i).getSensitivity().get(0);
					x[1] += boxData[1][sum] = allBoids.get(j).boids.get(i).getSensitivity().get(1);
					x[2] += boxData[2][sum] = allBoids.get(j).boids.get(i).getSensitivity().get(2);
					x[3] += boxData[3][sum] = allBoids.get(j).boids.get(i).getSensitivity().get(3);
					x[4] += boxData[4][sum] = allBoids.get(j).boids.get(i).getSensitivity().get(4);
					x[5] += boxData[5][sum] = allBoids.get(j).boids.get(i).getExpected();
//								x[6] += boxData[6][sum] = allBoids.get(j).boids.get(i).getFitness();
					sum++;
				}
			}
			for(int i = 0; i < populationSize; i++) {
				genData[1][i][0][k] = x[0] / (metaPopulationSize * populationSize);
				genData[1][i][1][k] = x[1] / (metaPopulationSize * populationSize);
				genData[1][i][2][k] = x[2] / (metaPopulationSize * populationSize);
				genData[1][i][3][k] = x[3] / (metaPopulationSize * populationSize);
				genData[1][i][4][k] = x[4] / (metaPopulationSize * populationSize);
				genData[1][i][5][k] = x[5] / (metaPopulationSize * populationSize);
			}
			
			// Collecting best data
			for(int i = 0; i < populationSize; i++) {
				genData[2][i][0][k] = allBoids.get(allBoids.size()-1).boids.get(i).getSensitivity().get(0);
				genData[2][i][1][k] = allBoids.get(allBoids.size()-1).boids.get(i).getSensitivity().get(1);
				genData[2][i][2][k] = allBoids.get(allBoids.size()-1).boids.get(i).getSensitivity().get(2);
				genData[2][i][3][k] = allBoids.get(allBoids.size()-1).boids.get(i).getSensitivity().get(3);
				genData[2][i][4][k] = allBoids.get(allBoids.size()-1).boids.get(i).getSensitivity().get(4);
				genData[2][i][5][k] = allBoids.get(allBoids.size()-1).boids.get(i).getExpected();
				genData[2][i][5][k] = allBoids.get(allBoids.size()-1).boids.get(i).getFitness();
			}
			
			for(int j = 0; j < metaPopulationSize; j++) {
				allBoids.get(j).runSim();
			}
			
			if(k == generations - 1) {
				for(int i = 0; i < allBoids.size(); i++) {
					allBoids.get(i).writeToCSV(allBoids.get(i).popData, "pos_graph" + i + ".csv");
				}
			}
			
			Collections.sort(allBoids);
			
			select();
			
//			if(fitness.equals("predator") || fitness.equals("disease") || fitness.equals("energy")) {
			repopulateDead();
//			}
			
			repopulate(seed);
			
			mutate(genome, seed);
		}
	}

	private void repopulateDead() {
		for(InitializeBoids boidPopulation : allBoids) {
			if(!boidPopulation.deadBoids.isEmpty()) {
				boidPopulation.boids.addAll(boidPopulation.deadBoids);
			}
		}
	}

	public InitializeBoids deepCopy(InitializeBoids population, int seed) {
		InitializeBoids newPopulation = new InitializeBoids(population.boids, seed);
		return newPopulation;
	}
	
	private void repopulate(int seed) {
		ArrayList<InitializeBoids> repopulatedBoids = new ArrayList<InitializeBoids>();
		
		int index = allBoids.size() - 1;
		while (repopulatedBoids.size() < metaPopulationSize) {
			if (index < 0) index = allBoids.size() - 1;
			InitializeBoids newPopulation = deepCopy(allBoids.get(index), seed);
			repopulatedBoids.add(newPopulation);
			index--;
		}
		
		allBoids.clear();
		for(int i = 0; i < repopulatedBoids.size(); i++) {
			allBoids.add(repopulatedBoids.get(i));
		}
	}

	private void select() {
		for (int i = 0; i < metaPopulationSize / 2; i++) {
			allBoids.remove(0);
		}
	}
	
	private void mutate(boolean[] genome, int seed) {
		double mutatePercentage = Math.ceil(mutationRate / 100.0 * 100.0);
		Random random = MyRandom.getInstanceOfRandom();
		for(InitializeBoids population : allBoids) {
			for(Boid b : population.boids) {
				if(genome[0] && random.nextInt(100) + 1 <= mutatePercentage) b.setInterest(random.nextDouble());
				if(genome[1] && random.nextInt(100) + 1 <= mutatePercentage) b.setExpected(random.nextDouble());
				if(genome[2] && random.nextInt(100) + 1 <= mutatePercentage) b.setInteractChance(random.nextDouble());
				if(genome[3] && random.nextInt(100) + 1 <= mutatePercentage) b.setSensitivity(0, random.nextDouble() * 2 - 1);
				if(genome[4] && random.nextInt(100) + 1 <= mutatePercentage) b.setSensitivity(1, random.nextDouble() * 2 - 1);
				if(genome[5] && random.nextInt(100) + 1 <= mutatePercentage) b.setSensitivity(2, random.nextDouble() * 2 - 1);
				if(genome[6] && random.nextInt(100) + 1 <= mutatePercentage) b.setSensitivity(3, random.nextDouble() * 2 - 1);
				if(genome[7] && random.nextInt(100) + 1 <= mutatePercentage) b.setSensitivity(4, random.nextDouble() * 2 - 1);
			}
		}
	}

	public void writeToCSV(double[][][][] arr, String filename) throws IOException {
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\gottlijd\\Desktop\\ABML\\src\\gen_csv\\" + filename));
//		File file = new File(filename);
		StringBuilder sb = new StringBuilder();
		
		sb.append("gen,");
		
		for(int i = 1; i < populationSize + 1; i++) {
			sb.append("s1Worst" + i + ",");
			sb.append("s2Worst" + i + ",");
			sb.append("s3Worst" + i + ",");
			sb.append("s4Worst" + i + ",");
			sb.append("s5Worst" + i + ",");
			sb.append("expWorst" + i + ",");
			sb.append("fitWorst" + i + ",");
		}
		
		for(int i = 1; i < populationSize + 1; i++) {
			sb.append("s1Average" + i + ",");
			sb.append("s2Average" + i + ",");
			sb.append("s3Average" + i + ",");
			sb.append("s4Average" + i + ",");
			sb.append("s5Average" + i + ",");
			sb.append("expAverage" + i + ",");
			sb.append("fitAverage" + i + ",");
		}
		
		for(int i = 1; i < populationSize + 1; i++) {
			sb.append("s1Best" + i + ",");
			sb.append("s2Best" + i + ",");
			sb.append("s3Best" + i + ",");
			sb.append("s4Best" + i + ",");
			sb.append("s5Best" + i + ",");
			sb.append("expBest" + i + ",");
			sb.append("fitBest" + i + ",");
		}
		sb.append("\n");
		
		
		for(int i = 0; i < arr[0][0][0].length; i++) {
			sb.append(String.valueOf(i) + ",");
			for(int m = 0; m < arr.length; m++) {
				for(int j = 0; j < arr[0].length; j++) {
					for(int k = 0; k < arr[0][0].length; k++) {
						sb.append(String.valueOf(Math.round(arr[m][j][k][i] * 100.0) / 100.0) + ",");
					}
				}
			
			}
			sb.append("\n");
		}
		
		pw.write(sb.toString());
		pw.close();
	}
	
	public void writeToBoxCSV(double[][] arr, String filename) throws IOException {
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\gottlijd\\Desktop\\ABML\\src\\box_csv\\" + filename));
		StringBuilder sb = new StringBuilder();
	
		sb.append("s1,");
		sb.append("s2,");
		sb.append("s3,");
		sb.append("s4,");
		sb.append("s5,");
		sb.append("exp,");
		sb.append("\n");
		
		for(int i = 0; i < arr[0].length; i++) {
			for(int j = 0; j < arr.length; j++) {
					sb.append(String.valueOf(Math.round(arr[j][i] * 100.0) / 100.0) + ",");
			}
			sb.append("\n");
		}
		
		pw.write(sb.toString());
		pw.close();
	}
	
}
