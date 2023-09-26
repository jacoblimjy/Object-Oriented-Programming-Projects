class Done extends Event {

    private final boolean pending;
    private final Server server;
    private final Server chosenCounter;

    Done(Customer customer, double eventTime, Server server, Server chosenCounter) {
        super(customer, eventTime);
        this.server = server;
        this.pending = false;
        this.chosenCounter = chosenCounter;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public int customerServed() {
        return 1;
    }

    public Pair<PQ<Event>, Shop> generate(PQ<Event> pq, Shop shop) {
        Server s = getServer();
        s = shop.getServers().get(s.getServerNum() - 1);
        s = s.updateAvailableTime(s.getRestTime());     
        shop = shop.updateServer(s);
        return new Pair<PQ<Event>, Shop>(pq, shop);
    }


    @Override
    public String toString() {
        return String.format("%.3f %s done serving by %s", 
                getEventTime(), getCustomer().toString(), this.chosenCounter.toString());
    }
}

