import java.util.List;

public class ChaseClosest extends State {
    private Order closestOrder;
    ChaseClosest(int currentState, Corporation corpo) {
        super(currentState, "Chase", corpo);
        // chase closest has a destination identical to corporation's position
        this.destination = new Position(corpo.getPosition().getX(), corpo.getPosition().getY());
        setNewDestination( this.destination.getX(),  this.destination.getY());
        // reference the picked order and follow it until it is destroyed.
        closestOrder = null;
        // to keep track of any closest order
    }

    // searches and sets closest order reference.
    // it keeps the closestOrder reference null if no orders found.
    public boolean findClosestOrder(double x, double y) {
        double minDistance = Double.MAX_VALUE;
        boolean set = false;
        for (Country c : Common.getCountries()) {
            List<Order> orders = c.getOrders();
            for (Order o : orders) {
                if(o.chasable() == false) continue;
                double orderDistance = (Common.getDistance(o.position, x, y));
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
    public void changeDestination(double x, double y) {
        if(findClosestOrder(x,y)){
            this.destination = closestOrder.position;
        }
    }
    @Override
    public void setNewDestination(double x, double y){
        if(closestOrder != null) {
            System.out.println("Caught.");
            // when order is caught.

            // as chase state also delete closestOrder's reference,
            // order object's reference counter will become 0 and be destructed by GC.
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
            // or already tried and could not find any order to chase yet (when there are no gold orders roaming around)
            // or its order has been absorv
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
        // state dest represents reference assignment of order's current position variable
        // leave an acceptable 20 padding-distance between objects
        double diff_x = destination.getX()-corporation.getPosition().getX();
        double diff_y = destination.getY()-corporation.getPosition().getY();
        if(diff_x < -1.0 && diff_x > -20 && diff_y > -120 && diff_y < 20) return true;
        else if(diff_x > -20  && diff_x < 120 && diff_y > -120 && diff_y < 20) return true;
        return false;
    }
}