package io.github.jgcodes.dmoj.ccc.s2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bridge {
  public static void main(String[] args) {
    final int limit, count;
    final String[] names;
    final int[] speeds;

    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      limit = Integer.parseInt(readIn.readLine());
      count = Integer.parseInt(readIn.readLine());

      names = new String[count];
      speeds = new int[count];

      for (int i = 0; i < count; i++) {
        names[i] = readIn.readLine();
        speeds[i] = Integer.parseInt(readIn.readLine());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    final int[] dp = new int[count];
    Arrays.fill(dp, Integer.MAX_VALUE);
    final int[] gLen = new int[count];

    for (int i = 0; i < count; i++) {

    }
  }
}
