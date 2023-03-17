package functionalities;

import player.*;
public class LogAnalytics implements PlayerLocationObserver {
	private static boolean connection;
	private PlayerLocation playerLocation;

	public LogAnalytics(PlayerLocation playerLocation) throws Exception {
		this.playerLocation = playerLocation;
		playerLocation.registerObserver(this);
		if (connection == false) {
			// Create connection if it has not already been created
			connection = true;
			System.out.println("Connection to the database established");
		}
		else{
			throw new Exception("Cannot create more than one connection to the database");
		}
	}

	public void logMove(Coordinates newCoordinates) {
		// Log move to database
		System.out.println("Logging: " +newCoordinates.toString());
	}

	@Override
	public void updatePlayerLocation(Coordinates newCoordinates) {
		logMove(newCoordinates);
	}
}
