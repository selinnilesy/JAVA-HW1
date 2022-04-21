import java.awt.*;

public abstract class State {
    // TODO
    // Rest(0), Shake(1), GotoXY(2), ChaseClosest(3);
    // private bcs subclasses will not use.
    private int currentState;
    protected int speed;
    protected Position destination;
    protected final String name;
    protected Corporation corporation;
    protected static Color stateColor = new Color(0, 0, 255);
    State(int currentState, String name, Corporation corpo) {
        this.currentState = currentState;
        this.name = name;
        this.corporation = corpo;
    }
    public int getSpeed(){ return this.speed;}
    public int getState(){ return this.currentState;}
    public Position getDestination(){ return this.destination;}
    public abstract void setNewDestination(double x, double y);
    public abstract boolean destinationReached();
}