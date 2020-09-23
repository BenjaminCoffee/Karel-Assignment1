/*
 * File: VotingBox.java
 * ---------------------
 * The VotingBox class extends SuperKarel.
 * VotingBox.java is meant to run in conjunction with the VotingBox.w file.
 * The VotingBox.w simulates a ballot with partially punched holes.
 * The code within VotingBox.java allows Karel to navigate
 * through the ballot, determine whether a hole was meant to be punched,
 * and clean any remaining chads from a partially punched hole.
 */

import stanford.karel.*;

public class VotingBox extends SuperKarel {

	public void run() {
		// bring Karel to the appropriate starting point
		// (Karel starts in the bottom left corner when
		// a new world is loaded)
		// bringKarelToOneThreeAndOrientEast();
		// note, the starting point of Karel was edited
		// in VotingBox.w and this method call is now unnecessary.

		// move to the first slot
		move();

		while (frontIsClear()) {
			// if a beeper is present, continue to the next slot.
			if (beepersPresent()) {
				//moveThisMany(2);
				for(int i = 0; i < 2; i++) {
					move();
				}
			}

			if (noBeepersPresent()) {

				// checks and cleans chads and faces Karel to the east.
				cleanChads();

				// stops karel from hitting the end wall and throwing an error.
				for (int i = 0; i < 2; i++) {
					if (frontIsClear()) {
						move();
					}
				}
			}
		}
	}

	/*
	 * Moves Karel specified number of spaces in the currently facing direction.
	 * Note - Any call to this method has been commented out
	 * as we are not allowed to use any outside java code per
	 * Lecture 3(Stanford website CS106A).
	 */
	private void moveThisMany(int movements) {
		for (int i = 0; i < movements; i++) {
			move();
		}
	}

	/*
	 * Cleans all remaining chads from the current slot and faces Karel
	 * to the east.
	 */
	private void cleanChads() {
		turnLeft();
		move();

		// clear all northern chads
		while (beepersPresent()) {
			pickBeeper();
		}

		turnAround();
		//moveThisMany(2);
		for(int i = 0; i < 2; i++) {
			move();
		}

		// clear all southern chads
		while (beepersPresent()) {
			pickBeeper();
		}

		// orient Karel so that it is facing east again.
		turnAround();
		move();
		turnRight();
	}
	
	private void bringKarelToOneThreeAndOrientEast() {
		turnLeft();
		moveThisMany(2);
		turnRight();
	}

}
