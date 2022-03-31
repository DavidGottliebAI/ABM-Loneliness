import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class mouseListener implements MouseListener {
	
	private JFrame frame;

	public mouseListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		double fitness = Math.sqrt(Math.pow(MouseInfo.getPointerInfo().getLocation().x - this.frame.getWidth() / 10, 2) + 
					(Math.pow(MouseInfo.getPointerInfo().getLocation().y - this.frame.getHeight() / 10, 2)));
		System.out.println(fitness);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
