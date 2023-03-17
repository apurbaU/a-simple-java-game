package game;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import encounters.*;

import player.*;

import functionalities.*;
public class OpenWorld {

	private static int northSouthBoundary;
	private static int eastWestBoundary;
	private PlayerLocation playerLocation;
	private static ArrayList<Encounter> encounters = new ArrayList<Encounter>();
	private static boolean gameOver = false;

	private ArrayList<ArrayList<Integer>> gameWorld = new ArrayList<>();

	private static Player player;

	public OpenWorld() {

	}


	static void initialise() {
		for (int i = 0; i < northSouthBoundary; i++) {
			for (int j = 0; j < eastWestBoundary; j++) {
				Encounter encounter;
				if (i == 0 && j == 0) {
					encounter = new StartingPoint(new Coordinates(i, j));
				} else {
					encounter = EncounterFactory.RandomEncounterGenerator(new Coordinates(i, j));
				}
				encounters.add(encounter);

			}
		}
		Boolean finishAdded = false;
		for (Encounter encounter : encounters){
			if ((encounter instanceof FinishPoint)) {
				finishAdded = true;
			}
		}
		if(!finishAdded){
			Random random = new Random();
			int randomNumber = random.nextInt(1, encounters.size()-1);
			Coordinates randomCoord = encounters.get(randomNumber).getCoordinates();
			encounters.set(randomNumber, new FinishPoint(randomCoord));
		}

		System.out.println("---------------------------");
	}


	public static void shrinkMap() {
		northSouthBoundary -= 1;
		eastWestBoundary -= 1;
		encounters.clear(); // remove all encounters from previous map

		for (int i = 0; i < northSouthBoundary; i++) {
			for (int j = 0; j < eastWestBoundary; j++) {
				Encounter encounter;
				if (i == 0 && j == 0) {
					encounter = new StartingPoint(new Coordinates(i, j));
				} else {
					encounter = EncounterFactory.RandomEncounterGenerator(new Coordinates(i, j));
				}
				encounters.add(encounter);
			}
		}

		// Check if a finish point already exists
		boolean finishAdded = encounters.stream().anyMatch(e -> e instanceof FinishPoint);

		if (!finishAdded) {
			try {
				// Add finish point randomly to new map
				Random random = new Random();
				int randomNumber = random.nextInt(1, encounters.size() - 1);
				Coordinates randomCoord = encounters.get(randomNumber).getCoordinates();
				encounters.set(randomNumber, new FinishPoint(randomCoord));
			}
			catch (Exception e){
				System.out.println("Oh no, the world collapsed. The game is over.");
				player.setGameOver(true);
				gameOver = true;
			}
		}

	}


	public static void setGameOver(Boolean isGameOver) {
		gameOver = isGameOver;
	}

	private void playerMove(int northSouthChange, int eastWestChange) {
			int newNorthSouth = playerLocation.getCoordinates().getNorthSouth() + northSouthChange;
			int newEastWest = playerLocation.getCoordinates().getEastWest() + eastWestChange;


			if (newNorthSouth < 0 || newNorthSouth >= northSouthBoundary || newEastWest < 0 || newEastWest >= eastWestBoundary) {
				System.out.println("Invalid move. Please try again.");
				return;
			}
			else {
				playerLocation.changeCoordinates(new Coordinates(newNorthSouth, newEastWest));
				printMap();
				Encounter currentEncounter = getEncounterAtPlayerLocation();
				if (player.isGameOver() || gameOver == true) {
					System.out.println("---------------------------");
					System.out.println("Oh no, you have no health remaining. You lost the game.");
				}
				else {
				if (currentEncounter!= null){
					currentEncounter.printEncounterDetails();
					currentEncounter.onPlayerEnter(player);
					if (currentEncounter.getType().equals(EncounterType.FINISH_POINT)){
						gameOver = true;
						return;
					}
					if (currentEncounter.getType().equals(EncounterType.STORMY_WEATHER)){

					}
					else{
						System.out.println("---------------------------");
						if(currentEncounter.getType().equals(EncounterType.SHRINK_MAP)){
							printMap();
						}
						else {
							for (Encounter nearEncounter : encounters) {
								getNearbyAlerts(nearEncounter);
							}
						}
					}
					System.out.println("Current Health: " + player.getHealth());


				}


			}

			System.out.println("---------------------------");
		}

	}



	public void getNearbyAlerts(Encounter encounter) {
		int playerXCoordinate = playerLocation.getCoordinates().getEastWest();
		int playerYCoordinate = playerLocation.getCoordinates().getNorthSouth();
		int encounterXCoordinate = encounter.getCoordinates().getEastWest();
		int encounterYCoordinate = encounter.getCoordinates().getNorthSouth();

		if (isAdjacent(playerXCoordinate, encounterXCoordinate) && playerYCoordinate == encounterYCoordinate) {
			switch (playerXCoordinate - encounterXCoordinate) {
				case 1:
					System.out.println(String.format("To the west you see %s", encounter.getNearbyAlert()));
					break;
				case -1:
					System.out.println(String.format("To the east you see %s", encounter.getNearbyAlert()));
					break;
			}
		} else if (isAdjacent(playerYCoordinate, encounterYCoordinate) && playerXCoordinate == encounterXCoordinate) {
			switch (playerYCoordinate - encounterYCoordinate) {
				case 1:
					System.out.println(String.format("To the south you see %s", encounter.getNearbyAlert()));
					break;
				case -1:
					System.out.println(String.format("To the north you see %s", encounter.getNearbyAlert()));
					break;
			}

		}
	}

	public String getNearbySymbol(Encounter encounter) {
		int playerXCoordinate = playerLocation.getCoordinates().getEastWest();
		int playerYCoordinate = playerLocation.getCoordinates().getNorthSouth();
		int encounterXCoordinate = encounter.getCoordinates().getEastWest();
		int encounterYCoordinate = encounter.getCoordinates().getNorthSouth();

		if (isAdjacent(playerXCoordinate, encounterXCoordinate) && playerYCoordinate == encounterYCoordinate) {
			switch (playerXCoordinate - encounterXCoordinate) {
				case 1, -1:
					return encounter.getEncounterSymbol();
			}
		} else if (isAdjacent(playerYCoordinate, encounterYCoordinate) && playerXCoordinate == encounterXCoordinate) {
			switch (playerYCoordinate - encounterYCoordinate) {
				case 1, -1:
					return encounter.getEncounterSymbol();
			}

		}
		return null;
	}

	private boolean isAdjacent(int firstPosition, int secondPosition) {
		return Math.abs(firstPosition - secondPosition) == 1;
	}


	private Encounter getEncounterAtPlayerLocation() {
		for (Encounter encounter : encounters) {
			if (encounter.getCoordinates().getNorthSouth() == playerLocation.getCoordinates().getNorthSouth() && encounter.getCoordinates().getEastWest() == playerLocation.getCoordinates().getEastWest()) {

				return encounter;
			}
		}
		return null;
	}

	private void printMap() {
		System.out.print(" ╔ ");
		for (int i = 0; i < eastWestBoundary; i++) {
			System.out.print("════");
			if (i != eastWestBoundary - 1) {
				System.out.print(" ╦ ");
			}
		}
		System.out.println(" ╗ ");

		for (int i = northSouthBoundary - 1; i >= 0; i--) {
			for (int j = 0; j < eastWestBoundary; j++) {
				if (j == 0) {
					System.out.print(" ║ ");
				}
				if (i == playerLocation.getCoordinates().getNorthSouth() && j == playerLocation.getCoordinates().getEastWest()) {
					Encounter encounter = getEncounterAtPlayerLocation();
					if (encounter!= null){
						System.out.print("👤" + encounter.getEncounterSymbol());
						}
					else{
						System.out.print("👤🚩");
						}

				} else {
					if (i == 0 && j == 0){
						System.out.print(" 🚩 ");
					}
					else {
						boolean hasEncounter = false;
						for (Encounter encounter : encounters) {
							if (encounter.getCoordinates().getNorthSouth() == i && encounter.getCoordinates().getEastWest() == j) {
								String symbol = getNearbySymbol(encounter);
								Encounter playerEncounter = getEncounterAtPlayerLocation();
								if (symbol != null) {
									if (!playerEncounter.getType().equals(EncounterType.STORMY_WEATHER)) {
										System.out.print(" " + symbol + " ");
										hasEncounter = true;
										break;
									}
									else{
										System.out.print("");
									}
								}
							}
						}
						if (!hasEncounter) {
							System.out.print("    ");
						}
					}


				}
				if (j == eastWestBoundary - 1) {
					System.out.print(" ║ ");
				} else {
					System.out.print(" ║ ");
				}
			}
			System.out.println();

			if (i != 0) {
				System.out.print(" ╠ ");
				for (int j = 0; j < eastWestBoundary; j++) {
					System.out.print("════");
					if (j != eastWestBoundary - 1) {
						System.out.print(" ╬ ");
					}
				}
				System.out.println(" ╣ ");
			}
		}

		System.out.print(" ╚ ");
		for (int i = 0; i < eastWestBoundary; i++) {
			System.out.print("════");
			if (i != eastWestBoundary - 1) {
				System.out.print(" ╩ ");
			}
		}
		System.out.println(" ╝ ");
	}

	public void play() {
		Scanner scanner = new Scanner(System.in);
		boolean playAgain = false;

		do {
			try{
				// prompt the user to choose the grid size for the game
				System.out.println("Please choose the grid size for the game (Enter in the format n,n)");
				String gridSize = scanner.nextLine();
				String[] boundaries = gridSize.split(",");
				this.northSouthBoundary = Integer.parseInt(boundaries[0]);
				this.eastWestBoundary = Integer.parseInt(boundaries[1]);

				if(northSouthBoundary < 2 || eastWestBoundary < 2) {
					throw new IllegalArgumentException("Grid size cannot be less than 2,2.");
				}

				this.northSouthBoundary = northSouthBoundary;
				this.eastWestBoundary = eastWestBoundary;
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue; // go back to the start of the loop to prompt the user for input again
			}
			// create a new player and initialize the game state
			System.out.println("Welcome to the Open World game! What is your name?");
			String name = scanner.nextLine();
			player = new Player(name, new Coordinates(0, 0));
			this.playerLocation = new PlayerLocation(northSouthBoundary, eastWestBoundary);
			playerLocation.registerObserver(player);
			initialise();
			printMap();
			System.out.println("Welcome to the game " + name + ". You are now at co-ordinate 0, 0.");
			System.out.println("Current Health: " + player.getHealth());
			getNearbyAlerts(encounters.get(0));

			// play the game
			while (!gameOver) {
				//another approach to make the user be able to move anywhere on the grid at once
				//not used, but can be uncommented and used as desired.

				/*System.out.println("Enter the change you want in North-South direction.");
				int nsChange = Integer.parseInt(scanner.nextLine());
				System.out.println("Enter the change you want in East-West direction.");
				int ewChange = Integer.parseInt(scanner.nextLine());
				playerMove(nsChange, ewChange);*/

				System.out.println("Enter your move (north, south, east, west) or 'quit' to exit:");
				String input = scanner.nextLine().toLowerCase();
				switch (input) {
					case "north":
						playerMove(1, 0);
						break;
					case "south":
						playerMove(-1, 0);
						break;
					case "east":
						playerMove(0, 1);
						break;
					case "west":
						playerMove(0, -1);
						break;
					case "quit":
						System.out.println("Thanks for playing!");
						gameOver = true;
						break;
					default:
						System.out.println("Invalid move. Please try again.");
						break;
				}
			}

			// prompt the user to play again
			System.out.println("Do you want to play again? (y/n)");
			String playAgainInput = scanner.nextLine();
			playAgain = playAgainInput.equalsIgnoreCase("y");

			// reset the game state
			gameOver = false;
			encounters.clear(); // clear the encounters from the previous game
			playerLocation.removeObserver(player); // remove the player observer from the previous game
		} while (playAgain);

		scanner.close();
	}






	public static void main(String[]args)
	{
		OpenWorld world = new OpenWorld();
		world.play();

	}

}
