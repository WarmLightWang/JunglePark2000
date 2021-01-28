/**
 * This class inherits all the features of its superclass Button, and overrides
 * some of them. The class has following methods and functions in turn:
 * 
 * @constructor ClearButton: Constructor that creates a new ClearButton object
 *              positioned at the given position of the display window
 * @method mousePressed: called each time the mouse is Pressed. If mouse is over
 *         the button, call clear method defined in the JunglePark class
 */
public class ClearButton extends Button {

	/**
	 * Constructor that creates a new Button object positioned at the given position
	 * of the display window
	 * 
	 * @param x:          postionX of the button center
	 * @param y:          postionY of the button center
	 * @param processing: processing PApplet object that represents the display
	 *                    window
	 */
	public ClearButton(float x, float y, JunglePark park) {
		super(x, y, park); // call the superclass constructor
		this.label = "Clear Park"; // set the button label to "Clear Park"
	}

	/**
	 * called each time the mouse is Pressed. If mouse is over the button when
	 * clicked, call clear method defined in the JunglePark class, and the listGUI
	 * will be clear.
	 */
	@Override
	public void mousePressed() {
		if (isMouseOver()) {
			processing.clear();
		}
	}
}
