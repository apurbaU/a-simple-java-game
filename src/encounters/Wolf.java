package encounters;
import functionalities.*;
import player.*;
public class Wolf extends Encounter {

    public Wolf(Coordinates coordinates) {

        super("Wolf", coordinates, "hear distant howling.");

    }

    @Override
    public void printEncounterDetails()
    {
        System.out.println("You are attacked by wolves!");
    }

    @Override
    public void onPlayerEnter(Player player) {
        System.out.println("Oh no! You sustained 20 damage to your health.");
        player.takeDamage(20);
    }

    @Override
    public EncounterType getType(){
        return EncounterType.WOLF;
    }

    @Override
    public String getEncounterSymbol() {
        return "\uD83D\uDC3A";
    }

}

