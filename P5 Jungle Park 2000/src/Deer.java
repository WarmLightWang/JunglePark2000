/**
 * This class Deer inherits all the features of its superclass Animal, and
 * overrides some of them. Also this class create some new methods which are
 * only belong to Deer class. The class has following methods and functions in
 * turn:
 * 
 * @constructor Deer: initialize a new deer object within a specific processing
 *              object.
 * @method scanForThreat: called by the action method to detect if any tiger is
 *         within the range. if there is any, return true, if not, return false
 * @method action: called constantly. If any time a tiger is near a deer, a
 *         threat message will be displayed.
 */
public class Deer extends Animal {
	private static final int SCAN_RANGE = 175; // scan range area to check for a threat in the
												// neighborhood
	private static final String IMAGE_FILE_NAME = "images/deer.png";
	private static int nextID = 1; // class variable that represents the identifier of the next deer
									// to be created

	private static final String TYPE = "DR"; // A String that represents the deer type
	private final int id; // Deer's id: positive number that represents the order of the deer

	/**
	 * Constructor that creates a new Deer object positioned at a random position of
	 * the display window
	 * 
	 * @param processing PApplet object that represents the display window
	 */
	public Deer(JunglePark processing) {
		// Set Dear drawing parameters
		super(processing, IMAGE_FILE_NAME);

		// Set Tiger identification fields
		id = nextID;
		this.label = TYPE + id; // String that identifies the current tiger
		nextID++;
	}

	/**
	 * Checks if there is a threat (a Tiger for instance) at the neighborhood
	 * scanRange is an integer that represents the range of the area to be scanned
	 * around the animal
	 * 
	 * @param int value scanRange: the range that a deer detect within to find a
	 *            tiger
	 * @return true if there is a tiger within the scanRange, false otherwise
	 */
	public boolean scanForThreat(int scanRange) {
		for (int i = 0; i < processing.listGUI.size(); i++) {
			Animal ani = (Animal) processing.listGUI.get(i);
			if (ani.label.charAt(0) == 'T' && isClose(ani, scanRange))
				return true;
		}
		return false;
	}

	/**
	 * Defines the behavior of a Deer object in the Jungle park it scan for the
	 * tiger within the default range, if detect any, a threat message will be
	 * displayed above the deer's head.
	 */
	@Override
	public void action() {
		if (scanForThreat(SCAN_RANGE)) {
			this.processing.fill(0); // specify font color: black
			this.processing.text("THREAT!", this.getPositionX(), this.getPositionY() - this.image.height / 2 - 4);
		}
	}
}
