import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;

public class startListener implements ActionListener {
	
	private InitializeBoids initializeBoids;
	private JButton startButton;

	public startListener(InitializeBoids initializeBoids, JButton startButton) {
		this.initializeBoids = initializeBoids;
		this.startButton = startButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.startButton.getText().equals("Start") && !this.initializeBoids.simulationRunning) {
			this.startButton.setText("Stop");
			this.initializeBoids.setSimulationRunning(true);
		} else if(this.startButton.getText().equals("Stop") && this.initializeBoids.simulationRunning) {
			this.startButton.setText("Start");
			this.initializeBoids.setSimulationRunning(false);
		}
	}

}
