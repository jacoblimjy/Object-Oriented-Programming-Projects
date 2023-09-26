class Serve extends Event {

    private final boolean pending;
    private final Server server;

    Serve(Customer customer, double eventTime, Server server) {
        super(customer, eventTime);
        this.server = server;
        this.pending = false;
    }

    public Server getServer() {
        return this.server;
    }

    @Override
    public double getWaitingTime() {
        return getEventTime() - getCustomer().getArrivalTime();
    }

    public Pair<PQ<Event>, Shop> generate(PQ<Event> pq, Shop shop) {
        Server s = getServer();
        s = shop.getServers().get(s.serverNum - 1);
        if (s.getQueue().size() > 0) {
            s = s.shortenQueue(getCustomer());
            shop = shop.updateServer(s);
            s = s.serve(getCustomer(), getEventTime());
            shop = shop.updateServer(s);
            Done newDone = new Done(getCustomer(), s.chosenCounter().getNextAvailableTime(),
                    s);
            pq = pq.add(newDone);
            return new Pair<PQ<Event>, Shop>(pq, shop);
        } else {
            s = s.serve(getCustomer(), getEventTime());
            shop = shop.updateServer(s);
            Done newDone = new Done(getCustomer(), s.chosenCounter().getNextAvailableTime(),
                    s);
            pq = pq.add(newDone);
            return new Pair<PQ<Event>, Shop>(pq, shop);
        }
    }

    @Override
    public String toString() {
        Server counter = this.server.findCounter(this.eventTime);
        double eventTime = this.eventTime; 
        return String.format("%.3f %s serves by %s",
                eventTime, getCustomer().toString(), counter.toString());
    }
}
