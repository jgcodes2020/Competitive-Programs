package io.github.jgcodes.dmoj.ccc.j2020;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class DogTreats {
  public static void main(String[] args) throws Throwable {
    final int s, m, l;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      s = parseInt(readIn.readLine());
      m = parseInt(readIn.readLine());
      l = parseInt(readIn.readLine());
    }
    System.out.println(((s + 2 * m + 3 * l) >= 10)? "happy" : "sad");
  }
}
