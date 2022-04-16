import java.awt.*;
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
    private final Font font = new Font("Verdana", Font.BOLD, 16);


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
        int randomState = (int) (Common.getRandomGenerator().nextDouble() * (3) + 0.5);
        // since state is given abstract and adding a class to HW is forbidden,
        // i cannot create a factory to generate state objects by invoking
        // generate methods of corresponding factories but rather mess with ifs.
        if(randomState==0) this.state = new Rest(randomState, this.position.getX(), this.position.getY());
        if(randomState==1) this.state = new Shake(randomState, this.position.getX(), this.position.getY());
        if(randomState==2) this.state = new GotoXY(randomState);
        if(randomState==3) this.state = new ChaseClosest(randomState);
        System.out.println("Position Corporation-" + name + " " + position.getIntX() + "," + position.getIntY() );
    }

    private final Color cashColor = new Color(180, 0, 0);

    @Override
    public void draw(Graphics2D g2d) {
        try{
            image = new BufferedImage(100,100, TYPE_INT_RGB);
            image = ImageIO.read( new File(System.getProperty("user.dir") + "/../images/" + this.name + ".png"));
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
        g2d.setColor(Color.RED);
        g2d.drawString(String.format("%s", this.cash), position.getIntX()+20, position.getIntY()+100+50);
        // stockname
        g2d.setColor(Color.BLACK);

        g2d.drawString(String.format("%s", this.stockName), position.getIntX()+20, position.getIntY()-9);
    }

    @Override
    public void step() {

        this.state.lifeTime++;
        /*
        if(this.state.lifeTime==this.state.stateCounter){
            this.state.lifeTime=0;
            int randomState = (int) (Common.getRandomGenerator().nextDouble() * (3));
            if (randomState != this.state.currentState) {
                // destroy previous state to prevent memleak by notifying GC
                this.state = null;

                // since state is given abstract and adding a class to HW is forbidden,
                // i cannot create a factory to generate state objects by invoking
                // generate methods of corresponding factories but rather mess with ifs.
                if (randomState == 0) this.state = new Rest(randomState, this.position.getX(), this.position.getY());
                if (randomState == 1) this.state = new Shake(randomState, this.position.getX(), this.position.getY());
                if (randomState == 2) this.state = new GotoXY(randomState);
                if (randomState == 3) this.state = new ChaseClosest(randomState);
            }
        }

        else{
        */

            // shake, goto , chase, even rest have a destination.
            // for the rest case, it is set equal to its own position.
            // if state is smth else like goto, chase it is set accordingly in the state class.
            if(Common.destinationReached(this.position, this.state.destination)){
                     (this.state).setNewDestination(this.position.getX(), this.position.getY());
            }
            // move content of corporation (entity) object on gui based on speed and relative distance.
            Common.moveContent(this, this.position, this.state.destination, this.state.speed);
    }
}