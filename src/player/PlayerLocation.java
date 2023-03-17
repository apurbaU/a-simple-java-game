package player;

import java.util.ArrayList;

import functionalities.*;
public class PlayerLocation {
	private Coordinates playerCoordinates;
	private int northSouthBoundary;
	private int eastWestBoundary;
	public ArrayList<PlayerLocationObserver> observers = new ArrayList<>();

	public PlayerLocation(int northSouthBoundary, int eastWestBoundary) {
		this.northSouthBoundary = northSouthBoundary;
		this.eastWestBoundary = eastWestBoundary;
		playerCoordinates = new Coordinates(0,0);
	}

	public Coordinates getCoordinates(){
		return playerCoordinates;
	}

	public void changeCoordinates(Coordinates updateCoordinates) {
		if (updateCoordinates.getNorthSouth() >= 0 && updateCoordinates.getNorthSouth() <= northSouthBoundary
				&& updateCoordinates.getEastWest() >= 0 && updateCoordinates.getEastWest() <= eastWestBoundary) {
			playerCoordinates = updateCoordinates;
			System.out.println("---------------------------");
			System.out.println("You are at location:" + playerCoordinates.toString());
			notifyObservers(playerCoordinates);
		} else {
			System.out.println("Out of bounds move attempted - position has not changed, you are at:"
					+ playerCoordinates.toString());
		}
	}

	public void registerObserver(PlayerLocationObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(PlayerLocationObserver observer) {
		observers.remove(observer);
	}

	public void notifyObservers(Coordinates newCoordinates) {
		for (PlayerLocationObserver observer : observers) {
			observer.updatePlayerLocation(newCoordinates);
		}
	}
}
