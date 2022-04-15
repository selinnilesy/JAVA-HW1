public class Shake extends State {
    Shake(int currentState, double x, double y) {
        super(currentState, "Shake");
        this.destination = new Position(0,0);
        setNewDestination(x,y);
        this.speed=  2;
    }

    @Override
    public void setNewDestination(double x, double y){
        double change = Common.getRandomGenerator().nextDouble() * 1 + 2;
        change = Common.getRandomGenerator().nextBoolean() ? change :  -change;
        this.destination.setX(x + change);
        System.out.println("X set to: " +  this.destination.getX() );
        change = Common.getRandomGenerator().nextDouble() * 1 + 2;
        change = Common.getRandomGenerator().nextBoolean() ? change : -change;
        this.destination.setY(y + change );
        System.out.println("Y set to: " +  this.destination.getY() );
        this.speed= 2;
    }
}