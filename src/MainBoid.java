import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainBoid {

	public static void main(String[] args) throws IOException {

		ArrayList<Boid> boids = new ArrayList<Boid>();

		MyRandom.intializeRandom(0);
		Random random = MyRandom.getInstanceOfRandom();

//		double[] defaultBehavior1 = {random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextDouble()};
		double[] defaultBehavior = { 1, 1, 1, 1, 1, 1, 1 };

		double[] sensitivity0 = { 1, 1, 1, 1, 1, 1 };
		double[] sensitivityX = { -1, -1, -1, -1, -1, -1 };

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

//		for(int i = 0; i < 10; i++) {
//			hills.add(new Hill(random.nextInt(400), random.nextInt(400), random.nextInt(25 - 5) + 5, 
//							   random.nextInt(100 - 10) + 10));
//		}
//		
//		for(int i = 0; i < 50; i++) {
//			initialPositions.add(new int[] {random.nextInt(400), random.nextInt(400)});
//			double[] defaultBehavior1 = {random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextDouble()};
//			Boid b = new Boid(initialPositions.get(i)[0], initialPositions.get(i)[1], 1, defaultBehavior1, sensitivity0, "", "");
//			boids.add(b);
//		}

		hills.add(new Hill(200, 100, 15, 500));
//		hills.add(new Hill(150, 150, 10, 100));

		initialPositions.add(new int[] { 10, 100 });
		initialPositions.add(new int[] { 145, 145 });

//		initialPositions.add(new int[] {50, 50});
//		initialPositions.add(new int[] {60, 60});
//		initialPositions.add(new int[] {55, 55});
//		initialPositions.add(new int[] {45, 55});
//		initialPositions.add(new int[] {60, 45});
//		initialPositions.add(new int[] {50, 60});

		double[] sensitivity1 = { 0.23, 0.43, 0.35, 0.16, -0.57, 0.73 };
		double[] sensitivity2 = { 0.68, 0.14, 0.67, -0.86, -0.09, 0.01 };
		double[] sensitivity3 = { 0.25, 0.43, -0.99, 0.9, 0.18, 0.4 };

		// x | y | interest | expected | % | sensitivity | fitness

		Boid b1 = new Boid(initialPositions.get(0)[0], initialPositions.get(0)[1], 0.7, defaultBehavior, sensitivity0,
				"", "");
		Boid b2 = new Boid(initialPositions.get(1)[0], initialPositions.get(1)[1], 0.42, defaultBehavior, sensitivityX,
				"", "blue");
//		Boid b3 = new Boid(initialPositions.get(2)[0], initialPositions.get(2)[1], 1, defaultBehavior, sensitivityX, "", "red");
//		Boid b4 = new Boid(initialPositions.get(3)[0], initialPositions.get(3)[1], 1, defaultBehavior, sensitivityX, "", "pink");
//		Boid b5 = new Boid(initialPositions.get(4)[0], initialPositions.get(4)[1], 1, defaultBehavior, sensitivityX, "", "orange");
//		Boid b6 = new Boid(initialPositions.get(5)[0], initialPositions.get(5)[1], 1, defaultBehavior, sensitivityX, "", "green");
//		Boid b7 = new Boid(initialPositions.get(6)[0], initialPositions.get(6)[1], 1, defaultBehavior1, sensitivity0, "", "pink");
//		Boid b8 = new Boid(initialPositions.get(7)[0], initialPositions.get(7)[1], 1, defaultBehavior1, sensitivity0, "", "orange");
//		Boid b9 = new Boid(initialPositions.get(8)[0], initialPositions.get(8)[1], 1, defaultBehavior1, sensitivity0, "", "green");

		boids.add(b1);
//		boids.add(b2);
//		boids.add(b3);
//		boids.add(b4);
//		boids.add(b5);
//		boids.add(b6);
//		boids.add(b7);
//		boids.add(b8);
//		boids.add(b9);
//		boids.add(b4);
//		boids.add(b5);
//		boids.add(b6);

		InitializeBoids initialize = new InitializeBoids("viz", 2, "", 1000, true, hills, initialPositions, "");
		initialize.setPositionsSpecified(boids);
//		initialize.runSim();
//		System.out.println(initialize.bigSum);

//		boolean[] exampleGenome = {false,false,false,false,false,false,true};
//		new BoidPopulation(5, exampleGenome, "disease", 2, 100, false, hills, initialPositions, "name");
	}
}