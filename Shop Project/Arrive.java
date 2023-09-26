class Arrive extends Event {

    private final boolean pending;
    
    Arrive(Customer customer, double eventTime) {
        super(customer, eventTime);
        this.pending = false;
    }

    public Pair<PQ<Event>, Shop> generate(PQ<Event> pq, Shop shop) {
        if (shop.availableServer(getEventTime())) {
            Server s = shop.findServer(getEventTime());
            Serve newServe = new Serve(getCustomer(), getEventTime(), s);
            pq = pq.add(newServe);
            return new Pair<PQ<Event>, Shop>(pq, shop);
        } else if (shop.canQueue()) {
            Server s = shop.findQueue();
            Wait newWait = new Wait(getCustomer(), getEventTime(), s);
            pq = pq.add(newWait);
            return new Pair<PQ<Event>, Shop>(pq, shop);
        } else {
            Leave newLeave = new Leave(getCustomer(), getEventTime());
            pq = pq.add(newLeave);
            return new Pair<PQ<Event>, Shop>(pq, shop);
        }

    }

    @Override
    public String toString() {
        return String.format("%.3f %s arrives", getEventTime(), getCustomer().toString());
    }
}

