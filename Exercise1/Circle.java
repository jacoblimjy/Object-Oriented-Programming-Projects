class Circle {
    private final Point centre;
    private final double radius;
    private static final double epsilon = 1E-15;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    Point getCentre() {
        return this.centre;
    }

    double getRadius() {
        return this.radius;
    }
    
    boolean contains(Point p) {
        double dx = this.centre.getX() - p.getX();
        double dy = this.centre.getY() - p.getY();
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return distance <= this.getRadius() + Circle.epsilon;
    }

    public String toString() {
        return "circle of radius " + this.radius + " centred at " + this.centre.toString();
    }
}
