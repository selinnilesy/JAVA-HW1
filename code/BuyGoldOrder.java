import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BuyGoldOrder extends GoldOrder {
    public BuyGoldOrder(Position pos, String label) {
        super(pos, label);
        this.color = new Color(0,255,0);
    }

    // TODO
    // RGB --> (0, 200, 0)


    @Override
    public  void buygold(){}
    @Override
    public  void sellgold(){}
}