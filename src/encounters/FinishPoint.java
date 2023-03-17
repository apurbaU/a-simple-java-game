package encounters;

import functionalities.*;

import player.*;
public class FinishPoint extends Encounter {

    public FinishPoint(Coordinates coordinates) {
        super("Finish Point", coordinates, "see a golden beam of light");
    }

    @Override
    public void printEncounterDetails()
    {
        System.out.println("You have reached your goal!");
    }

    @Override
    public void onPlayerEnter(Player player) {
        System.out.println("---------------------------");
        System.out.println("Congratulations! You found the finish point. You won the game.");
    }

    @Override
    public EncounterType getType(){
        return EncounterType.FINISH_POINT;
    }

    @Override
    public String getEncounterSymbol() {
        return "\uD83C\uDFC1";
    }

}

