package com.example.robotChallenge;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.exception.EmptyFileException;
import com.example.robotChallenge.worker.RoversFollowingInstructionsWorker;

public class RobotChallengeApplication {
	private static RoversFollowingInstructionsWorker roversFollowingInstructions = RoversFollowingInstructionsWorker.getInstance();

	
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args[0] == null || args[0].trim().isEmpty()) {
				System.out.println("You need to specify a path!");
				return;
			} else {
				roversFollowingInstructions.dispatchData(args[0]);
			}
		} catch (EmptyFileException | BadDataEntryException e) {
			System.out.println(e.getMessage());
		}

	}

}
