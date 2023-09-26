class SolidSphere extends Sphere {

    private final SolidImpl solidImpl;

    SolidSphere(double radius, double density) {
        super(radius);
        this.solidImpl = new SolidImpl(this, density);
    }

    public double mass() {
        return solidImpl.mass();
    }

    @Override
    public String toString() {
        return String.format("solid-sphere [%.2f] with a mass of %.2f", getRadius(), this.mass());
    }
}

