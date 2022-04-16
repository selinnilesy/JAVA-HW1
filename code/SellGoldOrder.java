import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SellGoldOrder extends GoldOrder {
    public SellGoldOrder(Position pos, String label) {
        super(pos, label);
        this.color = new Color(255,0,0);
    }

    // TODO
    // RGB --> (180, 0, 0)



    @Override
    public  void sellgold(){}
    @Override
    public  void buygold(){}
}