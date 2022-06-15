import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class Testing {
	
		@Test 
		public void R0() throws IOException{
			InitializeBoids initialize = new InitializeBoids("", 2, "one", 70, false);
			ArrayList<Boid> boids = new ArrayList<Boid>();
			double[] sensitivity1 = {0,0,0,0,0};
			Boid b1 = new Boid(50, 50, 0.0, sensitivity1, "one", "");
			Boid b2 = new Boid(80, 80, 0.0, sensitivity1, "one", "blue");
			boids.add(b1);
			boids.add(b2);
			initialize.setPositionsSpecified(boids);
			initialize.runSim();
			Assert.assertEquals(127, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(92, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(115, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(80, Math.round(boids.get(1).position[1]));
		}

		@Test
		public void R1() throws IOException{
			InitializeBoids initialize = new InitializeBoids("", 2, "one", 70, false);
			ArrayList<Boid> boids = new ArrayList<Boid>();
			double[] sensitivity1 = {1,0,0,0,0};
			Boid b1 = new Boid(50, 50, 0.0, sensitivity1, "one", "");
			Boid b2 = new Boid(80, 80, 0.0, sensitivity1, "one", "blue");
			boids.add(b1);
			boids.add(b2);
			initialize.setPositionsSpecified(boids);
			initialize.runSim();
			Assert.assertEquals(91, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(65, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(101, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(79, Math.round(boids.get(1).position[1]));
		}	
		
		@Test
		public void R2() throws IOException{
			InitializeBoids initialize = new InitializeBoids("", 2, "one", 40, false);
			ArrayList<Boid> boids = new ArrayList<Boid>();
			double[] sensitivity1 = {0,1,0,0,0};
			Boid b1 = new Boid(50, 50, 0.0, sensitivity1, "one", "");
			Boid b2 = new Boid(51, 51, 0.0, sensitivity1, "one", "blue");
			boids.add(b1);
			boids.add(b2);
			initialize.setPositionsSpecified(boids);
			initialize.runSim();
			Assert.assertEquals(182, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(156, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(189, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(158, Math.round(boids.get(1).position[1]));
		}		
		
		

		@Test
		public void R3() throws IOException{
			InitializeBoids initialize = new InitializeBoids("", 2, "one", 115, false);
			ArrayList<Boid> boids = new ArrayList<Boid>();
			double[] sensitivity1 = {0,0,1,0,0};
			Boid b1 = new Boid(50, 50, 0.0, sensitivity1, "one", "");
			Boid b2 = new Boid(80, 80, 0.0, sensitivity1, "one", "blue");
			boids.add(b1);
			boids.add(b2);
			initialize.setPositionsSpecified(boids);
			initialize.runSim();
			Assert.assertEquals(117, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(102, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(122, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(111, Math.round(boids.get(1).position[1]));
		}	
		
		@Test
		public void R4() throws IOException{
			InitializeBoids initialize = new InitializeBoids("", 2, "one", 37, false);
			ArrayList<Boid> boids = new ArrayList<Boid>();
			double[] sensitivity1 = {0,0,0,1,0};
			Boid b1 = new Boid(50, 50, 0.0, sensitivity1, "one", "");
			Boid b2 = new Boid(51, 51, 0.0, sensitivity1, "one", "blue");
			boids.add(b1);
			boids.add(b2);
			initialize.setPositionsSpecified(boids);
			initialize.runSim();
			Assert.assertEquals(95, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(95, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(96, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(96, Math.round(boids.get(1).position[1]));
		}	
		
		@Test
		public void R5a() throws IOException{
			InitializeBoids initialize = new InitializeBoids("", 2, "one", 70, false);
			ArrayList<Boid> boids = new ArrayList<Boid>();
			double[] sensitivity1 = {0,0,0,0,1};
			Boid b1 = new Boid(50, 50, 0.0, sensitivity1, "one", "");
			Boid b2 = new Boid(80, 80, 0.0, sensitivity1, "one", "blue");
			boids.add(b1);
			boids.add(b2);
			initialize.setPositionsSpecified(boids);
			initialize.runSim();
			Assert.assertEquals(102, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(74, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(104, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(81, Math.round(boids.get(1).position[1]));
		}	
		
		
		
		@Test
		public void R5b() throws IOException{
			InitializeBoids initialize = new InitializeBoids("", 3, "one", 15, false);
			ArrayList<Boid> boids = new ArrayList<Boid>();
			double[] sensitivity1 = {0,0,0,0,1};
			Boid b11 = new Boid(50, 50, 0.0, sensitivity1, "one", "");
			Boid b21 = new Boid(80, 80, 0.0, sensitivity1, "one", "blue");
			Boid b31 = new Boid(110, 110, 1.0, sensitivity1, "one", "blue");
			boids.add(b11);
			boids.add(b21);
			boids.add(b31);
			initialize.setPositionsSpecified(boids);
			initialize.runSim();
			Assert.assertEquals(145, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(145, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(137, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(137, Math.round(boids.get(1).position[1]));
			Assert.assertEquals(185, Math.round(boids.get(2).position[0]));
			Assert.assertEquals(178, Math.round(boids.get(2).position[1]));
			
			boids.clear();
			
			InitializeBoids initialize2 = new InitializeBoids("", 3, "one", 15, false);
			Boid b12 = new Boid(50, 50, 0.0, sensitivity1, "one", "");
			Boid b22 = new Boid(80, 80, 0.0, sensitivity1, "one", "blue");
			Boid b32 = new Boid(110, 110, 0.0, sensitivity1, "one", "blue");
			boids.add(b12);
			boids.add(b22);
			boids.add(b32);
			initialize2.setPositionsSpecified(boids);
			initialize2.runSim();
			Assert.assertEquals(159, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(159, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(151, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(151, Math.round(boids.get(1).position[1]));
			Assert.assertEquals(145, Math.round(boids.get(2).position[0]));
			Assert.assertEquals(145, Math.round(boids.get(2).position[1]));
		}	
		
		@Test
		public void E() throws IOException{
			InitializeBoids initialize = new InitializeBoids("", 2, "energy", 23, false);
			ArrayList<Boid> boids = new ArrayList<Boid>();
			double[] sensitivity1 = {1,1,1,1,1};
			Boid b11 = new Boid(50, 50, 0.0, sensitivity1, "energy", "");
			Boid b21 = new Boid(80, 80, 0.0, sensitivity1, "energy", "blue");
			boids.add(b11);
			boids.add(b21);
			initialize.setPositionsSpecified(boids);
			initialize.runSim();
			Assert.assertEquals(116, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(116, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(119, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(119, Math.round(boids.get(1).position[1]));
			
			boids.clear();
			
			InitializeBoids initialize2 = new InitializeBoids("", 2, "energy", 99, false);
			Boid b12 = new Boid(50, 50, 0.0, sensitivity1, "energy", "");
			Boid b22 = new Boid(80, 80, 0.0, sensitivity1, "energy", "blue");
			boids.add(b12);
			boids.add(b22);
			initialize2.setPositionsSpecified(boids);
			initialize2.runSim();
			Assert.assertEquals(35, Math.round(boids.get(0).position[0]));
			Assert.assertEquals(35, Math.round(boids.get(0).position[1]));
			Assert.assertEquals(32, Math.round(boids.get(1).position[0]));
			Assert.assertEquals(32, Math.round(boids.get(1).position[1]));
			
			boids.clear();
			
			InitializeBoids initialize3 = new InitializeBoids("", 2, "energy", 101, false);
			Boid b13 = new Boid(50, 50, 0.0, sensitivity1, "energy", "");
			Boid b23 = new Boid(80, 80, 0.0, sensitivity1, "energy", "blue");
			boids.add(b13);
			boids.add(b23);
			initialize3.setPositionsSpecified(boids);
			initialize3.runSim();
			Assert.assertEquals(true, boids.get(0).toKill);
			Assert.assertEquals(true, boids.get(1).toKill);
		}	
		
		@Test
		public void D() throws IOException{
			InitializeBoids initialize = new InitializeBoids("", 3, "disease", 33, false);
			ArrayList<Boid> boids = new ArrayList<Boid>();
			double[] sensitivity1 = {1,1,1,0,1};
			Boid b11 = new Boid(5, 5, 0.0, sensitivity1, "disease", "");
			Boid b21 = new Boid(80, 80, 0.0, sensitivity1, "disease", "blue");
			Boid b31 = new Boid(110, 110, 0.0, sensitivity1, "disease", "blue");
			boids.add(b11);
			boids.add(b21);
			boids.add(b31);
			initialize.setPositionsSpecified(boids);
			initialize.runSim();
			Assert.assertEquals(true, boids.get(0).hasDisease);
			Assert.assertEquals(false, b21.hasDisease);
			Assert.assertEquals(false, b31.hasDisease);
			
			boids.clear();
			
			InitializeBoids initialize2 = new InitializeBoids("", 3, "disease", 80, false);
			Boid b12 = new Boid(5, 5, 0.0, sensitivity1, "disease", "");
			Boid b22 = new Boid(80, 80, 0.0, sensitivity1, "disease", "blue");
			Boid b32 = new Boid(110, 110, 0.0, sensitivity1, "disease", "blue");
			boids.add(b12);
			boids.add(b22);
			boids.add(b32);
			initialize2.setPositionsSpecified(boids);
			initialize2.runSim();
			Assert.assertEquals(true, b12.hasDisease);
			Assert.assertEquals(true, b22.hasDisease);
			Assert.assertEquals(true, b32.hasDisease);
		}
		
		
}
