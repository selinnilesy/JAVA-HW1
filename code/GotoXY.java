public class GotoXY extends State {
    GotoXY(int currentState) {
        super(currentState, "GotoXY");
        this.destination = new Position(0,0);
        this.destination.setX((double) ((Math.random() * (Common.windowWidth-100 - 0)) + 0));
        // consider countries positioned at 3/5 of the height. do not pass them below.
        this.destination.setY(((double) ((Math.random() * (Common.getWindowHeight()*3/5 - Common.horizontalLineY)) + Common.horizontalLineY)));
    }

    // TODO
    @Override
    public void move(){

    }
}