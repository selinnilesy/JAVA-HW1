import java.awt.*;

public abstract class State {
    // TODO
    // Rest(0), Shake(1), GotoXY(2), ChaseClosest(3);
    public int currentState, speed;
    protected Position destination;
    protected final String name;
    public int stateCounter, lifeTime;
    protected static Color stateColor = new Color(0, 0, 255);
    State(int currentState, String name) {
        this.currentState = currentState;
        this.name = name;
        this.stateCounter=(int) (Common.getRandomGenerator().nextDouble()*(100));
        this.destination = null;
        this.speed=1;
    }
    public int getState(){ return this.currentState;}
    public abstract void move();
}