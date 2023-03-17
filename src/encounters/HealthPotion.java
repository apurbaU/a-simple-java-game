package encounters;
import functionalities.*;
import player.*;
public class HealthPotion extends Encounter {

    private static int HEALTH_ADD = 15;

    private String encounterSymbol = "\uD83E\uDDEA";

    private boolean removeEncounter = false;

    private String encounterMessage = "The drink turned out to be a health potion.";
    public HealthPotion(Coordinates coordinates) {

        super("Health Potion", coordinates, "a vial of drink.");

    }

    @Override
    public void printEncounterDetails() {
        System.out.println(encounterMessage);
    }




    @Override
    public void onPlayerEnter(Player player) {
        if (removeEncounter == false){
            if (player.getHealth() == 100){
                System.out.println("Cannot use the health potion. Already at full health.");
            }
            else{
                System.out.println("You have gained 15 points to your health.");
                player.healPlayer(HEALTH_ADD);
                removeEncounter();
                removeEncounter = true;
                encounterMessage = "So empty :(";
                super.nearbyAlert = "nothing. There used to be a health potion here, but you drank it.";
            }

        }
    }

    @Override
    public EncounterType getType(){
        return EncounterType.HEALTH_POTION;
    }

    @Override
    public String getEncounterSymbol() {
        return encounterSymbol;
    }

    public void setEncounterSymbol(String symbol){
        encounterSymbol = symbol;
    }

    public void removeEncounter(){
        setEncounterSymbol("  ");

    }
}

