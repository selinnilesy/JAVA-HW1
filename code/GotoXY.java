public class GotoXY extends State {
    private double speed;
    GotoXY(int currentState) {
        super(currentState, "GotoXY");
        this.destination = new Position(0,0);
        setNewDestination( );
        this.speed= (int) (Common.getRandomGenerator().nextDouble() * (20) + 20);
        System.out.println("Speed of state gotoxy: %s" + speed);
    }

    @Override
    public void setNewDestination(){
        double change = Common.getRandomGenerator().nextDouble() * (Common.windowWidth-100);
        this.destination.setX(change);
        // consider countries positioned at 3/5 of the height. do not pass them below.
        change = Common.getRandomGenerator().nextDouble() * ((Common.getWindowHeight()*3/5) - Common.horizontalLineY) + Common.horizontalLineY;
        this.destination.setY( change );
        this.speed= (int) (Common.getRandomGenerator().nextDouble() * (20) + 20);
    }
    @Override
    public void setNewDestination(double x, double y){}

    // TODO
    @Override
    public void move(){

    }
}