package com.example.raytrace;
import java.lang.Math.*;

public class Vector {
  double x, y, z;
  public Vector() {}
  public Vector(double i, double j, double k) {
    x = i;
    y = j;
    z = k;
  }
  public double magnitude() {
    return Math.sqrt(x * x + y * y + z * z);
  }
  public void normalise() {
    double mag = magnitude();
    if (mag != 0) {
      x /= mag;
      y /= mag;
      z /= mag;
    }
  }
  public double dot(Vector a) {
    return x * a.x + y * a.y + z * a.z;
  }
  public Vector sub(Vector a) {
    return new Vector(x - a.x, y - a.y, z - a.z);
  }
  public Vector add(Vector a) {
    return new Vector(x + a.x, y + a.y, z + a.z);
  }
  public Vector mul(double d) {
    return new Vector(d * x, d * y, d * z);
  }
  public void print() {
    System.out.println("x=" + x + ", y=" + y + ", z=" + z);
  }
  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getZ() {
    return z;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setZ(double z) {
    this.z = z;
  }
}