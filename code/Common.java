import java.awt.*;
import java.io.*;
import java.util.ArrayList;
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

    private static List<Country> countries = new ArrayList<>(5);
    private static List<Corporation> corporations = new ArrayList<>(5);

    // countries
     static  {
        // TODO: Here you can instantiate entities/fields
        Country Chile = new Country("chile", 1, "CL");
        Country Malaysia = new Country("malaysia", 2, "MY");
        Country Mexico = new Country("mexico", 3, "MX");
        Country Nigeria = new Country("nigeria", 4,"NG");
        Country Poland = new Country("poland", 5,"PL");
        Corporation boeing = new Corporation("boeing", "BA", 1);
        Corporation general_dynamics = new Corporation("general_dynamics", "GD", 2);
        Corporation lockheed_martin = new Corporation("lockheed_martin", "LMT", 3);
        Corporation northrop_grumman = new Corporation("northrop_grumman", "NOC", 4);
        Corporation raytheon = new Corporation("raytheon", "RTX", 5);

        countries.add(Mexico);
        countries.add(Chile);
        countries.add(Poland);
        countries.add(Nigeria);
        countries.add(Malaysia);

        corporations.add(boeing);
        corporations.add(general_dynamics);
        corporations.add(lockheed_martin);
        corporations.add(northrop_grumman);
        corporations.add(raytheon);

    }

    // return Euclidian distance between entities
    public static double getDistance(Position position1, double x, double y){
        return Math.sqrt( Math.pow(position1.getX() - x, 2) + Math.pow(position1.getY()-y, 2) );
    }
    public static List<Country> getCountries(){
         return countries;
     }
    public static List<Corporation> getCorporations(){
        return corporations;
    }
    // since i did not prefer writing the same move code for all classes extending Entity,
    // i globalized it here.
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
        if (randomGenerator.nextInt(200) == 0) foodPrice.step();
        if (randomGenerator.nextInt(300) == 0) electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0) goldPrice.step();

        // TODO: call other entities' step()

        for(Country x : Common.getCountries())
            x.step();

        for(Corporation x : Common.getCorporations())
            x.step();
    }
}