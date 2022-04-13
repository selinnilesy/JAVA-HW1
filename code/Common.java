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

    private static final Random randomGenerator = new Random(1234);

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

    public static Country Chile = new Country("chile", 1);
    public static Country Malaysia = new Country("malaysia", 2);
    public static Country Mexico = new Country("mexico", 3);
    public static Country Nigeria = new Country("nigeria", 4);
    public static Country Poland = new Country("poland", 5);
    public static Corporation boeing = new Corporation("boeing", "BA", 1);
    public static Corporation general_dynamics = new Corporation("general_dynamics", "GD", 2);
    public static Corporation lockheed_martin = new Corporation("lockheed_martin", "LMT", 3);
    public static Corporation northrop_grumman = new Corporation("northrop_grumman", "NOC", 4);
    public static Corporation raytheon = new Corporation("raytheon", "RTX", 5);

    // countries
     static  {
        // TODO: Here you can instantiate entities/fields

    }


    public static void stepAllEntities() {
        if (randomGenerator.nextInt(200) == 0) foodPrice.step();
        if (randomGenerator.nextInt(300) == 0) electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0) goldPrice.step();
        if (randomGenerator.nextInt(500) == 0) Chile.step();
        if (randomGenerator.nextInt(600) == 0) Malaysia.step();
        if (randomGenerator.nextInt(700) == 0) Mexico.step();

        // TODO: call other entities' step()
    }
}