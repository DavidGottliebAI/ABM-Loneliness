import java.util.ArrayList;
import java.util.Random;

public class Fitness {
	
	String fitnessType;
	int dimEnvironmentX;
	int dimEnvironmentY;
	
	public Fitness(String fitnessType, int dimEnvironmentX, int dimEnvironmentY) {
		this.fitnessType = fitnessType;
		this.dimEnvironmentX = dimEnvironmentX;
		this.dimEnvironmentY = dimEnvironmentY;
	}
	
	public void calculateFitness(Boid b) {
		 if(b.fitnessType == "two") {
			b.setFitness(1 / (Math.sqrt(Math.pow(b.position[0] - dimEnvironmentX / 5, 2) 
									 + (Math.pow(b.position[1] - dimEnvironmentY / 5, 2))) 
						    + Math.sqrt(Math.pow(b.position[0] - 4 * dimEnvironmentX / 5, 2) 
							         + (Math.pow(b.position[1] - 4 * dimEnvironmentY / 5, 2)))));
		} else if(b.fitnessType == "disease") {
			b.setFitness(1 / Math.sqrt(Math.pow(b.position[0] - dimEnvironmentX / 2, 2) + (Math.pow(b.position[1] - dimEnvironmentX / 2, 2))));
		} else {
			
			ArrayList<Hill> hills = new ArrayList<Hill>();
			
			Hill hill1 = new Hill(dimEnvironmentX/2, dimEnvironmentY/2, 50, 2);
		
			hills.add(hill1);
			
			double totalFitness = 0;
			for(Hill h : hills) {
				if(distanceBetween(b.getPosition(), h.getPosition()) < h.getRadius()) {
					totalFitness +=
						1 / Math.sqrt(Math.pow(b.position[0] - h.getPosition()[0] / 2, h.getElevation()) 
						+ (Math.pow(b.position[1] - h.getPosition()[1] / 2, h.getElevation())));
				}
			}
			
			b.setFitness(totalFitness);
			
//			b.setFitness(1 / Math.sqrt(Math.pow(b.position[0] - dimEnvironmentX / 2, 2) + (Math.pow(b.position[1] - dimEnvironmentY / 2, 2))));
//			double[] hill1 = {dimEnvironmentX/4, dimEnvironmentY/2};
//			if(distanceBetween(b.position, hill1) < 10) {
//				b.setFitness(60);
//			} else if(distanceBetween(b.position, hill1) < 20) {
//				b.setFitness(50);
//			} else if(distanceBetween(b.position, hill1) < 30) {
//				b.setFitness(40);
//			} 
//			
//			double[] hill2 = {3*dimEnvironmentX/4, dimEnvironmentY/2};
//			if(distanceBetween(b.position, hill2) < 10) {
//				b.setFitness(30);
//			} else if(distanceBetween(b.position, hill2) < 20) {
//				b.setFitness(20);
//			} else if(distanceBetween(b.position, hill2) < 30) {
//				b.setFitness(10);
//			} 
		}
		
	}
	
	public void calculateFitness(Boid b, ArrayList<Boid> boids, int time, double[] resourceLocation) {
		// to start, and with every 50 timesteps, randomize the position of the fitness
		if(time % 50 == 0) {
			for(Boid boid : boids) {
				boid.setBestFitness(Double.NEGATIVE_INFINITY);
			}
			Random random = MyRandom.getInstanceOfRandom();
			resourceLocation[0] = random.nextDouble() * 200;
			resourceLocation[1] = random.nextDouble() * 200;
		} 
		b.setFitness(1 / Math.sqrt(Math.pow(b.position[0] - resourceLocation[0], 2) 
				+ (Math.pow(b.position[1] - resourceLocation[1], 2))));
	}
	
	private double distanceBetween(double[] pos1, double[] pos2) {
		return Math.sqrt(Math.pow(pos2[0] - pos1[0], 2) 
				+ Math.pow(pos2[1] - pos1[1], 2));
	}
}
