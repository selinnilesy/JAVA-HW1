import java.awt.*;

public abstract class GoldOrder extends Order {
    // TODO
    @Override
    public abstract void draw(Graphics2D g2d);

    @Override
    public abstract void step() ;

    public abstract void buygold();
    public abstract void sellgold();

}