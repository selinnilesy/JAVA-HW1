import java.awt.*;

public abstract class State {
    // TODO
    // Rest(0), Shake(1), GotoXY(2), ChaseClosest(3);
    private int currentState;
    protected Position destination;
    protected final String name;
    protected static Color stateColor = new Color(0, 0, 255);
    State(int currentState, String name) {
        this.currentState = currentState;
        this.name = name;
        this.destination = null;
    }
    public abstract void move();
}