public class Rest extends State {
    Rest(int currentState, Corporation corpo) {
        super(currentState, "Rest", corpo);
        this.destination = new Position(corpo.getPosition().getX(), corpo.getPosition().getY());
    }

    // TODO
    @Override
    public void setNewDestination(double x, double y){
        this.destination.setX(x);
        this.destination.setY(y);
    }
    @Override
    public boolean destinationReached() {
        if (Math.abs(destination.getX()-corporation.getPosition().getX())<0.5 && Math.abs(corporation.getPosition().getY()-destination.getY())<0.5)
            return true;
        return false;
    }
    @Override
    public void changeDestination(double x, double y) {
    }
}