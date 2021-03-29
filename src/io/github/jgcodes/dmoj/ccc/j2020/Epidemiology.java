package io.github.jgcodes.dmoj.ccc.j2020;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Epidemiology {
  public static void main(String[] args) throws Throwable {
    int p, n, r;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      p = parseInt(readIn.readLine());
      n = parseInt(readIn.readLine());
      r = parseInt(readIn.readLine());
    }
    int days = 0, next = n * r;
    while (n <= p) {
      n += next;
      next *= r;
      days += 1;
    }
    System.out.println(days);

  }
}
