public class GotoXY extends State {
    private double speed;
    GotoXY(int currentState) {
        super(currentState, "GotoXY");
        this.destination = new Position(0,0);
        // arguments not needed this time, therefore 0. wanted to use function overload but
        // state type cannot be known by the corportation, which has an abstract view of
        // state
        setNewDestination(0,0 );
        this.speed= (int) (Common.getRandomGenerator().nextDouble() * (20) + 20);
        System.out.println("Speed of state gotoxy: %s" + speed);
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

}