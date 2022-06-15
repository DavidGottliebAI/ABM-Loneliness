import java.util.Random;

public class MyRandom {

	static Random myRandom;

	public static Random getInstanceOfRandom() {
		if (myRandom == null) {
			Random rand = new Random();
			return rand;
		} else {
			return myRandom;
		}

	}

	public static void intializeRandom(int seed) {
		myRandom = new Random(seed);
	}
}
