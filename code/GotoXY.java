public class GotoXY extends State {
    private double speed;
    GotoXY(int currentState, Corporation corpo) {
        super(currentState, "GotoXY", corpo);
        this.destination = new Position(0,0);
        // arguments not needed this time, therefore 0.
        // I wanted to use function overload but
        // since state type cannot be known by the corporation,
        // it cannot know what set new destination function signature to call
        // when step is invoked.
        setNewDestination(0,0 );
    }

    @Override
    public void setNewDestination(double x, double y){
        double change = Common.getRandomGenerator().nextDouble() * (Common.windowWidth-100);
        this.destination.setX(change);
        // consider countries positioned at 3/5 of the height. do not pass them below.
        change = Common.getRandomGenerator().nextDouble() * ((Common.getWindowHeight()*3/5) - Common.horizontalLineY) + Common.horizontalLineY;
        this.destination.setY( change );
        this.speed= (int) (Common.getRandomGenerator().nextDouble() * (20) + 20);
    }

    @Override
    public boolean destinationReached() {
        if (Math.abs(destination.getX()-corporation.getPosition().getX())<0.5 && Math.abs(corporation.getPosition().getY()-destination.getY())<0.5)
            return true;
        return false;
    }

}