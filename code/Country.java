import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
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
    private String name;
    private BufferedImage image = null;

    private final Color nameColor = new Color(0, 0, 0);
    private final Color worthColor = new Color(0, 0, 255);
    private final Color cashColor = new Color(0, 100, 0);
    private final Color goldColor = new Color(255, 255, 0);
    private final Color happinessColor = new Color(180, 0, 0);
    private final Font font = new Font("Verdana", Font.BOLD, 16);

    // position countries in GUI based on initOrder
    public Country(String name, int initOrder){
        super(Common.getWindowWidth()*initOrder/7,Common.getWindowHeight()*3/5);
        this.name=name;
        this.worth=8750;
        this.happiness=50.0;
        this.gold = 50;
        this.cash=5000;
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
        g2d.drawString(String.format("Happiness: %s%%", this.happiness), position.getIntX(), position.getIntY()+150+150);
    }

    @Override
    public void step() {

    }
}