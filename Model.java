/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model{
	final int X_SPEED = 6;
	final int Y_SPEED = 6;
	int frameWidth;
	int frameHeight;
	int imageWidth;
	int imageHeight;
	int x = 0;
	int y = 0;
	Direction direction = Direction.SOUTHEAST;
	
	final int leftBound = 0;
	int rightBound;
	final int topBound = 0;
	int bottomBound;
	
	public Model(int width, int height, int imageWidth, int imageHeight) {
		frameWidth = width;
		frameHeight = height;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		findBounds();
	}
	
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	
	public Direction getDirect() {
		// TODO Auto-generated method stub
		return direction;
	}
	

	public void updateLocationAndDirection() {
		int newX = x + xIncriment() * X_SPEED;
		int newY = y + yIncriment() * Y_SPEED;
	
		if (newX < leftBound) {
			newX = leftBound;
			flipDirection(Direction.WEST);

		}
		if (newX > rightBound){
			newX = rightBound;
			flipDirection(Direction.EAST);

		}
		if (newY < topBound){
			newY = topBound;
			flipDirection(Direction.NORTH);

		}
		if (newY > bottomBound){
			newY = bottomBound;
			flipDirection(Direction.SOUTH);

		}
		x = newX;
		y = newY;
		
	}
	

	
	private void findBounds(){
		rightBound = frameWidth - imageWidth;
		bottomBound = frameHeight - imageWidth;
	}
	
	
	private int yIncriment(){
		switch (direction){
			case NORTH: 
				return -1;
			case NORTHEAST:
				return -1;
			case EAST:
				return 0;
			case SOUTHEAST:
				return 1;
			case SOUTH:
				return 1;
			case SOUTHWEST:
				return 1;
			case WEST:
				return 0;
			default: //NORTHWEST
				return -1;
		}
	}
    private int xIncriment(){
		switch (direction){
			case NORTH: 
				return 0;
			case NORTHEAST:
				return 1;
			case EAST:
				return 1;
			case SOUTHEAST:
				return 1;
			case SOUTH:
				return 0;
			case SOUTHWEST:
				return -1;
			case WEST:
				return -1;
			default: //NORTHWEST
				return -1;
		}
	}
    
    // Flips the direction, in the direction of the flip argument provided,
    // i.e. if direction == NORTHEAST, flipDirection(EAST) makes
    // direction = NORTHWEST
    private void flipDirection(Direction flip){
    	switch (direction){
			case NORTH: 
				direction = Direction.SOUTH;
				break;
			case NORTHEAST:
				if (flip == Direction.EAST) direction = Direction.NORTHWEST;
				else direction = Direction.SOUTHEAST;
				break;
			case EAST:
				direction = Direction.WEST;
				break;
			case SOUTHEAST:
				if (flip == Direction.EAST) direction = Direction.SOUTHWEST;
				else direction = Direction.NORTHEAST;
				break;
			case SOUTH:
				direction = Direction.NORTH;
				break;
			case SOUTHWEST:
				if (flip == Direction.WEST) direction = Direction.SOUTHEAST;
				else direction = Direction.NORTHWEST;
				break;
			case WEST:
				direction = Direction.EAST;
				break;
			default: //NORTHWEST
				if (flip == Direction.WEST) direction = Direction.NORTHEAST;
				else direction = Direction.SOUTHWEST;
				break;
    	}
    }
    
    // Reverse Current Direction
    public void reverseDir() {
    	switch (direction){
			case NORTH: 
				direction = Direction.SOUTH;
				break;
			case NORTHEAST:
				direction = Direction.SOUTHWEST;
				break;
			case EAST:
				direction = Direction.WEST;
				break;
			case SOUTHEAST:
				direction = Direction.NORTHWEST;
				break;
			case SOUTH:
				direction = Direction.NORTH;
				break;
			case SOUTHWEST:
				direction = Direction.NORTHEAST;
				break;
			case WEST:
				direction = Direction.EAST;
				break;
			default: //NORTHWEST
				direction = Direction.SOUTHEAST;
				break;
	}
    }
	
}
