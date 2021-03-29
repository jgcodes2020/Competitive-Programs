package io.github.jgcodes.dmoj.ccc.j2021;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

//J1
public class BoilingWater {
  public static void main(String[] args) throws Throwable {
    final int b;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      b = parseInt(readIn.readLine());
    }
    final int p = 5 * b - 400;
    System.out.println(p);
    System.out.println((p > 100)? 1 : ((p == 100)? 0 : -1));
  }
}
