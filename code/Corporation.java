import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Corporation extends Entity {
    // TODO
    // Corporation image is 100 x 100
    // Cash RGB --> (180, 0, 0)
    // Badge is 20 x 20
    private int cash;
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
        int randomState = (int) ((Math.random() * (3 - 0)) + 0);
        // since state is given abstract and adding a class to HW is forbidden,
        // i cannot create a factory to generate state objects by invoking
        // generate methods of corresponding factories but rather mess with ifs.
        if(randomState==0) this.state = new Rest(randomState);
        if(randomState==1) this.state = new Shake(randomState);
        if(randomState==2) this.state = new GotoXY(randomState);
        if(randomState==3) this.state = new ChaseClosest(randomState);
        System.out.println("Position Corporation-" + name + " " + position.getIntX() + "," + position.getIntY() );
    }

    private final Color cashColor = new Color(180, 0, 0);

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
        g2d.setColor(Color.RED);
        g2d.drawString(String.format("%s", this.cash), position.getIntX()+20, position.getIntY()+100+50);
        // stockname
        g2d.setColor(Color.BLACK);

        g2d.drawString(String.format("%s", this.stockName), position.getIntX()+20, position.getIntY()-9);
            this.position.setX(this.position.getX() + (this.state.destination.getX()-this.position.getX())/60);
            this.position.setY(this.position.getY() + (this.state.destination.getY()-this.position.getY())/60);
    }

    @Override
    public void step() {
        // destroy previous state to prevent memleak by notifying GC
        this.state = null;
        int randomState = (int) ((Math.random() * (3 - 0)) + 0);
        // since state is given abstract and adding a class to HW is forbidden,
        // i cannot create a factory to generate state objects by invoking
        // generate methods of corresponding factories but rather mess with ifs.
        if(randomState==0) this.state = new Rest(randomState);
        if(randomState==1) this.state = new Shake(randomState);
        if(randomState==2) this.state = new GotoXY(randomState);
        if(randomState==3) this.state = new ChaseClosest(randomState);
    }
}