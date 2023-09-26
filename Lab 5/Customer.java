import java.util.function.Supplier;

class Customer {
    private final int customerNum;
    private final double arrivalTime;
    private final Supplier<Double> serviceTime;

    Customer(int customerNum, double arrivalTime, Supplier<Double> serviceTime) {
        this.customerNum = customerNum;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    int getCustomerNum() {
        return this.customerNum;
    }

    double getArrivalTime() {
        return this.arrivalTime;
    }

    double getServiceTime() {
        return this.serviceTime.get();
    }

    @Override
    public String toString() {
        return String.format("%d", this.customerNum);
    }
}




