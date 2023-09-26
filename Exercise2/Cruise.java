class Cruise {
    private final String id;
    private final int arrivalTime;
    private final int serviceTime;
    private final int numOfLoadersRequired;
    private final int m1 = 60;

    public Cruise(String id, int arrivalTime, int numOfLoadersRequired, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.numOfLoadersRequired = numOfLoadersRequired;
        this.serviceTime = serviceTime;
    }

    public String getID() {
        return this.id;
    }

    public int getArrivalTime() {
        int hours = arrivalTime / 100;
        int minutes = arrivalTime % 100;
        int time = hours * m1 + minutes;
        return time;
    }

    public int getNumOfLoadersRequired() {
        return this.numOfLoadersRequired;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    @Override
    public String toString() {
        return this.id + "@" + String.format("%04d", this.arrivalTime);
    }
}
