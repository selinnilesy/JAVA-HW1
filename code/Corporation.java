import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
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

    public Corporation(String name, String stockName,  int initOrder){
        super(Common.getWindowWidth()*initOrder/7,Common.getWindowHeight()*1/5);
        this.name=name;
        this.cash=0;
        this.stockName = stockName;
        System.out.println("Position Corporation-" + name + " " + position.getIntX() + "," + position.getIntY() );
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
        // shake, goto , chase, even rest have a destination.
        // for the rest case, it is set equal to its own position.
        // if state is smth else like goto, chase it is set accordingly in the state class.
        if (this.getState().destinationReached()){
            (this.getState()).setNewDestination(this.position.getX(), this.position.getY());
            System.out.println("Obejct: " + this + " attempted to reset destination.");
        }
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