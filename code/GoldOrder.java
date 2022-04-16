import java.awt.*;

public abstract class GoldOrder extends Order {
    public GoldOrder(Position pos, String label) {
        super(pos, label);
    }

    // TODO
    public abstract void buygold();
    public abstract void sellgold();

}