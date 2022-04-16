public class Shake extends State {
    Shake(int currentState, double x, double y) {
        super(currentState, "Shake");
        this.destination = new Position(0,0);
        setNewDestination(x,y);
        this.speed= (int) (Common.getRandomGenerator().nextDouble() * 2) + 1;
    }

    @Override
    public void setNewDestination(double x, double y){
        double change = Common.getRandomGenerator().nextDouble() * 5;
        change = Common.getRandomGenerator().nextBoolean() ? change :  -change;
        this.destination.setX(x + change);
        change = Common.getRandomGenerator().nextDouble() * 5;
        change = Common.getRandomGenerator().nextBoolean() ? change : -change;
        this.destination.setY(y + change );
        this.speed= (int) (Common.getRandomGenerator().nextDouble() * 2) + 1;
    }
}