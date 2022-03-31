import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class BoidVisualizer extends JComponent{
	
	/**
	 * Private parameters
	 */
//	private int numBoids;
	private ArrayList<Boid> boids;
	private boolean update;
	private int adjustment;
	private InitializeBoids initialBoids;
	
	/**
	 * BoidVisualizer: Visualizer constructor
	 * @params: initialBoids
	 */
	public BoidVisualizer(InitializeBoids initialBoids) {
		this.initialBoids = initialBoids;
	}
	
	/***
	 * BoidVisualizer: Visualizer constructor
	 * @params: initialBoids
	 */
	public void update(int numBoids, ArrayList<Boid> boids, int adjustment) {
//		this.numBoids = numBoids;
		this.boids = boids;
		this.update = true;
		this.adjustment = adjustment;
	}
	
	/**
	 * paintComponent: Visualizes boid(s) at each timestep
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// TODO: dependent on the fitness being used
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				double x = i * initialBoids.frame.getWidth() / 100;
				double y = j * initialBoids.frame.getHeight() / 100;
				double fitness = calculateFitness(x,y);
				int colorValue = (int) (fitness*100000);
				
				if(colorValue > 255) {
					colorValue = 255;
				}
				g2.setColor(new Color(0, colorValue,0));
				g2.fillOval((int) x, (int) y, 20, 20);
			}
		}
		
		
//		if(initialBoids.fitnessType.equals("one")) {
//			g2.setColor(Color.GREEN);
//			g2.fillOval(this.initialBoids.frame.getWidth() / 2, this.initialBoids.frame.getHeight() / 2, 20, 20);
//		} else {
//			g2.setColor(Color.GREEN);
//			g2.fillOval(this.initialBoids.frame.getWidth() / 5, this.initialBoids.frame.getHeight() / 5, 20, 20);
//			g2.fillOval(4 * this.initialBoids.frame.getWidth() / 5, 4 * this.initialBoids.frame.getHeight() / 5, 20, 20);
//		}
		
		if(this.update) {
			for(Boid b : boids) {
				double posX = b.position[0] * this.adjustment;
				double posY = b.position[1] * this.adjustment;
			
				if(b.getColor().equals("blue")) {
					g2.setColor(Color.BLUE);
				} else if(b.getColor().equals("red")){
					g2.setColor(Color.RED);
				} else if(b.getColor().equals("pink")){
					g2.setColor(Color.PINK);
				} else if(b.getColor().equals("orange")){
					g2.setColor(Color.ORANGE);
				} else if(b.getColor().equals("green")){
					g2.setColor(Color.GREEN);
				} else {
					g2.setColor(Color.BLACK);
				}
				g2.fillOval((int) posX, (int) posY, 10, 10);
				
				g2.setColor(Color.BLACK);
				if(b.getConnectionList() != null) {
					for(Boid boid : b.getConnectionList()) {
						g2.setStroke(new BasicStroke(0.02f));
						g2.draw(new Line2D.Double(posX, posY + 1, 
								boid.position[0] * this.adjustment, boid.position[1] * this.adjustment + 1));
					}
				}
				
				g2.setStroke(new BasicStroke(2));
				g2.setFont(g2.getFont().deriveFont(20.0f));
				
				g2.setColor(Color.RED);
				g2.drawLine((int) posX + 5, (int) posY + 5, (int) (posX + 5 + b.getRules()[0][0] * 2), (int) (posY + 5 + b.getRules()[0][1] * 2));
				g2.setColor(Color.GREEN);
				g2.drawLine((int) posX + 5, (int) posY + 5, (int) (posX + 5 + b.getRules()[1][0] * 2), (int) (posY + 5 + b.getRules()[1][1] * 2));
				g2.setColor(Color.BLUE);
				g2.drawLine((int) posX + 5, (int) posY + 5, (int) (posX + 5 + b.getRules()[2][0] * 2), (int) (posY + 5 + b.getRules()[2][1] * 2));
				g2.setColor(Color.ORANGE);
				g2.drawLine((int) posX + 5, (int) posY + 5, (int) (posX + 5 + b.getRules()[3][0] * 2), (int) (posY + 5 + b.getRules()[3][1] * 2));
				g2.setColor(Color.YELLOW);
				g2.drawLine((int) posX + 5, (int) posY + 5, (int) (posX + 5 + b.getRules()[4][0] * 2), (int) (posY + 5 + b.getRules()[4][1] * 2));
			}
			
			if(initialBoids.fitnessType.equals("predator")) {
				double posX = initialBoids.predator.position[0] * this.adjustment;
				double posY = initialBoids.predator.position[1] * this.adjustment;
			
				g2.setColor(Color.RED);
				g2.fillOval((int) posX, (int) posY, 10, 10);
			}
		}
		this.update = false;
	}
	
	public double calculateFitness(double x, double y) {
		return 1 / (Math.sqrt(Math.pow(x - initialBoids.frame.getWidth() / 25, 2) + (Math.pow(y - initialBoids.frame.getHeight() / 25, 2))) 
				+ Math.sqrt(Math.pow(x - 4 * initialBoids.frame.getWidth() / 25, 2) 
				+ (Math.pow(y - 4 * initialBoids.frame.getHeight() / 25, 2))));
	}
}
