public class GotoXY extends State {
    GotoXY(int currentState, Corporation corpo) {
        super(currentState, "GotoXY", corpo);
        this.destination = new Position(corpo.getPosition().getX(), corpo.getPosition().getY());
    }

    @Override
    public void setNewDestination(double x, double y){
        System.out.println("GOTOXY object" + this + "is here: " + this.destination.getX() + ","+ this.destination.getY() + " with speed: " + this.speed);

        double newPos = Common.getRandomGenerator().nextDouble(0.0,Common.windowWidth-100-x);
        this.destination.setX(newPos);
        // consider countries positioned at 3/5 of the height. do not pass them below.
        newPos = Common.getRandomGenerator().nextDouble(Common.horizontalLineY,(Common.getWindowHeight()*(3.0/5)));
        this.destination.setY( newPos );
        this.speed= (int) (Common.getRandomGenerator().nextDouble() * (20) + 20);

        System.out.println("GOTOXY object" + this + "is set to: " + this.destination.getX() + ","+ this.destination.getY() + " with speed: " + this.speed);
    }

    @Override
    public boolean destinationReached() {
        // leave an acceptable 20 padding-distance between objects. otherwise, floating point errors occur due to precision loss.
        if (Math.abs(this.destination.getX()-this.corporation.getPosition().getX())<20 &&
                Math.abs(this.corporation.getPosition().getY()-this.destination.getY())<20){
            System.out.println("GOTOXY object: " + this +  " destinationReached returns true");
            return true;
        }
        System.out.println("GOTOXY object: " + this +  " destinationReached returns false");
        return false;
    }
    @Override
    public void changeDestination(double x, double y) {
    }

}