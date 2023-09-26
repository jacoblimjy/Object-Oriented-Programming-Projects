class Pending extends Event {

    private final boolean pending;
    private final Server server;

    Pending(Customer customer, double eventTime, Server server) {
        super(customer, eventTime);
        this.server = server;
        this.pending = true;
    }

    public Server getServer() {
        return this.server;
    }

    @Override
    public boolean getPending() {
        return true;
    }

    public Pair<PQ<Event>, Shop> generate(PQ<Event> pq, Shop shop) {
        Server s = getServer();
        s = shop.getServers().get(s.getServerNum() - 1);
        if (s.getIsIdle(getEventTime()) && s.getQueue().get(0) == getCustomer()) {
            Serve newServe = new Serve(getCustomer(), getEventTime(), s);
            pq = pq.add(newServe);
            return new Pair<PQ<Event>, Shop>(pq, shop);
        } else {
            Pending newPending = new Pending(getCustomer(), s.getNextAvailableTime(), s);
            pq = pq.add(newPending);
            return new Pair<PQ<Event>, Shop>(pq, shop);
        }
    }
}



