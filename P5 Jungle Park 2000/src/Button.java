/**
 * This a super class for any Button that can be added to a PApplet application
 * This class implements the ParkGUI interface The class has following methods
 * and functions in turn:
 * 
 * @constructor Button: Constructor that creates a new Button object positioned
 *              at the given position of the display window
 * @method draw: Draws the button to the display window. The color of the button
 *         also changes if the mouse is over it, turning from light gray to dark
 *         gray.
 * @method mousePressed: called each time the mouse is Pressed. Print out a
 *         message.
 * @method mouseReleased: called each time the mouse is Released.
 * @method isMouseOver: checks whether the mouse is over a ParkGUI object.
 */
public class Button implements ParkGUI {
	private static final int WIDTH = 85; // Width of the Button
	private static final int HEIGHT = 32; // Height of the Button
	protected JunglePark processing; // PApplet object where the button will be displayed
	private float[] position; // array storing x and y positions of the Button with respect to the display
								// window
	protected String label; // text/label that represents the button

	/**
	 * Constructor that creates a new Button object positioned at the given position
	 * of the display window
	 * 
	 * @param x:          postionX of the button center
	 * @param y:          postionY of the button center
	 * @param processing: processing PApplet object that represents the display
	 *                    window
	 */
	public Button(float x, float y, JunglePark processing) {
		this.processing = processing; // set the work space to program processing
		this.position = new float[2]; // create a new float array to store the button position X and Y
		this.position[0] = x; // set the button center positionX to x
		this.position[1] = y; // set the button center positionY to y
		this.label = "Button"; // set the button label to "Button"
	}

	/**
	 * Draws the button to the display window. The color of the button also changes
	 * if the mouse is over it, turning from light gray to dark gray.
	 */
	@Override
	public void draw() {
		this.processing.stroke(0);// set line value to black
		if (isMouseOver())
			processing.fill(100); // set the fill color to dark gray if the mouse is over the button
		else
			processing.fill(200); // set the fill color to light gray otherwise

		// draw the button (rectangle with a centered text)
		processing.rect(position[0] - WIDTH / 2.0f, position[1] - HEIGHT / 2.0f, position[0] + WIDTH / 2.0f,
				position[1] + HEIGHT / 2.0f);
		processing.fill(0); // set the fill color to black
		processing.text(label, position[0], position[1]); // display the text of the current button
	}

	/**
	 * called each time the mouse is Pressed. Print out a message.
	 */
	@Override
	public void mousePressed() {
		if (isMouseOver())
			System.out.println("A button was pressed."); // Print a message
	}

	/**
	 * called each time the mouse is Released.
	 */
	@Override
	public void mouseReleased() {
	}

	/**
	 * checks whether the mouse is over a ParkGUI object.
	 * 
	 * @return true if the mouse is over this object, false otherwise.
	 */
	@Override
	public boolean isMouseOver() {
		// check the mouse position, if and only if all these 4 conditions are satisfied
		// can the method say the mouse is over the button.
		if (this.processing.mouseX > this.position[0] - WIDTH / 2
				&& this.processing.mouseX < this.position[0] + WIDTH / 2
				&& this.processing.mouseY > this.position[1] - HEIGHT / 2
				&& this.processing.mouseY < this.position[1] + HEIGHT / 2)
			return true;
		return false;
	}
}
