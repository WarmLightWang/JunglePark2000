import java.util.ArrayList;

/**
 * This class JunglePark inherits all the features of its superclass PApplet,
 * and overrides some of them. Also this class create some new methods. The
 * class has following methods and functions in turn:
 * 
 * @method setup: CallBack method Defines initial environment property such as
 *         screen size and to load background images and fonts as the program
 *         starts Initializes the backgroundImage and listGUI instance fields.
 * @method settings: Sets the size of the application display window
 * @method draw: Callback method called in an infinite loop. It draws the Jungle
 *         Park's window display count number increase by 1, and release the
 *         mouse.
 * @method mousePressed: Callback method called each time the user presses the
 *         mouse
 * @method mouseReleased: Callback method called each time the mouse is released
 * @method keyPressed: Callback method called each time the user presses a key
 * @method clear: Removes all the animals from the park.
 * @method main: This main method starts the application.
 */
public class JunglePark extends PApplet {
	private PImage backgroundImage; // PImage object that represents the background image
	protected ArrayList<ParkGUI> listGUI; // ArrayList storing the current graphical objects (animals
	// present in the Jungle Park and buttons)
	protected AddAnimalButton tigerButton; // Claim a tigerButton object, waiting to be initialized in the constructor
	protected AddAnimalButton deerButton; // Claim a deerButton object, waiting to be initialized in the constructor
	protected ClearButton clearButton; // Claim a clearButton object, waiting to be initialized in the constructor

	/**
	 * CallBack method Defines initial environment properties such as screen size
	 * and to load background images and fonts as the program starts Initializes the
	 * backgroundImage and listGUI instance fields.
	 */
	@Override
	public void setup() {
		this.getSurface().setTitle("Jungle Park"); // Displays text in the title of the display window
		this.textAlign(PApplet.CENTER, PApplet.CENTER); // Sets the current alignment for drawing text
														// to CENTER
		this.imageMode(PApplet.CENTER); // Sets the location from which images are drawn to CENTER
		this.rectMode(PApplet.CORNERS); // Sets the location from which rectangles are drawn.
		// rectMode(CORNERS) interprets the first two parameters of rect() method as the
		// location of one
		// corner, and the third and fourth parameters as the location of the opposite
		// corner.
		// rect() method draws a rectangle to the display window
		this.focused = true; // Confirms that our Processing program is "focused," meaning that
		// it is active and will accept mouse or keyboard input.
		backgroundImage = this.loadImage("images/background.png"); // load the background image

		listGUI = new ArrayList<ParkGUI>(); // create the listGUI ArrayList that would store all the
		// graphic objects (animals and buttons) that would be drawn on the display
		// window
		tigerButton = new AddAnimalButton("Tiger", 43, 16, this);
		deerButton = new AddAnimalButton("Deer", 129, 16, this);
		clearButton = new ClearButton(215, 16, this);
	}

	/**
	 * Sets the size of the application display window
	 */
	@Override
	public void settings() {
		size(800, 632); // sets the size of the display window to 800 x 632 pixels
	}

	/**
	 * Callback method called in an infinite loop. It draws the Jungle Park's window
	 * display
	 */
	@Override
	public void draw() {
		// Set the color used for the background of the Processing window
		this.background(245, 255, 250); // Set the mint cream color background
		this.image(backgroundImage, this.width / 2, this.height / 2); // draw the background image at the center of the
																		// display window
		// traverse the tigers array and draw each stored tiger
		tigerButton.draw();
		deerButton.draw();
		clearButton.draw();
		for (int i = 0; i < listGUI.size(); i++)
			listGUI.get(i).draw();
	}

	/**
	 * Callback method called each time the user presses the mouse
	 */
	@Override
	public void mousePressed() {
		// traverse listGUI and call mousePressed() of the first graphical object which
		// the mouse is over
		tigerButton.mousePressed();
		deerButton.mousePressed();
		clearButton.mousePressed();
		for (int i = 0; i < listGUI.size(); i++)
			if (listGUI.get(i).isMouseOver()) {
				listGUI.get(i).mousePressed();
				break;
			}
	}

	/**
	 * Callback method called each time the mouse is released
	 */
	@Override
	public void mouseReleased() {
		// traverse listGUI and call mouseReleased() method defined for every graphic
		// object
		for (int i = 0; i < listGUI.size(); i++)
			listGUI.get(i).mouseReleased();
	}

	/**
	 * Callback method called each time the user presses a key
	 */
	@Override
	public void keyPressed() {

		switch (Character.toUpperCase(this.key)) {
		case 'T': // add new tiger to the Jungle Park
			listGUI.add(new Tiger(this));
			break;
		case 'D': // add new tiger to the Jungle Park
			listGUI.add(new Deer(this));
			break;
		case 'R': // remove an animal from the Jungle Park if the mouse is over it
			// traverse the listGUI list and consider only animal objects to be removed if
			// any
			for (int i = 0; i < listGUI.size(); i++) {
				if (listGUI.get(i) instanceof Animal && listGUI.get(i).isMouseOver()) {
					listGUI.remove(i);
					break; // remove the first animal which the mouse is over it while the r-key is pressed
				}
			}
		}
	}

	/**
	 * Removes all the animals from the park
	 */
	public void clear() {
		for (int i = 0; i < listGUI.size(); i++) {
			if (listGUI.get(i) instanceof Animal) {
				listGUI.remove(i);
				i--;
			}
		}
	}

	/**
	 * This main method starts the application
	 */
	public static void main(String[] args) {
		// starts the application (calls PApplet main() method with the name of the
		// PApplet class to run as parameter)
		PApplet.main("JunglePark");
	}
}
