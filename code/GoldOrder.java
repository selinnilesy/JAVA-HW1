import java.awt.*;

public abstract class GoldOrder extends Order {
    public GoldOrder( Country country) {
        super(country);
    }

    // TODO

    public abstract void corporationInteraction(Corporation corp);
    public void setBadgesToCorporation(Corporation corp){
        if(corp.getCash() > 2000) corp.setBadge(true, false, false);
        if(corp.getCash() > 4000) corp.setBadge(true, true, false);
        if(corp.getCash() > 6000) corp.setBadge(true, true, true);
    }

    @Override
    public abstract void execute();

    // is it fit for hunting? yes. other order types are not.
    @Override
    public  boolean chasable(){
        return true;
    }

}