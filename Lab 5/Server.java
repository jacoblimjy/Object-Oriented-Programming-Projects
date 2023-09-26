import java.util.function.Supplier;

class Server {
    protected final int serverNum;
    protected final Customer servingCustomer;
    protected final double nextAvailableTime;
    protected final int maxq;
    protected final ImList<Customer> queue;
    protected final Supplier<Double> restTime;

    Server(int serverNum) {
        this.serverNum = serverNum;
        Supplier<Double> supplier = () -> 0.0; 
        this.servingCustomer = new Customer(0, 0, supplier); 
        this.nextAvailableTime = 0;
        this.maxq = 0;
        this.queue = new ImList<Customer>();
        this.restTime = () -> 0.0;
    }

    Server(int serverNum, int maxq) {
        this.serverNum = serverNum;
        Supplier<Double> supplier = () -> 0.0;
        this.servingCustomer = new Customer(0, 0, supplier);
        this.nextAvailableTime = 0;
        this.maxq = maxq;
        this.queue = new ImList<Customer>();
        this.restTime = () -> 0.0;
    }


    Server(int serverNum, int maxq, Supplier<Double> restTime) {
        this.serverNum = serverNum;
        Supplier<Double> supplier = () -> 0.0;
        this.servingCustomer = new Customer(0, 0, supplier);
        this.nextAvailableTime = 0;
        this.maxq = maxq;
        this.queue = new ImList<Customer>();
        this.restTime = restTime;
    }

    Server(int serverNum, Customer servingCustomer, double nextAvailableTime,
            int maxq, ImList<Customer> queue, Supplier<Double> restTime) {
        this.serverNum = serverNum;
        this.servingCustomer = servingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxq = maxq;
        this.queue = queue;
        this.restTime = restTime;
    }

    Server chosenCounter() {
        return this;
    }

    double getRestTime() {
        return this.restTime.get();
    }

    double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    int getServerNum() {
        return this.serverNum;
    }

    int getmaxq() {
        return this.maxq;
    }

    ImList<Customer> getQueue() {
        return this.queue;
    }

    boolean getIsIdle(double eventTime) {
        return this.nextAvailableTime <= eventTime;
    }

    boolean canQueue() {
        return this.queue.size() < this.maxq;
    }

    boolean canServe(Customer newCustomer) {
        return newCustomer.getArrivalTime() >= this.nextAvailableTime;
    }

    Server serve(Customer customer, double eventTime) {
        return new Server(this.serverNum, customer, eventTime + customer.getServiceTime(), 
                this.maxq, this.queue, this.restTime);
    }

    Server assignQueue(Customer newCustomer) {
        ImList<Customer> newQueue = this.queue;
        newQueue = newQueue.add(newCustomer);
        return new Server(this.serverNum, this.servingCustomer, this.nextAvailableTime, 
                this.maxq, newQueue, this.restTime);
    }

    Server shortenQueue(Customer customer) {
        int index = this.queue.indexOf(customer);
        ImList<Customer> newQueue = this.queue.remove(index); 
        return new Server(this.serverNum, this.servingCustomer, this.nextAvailableTime, 
                this.maxq, newQueue, this.restTime);
    }

    Server updateAvailableTime(double restTime) {
        return new Server(this.serverNum, this.servingCustomer, this.nextAvailableTime + restTime, 
                this.maxq, this.queue, this.restTime);
    }

    @Override
    public String toString() {
        return "" + this.serverNum;
    }
}

