import java.util.ArrayList;
import java.util.Random;

public class Predator {
	public double[] position = {7, 7};
	private double[] velocity = {0, 0};
	private ArrayList<Boid> boids;
	private static int groupDistance = 10;
	private static int killDistance = 10;
	private static int maxVelocity = 5;
	private static int velBoundChg = 2;
	
	
	public Predator(int x, int y, ArrayList<Boid> boids) {
		position[0] = x;
		position[1] = y;
		this.boids = boids;
	}
	
	public void update() {
		ArrayList<Boid> filtered = filterBoidsByGroup();
		if(!filtered.isEmpty()) {
			Boid closestBoid = findClosestBoid(filtered);
			moveToward(closestBoid);
			consume(filtered);
		} else {
			moveRandomDirection();
		}
	}

	private void moveRandomDirection() {
		Random random = MyRandom.getInstanceOfRandom();
		velocity[0] += (random.nextDouble() * 2 - 1);
		velocity[1] += (random.nextDouble() * 2 - 1);
		
		if(velocity[0] > maxVelocity) {
			velocity[0] = maxVelocity;
		} else if(velocity[0] < -1 * maxVelocity) {
			velocity[0] = -1 * maxVelocity;
		}
		if(velocity[1] > maxVelocity) {
			velocity[1] = maxVelocity;
		} else if(velocity[0] < -1 * maxVelocity) {
			velocity[1] = -1 * maxVelocity;
		}
		
		position[0] += velocity[0];
		position[1] += velocity[1];	
		
		boundPosition();
	}

	private void moveToward(Boid closestBoid) {
		velocity[0] = (closestBoid.getPosition()[0] - position[0]) / 50;
		velocity[1] = (closestBoid.getPosition()[1] - position[1]) / 50;
		
//		if(velocity[0] < 1) velocity[0] = .1;
//		if(velocity[1] < 1) velocity[1] = .1;
		
		if(velocity[0] > maxVelocity) {
			velocity[0] = maxVelocity;
		} else if(velocity[0] < -1 * maxVelocity) {
			velocity[0] = -1 * maxVelocity;
		}
		if(velocity[1] > maxVelocity) {
			velocity[1] = maxVelocity;
		} else if(velocity[0] < -1 * maxVelocity) {
			velocity[1] = -1 * maxVelocity;
		}
		
		position[0] += velocity[0];
		position[1] += velocity[1];	
		
		boundPosition();
	}

	private Boid findClosestBoid(ArrayList<Boid> filtered) {
		Boid closestBoid = null;
		double bestDistance = Double.POSITIVE_INFINITY;
		for(Boid b : filtered) {
			double distance = distanceBetween(this.position, b.position);
			if(distance < bestDistance) {
				bestDistance = distance;
				closestBoid = b;
			}
			if(closestBoid == null) {
				int i = 0;
			}
		}
		
		return closestBoid;
	}

	private void consume(ArrayList<Boid> filtered) {
		for(Boid b : filtered) {
			if(distanceBetween(this.position, b.position) <= killDistance) {
				b.toRemove = true;
				return;
			}
		}
	}

	private ArrayList<Boid> filterBoidsByGroup() {
		ArrayList<Boid> filteredGroup = new ArrayList<Boid>();
		ArrayList<Boid> groupMembers = new ArrayList<Boid>();
		
		for(Boid b : boids) {
			ArrayList<Boid> neighbors = new ArrayList<Boid>();
			for(Boid n : boids) {
				if(!b.equals(n) && distanceBetween(b.position, n.position) <= groupDistance) {
					neighbors.add(n);
				} 
			}
			if(neighbors.size() >= 2) {
				if(!groupMembers.contains(b)) groupMembers.add(b);
				for(Boid n : neighbors) {
					if(!groupMembers.contains(n)) {
						groupMembers.add(n);
					}
				}
			}
		}
		for(Boid b : boids) {
			if(!groupMembers.contains(b)) {
				filteredGroup.add(b);
			}
		}
		return filteredGroup;
	}

	private double distanceBetween(double[] pos1, double[] pos2) {
		return Math.sqrt(Math.pow(pos2[0] - pos1[0], 2) 
				+ Math.pow(pos2[1] - pos1[1], 2));
	}
	
	private void boundPosition() {
		if(position[0] < 0) {
			velocity[0] = velBoundChg;
		} else if(position[0] > 200) {
			velocity[0] = -velBoundChg;
		}
		
		if(position[1] < 0) {
			velocity[1] = velBoundChg;
		} else if(position[1] > 200) {
			velocity[1] = -velBoundChg;
		}
	}

}
