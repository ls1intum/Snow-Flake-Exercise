package de.tum.in.ase;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        set(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Point rotate(double phi) {
        double newX = x * Math.cos(phi) - y * Math.sin(phi);
        double newY = y * Math.cos(phi) + x * Math.sin(phi);
        set(newX, newY);
        return this;
    }

    Point translate(double x, double y) {
        set(this.x + x, this.y + y);
        return this;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }
}
