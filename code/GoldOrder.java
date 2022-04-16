import java.awt.*;

public abstract class GoldOrder extends Order {
    public GoldOrder( Country country) {
        super(country);
    }

    // TODO
    // i did not use gold order abstract class
    // as i found many common things between all orders
    // and implemented them in order.java.
    // buy and sell orders only have different execute functionalites
    // at hit time so they are implemented in their classes.
    // therefore, this class got nothing special.

    @Override
    public abstract void execute();

}