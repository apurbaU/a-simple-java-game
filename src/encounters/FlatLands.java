package encounters;

import functionalities.*;

import player.*;
public class FlatLands extends Encounter {

    public FlatLands(Coordinates coordinates) {

        super("Flatlands", coordinates, "boring open planes.");

    }

    @Override
    public void printEncounterDetails() {
        System.out.println("Nothing to see here.");
    }

    @Override
    public void onPlayerEnter(Player player) {
    }

    @Override
    public EncounterType getType(){
        return EncounterType.FLAT_LANDS;
    }

    @Override
    public String getEncounterSymbol() {
        return "\uD83C\uDFD6";
    }
}

