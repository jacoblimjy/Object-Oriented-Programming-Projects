class SolidCuboid extends Cuboid implements Solid {

    private final SolidImpl solidImpl;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.solidImpl = new SolidImpl(this, density);
    }

    public double mass() {
        return solidImpl.mass();
    }

    @Override
    public String toString() {
        return String.format("solid-cuboid [%.2f x %.2f x %.2f] with a mass of %.2f", 
                getHeight(), getWidth(), getLength(), this.mass());
    }
}
