import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SellGoldOrder extends GoldOrder {
    public SellGoldOrder(Country country) {
        super(country);
        this.color = new Color(255,0,0);
    }

    // TODO
    // RGB --> (180, 0, 0)

    @Override
    public  void execute(){
        country.changeGold(-this.amount );
        country.changeCash((int) (this.amount*Common.getGoldPrice().getCurrentPrice()));
    }

    @Override
    public void corporationInteraction(Corporation corp){
        this.country.changeGold(-this.amount);
        corp.changeCash((int) (this.amount*Common.getGoldPrice().getCurrentPrice()));
        this.country.changeHappiness(-this.amount*0.1);
        setBadgesToCorporation(corp);
    }

    @Override
    public  boolean chasable(){
        return true;
    }
}