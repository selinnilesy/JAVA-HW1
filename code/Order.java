import java.awt.*;

public abstract class Order extends Entity {
    // TODO
    // Order is 24 x 24
    public Order(){
        super(0,0);
    }

    @Override
    public abstract void draw(Graphics2D g2d);

    @Override
    public abstract void step() ;

    public abstract void nanana() ;
}