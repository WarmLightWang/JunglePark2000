/**
 * The interface ParkGUI provide us with a model of what elements a graphic
 * component needs. Our application will contain different graphic components.
 * These objects should be drawn constantly to the display window and must react
 * appropriately to the mouse events when detected. We have different graphic
 * objects which share some features and present different behaviors. To avoid
 * redundancy and promote abstraction in our code, we defined this interface
 * ParkGUI The class has following methods and functions in turn:
 * 
 * @method draw: draws a ParkGUI object (either an animal or a button) to the
 *         display window
 * @method mousePressed(): called each time the mouse is Pressed
 * @method mouseReleased: called each time the mouse is Released
 * @method isMouseOver: checks whether the mouse is over a ParkGUI object
 */
public interface ParkGUI {

	/**
	 * draws a ParkGUI object (either an animal or a button) to the display window
	 * has no return values
	 */
	public void draw();

	/**
	 * called each time the mouse is Pressed has no return values
	 */
	public void mousePressed();

	/**
	 * called each time the mouse is Released has no return values
	 */
	public void mouseReleased();

	/**
	 * checks whether the mouse is over a ParkGUI object
	 * 
	 * @return return true if the mouse is over the object, false otherwise.
	 */
	public boolean isMouseOver();

}
