public class ChaseClosest extends State {
    private double speed;
    ChaseClosest(int currentState) {
        super(currentState, "ChaseClosest");
        this.destination = new Position(0,0);
        this.speed= (int) (Common.getRandomGenerator().nextDouble() * (20) + 20);
    }

    // TODO
    @Override
    public void setNewDestination(double x, double y){}
}