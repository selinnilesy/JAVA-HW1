import java.awt.*;

public abstract class State {
    // TODO
    // Rest(0), Shake(1), GotoXY(2), ChaseClosest(3);
    private int currentState;
    protected int speed;
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
    public int getSpeed(){ return this.speed;}
    public abstract void setNewDestination(double x, double y);
}