package encounters;
import functionalities.*;
import player.*;
public class StartingPoint extends Encounter {

    public StartingPoint(Coordinates coordinates) {
        super("Starting Point", coordinates, "see the starting point.");
    }

    @Override
    public void printEncounterDetails()
    {
        System.out.println("You are back at the starting point. Are you trying to run away? Make a move.");
    }

    @Override
    public void onPlayerEnter(Player player) {
    }

    @Override
    public EncounterType getType(){
        return EncounterType.STARTING_POINT;
    }

    @Override
    public String getEncounterSymbol() {
        return "🚩";
    }

}

