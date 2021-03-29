package io.github.jgcodes.dmoj.ccc.j2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class Art {
  public static class Point {
    public static Point valueOf(String str) {
      String[] tokens = str.split(",");
      return new Point(parseInt(tokens[0]), parseInt(tokens[1]));
    }

    public final int x;
    public final int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return this.x + "," + this.y;
    }
  }

  public static void main(String[] args) throws Throwable {
    int minX = 100, maxX = 0, minY = 100, maxY = 0;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final int count = parseInt(readIn.readLine());
      for (int i = 0; i < count; i++) {
        Point p = Point.valueOf(readIn.readLine());
        minX = Math.min(minX, p.x);
        minY = Math.min(minY, p.y);
        maxX = Math.max(maxX, p.x);
        maxY = Math.max(maxY, p.y);
      }
    }
    System.out.println(new Point(minX - 1, minY - 1).toString());
    System.out.println(new Point(maxX + 1, maxY + 1).toString());
  }
}
