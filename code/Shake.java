public class Shake extends State {
    Shake(int currentState) {
        super(currentState, "Shake");
        this.destination = new Position(0,0);
        this.destination.setX( (double) (Math.random() * (20 - 3) + 3));
        // consider countries positioned at 3/5 of the height. do not pass them below.
        this.destination.setY( (double) (Math.random() * (20-3) + 3) );
    }

    // TODO
    @Override
    public void move(){

    }
}