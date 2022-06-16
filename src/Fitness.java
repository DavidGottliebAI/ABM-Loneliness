import java.util.ArrayList;
import java.util.Random;

public class Fitness {

	private static int survivalFitness = 100;

	String fitnessType;
	int dimEnvironmentX;
	int dimEnvironmentY;

	public Fitness(String fitnessType, int dimEnvironmentX, int dimEnvironmentY) {
		this.fitnessType = fitnessType;
		this.dimEnvironmentX = dimEnvironmentX;
		this.dimEnvironmentY = dimEnvironmentY;
	}

	public void calculateFitness(Boid b, ArrayList<Hill> hills) {

		double resourceFitness = 0;
		if (!hills.isEmpty()) {
			for (Hill h : hills) {

				if (distanceBetween(b.getPosition(), h.getPosition()) < h.getRadius()) {
					resourceFitness += h.getElevation();
				} // else {
//					double x1 = 0;
//					double x2 = 0;
//					double y1 = 0;
//					double y2 = 0;
//					double z1 = Math.abs(dimEnvironmentX - b.getPosition()[0]);
//					double z2 = Math.abs(dimEnvironmentY - b.getPosition()[1]);
//					if (b.getPosition()[0] > h.getPosition()[0]) {
//						if (b.getPosition()[1] > h.getPosition()[1]) {
//							x1 = dimEnvironmentX - b.getPosition()[0];
//							x2 = dimEnvironmentY - b.getPosition()[1];
//							y1 = b.getPosition()[0];
//							y2 = b.getPosition()[1];
//						} else {
//							x1 = dimEnvironmentX - b.getPosition()[0];
//							x2 = b.getPosition()[1];
//							y1 = b.getPosition()[0];
//							y2 = dimEnvironmentY - b.getPosition()[1];
//						}
//					} else if (b.getPosition()[0] < h.getPosition()[0]) {
//						if (b.getPosition()[1] > h.getPosition()[1]) {
//							x1 = b.getPosition()[0];
//							x2 = dimEnvironmentY - b.getPosition()[1];
//							y1 = dimEnvironmentX - b.getPosition()[0];
//							y2 = b.getPosition()[1];
//						} else {
//							x1 = b.getPosition()[0];
//							x2 = b.getPosition()[1];
//							y1 = dimEnvironmentX - b.getPosition()[0];
//							y2 = dimEnvironmentY - b.getPosition()[1];
//						}
//					} else {
//						b.setFitness(resourceFitness);
//						return;
//					}
//
//					double dist1 = Math.sqrt(Math.pow(x1 + y1, 2) + Math.pow(z1, 2));
//					double dist2 = Math.sqrt(Math.pow(x2 + y2, 2) + Math.pow(z2, 2));
//
//					if (Math.min(dist1, dist2) < h.getRadius()) {
//						resourceFitness += h.getElevation();
//						System.out.println("Torus");
//					}
//				}
			}
		}

		b.setFitness(resourceFitness);

	}

	public void calculateFitness(Boid b, ArrayList<Boid> boids, int time, double[] resourceLocation) {
		// to start, and with every 50 timesteps, randomize the position of the fitness
		if (time % 50 == 0) {
			for (Boid boid : boids) {
				boid.setBestFitness(Double.NEGATIVE_INFINITY);
			}
			Random random = MyRandom.getInstanceOfRandom();
			resourceLocation[0] = random.nextDouble() * 200;
			resourceLocation[1] = random.nextDouble() * 200;
		}
		b.setFitness(1 / Math.sqrt(
				Math.pow(b.position[0] - resourceLocation[0], 2) + (Math.pow(b.position[1] - resourceLocation[1], 2))));
	}

	private double distanceBetween(double[] pos1, double[] pos2) {
		return Math.sqrt(Math.pow(pos2[0] - pos1[0], 2) + Math.pow(pos2[1] - pos1[1], 2));
	}
}
