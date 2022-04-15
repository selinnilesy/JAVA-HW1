import java.awt.*;

public abstract class Order extends Entity {
    // TODO
    // Order is 24 x 24
    private Color color;
    private int amount;
    public Order(){
        super(50,50);
        amount = (int) ((Common.getRandomGenerator().nextDouble() * 4) + 1);
        System.out.println("Order created with amount: "+ amount);
    }

    @Override
    public abstract void draw(Graphics2D g2d);

    @Override
    public abstract void step() ;
}