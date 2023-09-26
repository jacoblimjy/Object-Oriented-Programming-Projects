import java.util.function.Supplier;

class SelfCheckout extends Server {

    private final ImList<Counter> counters;
    private final Counter chosenCounter;

    SelfCheckout(int serverNum, ImList<Counter> counters) {
        super(serverNum);
        this.counters = counters;
        this.chosenCounter = new Counter(0);
    }

    SelfCheckout(int serverNum, int maxq, ImList<Counter> counters) {
        super(serverNum, maxq);
        this.counters = counters;
        this.chosenCounter = new Counter(0);
    }

    SelfCheckout(int serverNum, int maxq, Supplier<Double> restTime, ImList<Counter> counters) {
        super(serverNum, maxq, () -> 0.0);
        this.counters = counters;
        this.chosenCounter = new Counter(0);
    }

    SelfCheckout(int serverNum, Customer servingCustomer, double nextAvailableTime,
            int maxq, ImList<Customer> queue, Supplier<Double> restTime, ImList<Counter> counters) {
        super(serverNum, servingCustomer, nextAvailableTime, maxq, queue, () -> 0.0);
        this.counters = counters;
        this.chosenCounter = new Counter(0);
    }

    SelfCheckout(int serverNum, Customer servingCustomer, double nextAvailableTime,
            int maxq, ImList<Customer> queue, Supplier<Double> restTime, 
            ImList<Counter> counters, Counter chosenCounter) {
        super(serverNum, servingCustomer, nextAvailableTime, maxq, queue, () -> 0.0);
        this.counters = counters;
        this.chosenCounter = chosenCounter;
    }

    Counter chosenCounter() {
        return this.chosenCounter;
    }

    @Override 
    boolean getIsIdle(double eventTime) {
        for (Counter c: this.counters) {
            if (c.getIsIdle(eventTime)) {
                return true;
            }
        } 
        return false;
    }

    SelfCheckout serve(Customer customer, double eventTime) {
        for (Counter c: this.counters) {
            if (c.getIsIdle(eventTime)) {
                int index = this.counters.indexOf(c);
                c = c.serve(customer, eventTime);
                double nextAvailableTime = c.getNextAvailableTime(); 
                for (Counter counter: this.counters) {
                    if (counter.getNextAvailableTime() < c.getNextAvailableTime()) {
                        nextAvailableTime = counter.getNextAvailableTime(); 
                    }
                }
                return new SelfCheckout(this.serverNum, customer, nextAvailableTime, 
                        this.maxq, this.queue, this.restTime, this.counters.set(index,c), c);
            }
        }
        return this;
    }

    SelfCheckout assignQueue(Customer newCustomer) {
        ImList<Customer> newQueue = this.queue;
        newQueue = newQueue.add(newCustomer);
        return new SelfCheckout(this.serverNum, this.servingCustomer, this.nextAvailableTime, 
                this.maxq, newQueue, this.restTime, this.counters, this.chosenCounter);
    } 

    SelfCheckout shortenQueue(Customer customer) {
        int index = this.queue.indexOf(customer);
        ImList<Customer> newQueue = this.queue.remove(index); 
        return new SelfCheckout(this.serverNum, this.servingCustomer, this.nextAvailableTime, 
                this.maxq, newQueue, this.restTime, this.counters, this.chosenCounter);
    }

    SelfCheckout updateAvailableTime(double restTime) {
        return new SelfCheckout(this.serverNum, this.servingCustomer, 
                this.nextAvailableTime + restTime, this.maxq, this.queue, this.restTime, 
                this.counters, this.chosenCounter);
    }

    @Override
    public String toString() {
        return "self-check " + 1;
    }
}

