/**
 * This class inherits all the features of its superclass Button, and overrides
 * some of them. The class has following methods and functions in turn:
 * 
 * @constructor AddAnimalButton: Constructor that creates a new AnimalButton
 *              object positioned at the given position of the display window
 * @method mousePressed: called each time the mouse is Pressed. If mouse is over
 *         the button when clicked, create different type of animals according
 *         to the type field, add it to the listGUI
 */
public class AddAnimalButton extends Button {

	private String type; // type of the animal to add

	/**
	 * Constructor that creates a new Button object positioned at the given position
	 * of the display window
	 * 
	 * @param type:       type of the button
	 * @param x:          postionX of the button center
	 * @param y:          postionY of the button center
	 * @param processing: processing PApplet object that represents the display
	 *                    window
	 */
	public AddAnimalButton(String type, float x, float y, JunglePark park) {
		super(x, y, park); // call the superclass constructor
		this.type = type.toLowerCase(); // transfer the type String to its lower case
		this.label = "Add " + type; // set the button label to "Add" + type
	}

	/**
	 * called each time the mouse is Pressed. If mouse is over the button when
	 * clicked, create different type of animals according to the type field, add it
	 * to the listGUI
	 */
	@Override
	public void mousePressed() {
		if (isMouseOver()) {
			switch (type) {
			case "tiger":
				// create a new Tiger and add it to the JunglePark
				processing.listGUI.add(new Tiger(processing));
				break;
			case "deer":
				// create a new Deer and add it to the JunglePark
				processing.listGUI.add(new Deer(processing));
				break;
			}
		}
	}
}
