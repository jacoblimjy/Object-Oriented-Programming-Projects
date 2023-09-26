class Sphere implements Shape3D {

    private final double radius;
    private static final double SPHERE_CONSTANT = 4.0 / 3.0 * Math.PI;
    private static final double SPHERE_RAD_POW = 3;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }
    
    @Override
    public double volume() {
        return SPHERE_CONSTANT * Math.pow(this.radius, SPHERE_RAD_POW);
    }

    @Override
    public String toString() {
        return String.format("sphere [%.2f]", this.radius);
    }
}
