import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Corporation extends Entity {
    // TODO
    // Corporation image is 100 x 100
    // Cash RGB --> (180, 0, 0)
    // Badge is 20 x 20
    private int cash;
    // has relationship with state.
    private State state;
    private String name, stockName;
    private BufferedImage image;
    private boolean redBadge, whiteBadge, yellowBadge;
    private static final Font font = new Font("Verdana", Font.BOLD, 16);


    // to generate new badge each time awarded.
    private enum Badge {
        WHITE(255,255,255), YELLOW(255,255,0), RED(255,0,0);
        final Color badgecolor;
        Badge(int r, int g, int b) {
            this.badgecolor = new Color(r,g,b);
        }
    }

    // initOrder is only for positioning all corpos at initialization next to eachother
    // announcement about this has come on the submission date so i did not change it
    public Corporation(String name, String stockName,  int initOrder){
        super(Common.getWindowWidth()*initOrder/7,Common.getWindowHeight()*1/5);
        this.name=name;
        this.cash=0;
        this.stockName = stockName;
       // System.out.println("Position Corporation-" + name + " " + position.getIntX() + "," + position.getIntY() );
    }

    private static final Color cashColor = new Color(180, 0, 0);

    @Override
    public void draw(Graphics2D g2d) {
        try{
            image = new BufferedImage(100,100, TYPE_INT_RGB);
            image = ImageIO.read( new File(System.getProperty("user.dir") + "/images/" + this.name + ".png"));
        }
        catch(Exception e){
            System.out.println("image cannot be read. " );
        }
        //System.out.println("image width: " + image.getWidth(null));
        g2d.drawImage(image, position.getIntX(), position.getIntY(), 100, 100, null);
        g2d.setFont(this.font);
        // name
        g2d.setColor(State.stateColor);
        g2d.drawString(String.format("%s", this.state.name), position.getIntX()+15, position.getIntY()+100+30);
        // cash
        g2d.setColor(cashColor);
        g2d.drawString(String.format("%s", this.cash), position.getIntX()+20, position.getIntY()+100+50);
        // stockname
        g2d.setColor(Color.BLACK);
        g2d.drawString(String.format("%s", this.stockName), position.getIntX()+30, position.getIntY()-5);
        // badges
        if(this.whiteBadge){
            g2d.setColor(Color.WHITE);
            g2d.drawRect(position.getIntX()+5, position.getIntY()-40, 20,20);
            g2d.fillRect(position.getIntX()+5, position.getIntY()-40, 20,20);
        }
        if(this.yellowBadge){
            g2d.setColor(Color.YELLOW);
            g2d.drawRect(position.getIntX()+30, position.getIntY()-40, 20,20);
            g2d.fillRect(position.getIntX()+30, position.getIntY()-40, 20,20);
        }
        if(this.redBadge){
            g2d.setColor(Color.RED);
            g2d.drawRect(position.getIntX()+55, position.getIntY()-40, 20,20);
            g2d.fillRect(position.getIntX()+55, position.getIntY()-40, 20,20);
        }
    }

    @Override
    public void step() {
        // if any order has come onto my flag,consume it before moving.
        // and let corresponding country and any chasing corporations know about its destruction.
        // for loop checking each orders distance to me
        // when found, get its country.
        for(Country country : Common.getCountries()){
            // i have to use iterator to prevent runtime concurrent modification exception of the list.
            Iterator<Order> it = country.getOrders().iterator();
            while(it.hasNext()){
                Order o = (Order) it.next();
                // order has come onto me
                double diff_x = o.getPosition().getX()-this.position.getX();
                double diff_y = o.getPosition().getY()-this.position.getY();
                if(this.state instanceof Shake){
                    System.out.println("Shake corpo: " + this + "scanning orders in the environment");
                }
                // leave an acceptable 20 padding-distance between objects for float precision
                // needs to be chasable (goldorder) to  absorb
                if(o.chasable() && ((diff_x < -1.0 && diff_x > -20 && diff_y > -120 && diff_y < 20) || (diff_x > -20  && diff_x < 120 && diff_y > -120 && diff_y < 20))){
                    // execute the arms-sell and cash gain of the corporation
                    // i had to left some emthods empty bcs order type cannot be known but still only goldorders are absorbed by corps
                    // to solve that, i used chasable method to not invoke empty methods at all, but if code can be discarded as well.
                    if(this.state instanceof Shake && (o instanceof BuyGoldOrder || o instanceof SellGoldOrder)){
                        System.out.println("Shake corpo: " + this + "found goldorder on itself");
                    }
                    o.corporationInteraction(this);
                    // destroy from originating country's memory
                    it.remove();
                    //System.out.println("Order object: " + o + " has been removed from country orderlist");
                    // clean from chasing state's destination assignment (if any)
                    // i did not want to leave empty methods in polymorphism (chasing only happens to goldorder)
                    // therefore i used testing whether the order is chasable for state to quit chasing it.

                        // quit chasing  for other corporations who did not reach its order yet as order is being destroyed.
                        for(Corporation corpo : Common.getCorporations()) {
                            // to not have a memory error, process other corporations, then delete from me.
                            // based on my implementation design,
                            // position-destination are only set equal by their references if it's a chasing state.
                            // so safe to use downcasting.
                            // country calls below function as well (when order hits the horizontal)
                            if (this != corpo && corpo.getState().getDestination() == o.getPosition()) {
                                // fix corpo's destination to its position to have a new destination implicitly in the next step.
                                ((ChaseClosest) (corpo.getState())).stopChasing(corpo.getPosition());
                            }
                        }
                        // i here skipped processing this corporation's chasing possibility
                        // because I already handled in the ChaseClosest class's order-following code.
                        // (below, there is setNewDestination function overriden by each class).
                     }
             }
         }

        // shake, goto , chase, even rest have a destination.
        // for the rest case, it is set equal to its own position.
        // if state is smth else like goto, chase it is set accordingly in the state class.
        if (this.getState().destinationReached()){
            (this.getState()).setNewDestination(this.position.getX(), this.position.getY());
           // System.out.println("Obejct: " + this + " attempted to reset destination.");
        }
        //
        // if a corp with chase state, corp has to change its mind to move towards another order.
        // this could be also implemented in a move function for entities, but i did not want to use the same lines of code (for simply moving)
        // for orders and corporations with states other than chase (shake, goto.. nothing special)
        this.state.changeDestination(this.position.getX(), this.position.getY());
        // move content of corporation (entity) object on gui based on speed and relative distance.
        Common.moveContent(this, this.position, this.state.getDestination(), this.state.getSpeed());
    }

    public void setState(State x) {this.state=x;}

    public State getState(){return this.state;}

    public void changeCash(int x) {this.cash+=x;}

    public int getCash() {return cash;}

    // 0 -> white, 1->yellow, 2-> red badge
    public void setBadge(boolean w, boolean y, boolean r) {
        redBadge=r;
        yellowBadge = y;
        whiteBadge = w;
    }
}