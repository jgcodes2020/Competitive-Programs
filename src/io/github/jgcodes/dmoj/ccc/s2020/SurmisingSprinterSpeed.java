package io.github.jgcodes.dmoj.ccc.s2020;

import java.io.*;
import java.util.*;

public class SurmisingSprinterSpeed {
  public static void main(String[] args) {
    // input
    List<Observation> input = new ArrayList<>();
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      int n = Integer.parseInt(readIn.readLine());
      for (int i = 0; i < n; i++) {
        String[] obsData = readIn.readLine().split(" ");
        if (obsData.length != 2) {
          throw new RuntimeException("Observation inconsistent");
        }

        input.add(new Observation(Integer.parseInt(obsData[0]), Integer.parseInt(obsData[1])));
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return;
    }
    // processing
    if (input.size() < 2) {
      System.err.println("Not enough data");
      return;
    }

    Collections.sort(input);

    double max = Double.MIN_VALUE;
    for (int i = 1; i < input.size(); i++) {
      double speed = input.get(i).calcSpeed(input.get(i - 1));
      if (speed > max) max = speed;
    }

    System.out.println(max);
  }
}

class Observation implements Comparable<Observation> {
  private final int time;
  private final int pos;

  public Observation(int time, int pos) {
    this.time = time;
    this.pos = pos;
  }

  public double calcSpeed(Observation that) {
    double displacement = this.pos - that.pos;
    double time = this.time - that.time;
    return Math.abs(displacement / time);
  }

  @Override
  public int compareTo(Observation that) {
    return Integer.compare(this.time, that.time);
  }
}