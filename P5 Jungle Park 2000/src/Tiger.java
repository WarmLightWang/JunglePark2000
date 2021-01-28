/**
 * This class Tiger inherits all the features of its superclass Animal, and
 * overrides some of them. Also this class create some new methods which are
 * only belong to Tiger class. The class has following methods and functions in
 * turn:
 * 
 * @constructor Tiger: initialize a new tiger object within a specific
 *              processing object
 * @method action: called constantly. If any time a tiger is near a deer, it
 *         hops to the deer. If any time a tiger has a deerEatenCount more than
 *         0, it will call the display count method.
 * @method displayDeerEatenCount: draw a black count number above the tiger's
 *         head.
 * @method hop: move the position of a tiger to the hopped deer's position,
 *         remove the deer, count number increase by 1, and release the mouse.
 * @method getDeerEatenCount: return the number of deer a tiger has eaten.
 *
 */
public class Tiger extends Animal {
	private static final int SCAN_RANGE = 100; // range dimension for scanning the neighborhood for food
	private static final String IMAGE_FILE_NAME = "images/tiger.png"; // load tiger image from file
	private static int nextID = 1; // class variable that represents the identifier of the next tiger
									// to be created
	// Tiger's identification fields
	private static final String TYPE = "TGR"; // A String that represents the tiger type
	private final int id; // Tiger's id: positive number that represents the order of the tiger
	private int deerEatenCount; // Number of Deers that the current tiger has eaten so far
	private int hoppedDeerIndex; // Index of the deer to be hopped by a tiger in the action method.
	// This helps action method to transfer this index without using a method parameter

	/**
	 * Creates a new Tiger object positioned at a random position of the display
	 * window
	 * 
	 * @param processing PApplet object that represents the display window
	 */
	public Tiger(JunglePark processing) {
		// Set Tiger drawing parameters
		super(processing, IMAGE_FILE_NAME);

		// Set Tiger identification fields
		id = nextID;
		this.label = TYPE + id; // String that identifies the current tiger
		nextID++;
		deerEatenCount = 0; // initialize count number to 0 when a new tiger object is created.
	}

	/**
	 * Defines the Tiger's behavior in the Jungle Park Scans for food at the
	 * neighborhood of the current tiger. If the Tiger founds any deer at its
	 * proximity, it hops on it, and eats it
	 * 
	 * Note the sequence of the actions: a for loop is used to detect the food, if
	 * any deer is found, the index of the deer will be recorded first, then the hop
	 * method will be called, so that the deer object can be deleted in the hop
	 * method more easily.
	 */
	@Override
	public void action() {
		for (int i = 0; i < processing.listGUI.size(); i++) {
			Animal ani = (Animal) processing.listGUI.get(i);
			if (ani.label.charAt(0) == 'D' && isClose(ani, SCAN_RANGE)) {
				hoppedDeerIndex = i; // Record the index of the deer that is going to be hopped.
				hop((Deer) ani);
			}
		}

		if (deerEatenCount > 0)
			displayDeerEatenCount(); // display deerEatenCount
	}

	/**
	 * Displays the number of eaten deers if any on the top of the tiger image
	 */
	public void displayDeerEatenCount() {
		this.processing.fill(0); // specify font color: black
		// display deerEatenCount on the top of the Tiger's image
		this.processing.text(deerEatenCount, this.getPositionX(), this.getPositionY() - this.image.height / 2 - 4);
	}

	/**
	 * Move the position of a tiger to the hopped deer's position, remove the deer,
	 * count number increase by 1, and release the mouse.
	 * 
	 * @param Deer type object that to be hopped by the tiger
	 */
	public void hop(Deer food) {
		processing.mouseReleased(); // once tiger hopped, set mouse status to released
		this.setPositionX(food.getPositionX()); // move the tiger to the new postion
		this.setPositionY(food.getPositionY());
		deerEatenCount++; // count plus 1
		processing.listGUI.remove(hoppedDeerIndex); // remove the deer object from list
	}

	/**
	 * @return return the number of deer a tiger has eaten.
	 */
	public int getDeerEatenCount() {
		return deerEatenCount;
	}
}
