public class Hill {

	private double[] position = {0,0};
	private int radius;
	private int elevation;

	public Hill(int posX, int posY, int radius, int elevation) {
		this.position[0] = posX;
		this.position[1] = posY;
		this.radius = radius;
		this.elevation = elevation;
	}
	
	public double[] getPosition() {
		return this.position;
	}
	
	public int getRadius() {
		return this.radius;
	}
	
	public int getElevation() {
		return this.elevation;
	}

}
