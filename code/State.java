import java.awt.*;

public abstract class State {
    // TODO
    // Rest(0), Shake(1), GotoXY(2), ChaseClosest(3);
    private int currentState;
    protected int speed;
    protected Position destination;
    protected final String name;
    protected Corporation corporation;
    public int stateCounter, lifeTime;
    protected static Color stateColor = new Color(0, 0, 255);
    State(int currentState, String name, Corporation corpo) {
        this.currentState = currentState;
        this.name = name;
        this.corporation = corpo;
        this.stateCounter=(int) (Common.getRandomGenerator().nextDouble()*(100));
    }
    public int getSpeed(){ return this.speed;}
    public Position getDestination(){ return this.destination;}
    public abstract void setNewDestination(double x, double y);

    public abstract boolean destinationReached();
}