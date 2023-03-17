package encounters;

import java.util.Random;

import functionalities.*;

public class EncounterFactory {

    public static Encounter createEncounter(EncounterType type, Coordinates coordinates) {
        switch (type) {
            case FLAT_LANDS:
                return new FlatLands(coordinates);
            case FINISH_POINT:
                return new FinishPoint(coordinates);
            case WOLF:
                return new Wolf(coordinates);
            case LAKE:
                return new Lake(coordinates);
            case HEALTH_POTION:
                return new HealthPotion(coordinates);
            case POISON:
                return new Poison(coordinates);
            case STORMY_WEATHER:
                return new StormyWeather(coordinates);
            case SHRINK_MAP:
                return new ShrinkMap(coordinates);
                default:
                throw new IllegalArgumentException("Not a valid encounter type");
        }
    }

    public static Encounter RandomEncounterGenerator(Coordinates coord) {
        Random random = new Random();
        boolean finishGenerated = false;

        while (true) {
            int randomInt = random.nextInt(8) + 1;
            switch (randomInt) {
                case 1:
                    return createEncounter(EncounterType.FLAT_LANDS, coord);
                case 2:
                    if (finishGenerated == false){
                        finishGenerated = true;
                        return createEncounter(EncounterType.FINISH_POINT, coord);
                    }
                case 3:
                    return createEncounter(EncounterType.WOLF, coord);
                case 4:
                    return createEncounter(EncounterType.LAKE, coord);
                case 5:
                    return createEncounter(EncounterType.HEALTH_POTION, coord);
                case 6:
                    return createEncounter(EncounterType.POISON, coord);
                case 7:
                    return createEncounter(EncounterType.STORMY_WEATHER, coord);
                case 8:
                    return createEncounter(EncounterType.SHRINK_MAP, coord);
                    default:
                        break;
            }

        }
    }
}