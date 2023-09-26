import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int numOfSelfChecks;
    private final int maxq;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTime;
    private final Supplier<Double> restTime;

    Simulator(int numOfServers, int numOfSelfChecks, int maxq, ImList<Double> arrivalTimes, 
            Supplier<Double> serviceTime, Supplier<Double> restTime) {
        this.numOfServers = numOfServers;
        this.numOfSelfChecks = numOfSelfChecks;
        this.maxq = maxq; 
        this.arrivalTimes = arrivalTimes;
        this.serviceTime = serviceTime;   
        this.restTime = restTime;
    }

    public String simulate() {
        int customerServed = 0;
        int customerNotServed = 0;
        double waitingTime = 0;
        ImList<Server> servers = new ImList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            servers = servers.add(new Server(i + 1, this.maxq, this.restTime));
        }
        /*ImList<Counter> counters = new ImList<Counter>();
        for (int i = 0; i < numOfSelfChecks; i++) {
            int index = numOfServers + 1;
            counters = counters.add(new Counter(index + i, this.maxq));
        }
        if (numOfSelfChecks > 0) {
            SelfCheckout selfCheckout = new SelfCheckout(numOfServers + 1, this.maxq,
                this.restTime, counters);
            servers = servers.add(selfCheckout);
        }*/

        Shop shop = new Shop(servers);

        ImList<Customer> customers = new ImList<Customer>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            customers = customers.add(new Customer(i + 1, arrivalTimes.get(i),
                        serviceTime));
        }

        PQ<Event> pq = new PQ<Event>(new EventComparator());
        for (Customer c: customers) {
            pq = pq.add(new Arrive(c, c.getArrivalTime()));
        }

        String result = "";
        while (!pq.isEmpty()) {
            Pair<Event, PQ<Event>> pair = pq.poll();
            Event event = pair.first();
            pq = pair.second();
            customerServed += event.customerServed();
            customerNotServed += event.customerNotServed(); 
            waitingTime += event.getWaitingTime();
            if (!event.getPending()) { 
                result += event.toString() + "\n";
            }
            Pair<PQ<Event>, Shop> generation = event.generate(pq, shop);
            pq = generation.first();
            shop = generation.second();
        }

        double averageWaitingTime = (customerServed == 0) ? 0.000 : (waitingTime / customerServed);

        return result + String.format("[%.3f %s %s]", averageWaitingTime, 
                customerServed, customerNotServed);
    }
}
