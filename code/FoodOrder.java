import java.awt.*;
import java.awt.geom.Ellipse2D;

public class FoodOrder extends Order {
    public FoodOrder(Country country) {
        super( country);
        this.color = new Color(255,255,0);
    }

    // TODO
    // RGB --> (245, 222, 179)

    @Override
    public  void execute(){
        country.changeHappiness(this.amount * 0.2);
        country.changeCash((int) (-this.amount*Common.getFoodPrice().getCurrentPrice()));
    }

}