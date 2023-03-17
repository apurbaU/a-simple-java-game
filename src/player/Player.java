package player;
import functionalities.*;

public class Player implements PlayerLocationObserver{
    private String name;
    private int health;
    private Coordinates location;

    private static int MAX_HEALTH = 100;

    private boolean isGameOver;

    public Player(String name, Coordinates location) {
        this.name = name;
        this.health = 100;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void healPlayer(int addHealth){
        if (health + addHealth > MAX_HEALTH){
            health = MAX_HEALTH;
        }
        else{
            health += addHealth;
        }

    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return health <= 0;
    }

    public void setGameOver(Boolean gameEnd) {
        isGameOver = gameEnd;

    }

    @Override
    public void updatePlayerLocation(Coordinates newLocation) {

    }
}
