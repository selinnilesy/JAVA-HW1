import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Common {
    private static final String title = "Arms Race";

    private static final  Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    // i designed my GUI for all screen sizes, i read them with the Dimension size object.
    // then, i positioned countries and corpos according to this width and height's proportions to the screen.
    // for example, countries are positioned at 3/5 of the height in their constructors.
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
        // initOrder is only for positioning all corpos by using a padding (next to eachother) at initialization
        // announcement about this has come on the submission date so i did not change it to assign
        // a random position between the horizontal-countries range.
        // they are always positioned (sometimes their destinations) in the horizontal-countries range.
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

        int randomState;
        // call this block once within the class creation time
        // to set state to generated corporations and display them initially.
        // in the step function below,
        // these states will be updated randomly later on.
        for(Corporation corp : Common.getCorporations()){
            randomState = (int) (Common.getRandomGenerator().nextDouble() * (3) + 0.5);
            if(randomState==0) corp.setState(new Rest(randomState, corp));
            if(randomState==1) corp.setState(new Shake(randomState, corp));
            if(randomState==2) corp.setState(new GotoXY(randomState, corp));
            if(randomState==3) corp.setState(new ChaseClosest(randomState, corp));
        }
    }

    // i did not notice this has been already implemented by the TA
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
    // i globalized it here. this function dispositions Entity object considering its current pos and dest and speed.
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
        e.getPosition().setX(position.getX() + (normalized_disposition_x * speed));
        e.getPosition().setY(position.getY() + (normalized_disposition_y * speed));
        if(e instanceof Corporation &&  ((Corporation) e).getState() instanceof GotoXY){
            System.out.println("Entity object" + ((Corporation) e).getState() + " moved to: " + e.getPosition().getX() + ","+ e.getPosition().getY() + " with speed: " + speed);
        }
    }


    public static void stepAllEntities() {
        if (randomGenerator.nextInt(200) == 0) foodPrice.step();
        if (randomGenerator.nextInt(300) == 0) electronicsPrice.step();
        if (randomGenerator.nextInt(400) == 0) goldPrice.step();

        // TODO: call other entities' step()

        // create new orders for countries and assign them
        for(Country country : Common.getCountries()){
            // do not generate orders at an extreme rate. lower the chance (95%) to keep it slow
            // that is comfortable for eye-catching of the TA.
            if(Common.getRandomGenerator().nextDouble() > 0.95) {
                Order order = null;
                if(country.getHappiness() < 50.0){
                    // keep the possibility large for non-goldorder types
                    // to generate food and electronics order more likely
                    int randomOrderType = Common.getRandomGenerator().nextInt(0,6);
                    if(randomOrderType==0) order = new BuyGoldOrder( country);
                    else if(randomOrderType==1) order = new SellGoldOrder( country);
                    else if(randomOrderType>1 && randomOrderType<4 ) order = new FoodOrder( country);
                    else if(randomOrderType>3) order = new ElectronicsOrder( country);
                }
                else{
                    boolean buy = Common.getRandomGenerator().nextBoolean();
                    if (buy) {
                        order = new BuyGoldOrder( country);
                    } else {
                        order = new SellGoldOrder( country);
                    }
                }
                country.addOrder(order);
            }
            country.step();
        }

        /*
        // create new states and assign to current states of corporations
        // (95%) is again for eye-catching.
        if(Common.getRandomGenerator().nextDouble() > 0.95) {
            for(Corporation corpo : Common.getCorporations()) {
                int randomState = (int) (Common.getRandomGenerator().nextDouble() * (3));
                // change the state to something else. otherwise, it would be harder to
                // assess by eye my homework implementation of a state such as chase.
                // it would not demonstrate its functionality properly.
                if (randomState != corpo.getState().getState()) {
                    // destroy previous state to prevent memleak by notifying GC.
                    // its country (2-way has relationship) was only a reference so nothing to do with it.
                    corpo.setState(null);
                    randomState = (int) (Common.getRandomGenerator().nextDouble() * (3) + 0.5);
                    if (randomState == 0) corpo.setState(new Rest(randomState, corpo));
                    if (randomState == 1) corpo.setState(new Shake(randomState, corpo));
                    if (randomState == 2) corpo.setState(new GotoXY(randomState, corpo));
                    if (randomState == 3) corpo.setState(new ChaseClosest(randomState, corpo));
                }
            }
        }
        */


        // absorb any orders on itself, and warn others chasing it to stop chasing and
        // countries possessing that order and dispositioning them.
        // also invoke move overridden function of each state
        for(Corporation corpo : Common.getCorporations())
            corpo.step();
    }
}