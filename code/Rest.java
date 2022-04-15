public class Rest extends State {
    Rest(int currentState, double x, double y) {
        super(currentState, "Rest");
        this.destination = new Position(x,y);
    }

    // TODO
    @Override
    public void setNewDestination(double x, double y){
        this.destination.setX(x);
        this.destination.setY(y);
    }
}