import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

@SuppressWarnings("serial")
public class View extends JPanel{
	
	// Constants for the size of the view
	private final int viewWidth = 500;
	private final int viewHeight = 300;
	
	// constant size of subImages
	private final int subImageWidth = 165;
	private final int subImageHeight = 165;
	
	// Frame count for each movement
	private ArrayList<BufferedImage[]> orcAnimations = new ArrayList<BufferedImage[]>();

	private JFrame frame;
	// Current pic in the animation and the picNum for animation iteration
	private BufferedImage[] currentPic;
	private int picNum;
	
	// Location and Direction from Model
	private int yLoc;
	private int xLoc;
	private Direction direct;
	
	private final int drawDelay = 30;
	
	private JButton reverseButton = new JButton("Reverse");
	private JButton startStopButton = new JButton("Start/Stop");

	
	public View() {
	
		// Load in orc animations from /images/orc/
		ArrayList<String> orcFileLocs = new ArrayList<String>();
    	
    		// Read in the images using a for loop and the enumeration.
		for(Direction d : Direction.values()) {
    			//System.out.println(d.getName());
    			orcFileLocs.add("images/orc/orc_forward_" + d.getName() + ".png");
		}
 
		// Store images
		for(String imgLoc : orcFileLocs) {
			BufferedImage img = createImage(imgLoc);
			int frameCount = img.getWidth() / subImageHeight;
			BufferedImage[] pics = new BufferedImage[frameCount];
			for(int i = 0; i < frameCount; i++) {
				pics[i] = img.getSubimage(subImageWidth*i, 0, subImageWidth, subImageHeight);	
			}
			orcAnimations.add(pics);
			
		}
    	
		// Set current pic and start animation at frame 0;
		picNum = 0;
		currentPic = orcAnimations.get(3);
		
		//JPanel buttonPanel = new JPanel(new GridLayout(1,2,4,4));
		//buttonPanel.add(startStopButton);
		//buttonPanel.add(reverseButton);
		
			
		// Set up the JFrame
		frame = new JFrame();
		frame.getContentPane().add(this, BorderLayout.CENTER);
		//frame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		frame.getContentPane().setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		frame.setSize(viewWidth, viewHeight);
		frame.setVisible(true);
	    }
	
	//Accessors
	public int getImageWidth() {
		int subImageWidth = 0;
		if (currentPic.length == 10 || currentPic.length == 8 || currentPic.length == 4) {
			subImageWidth = 165;
		} else if (currentPic.length == 7) {
			subImageWidth = 232;
		}
		return subImageWidth;
	}
	
	public int getImageHeight() {
		int subImageHeight = 0;
		if (currentPic.length == 10 || currentPic.length == 8 || currentPic.length == 4) {
			subImageHeight = 165;
		} else if (currentPic.length == 7) {
			subImageHeight = 232;
		}
		return subImageHeight;
	}
	public int getDrawDelay() {
		return drawDelay;
	}
	
    //Read image from file and return
    private BufferedImage createImage(String loc){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(loc));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    }
    
    // Updates the image based on changes in the model
    public void update(int xL, int yL, Direction dir) {
    	// Read in the new locations and directions
    	xLoc = xL;
    	yLoc = yL;
    	direct = dir;
    	
    	
    	// update the current animation to the current animation set.
    	switch (direct){
    		case NORTH:
    			currentPic = orcAnimations.get(0);
    			break;
    		case NORTHEAST:
        		currentPic = orcAnimations.get(1);    		
        		break;
    		case EAST:
        		currentPic = orcAnimations.get(2);
        		break;
    		case SOUTHEAST:
        		currentPic = orcAnimations.get(3);
        		break;
    		case SOUTH:
        		currentPic = orcAnimations.get(4);
        		break;
    		case SOUTHWEST:
        		currentPic = orcAnimations.get(5);
        		break;
    		case WEST:
        		currentPic = orcAnimations.get(6);
        		break;
    		default:
        		currentPic = orcAnimations.get(7);
    	}
    	
    	// Repaint the frame
    	frame.repaint();
    	
    }
    
    
    protected void paintComponent(Graphics g) {
    	// Cycle through the animation cycle
		picNum = (picNum + 1) % currentPic.length;
		// Draw the image on the frame
		g.drawImage(currentPic[picNum], xLoc, yLoc, Color.gray, this);
	}

    // Action Listener for Reverse Button
    public void addReverseListener(ActionListener rev) {
    		reverseButton.addActionListener(rev);
    }

    public void addKeyInput(KeyListener kL){
		frame.addKeyListener(kL);
	//	.addKeyListener(kL);
		System.out.println("Key Listener added");
	}
    // Action Listener for Start/Stop Button
    public void addStartStopListener(ActionListener start) {
    	startStopButton.addActionListener(start);
    }
    
}

