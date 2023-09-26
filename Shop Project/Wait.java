class Wait extends Event {

    private final boolean pending;
    private final Server server;

    Wait(Customer customer, double eventTime, Server server) {
        super(customer, eventTime);
        this.server = server;
        this.pending = false;
    }

    public Server getServer() {
        return this.server;
    }

    public Pair<PQ<Event>, Shop> generate(PQ<Event> pq, Shop shop) {
        Server s = getServer();
        s = shop.getServers().get(s.serverNum - 1);
        s = s.assignQueue(getCustomer());
        shop = shop.updateServer(s);
        pq = pq.add(new Pending(getCustomer(), getEventTime(),s));
        return new Pair<PQ<Event>, Shop>(pq, shop);
    }

    @Override
    public String toString() {
        return String.format("%.3f %s waits at %s",
                getEventTime(), getCustomer().toString(), getServer().toString());
    }
}


