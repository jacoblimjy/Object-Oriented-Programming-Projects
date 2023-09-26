class BigCruise extends Cruise { 
    private final int lengthOfCruise;
    private final int numOfPassengers;
    private static final int m1 = 39;
    private static final int m2 = 40;
    private static final int m3 = 49;
    private static final int m4 = 50;

    public BigCruise(String id, int arrivalTime, int lengthOfCruise, int numOfPassengers) {
        super(id, arrivalTime, (lengthOfCruise + m1) / m2, (numOfPassengers + m3) / m4);
        this.lengthOfCruise = lengthOfCruise;
        this.numOfPassengers = numOfPassengers;
    }
}

