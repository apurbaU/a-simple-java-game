package encounters;
import functionalities.*;
import player.*;
public class Lake extends Encounter {

    public Lake(Coordinates coordinates) {
        super("Lake", coordinates, "there is a vast expanse of water.");

    }

    @Override
    public void printEncounterDetails() {

        System.out.println("The water is icy cold!");

    }

    @Override
    public void onPlayerEnter(Player player)
    {
        System.out.println("The tempreature is going down and so is your health. You sustained 10 damage.");
        player.takeDamage(10);
    }

    @Override
    public EncounterType getType(){
        return EncounterType.LAKE;
    }

    @Override
    public String getEncounterSymbol() {
        return "\uD83C\uDF0A";
    }
}

