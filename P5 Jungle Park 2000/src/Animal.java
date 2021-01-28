import java.util.Random;

/**
 * This class represents an animal in the Jungle Park application It implements
 * the interface ParkGUI The class has following methods and functions in turn:
 * 
 * @constructor1 Animal: initialize a new Animal object within a specific
 *               processing object, put it to the given position.
 * @constructor2 Animal: initialize a new Animal object within a specific
 *               processing object, put it to a random position.
 * @method draw: Draws the animal to the display window. It sets also its
 *         position to the mouse position if the tiger is being dragged (i.e. if
 *         its isDragging field is set to true).
 * @method displayLabel: called constantly. If any time a tiger is near a deer,
 *         a threat message will be displayed.
 * @method isMouseOver: Checks if the mouse is over the given tiger object
 * @method mousePressed: called each time the mouse is Pressed. If over, set
 *         isDragging to true
 * @method mouseReleased: called each time the mouse is Released. If over, set
 *         isDragging to false
 * @method getLabel: return the label that represents the tiger's identifier
 * @method getImage: return the image of type PImage of the tiger object
 * @method getPositionX: return the X coordinate of the animal position
 * @method getPositionY: return the Y coordinate of the animal position
 * @method setPositionX: set the X coordinate of the animal position
 * @method setPositionY: set the Y coordinate of the animal position
 * @method isDragging: return true if the animal is being dragged, false
 *         otherwise
 * @method distance: Computes the euclidean distance between the current animal
 *         and another one
 * @method distance: This method should be overriden by a subclasse
 * @method isClose: Check if the given animal is close to the current animal
 *         within the given range
 */
public class Animal implements ParkGUI {

	private static Random randGen = new Random(); // Generator of random numbers
	protected String label; // represents the animal's identifier
	// Fields defined to draw the animal in the application display window

	protected JunglePark processing; // PApplet object that represents the display window
	protected PImage image; // animal's image
	private float[] position; // tiger's position in the display window
								// Usage: position[0: x-coordinate, or 1: y-coordinate]
	private boolean isDragging; // indicates whether the animal is being dragged or not

	/**
	 * Creates a new Animal object positioned at a given position of the display
	 * window
	 * 
	 * @param processing    PApplet object that represents the display window
	 * @param positionX     x-coordinate of the animal's image in the display window
	 * @param positionY     y-coordinate of the animal's image in the display window
	 * @param imageFileName filename of the animal image
	 */
	public Animal(JunglePark processing, float positionX, float positionY, String imageFileName) {

		// Set Animal drawing parameters
		this.processing = processing; // set the PApplet Object where the animal will be drawn
		this.position = new float[] { positionX, positionY }; // sets the position of the animal object
		this.image = processing.loadImage(imageFileName);
		isDragging = false; // initially the animal is not dragging
	}

	/**
	 * Creates a new Animal object positioned at a random position of the display
	 * window
	 * 
	 * @param processing    PApplet object that represents the display window
	 * @param imageFileName filename of the animal image
	 */
	public Animal(JunglePark processing, String imageFileName) {
		this(processing, (float) randGen.nextInt(processing.width),
				Math.max((float) randGen.nextInt(processing.height), 100), imageFileName);
	}

	/**
	 * Draws the animal to the display window. It sets also its position to the
	 * mouse position if the tiger is being dragged (i.e. if its isDragging field is
	 * set to true).
	 */
	@Override
	public void draw() {
		// if the tiger is dragging, set its position to the mouse position with respect
		// to the display
		// window (processing) dimension
		if (this.isDragging) {
			if (this.processing.mouseX < 0) // mouse outside the screen
				this.position[0] = 0;
			else if (this.processing.mouseX > this.processing.width) // mouse outside the screen
				this.position[0] = this.processing.width;
			else
				this.position[0] = this.processing.mouseX;

			if (this.processing.mouseY < 0) // mouse outside the screen
				this.position[1] = 0;
			else if (this.processing.mouseY > this.processing.height) // mouse outside the screen
				this.position[1] = this.processing.height;
			else
				this.position[1] = this.processing.mouseY;
		}
		action();
		// draw the tiger at its current position
		this.processing.image(this.image, this.position[0], position[1]);
		// display label
		displayLabel();
	}

	/**
	 * display's the Tiger object label on the application window screen
	 */
	private void displayLabel() {
		this.processing.fill(0); // specify font color: black
		this.processing.text(label, this.position[0], this.position[1] + this.image.height / 2 + 4);
		// display label
	}

	/**
	 * Checks if the mouse is over the given tiger object
	 * 
	 * @param tiger reference to a given Tiger object
	 * @return true if the mouse is over the given tiger object, false otherwise
	 */
	@Override
	public boolean isMouseOver() {
		int tigerWidth = image.width; // image width
		int tigerHeight = image.height; // image height

		// checks if the mouse is over the tiger
		if (processing.mouseX > position[0] - tigerWidth / 2 && processing.mouseX < position[0] + tigerWidth / 2
				&& processing.mouseY > position[1] - tigerHeight / 2
				&& processing.mouseY < position[1] + tigerHeight / 2) {
			return true;
		}
		return false;
	}

	/**
	 * called each time the mouse is Pressed.If mouse is over, set isDragging to
	 * true
	 */
	@Override
	public void mousePressed() {
		if (isMouseOver())
			isDragging = true;
	}

	/**
	 * called each time the mouse is Release.If mouse is over, set isDragging to
	 * false
	 */
	@Override
	public void mouseReleased() {
		isDragging = false;
	}

	/**
	 * @return the label that represents the tiger's identifier
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the image of type PImage of the tiger object
	 */
	public PImage getImage() {
		return image;
	}

	/**
	 * @return the X coordinate of the animal position
	 */
	public float getPositionX() {
		return position[0];
	}

	/**
	 * @return the Y coordinate of the animal position
	 */
	public float getPositionY() {
		return position[1];
	}

	/**
	 * @param position the XPosition to set
	 */
	public void setPositionX(float position) {
		this.position[0] = position;
	}

	/**
	 * @param position the YPosition to set
	 */
	public void setPositionY(float position) {
		this.position[1] = position;
	}

	/**
	 * @return true if the animal is being dragged, false otherwise
	 */
	public boolean isDragging() {
		return isDragging;
	}

	/**
	 * Computes the euclidean distance between the current animal and another one
	 * 
	 * @param otherAnimal reference to another animal
	 * @return distance between the current animal and otherAnimal
	 */
	public double distance(Animal otherAnimal) {
		return Math.sqrt(Math.pow(this.getPositionX() - otherAnimal.getPositionX(), 2)
				+ Math.pow(this.getPositionY() - otherAnimal.getPositionY(), 2));
	}

	/**
	 * This method should be override by a subclasses
	 */
	public void action() {
		// This method should be override by a subclasses
	}

	/**
	 * Check if the given animal is close to the current animal within the given
	 * range
	 * 
	 * @return true if the given animal is within the range, false otherwise
	 */
	public boolean isClose(Animal otherAnimal, int range) {
		if (this.distance(otherAnimal) <= (double) range)
			return true;
		else
			return false;

	}
}
