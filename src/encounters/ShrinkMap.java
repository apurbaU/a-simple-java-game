package encounters;
import functionalities.*;
import player.*;
import game.OpenWorld;

public class ShrinkMap extends Encounter {

    public ShrinkMap(Coordinates coordinates) {
        super("Shrink Map", coordinates, "you see something strange.");
    }

    @Override
    public void printEncounterDetails() {
        System.out.println("You shouldn't have come here. Now the entire world is collapsing. Nothing makes sense. You don't know where you can go or where you will end up. Choose wisely.");
    }

    @Override
    public void onPlayerEnter(Player player) {
        OpenWorld.shrinkMap();
    }

    @Override
    public EncounterType getType() {
        return EncounterType.SHRINK_MAP;
    }

    @Override
    public String getEncounterSymbol() {
        return "\uD83D\uDC80";
    }

}
