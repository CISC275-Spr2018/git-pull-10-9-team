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
	
	
	
	// Frame count for each movement
	//private ArrayList<BufferedImage[]> orcAnimations = new ArrayList<BufferedImage[]>();
	private OrcImages orcAnimations = new OrcImages();
	
	
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
		
		
    
 
		// Store images
		orcAnimations.loadAnimationSet();
    	
		// Set current pic and start animation at frame 0;
		picNum = 0;
		direct = Direction.SOUTHEAST;
		currentPic = orcAnimations.getAnimationImages(Action.FORWARD, direct);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2,4,4));
	    buttonPanel.add(startStopButton);
		buttonPanel.add(reverseButton);
		
			
		// Set up the JFrame
		
		frame = new JFrame();
		frame.setBackground(Color.gray);
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
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
	
    
    
    // Updates the image based on changes in the model
    public void update(int xL, int yL, Direction dir) {
    	// Read in the new locations and directions
    	xLoc = xL;
    	yLoc = yL;
    	direct = dir;
    		
    	// update the current animation to the current animation set.
    	currentPic = orcAnimations.getAnimationImages(Action.FORWARD, dir);
    	
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


class OrcImages{
	// constant size of subImages
	private final int subImageWidth = 165;
	private final int subImageHeight = 165;
	
	private ArrayList<BufferedImage[]> dieImages = new ArrayList<>(8);
	private ArrayList<BufferedImage[]>  fireImages = new ArrayList<>(8);
	private ArrayList<BufferedImage[]>  jumpImages = new ArrayList<>(8);
	private ArrayList<BufferedImage[]>  forwardImages = new ArrayList<>(8);
	
	
	
	public void loadAnimationSet(){
		for (Action atype: Action.octaDirectionalValues()){
			ArrayList<BufferedImage[]> curAction = new ArrayList<>();
			for (Direction dir: Direction.values()){
				String imgLoc = "images/orc/" + atype.getName() + dir.getName() + ".png";
				BufferedImage img = createImage(imgLoc);
				int frameCount = img.getWidth() / subImageHeight;
				BufferedImage[] pics = new BufferedImage[frameCount];
				for(int i = 0; i < frameCount; i++) {
					pics[i] = img.getSubimage(subImageWidth*i, 0, subImageWidth, subImageHeight);	
				}
				curAction.add(pics);
			}
			setActionImages(atype, curAction);
		}
	}
	
	public void setActionImages(Action a, ArrayList <BufferedImage[]> img){
		switch (a){
			case DIE: 
				dieImages = img;
				break;
			case FIRE: 
				 fireImages = img;
				 break;
			case JUMP:
				jumpImages= img;
				break;
			default: //FORWARD
				forwardImages = img;
				break;
				
		}
	}
	
	private ArrayList<BufferedImage[]> getImageSet(Action a){
		switch (a){
			case DIE: 
				return dieImages;
			case FIRE: 
				return fireImages;
			case JUMP:
				return jumpImages;
			default: //FORWARD
				return forwardImages;
				
		}
	}
	
	public BufferedImage[] getAnimationImages(Action a, Direction d){
		return getImageSet(a).get(d.ordinal());
	}
	
	
	
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
	
}
