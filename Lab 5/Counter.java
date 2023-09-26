import java.util.function.Supplier;

class Counter extends Server {

    Counter(int serverNum) {
        super(serverNum);
    }

    Counter(int serverNum, int maxq) {
        super(serverNum, 0);
    }

    Counter(int serverNum, int maxq, Supplier<Double> restTime) {
        super(serverNum, 0, () -> 0.0);
    }

    Counter(int serverNum, Customer servingCustomer, double nextAvailableTime,
            int maxq, ImList<Customer> queue, Supplier<Double> restTime) {
        super(serverNum, servingCustomer, nextAvailableTime, 0, new ImList<Customer>(), () -> 0.0);
    }

    Counter serve(Customer customer, double eventTime) {
        return new Counter(this.serverNum, customer, eventTime + customer.getServiceTime(),
                this.maxq, this.queue, this.restTime);
    }

    Counter assignQueue(Customer newCustomer) {
        ImList<Customer> newQueue = this.queue;
        newQueue = newQueue.add(newCustomer);
        return new Counter(this.serverNum, this.servingCustomer, this.nextAvailableTime,
                this.maxq, newQueue, this.restTime);
    }

    Counter shortenQueue(Customer customer) {
        int index = this.queue.indexOf(customer);
        ImList<Customer> newQueue = this.queue.remove(index);
        return new Counter(this.serverNum, this.servingCustomer, this.nextAvailableTime,
                this.maxq, newQueue, this.restTime);
    }

    Counter updateAvailableTime(double restTime) {
        return new Counter(this.serverNum, this.servingCustomer, this.nextAvailableTime + restTime,
                this.maxq, this.queue, this.restTime);
    }

    @Override
    public String toString() {
        return "self-check " + this.serverNum;
    }
}

