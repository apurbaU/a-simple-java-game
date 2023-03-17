package encounters;

import functionalities.*;

import player.*;
public abstract class Encounter{
	
	Coordinates encounterCoordinates;
	String name;
	String nearbyAlert;

	public Encounter(String name, Coordinates encounterCoordinates, String nearbyAlert){
		this.name = name;
		this.encounterCoordinates = encounterCoordinates;
		this.nearbyAlert = nearbyAlert;
	}

	public String getName() {
		return name;
	}

	public Coordinates getCoordinates() {
		return encounterCoordinates;
	}

	public String getNearbyAlert() {
		return nearbyAlert;
	}
	public void printEncounterDetails()
	{
		System.out.println(name + " "+ encounterCoordinates.toString());
	}

	public abstract void onPlayerEnter(Player player);

	public abstract EncounterType getType();

	public abstract String getEncounterSymbol();

}
