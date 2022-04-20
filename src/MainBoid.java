import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainBoid {
	
	public static void main(String[] args) throws IOException {
		InitializeBoids initialize = new InitializeBoids("viz", 2, "one", 1000);
		ArrayList<Boid> boids = new ArrayList<Boid>();
	
		double[] sensitivity1 = {1,1,1,1,1};
		double[] sensitivity2 = {1,1,1,1,1};

		//     x | y | interest | expected | % | sensitivity | fitness
		
		Boid b1 = new Boid(149, 100, 0.0, 1.0, 1.0, sensitivity1, "one", "");
		Boid b2 = new Boid(180, 100, 0.0, 1.0, 1.0, sensitivity2, "one", "blue");
		Boid b3 = new Boid(110, 110, 0.0, 1.0, 1.0, sensitivity1, "disease", "red");
		Boid b4 = new Boid(100, 100, 0.0, 1.0, 1.0, sensitivity2, "one", "pink");
		Boid b5 = new Boid(105, 105, 0.0, 1.0, 1.0, sensitivity1, "one", "orange");
		Boid b6 = new Boid(97, 97, 0.0, 1.0, 1.0, sensitivity2, "one", "green");
		
		boids.add(b1);
		boids.add(b2);
//		boids.add(b3);
//		boids.add(b4);
//		boids.add(b5);
//		boids.add(b6);
//		boids.add(b1);
//		boids.add(b2);
//		boids.add(b3);
//		boids.add(b4);
//		boids.add(b5);
//		boids.add(b6);
		
		initialize.setPositionsSpecified(boids);
		
		
//		boolean[] exampleGenome = {false,false,false,false,false,false,true,true};
//		BoidPopulation bp = new BoidPopulation(5, exampleGenome, "one", 2, 100);
	}
}