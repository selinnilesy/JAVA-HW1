public class Shake extends State {
    Shake(int currentState, Corporation corpo) {
        super(currentState, "Shake", corpo);
        this.destination = new Position(0,0);
        setNewDestination(corpo.getPosition().getX(),corpo.getPosition().getY());
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

    @Override
    public boolean destinationReached() {
        if (Math.abs(this.destination.getX()-this.corporation.getPosition().getX())<0.5 && Math.abs(this.corporation.getPosition().getY()-this.destination.getY())<0.5)
            return true;
        return false;
    }
    @Override
    public void changeDestination(double x, double y) {}
}