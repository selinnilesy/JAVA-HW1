import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Random;

public class Common {
    private static final String title = "Arms Race";

    private static final  Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public static final int windowWidth = (int) size.width;
    public static final int windowHeight = (int) size.height;

    //private static final int windowWidth = 1650;
    //private static final int windowHeight = 1000;

    private static final int firstVerticalLineX = windowWidth/4;
    private static final int secondVerticalLineX = windowWidth*3/4;
    public static final int horizontalLineY = 100;

    private static final Random randomGenerator = new Random(System.currentTimeMillis());

    private static final LivePrice foodPrice = new LivePrice(10, 65, "Food Products", 5, 1, 1, 10);
    private static final LivePrice electronicsPrice = new LivePrice(firstVerticalLineX+ 20, 65,"Consumer Electronics", 30, 2, 10, 50);
    private static final LivePrice goldPrice = new LivePrice(secondVerticalLineX+10, 65, "Gold", 75, 3, 50, 100);

    // getters
    public static String getTitle() { return title; }
    public static int getWindowWidth() { return windowWidth; }
    public static int getWindowHeight() { return windowHeight; }

    public static int getFirstVerticalLineX() { return firstVerticalLineX; }
    public static int getSecondVerticalLineX() { return secondVerticalLineX; }
    public static int getHorizontalLineY() { return horizontalLineY; }

    public static Random getRandomGenerator() { return randomGenerator; }

    // live prices
    public static LivePrice getFoodPrice() { return foodPrice; }
    public static LivePrice getElectronicsPrice() { return electronicsPrice; }
    public static LivePrice getGoldPrice() { return goldPrice; }

    public static Country Chile = new Country("chile", 1, "CL");
    public static Country Malaysia = new Country("malaysia", 2, "MY");
    public static Country Mexico = new Country("mexico", 3, "MX");
    public static Country Nigeria = new Country("nigeria", 4,"NG");
    public static Country Poland = new Country("poland", 5,"PL");
    public static Corporation boeing = new Corporation("boeing", "BA", 1);
    public static Corporation general_dynamics = new Corporation("general_dynamics", "GD", 2);
    public static Corporation lockheed_martin = new Corporation("lockheed_martin", "LMT", 3);
    public static Corporation northrop_grumman = new Corporation("northrop_grumman", "NOC", 4);
    public static Corporation raytheon = new Corporation("raytheon", "RTX", 5);

    // countries
     static  {
        // TODO: Here you can instantiate entities/fields

    }

    public static boolean destinationReached(Position position, Position destination){
        if (Math.abs(destination.getX()-position.getX())<0.5 && Math.abs(position.getY()-destination.getY())<0.5)
            return true;
        return false;
    }
    public static void moveContent(Entity e, Position position, Position destination, int speed){
        double normalized_disposition_x, normalized_disposition_y;
        normalized_disposition_x=normalized_disposition_y=0.0;

        double disposition_x = destination.getX()-position.getX();
        double disposition_y = destination.getY()-position.getY();
        if(disposition_x!=0 && disposition_y!=0){
            double sqrt = Math.sqrt(disposition_x * disposition_x + disposition_y * disposition_y);
            normalized_disposition_x = (disposition_x/ sqrt);
            normalized_disposition_y = (disposition_y/ sqrt);
        }
        e.position.setX(position.getX() + (normalized_disposition_x * speed));
        e.position.setY(position.getY() + (normalized_disposition_y * speed));
    }


    public static void stepAllEntities() {

        System.out.println("Step call: " );

        if (randomGenerator.nextInt(200) == 0) foodPrice.step();
        if (randomGenerator.nextInt(300) == 0) electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0) goldPrice.step();

        Mexico.step();
        Chile.step();
        Nigeria.step();
        Poland.step();
        Malaysia.step();

        boeing.step();
        general_dynamics.step();
        lockheed_martin.step();
        northrop_grumman.step();
        raytheon.step();

        // TODO: call other entities' step()
    }
}