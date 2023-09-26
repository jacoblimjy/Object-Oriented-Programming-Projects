class Leave extends Event {

    private final boolean pending;

    Leave(Customer customer, double eventTime) {
        super(customer, eventTime);
        this.pending = false;
    }

    public Pair<PQ<Event>, Shop> generate(PQ<Event> pq, Shop shop) {
        return new Pair<PQ<Event>, Shop>(pq, shop);
    }

    @Override
    public String toString() {
        return String.format("%.3f %s leaves", getEventTime(), getCustomer().toString());
    }
}

