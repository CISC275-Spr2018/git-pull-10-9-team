import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class Controller{

	// Create instances of the Model and View classes
	private Model model;
	private View view;
	private boolean isStarted = false;
	
	
	// Draw Delay between frames
	private final int drawDelay = 50;

	// The Action to be taken after every update
	private Action drawAction;
	
	public Controller(){
		// Create new instances of Model and View
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		
		view.addStartStopListener(new StartStopListener());
		view.addReverseListener(new ReverseListener());
		view.addKeyInput(new KeyInput());

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
	// Key Input Listener
	class KeyInput implements KeyListener{
		@Override
		public void keyPressed(KeyEvent keyEvent) {
			int code = keyEvent.getKeyCode();
			System.out.println(code);
			if(code == 38){
				//moveUp()
			}
			else if(code == 37){
				//moveLeft()
			}
			else if(code ==39){
				//moveRight
			}
			else if(code == 40){
				//moveDown()
			}
			else if(keyEvent.getKeyCode() == 68 /* D */ ){
				//killOrc();
			}
			else if(keyEvent.getKeyCode() == 70 /* F */ ){
				//fire();
			}
			else if(keyEvent.getKeyCode() == 74 /* J */){
				//jump
			}
		}

		@Override
		public void keyReleased(KeyEvent keyEvent) {

		}

		@Override
		public void keyTyped(KeyEvent keyEvent) {

		}
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