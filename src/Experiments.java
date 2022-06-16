import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Experiments {

	private static int posMax = 400;
	private static int radiusHigh = 25;
	private static int radiusLow = 5;
	private static int elevationHigh = 100;
	private static int elevationLow = 10;

	public static void main(String[] args) throws IOException {
		experiment5b();
	}

	private static void experimentDistribution() throws IOException {
		String experimentName = "experimentDist";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(100, 100, 30, 100));

		initialPositions.add(new int[] { 70, 70 });
		initialPositions.add(new int[] { 110, 110 });
		initialPositions.add(new int[] { 130, 130 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "predator", 3, 100, false, false, hills, initialPositions, experimentName);
	}

	private static void experiment1() throws IOException {
		String experimentName = "experiment1";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(100, 100, 20, 100));

		initialPositions.add(new int[] { 70, 70 });
		initialPositions.add(new int[] { 110, 110 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 2, 100, false, false, hills, initialPositions, experimentName);
	}

	// Two hills, two agents
	private static void experiment2a() throws IOException {
		String experimentName = "experiment2a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 10, 500));
		hills.add(new Hill(150, 150, 10, 100));

		initialPositions.add(new int[] { 45, 45 });
		initialPositions.add(new int[] { 145, 145 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 2, 100, false, true, hills, initialPositions, experimentName);
	}

	private static void experiment2b() throws IOException {
		String experimentName = "experiment2b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 10, 500));
		hills.add(new Hill(150, 150, 10, 100));

		initialPositions.add(new int[] { 45, 45 });
		initialPositions.add(new int[] { 145, 145 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 2, 100, true, true, hills, initialPositions, experimentName);
	}

	private static void experiment3a() throws IOException {
		String experimentName = "experiment3a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(100, 100, 50, 100));

		initialPositions.add(new int[] { 70, 70 });
		initialPositions.add(new int[] { 130, 110 });
		initialPositions.add(new int[] { 90, 130 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "predator", 3, 100, false, false, hills, initialPositions, experimentName);
	}

	private static void experiment3b() throws IOException {
		String experimentName = "experiment3b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(100, 100, 50, 100));

		initialPositions.add(new int[] { 70, 70 });
		initialPositions.add(new int[] { 130, 110 });
		initialPositions.add(new int[] { 90, 130 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(6, exampleGenome, "predator", 3, 100, true, false, hills, initialPositions, experimentName);
	}

	private static void experiment4a() throws IOException {
		String experimentName = "experiment4a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 10, 10));
		hills.add(new Hill(150, 150, 10, 100));

		initialPositions.add(new int[] { 45, 45 });
		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 55, 55 });
		initialPositions.add(new int[] { 55, 45 });
		initialPositions.add(new int[] { 45, 55 });
		initialPositions.add(new int[] { 145, 145 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 6, 100, false, true, hills, initialPositions, experimentName);
	}

	private static void experiment4b() throws IOException {
		String experimentName = "experiment4b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 10, 10));
		hills.add(new Hill(150, 150, 10, 100));

		initialPositions.add(new int[] { 45, 45 });
		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 55, 55 });
		initialPositions.add(new int[] { 55, 45 });
		initialPositions.add(new int[] { 45, 55 });
		initialPositions.add(new int[] { 145, 145 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 6, 100, true, true, hills, initialPositions, experimentName);
	}

	private static void experiment5a() throws IOException {
		String experimentName = "experiment5a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 15, 10));
		hills.add(new Hill(100, 50, 15, 30));
		hills.add(new Hill(150, 50, 15, 6));
		hills.add(new Hill(30, 100, 15, 70));
		hills.add(new Hill(80, 100, 15, 50));
		hills.add(new Hill(160, 100, 15, 10));
		hills.add(new Hill(50, 150, 15, 20));
		hills.add(new Hill(100, 150, 15, 40));
		hills.add(new Hill(150, 150, 15, 60));

		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 100, 50 });
		initialPositions.add(new int[] { 150, 50 });
		initialPositions.add(new int[] { 30, 100 });
		initialPositions.add(new int[] { 80, 100 });
		initialPositions.add(new int[] { 160, 100 });
		initialPositions.add(new int[] { 50, 150 });
		initialPositions.add(new int[] { 100, 150 });
		initialPositions.add(new int[] { 150, 150 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 9, 100, false, true, hills, initialPositions, experimentName);
	}

	private static void experiment5b() throws IOException {
		String experimentName = "experiment5b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 15, 10));
		hills.add(new Hill(100, 50, 15, 30));
		hills.add(new Hill(150, 50, 15, 6));
		hills.add(new Hill(30, 100, 15, 70));
		hills.add(new Hill(80, 100, 15, 50));
		hills.add(new Hill(160, 100, 15, 10));
		hills.add(new Hill(50, 150, 15, 20));
		hills.add(new Hill(100, 150, 15, 40));
		hills.add(new Hill(150, 150, 15, 60));

		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 100, 50 });
		initialPositions.add(new int[] { 150, 50 });
		initialPositions.add(new int[] { 30, 100 });
		initialPositions.add(new int[] { 80, 100 });
		initialPositions.add(new int[] { 160, 100 });
		initialPositions.add(new int[] { 50, 150 });
		initialPositions.add(new int[] { 100, 150 });
		initialPositions.add(new int[] { 150, 150 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 9, 100, true, true, hills, initialPositions, experimentName);
	}

	private static void experiment6a() throws IOException {
		String experimentName = "experiment6a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(100, 100, 20, 10));

		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 60, 60 });
		initialPositions.add(new int[] { 55, 55 });
		initialPositions.add(new int[] { 45, 55 });
		initialPositions.add(new int[] { 60, 45 });

		initialPositions.add(new int[] { 130, 130 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "predator", 6, 100, false, false, hills, initialPositions, experimentName);
	}

	private static void experiment6b() throws IOException {
		String experimentName = "experiment6b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(100, 100, 20, 10));

		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 60, 60 });
		initialPositions.add(new int[] { 55, 55 });
		initialPositions.add(new int[] { 45, 55 });
		initialPositions.add(new int[] { 60, 45 });

		initialPositions.add(new int[] { 130, 130 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "predator", 6, 100, true, false, hills, initialPositions, experimentName);
	}

	private static void experiment7a() throws IOException {
		String experimentName = "experiment7a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(100, 100, 20, 100));

		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 60, 60 });
		initialPositions.add(new int[] { 55, 55 });
		initialPositions.add(new int[] { 45, 55 });
		initialPositions.add(new int[] { 60, 45 });
		initialPositions.add(new int[] { 50, 60 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "disease", 6, 100, false, false, hills, initialPositions, experimentName);
	}

	private static void experiment7b() throws IOException {
		String experimentName = "experiment7b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(100, 100, 20, 100));

		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 60, 60 });
		initialPositions.add(new int[] { 55, 55 });
		initialPositions.add(new int[] { 45, 55 });
		initialPositions.add(new int[] { 60, 45 });
		initialPositions.add(new int[] { 50, 60 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "disease", 6, 100, true, false, hills, initialPositions, experimentName);
	}

	private static void experiment8a() throws IOException {
		String experimentName = "experiment8a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		MyRandom.intializeRandom(0);
		Random random = MyRandom.getInstanceOfRandom();

		for (int i = 0; i < 10; i++) {
			hills.add(new Hill(random.nextInt(posMax), random.nextInt(posMax),
					random.nextInt(radiusHigh - radiusLow) + radiusLow,
					random.nextInt(elevationHigh - elevationLow) + elevationLow));
		}

		for (int i = 0; i < 50; i++) {
			initialPositions.add(new int[] { random.nextInt(posMax), random.nextInt(posMax) });
		}

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 50, 100, false, false, hills, initialPositions, experimentName);
	}

	private static void experiment8b() throws IOException {
		String experimentName = "experiment8b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		MyRandom.intializeRandom(0);
		Random random = MyRandom.getInstanceOfRandom();

		for (int i = 0; i < 10; i++) {
			hills.add(new Hill(random.nextInt(posMax), random.nextInt(posMax),
					random.nextInt(radiusHigh - radiusLow) + radiusLow,
					random.nextInt(elevationHigh - elevationLow) + elevationLow));
		}

		for (int i = 0; i < 50; i++) {
			initialPositions.add(new int[] { random.nextInt(posMax), random.nextInt(posMax) });
		}

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 50, 100, true, false, hills, initialPositions, experimentName);
	}

	private static void experiment9a() throws IOException {
		String experimentName = "experiment9a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		MyRandom.intializeRandom(0);
		Random random = MyRandom.getInstanceOfRandom();

		for (int i = 0; i < 10; i++) {
			hills.add(new Hill(random.nextInt(posMax), random.nextInt(posMax),
					random.nextInt(radiusHigh - radiusLow) + radiusLow,
					random.nextInt(elevationHigh - elevationLow) + elevationLow));
		}

		for (int i = 0; i < 50; i++) {
			initialPositions.add(new int[] { random.nextInt(posMax), random.nextInt(posMax) });
		}

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "predator", 50, 100, false, false, hills, initialPositions,
				experimentName);
	}

	private static void experiment9b() throws IOException {
		String experimentName = "experiment9b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		MyRandom.intializeRandom(0);
		Random random = MyRandom.getInstanceOfRandom();

		for (int i = 0; i < 10; i++) {
			hills.add(new Hill(random.nextInt(posMax), random.nextInt(posMax),
					random.nextInt(radiusHigh - radiusLow) + radiusLow,
					random.nextInt(elevationHigh - elevationLow) + elevationLow));
		}

		for (int i = 0; i < 50; i++) {
			initialPositions.add(new int[] { random.nextInt(posMax), random.nextInt(posMax) });
		}

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "predator", 50, 100, true, false, hills, initialPositions, experimentName);
	}

	private static void experiment10a() throws IOException {
		String experimentName = "experiment10a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		MyRandom.intializeRandom(0);
		Random random = MyRandom.getInstanceOfRandom();

		for (int i = 0; i < 10; i++) {
			hills.add(new Hill(random.nextInt(posMax), random.nextInt(posMax),
					random.nextInt(radiusHigh - radiusLow) + radiusLow,
					random.nextInt(elevationHigh - elevationLow) + elevationLow));
		}

		for (int i = 0; i < 50; i++) {
			initialPositions.add(new int[] { random.nextInt(posMax), random.nextInt(posMax) });
		}

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "disease", 50, 100, false, false, hills, initialPositions, experimentName);
	}

	private static void experiment10b() throws IOException {
		String experimentName = "experiment10b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		MyRandom.intializeRandom(0);
		Random random = MyRandom.getInstanceOfRandom();

		for (int i = 0; i < 10; i++) {
			hills.add(new Hill(random.nextInt(posMax), random.nextInt(posMax),
					random.nextInt(radiusHigh - radiusLow) + radiusLow,
					random.nextInt(elevationHigh - elevationLow) + elevationLow));
		}

		for (int i = 0; i < 50; i++) {
			initialPositions.add(new int[] { random.nextInt(posMax), random.nextInt(posMax) });
		}

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "disease", 50, 100, true, false, hills, initialPositions, experimentName);
	}

	private static void experiment11a() throws IOException {
		String experimentName = "experiment11a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 10, 500));
		hills.add(new Hill(150, 150, 10, 100));

		initialPositions.add(new int[] { 45, 45 });
		initialPositions.add(new int[] { 45, 50 });
		initialPositions.add(new int[] { 50, 45 });
		initialPositions.add(new int[] { 145, 145 });
		initialPositions.add(new int[] { 145, 150 });
		initialPositions.add(new int[] { 150, 145 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 6, 100, false, false, hills, initialPositions, experimentName);
	}

	private static void experiment11b() throws IOException {
		String experimentName = "experiment11b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 10, 500));
		hills.add(new Hill(150, 150, 10, 100));

		initialPositions.add(new int[] { 45, 45 });
		initialPositions.add(new int[] { 45, 50 });
		initialPositions.add(new int[] { 50, 45 });
		initialPositions.add(new int[] { 145, 145 });
		initialPositions.add(new int[] { 145, 150 });
		initialPositions.add(new int[] { 150, 145 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 6, 100, true, false, hills, initialPositions, experimentName);
	}

	private static void experiment12a() throws IOException {
		String experimentName = "experiment12a";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 10, 1));
		hills.add(new Hill(150, 150, 10, 5));

		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 60, 60 });
		initialPositions.add(new int[] { 55, 55 });
		initialPositions.add(new int[] { 45, 55 });
		initialPositions.add(new int[] { 60, 45 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 5, 100, false, true, hills, initialPositions, experimentName);
	}

	private static void experiment12b() throws IOException {
		String experimentName = "experiment12b";

		ArrayList<Hill> hills = new ArrayList<Hill>();
		ArrayList<int[]> initialPositions = new ArrayList<int[]>();

		hills.add(new Hill(50, 50, 10, 1));
		hills.add(new Hill(150, 150, 10, 5));

		initialPositions.add(new int[] { 50, 50 });
		initialPositions.add(new int[] { 60, 60 });
		initialPositions.add(new int[] { 55, 55 });
		initialPositions.add(new int[] { 45, 55 });
		initialPositions.add(new int[] { 60, 45 });

		boolean[] exampleGenome = { true, true, true, true, true, true, true };
		new BoidPopulation(5, exampleGenome, "", 5, 100, true, true, hills, initialPositions, experimentName);
	}

}
