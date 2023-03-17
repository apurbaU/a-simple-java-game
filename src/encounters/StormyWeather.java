package encounters;
import functionalities.*;
import player.*;
public class StormyWeather extends Encounter {

    public StormyWeather(Coordinates coordinates) {
        super("Stormy Weather", coordinates, "hear the sound of thunder and see flashes of lightning in the distance.");
    }

    @Override
    public void printEncounterDetails() {
        System.out.println("You are caught in a storm!");
    }

    @Override
    public void onPlayerEnter(Player player) {
        System.out.println("You struggle to make your way through the strong winds and heavy rain. Your visibility is affected.");

    }

    @Override
    public EncounterType getType() {
        return EncounterType.STORMY_WEATHER;
    }

    @Override
    public String getEncounterSymbol() {
        return "☁️";
    }
}
