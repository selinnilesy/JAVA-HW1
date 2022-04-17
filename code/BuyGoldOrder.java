import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BuyGoldOrder extends GoldOrder {
    public BuyGoldOrder(Country country) {
        super(country);
        this.color = new Color(0,255,0);
    }

    // TODO
    // RGB --> (0, 200, 0)

    @Override
    public  void execute(){
        country.changeGold(this.amount );
        country.changeCash((int) (-this.amount*Common.getGoldPrice().getCurrentPrice()));
    }

    @Override
    public void corporationInteraction(Corporation corp){
        this.country.changeCash((int) (-this.amount*Common.getGoldPrice().getCurrentPrice()));
        this.country.changeHappiness(-this.amount*0.1);
        corp.changeCash((int) (this.amount*Common.getGoldPrice().getCurrentPrice()));
        setBadgesToCorporation(corp);
    }


}