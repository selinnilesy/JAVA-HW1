import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Order extends Entity {
    // TODO
    // Order is 24 x 24
    protected Color color;
    protected Country country;
    protected int amount;
    protected int speed;
    protected Position destination;
    // country's positions to spawn an Order on GUI.
    public Order(Country country){
        super(country.position.getX()+ Common.getRandomGenerator().nextDouble() * 150, country.position.getY() - 20);
        this.country=country;
        this.amount = (int) ((Common.getRandomGenerator().nextDouble() * 4) + 1.5);
        System.out.println("Order created with amount: "+ amount);
        this.speed = (int) (Common.getRandomGenerator().nextDouble() * (70) + 15);
        this.destination = new Position(
                Common.getRandomGenerator().nextInt(
                        (int) (-country.position.getX()),
                        (int) (Common.getWindowWidth()-country.position.getX())
                ),
                -country.position.getY()
        );
    }

    // as color is a field, common function for all subclasses.
    public void draw(Graphics2D g2d){
        g2d.setColor(this.color);
        Ellipse2D.Double circle = new Ellipse2D.Double(this.getPosition().getIntX(), this.getPosition().getIntY(), 24, 24);
        g2d.fill(circle);
        g2d.setColor(Color.BLACK);
        g2d.drawString(String.format("%d",this.amount), this.getPosition().getIntX()+8,this.getPosition().getIntY()+15);
        g2d.drawString(this.country.label, this.getPosition().getIntX()+5,this.getPosition().getIntY()-5);
    }

    public void step(){
        // move content of order (entity) object on gui based on speed and relative distance.
        Common.moveContent(this, this.position, this.destination, this.speed);
    }

    public abstract void execute();
}