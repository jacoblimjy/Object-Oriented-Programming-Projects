abstract class Event {

    protected final Customer customer;
    protected final double eventTime;
    protected final boolean pending;

    Event(Customer customer, double eventTime) {
        this.customer = customer;
        this.eventTime = eventTime;
        this.pending = false;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getEventTime() {
        return this.eventTime;
    }

    public boolean getPending() {
        return false;
    }

    public int customerServed() {
        return 0;
    }

    public double getWaitingTime() {
        return 0.0;
    }

    public abstract Pair<PQ<Event>, Shop> generate(PQ<Event> pq, Shop shop);

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime);
    }
}
