class Service {
    private final Loader loader;
    private final Cruise cruise;
    private static final int m1 = 60;

    public Service(Loader loader, Cruise cruise) {
        this.loader = loader;
        this.cruise = cruise;
    }

    public Loader getLoader() {
        return this.loader;
    }

    public int getServiceStartTime() {
        return this.cruise.getArrivalTime();
    }

    public int getServiceEndTime() {
        return this.getServiceStartTime() + this.cruise.getServiceTime();
    }

    public int getReadyTime() {
        return getServiceEndTime() + this.loader.extraTime();
    }

    @Override
    public String toString() {
        return this.loader.toString() + " serving " + this.cruise.toString();
    }
}

