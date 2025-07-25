package com.example.raytrace;

public class Sphere {

    private double r;
    public Vector cs;
    public Vector color;

    public Sphere(double radius, Vector cs, Vector color) {
        this.r = radius;
        this.cs = cs;
        this.color = color;
    }

    public double getRadius() {
        return r;
    }

    public void setRadius(int radius) {
        this.r = radius;
    }

    public Vector getCs() {
        return cs;
    }

    public void setCs(Vector cs) {
        this.cs = cs;
    }

    public Vector getColor() {
        return color;
    }

    public void setColor(Vector color) {
        this.color = color;
    }

    public Vector intersection(int i, int j, int h, int w) {

        Vector o = new Vector();
        Vector d = new Vector(0, 0, 1);
        Vector cs = this.cs;
        double r = this.r;
        Vector p = new Vector();
        double t;
        double a, b, c;
        Vector v;
        Vector light = new Vector(200, 400, -220);
        o.x = i - 250;
        o.y = j - 250;
        o.z = -200;
        v = o.sub(cs);
        a = d.dot(d);
        b = 2 * v.dot(d);
        c = v.dot(v) - r * r;
        double disc = b * b - 4 * a * c;
        Vector col = new Vector(0, 0, 0);
        if (disc >= 0) {
            t = (-b - Math.sqrt(disc)) / (2 * a);
            p = o.add(d.mul(t));
            Vector Lv = light.sub(p);
            Lv.normalise();
            Vector n = p.sub(cs);
            n.normalise();
            double dp = Lv.dot(n);
            if (dp >= 0) {
                col = color.mul(dp);
            }
        }

        return col;
    }
}