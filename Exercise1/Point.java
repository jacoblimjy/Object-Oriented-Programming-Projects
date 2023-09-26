class Point {
    private final double x;
    private final double y;

    Point(double x, double y){
        this.x = x;
        this.y =  y;
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }

    Point midPoint(Point p) {
        double midx = (this.x + p.x) / 2;
        double midy = (this.y + p.y) / 2;
        Point midp = new Point(midx, midy);
        return midp;
    }

    double angleTo(Point p) {
        return Math.atan2(p.y - this.y, p.x - this.x);
    }

    Point moveTo(double theta, double d) {
        double newx = this.x + d * Math.cos(theta);
        double newy = this.y + d * Math.sin(theta);
        Point newp = new Point(newx, newy);
        return newp;
    }

    public String toString() {
        String result = String.format("point (%.3f, %.3f)", this.x, this.y);
        return result;
    }
}
