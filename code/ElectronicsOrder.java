import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ElectronicsOrder extends Order {
    public ElectronicsOrder(Country country) {
        super( country);
        this.color = new Color(0,0,255);
    }

    // TODO
    // RGB --> (0, 182, 204)
    @Override
    public  void execute(){
        country.changeHappiness(this.amount * 0.4);
        country.changeCash((int) (-this.amount*Common.getElectronicsPrice().getCurrentPrice()));
    }

}