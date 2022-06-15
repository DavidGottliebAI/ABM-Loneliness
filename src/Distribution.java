import java.util.ArrayList;
import java.util.Random;

public class Distribution {
	
	public static double calculateDistibutionValue(double mean, double std) {
		Random random = MyRandom.getInstanceOfRandom();
		double value = random.nextGaussian() * std + mean;
		if(value > 1) value = 1;
		if(value < -1) value = -1;
		return value;
	}
	
	public static double[] calculateMeanAndStd(double[] distribution) {
		
		double[] meanAndStd = new double[2];
		double n = distribution.length, mean;
		
		double sum = 0;
		for(int i = 0; i < n; i++) {
			sum += distribution[i];
		}
		mean = sum / n;
		meanAndStd[0] = mean;
		
		sum = 0;  
		for(int i = 0; i < n; i++) {
			sum += Math.pow((distribution[i] - mean), 2);
		}
		mean = sum / (n-1);
		double deviation = Math.sqrt(mean);
		meanAndStd[1] = deviation;
		
		return meanAndStd;
	}
	
}

