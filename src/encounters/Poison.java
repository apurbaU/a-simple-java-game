package encounters;
import functionalities.*;
import player.*;
public class Poison extends Encounter {

    private static int POISON_ADD = -15;

    private String encounterSymbol = "\uD83E\uDDEA";

    private boolean removeEncounter = false;

    private String encounterMessage = "Oh no, the drink was a poison.";
    public Poison(Coordinates coordinates) {

        super("Poison", coordinates, "a vial of drink.");

    }

    @Override
    public void printEncounterDetails() {
        System.out.println(encounterMessage);
    }

    @Override
    public void onPlayerEnter(Player player) {
        if (removeEncounter == false){
            System.out.println("You have lost 15 points from your health.");
            player.healPlayer(POISON_ADD);
            removeEncounter();
            removeEncounter = true;
            encounterMessage = "So empty :(";
            super.nearbyAlert = "nothing. There was a poison here, but you drank it.";

        }
    }

    @Override
    public EncounterType getType(){
        return EncounterType.HEALTH_POTION;
    }

    @Override
    public String getEncounterSymbol() {
        return "\uD83E\uDDEA";
    }

    public void setEncounterSymbol(String symbol){
        encounterSymbol = symbol;
    }

    public void removeEncounter(){
        setEncounterSymbol("  ");

    }
}

