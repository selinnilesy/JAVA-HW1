import java.util.List;

public class ChaseClosest extends State {
    private Order closestOrder;
    ChaseClosest(int currentState, Corporation corpo) {
        super(currentState, "ChaseClosest", corpo);
        // chase closest has a destination identical to corporation's position
        this.destination = new Position(corpo.getPosition().getX(), corpo.getPosition().getY());
        setNewDestination( this.destination.getX(),  this.destination.getY());
        // reference the picked order and follow it until it is destroyed.
        closestOrder = null;
    }

    // searches and sets closest order reference.
    // it can keep the reference null if no orders have been found.
    public boolean findClosestOrder(double x, double y) {
        double minDistance = Double.MAX_VALUE;
        boolean set = false;
        for (Country c : Common.getCountries()) {
            List<Order> orders = c.getOrders();
            for (Order o : orders) {
                if(o.chasable() == false) continue;
                double orderDistance = (Common.getDistance(o.position, x, y));
                System.out.println("Order-Corporation distance: " + orderDistance);
                if (orderDistance < minDistance) {
                    minDistance = orderDistance;
                    this.closestOrder = o;
                    set=true;
                }
            }
        }
        return set;
    }
    public void stopChasing(Position pos) {
        // destination was assigned to order's position reference,
        // now let its instantiation to be destroyed by nulling.
        this.destination = null;
        // free the object
        this.closestOrder = null;
        // its original positions to sttay still
        this.destination = new Position(pos.getX(), pos.getY() );
    }
    // TODO
    @Override
    public void setNewDestination(double x, double y){
        if(closestOrder != null) {
            System.out.println("Caught.");
            // when order is caught.
            // this is determined to be a gold order in findClosestOrder function.
            ((GoldOrder) this.closestOrder).corporationInteraction(this.corporation);
            // also warn other chasing corporations that this order is caught by this.
            // country does this as well (when order hits the horizontal)
            for(Corporation corp : Common.getCorporations()){
                if(corp!=this.corporation && corp.getState().getDestination()==this.closestOrder.getPosition()){
                    System.out.println(" and corporation is warned");
                    ((ChaseClosest) corp.getState()).stopChasing(corp.getPosition());
                }
            }
            // destroy it from the country's list.
            this.closestOrder.getCountry().removeOrder(this.closestOrder);
            // as chase state also delete its reference,
            // order object's reference counter became 0 and it is destructed.
            this.closestOrder = null;
            this.destination = null;
            boolean set = findClosestOrder( x,  y);
            this.speed = (int) (Common.getRandomGenerator().nextDouble() * (18) + 10);
            if(set == false){
                this.destination = new Position(x,y);
            }
            else{
                this.destination = closestOrder.position;
            }
        }
        else{
            // state has just initiated and looking for orders to catch
            // or already tried and could not find any order to chase yet.
            boolean set = findClosestOrder( x,  y);
            this.speed = (int) (Common.getRandomGenerator().nextDouble() * (18) + 10);
            if(set == false){
                this.destination = new Position(x,y);
            }
            else{
                this.destination = closestOrder.position;
            }
        }
    }

    // since image of a corporation is 100x100, we need to consider an order
    // to be absorbed when the image covers it from any corners.
    @Override
    public boolean destinationReached() {
        double diff_x = destination.getX()-corporation.getPosition().getX();
        double diff_y = destination.getY()-corporation.getPosition().getY();
        if(diff_x > -0.5 && diff_x < 100.5 && diff_y>-100.5 && diff_y < 0.5)
             return true;
        return false;
    }
}