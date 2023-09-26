import java.util.function.Supplier;

class Server {
    protected final int serverNum;
    protected final double nextAvailableTime;
    protected final int maxq;
    protected final ImList<Customer> queue;
    protected final Supplier<Double> restTime;

    Server(int serverNum) {
        this(serverNum, 0, () -> 0.0);
    }

    Server(int serverNum, int maxq) {
        this(serverNum, maxq, () -> 0.0);
    }

    Server(int serverNum, int maxq, Supplier<Double> restTime) {
        this(serverNum, 0, maxq, new ImList<Customer>(), restTime);
    }

    Server(int serverNum, double nextAvailableTime, int maxq, 
            ImList<Customer> queue, Supplier<Double> restTime) {
        this.serverNum = serverNum;
        this.nextAvailableTime = nextAvailableTime;
        this.maxq = maxq;
        this.queue = queue;
        this.restTime = restTime;
    }

    public Server chosenCounter() {
        return this;
    }

    public Server findCounter(double eventTime) {
        return this;
    }

    public Server update(Server server) {
        return this;
    }

    public double getRestTime() {
        return this.restTime.get();
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    public ImList<Customer> getQueue() {
        return this.queue;
    }

    public boolean getIsIdle(double eventTime) {
        return this.nextAvailableTime <= eventTime;
    }

    public boolean canQueue() {
        return this.queue.size() < this.maxq;
    }

    public boolean canServe(Customer newCustomer) {
        return newCustomer.getArrivalTime() >= this.nextAvailableTime;
    }

    public Server serve(Customer customer, double eventTime) {
        return new Server(this.serverNum, eventTime + customer.getServiceTime(), 
                this.maxq, this.queue, this.restTime);
    }

    public Server assignQueue(Customer newCustomer) {
        ImList<Customer> newQueue = this.queue;
        newQueue = newQueue.add(newCustomer);
        return new Server(this.serverNum, this.nextAvailableTime, 
                this.maxq, newQueue, this.restTime);
    }

    public Server shortenQueue(Customer customer) {
        int index = this.queue.indexOf(customer);
        ImList<Customer> newQueue = this.queue.remove(index); 
        return new Server(this.serverNum, this.nextAvailableTime, 
                this.maxq, newQueue, this.restTime);
    }

    public Server updateAvailableTime(double restTime) {
        return new Server(this.serverNum, this.nextAvailableTime + restTime, 
                this.maxq, this.queue, this.restTime);
    }

    @Override
    public String toString() {
        return "" + this.serverNum;
    }
}

