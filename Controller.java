import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Controller {
	// Create instances of the Model and View classes
	private Model model;
	private View view;
	boolean isStarted = false;
	
	
	// Draw Delay between frames
	final int drawDelay = 50;

	// The Action to be taken after every update
	Action drawAction;
	
	public Controller(){
		// Create new instances of Model and View
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		
		view.addStartStopListener(new StartStopListener());
		view.addReverseListener(new ReverseListener());


		// Make our draw action update the Model and the View whenever called
		drawAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
				if (!isStarted){}
				else {
					model.updateLocationAndDirection();
					view.update(model.getX(), model.getY(), model.getDirect());
				}
				
			
			}
		};
	}
	
	// Listener
	class ReverseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.reverseDir();
		}
	}
	class StartStopListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			isStarted = !isStarted;
		}

	}
	//run the simulation
	public void start(){
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				// Creates a timer that calls our drawAction every 50 ms.
				Timer t = new Timer(drawDelay, drawAction);
				// Starts the timer.
				t.start();
			}
		});
	}
	
}
