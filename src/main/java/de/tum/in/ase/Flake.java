package de.tum.in.ase;

import java.util.*;

public class Flake {
    private final List<Point> points = new ArrayList<>();

    public Flake(Point p1, Point p2) {
        points.add(p1);
        points.add(p2);
    }

    public List<Point> getPoints() {
        return points;
    }

    public void recursiveSnow(Point startPoint, Point endPoint, int depth) {
        if (depth > 0) {
            double shiftX = startPoint.getX();
            double shiftY = startPoint.getY();
            endPoint.translate(-shiftX, -shiftY);
            double angle = Math.atan2(endPoint.getY(), endPoint.getX());
            endPoint.rotate(-angle);
            Point helperPoint = new Point(endPoint.getX() / 3.0, 0.0);
            Point mid1 = helperPoint.rotate(angle).translate(shiftX, shiftY);
            helperPoint = new Point(endPoint.getX() / 2.0, endPoint.getX() / 6.0 * Math.sqrt(3.0));
            Point mid2 = helperPoint.rotate(angle).translate(shiftX, shiftY);
            helperPoint = new Point(2.0 * endPoint.getX() / 3.0, 0.0);
            Point mid3 = helperPoint.rotate(angle).translate(shiftX, shiftY);
            points.addAll(points.indexOf(startPoint) + 1, List.of(mid1, mid2, mid3));
            endPoint.rotate(angle).translate(shiftX, shiftY);
            recursiveSnow(startPoint, mid1, depth - 1);
            recursiveSnow(mid1, mid2, depth - 1);
            recursiveSnow(mid2, mid3, depth - 1);
            recursiveSnow(mid3, endPoint, depth - 1);
        }
    }

    public void snow(int depth) {
        for (int i = 0; i < depth; i++) {
            List<Point> newPoints = new ArrayList<>();
            Point endPoint = null;
            for (int j = 0; j < points.size() - 1; j++) {
                Point startPoint = points.get(j);
                endPoint = points.get(j + 1);
                double shiftX = startPoint.getX();
                double shiftY = startPoint.getY();
                endPoint.translate(-shiftX, -shiftY);
                // rotate to horizontal
                double angle = Math.atan2(endPoint.getY(), endPoint.getX());
                endPoint.rotate(-angle);
                // compute three new mid points using rotate and then translate
                Point helperPoint = new Point(endPoint.getX() / 3.0, 0.0);
                Point mid1 = helperPoint.rotate(angle).translate(shiftX, shiftY);
                helperPoint = new Point(endPoint.getX() / 2.0, endPoint.getX() / 6.0 * Math.sqrt(3.0));
                Point mid2 = helperPoint.rotate(angle).translate(shiftX, shiftY);
                helperPoint = new Point(2.0 * endPoint.getX() / 3.0, 0.0);
                Point mid3 = helperPoint.rotate(angle).translate(shiftX, shiftY);
                // create new polygon: add 3 mid points after start point
                newPoints.addAll(List.of(startPoint, mid1, mid2, mid3));
                // undo translation and rotation
                endPoint.rotate(angle).translate(shiftX, shiftY);
            }
            newPoints.add(endPoint);
            points.clear();
            points.addAll(newPoints);
        }
    }

    @Override
    public String toString() {
        return "Flake{" + points + '}';
    }
}
