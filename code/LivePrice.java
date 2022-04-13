import java.awt.*;

public class LivePrice extends Entity {
    private String name;
    private double currentPrice;

    private int maxChange;
    private int lowerLimit;
    private int upperLimit;

    private Font font = new Font("Verdana", Font.PLAIN, 30);

    public LivePrice(double x, double y, String name, double currentPrice, int maxChange, int lowerLimit, int upperLimit) {
        super(x, y);
        this.name = name;
        this.currentPrice = currentPrice;
        this.maxChange = maxChange;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public double getCurrentPrice() { return currentPrice; }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(String.format("%s: %.2f$", name, currentPrice), position.getIntX(), position.getIntY());
    }

    @Override
    public void step() {
        double change = Common.getRandomGenerator().nextDouble() * maxChange;
        currentPrice = Common.getRandomGenerator().nextBoolean() ? currentPrice + change : currentPrice - change;
        if(currentPrice < lowerLimit) currentPrice = lowerLimit;
        else if(currentPrice > upperLimit) currentPrice = upperLimit;
    }
}