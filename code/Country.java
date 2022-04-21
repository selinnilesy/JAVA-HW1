import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Country extends Entity {
    // TODO
    // Country image is 150 x 150
    // Name RGB --> Black
    // Worth RGB --> Blue
    // Cash RGB --> (0, 100, 0)
    // Gold RGB --> Yellow
    // Happiness RGB --> (180, 0, 0)
    private int worth, cash, gold;
    private double happiness;
    public String name, label;
    private BufferedImage image = null;
    // this is a 2-way has-relationship between country and orders.
    // even if the order is destructed later on, its country field's reference
    // will be kept in Common and country object will not be destroyed prematurely.
    private List<Order> orders = null;
    private static final Color nameColor = new Color(0, 0, 0);
    private static final Color worthColor = new Color(0, 0, 255);
    private static final Color cashColor = new Color(0, 100, 0);
    private static final Color goldColor = new Color(255, 255, 0);
    private static final Color happinessColor = new Color(180, 0, 0);
    private static final Font font = new Font("Verdana", Font.BOLD, 16);

    // position countries in GUI based on initOrder
    public Country(String name, int initOrder, String label){
        super(Common.getWindowWidth()*initOrder/7,Common.getWindowHeight()*3/5);
        this.name=name;
        this.worth=8750;
        this.happiness=50.0;
        this.gold = 50;
        this.cash=5000;
        this.label = label;
        // generate order randomly at a rate of 1000*5=5000msecs (5secs)
        // or 100*5=500 msecs (0.5sec) at fastest.
        this.orders = new ArrayList<>();
        System.out.println("Position Country-" + name + " " + position.getIntX() + "," + position.getIntY() );
    }
    @Override
    public void draw(Graphics2D g2d) {
        try{
            image = new BufferedImage(150,150, TYPE_INT_RGB);
            image = ImageIO.read( new File(System.getProperty("user.dir") + "/../images/" + this.name + ".png"));
        }

        catch(Exception e){
            System.out.println(System.getProperty("user.dir") + "../images/" + this.name + ".png" + "image cannot be read. " );
        }
        //System.out.println("image width: " + image.getWidth(null));
        g2d.drawImage(image, this.position.getIntX(), this.position.getIntY(), 150, 150, null);
        // draw text
        g2d.setColor(this.nameColor);
        g2d.setFont(this.font);
        g2d.drawString(String.format("%s", this.name), position.getIntX()+50, position.getIntY()+150+30);
        g2d.setColor(this.worthColor);
        g2d.drawString(String.format("Worth: %s$", this.worth), position.getIntX(), position.getIntY()+150+60);
        g2d.setColor(this.cashColor);
        g2d.drawString(String.format("Cash: %s$", this.cash), position.getIntX(), position.getIntY()+150+90);
        g2d.setColor(this.goldColor);
        g2d.drawString(String.format("Gold: %s", this.gold), position.getIntX(), position.getIntY()+150+120);
        g2d.setColor(this.happinessColor);
        g2d.drawString(String.format("Happiness: %.1f", this.happiness), position.getIntX(), position.getIntY()+150+150);
        // draw orders
        for(Order x : this.orders){
            x.draw(g2d);
        }

    }

    public void removeOrder(Order x){
        this.orders.remove(x);
    }
    @Override
    public void step() {

        // if an order is already caught while stepping corporations, corporation state handle it
        // and invoke this class to discard its position in the orders array.
        // therefore, no need to double check here again.
        for (int i = 0; i < orders.size(); i++){
            Order x = orders.get(i);
            x.step();
            if(x.position.getY() < Common.horizontalLineY) {
                orders.remove(x);
                // execute order.
                x.execute();
                // if any corporation is chasing it, handle
                for(Corporation corp : Common.getCorporations()){
                    // here i deliberately check reference equality, not values.
                    // if true, reference equality will yield
                    // this order is being chased by this unknown state
                    // (i set them not a value but reference to be able to constantly follow the position).
                    if(corp.getState().getDestination()==x.getPosition()){
                        // therefore, i use down casting.
                        ((ChaseClosest) corp.getState()).stopChasing(corp.getPosition());
                    }
                }
                // destroy hit object
                x=null;
                // decrement loop counter
                i--;
            }
        }
    }
    // only country class edit its below fields.
    // this indicates only orders (which has countries)
    // edit these fields amongst other classes.
    public void addOrder(Order order) {this.orders.add(order);}
    public double getHappiness() { return this.happiness;}
    public void changeHappiness(double x) {this.happiness+=x;}
    public void changeCash(int x) {this.cash+=x; updateWorth();}
    public void changeGold(int x) {this.gold+=x; updateWorth();}
    public void updateWorth() {this.worth=(int) (this.cash+this.gold*Common.getGoldPrice().getCurrentPrice());}
    public List<Order> getOrders() {return this.orders;}
}