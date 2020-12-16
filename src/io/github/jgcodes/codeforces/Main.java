package io.github.jgcodes.codeforces;

import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    // input
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final int count = Integer.parseInt(readIn.readLine());
      for (int i = 0; i < count; i++) {
        //skip a line
        readIn.readLine();
        //read and test array
        int[] array = Arrays.stream(readIn.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        test(array);
      }
    } catch (IOException e) {
      System.err.println("i/o error, how?");
      System.exit(-1);
    }
    // processing
  }
  public static void test(int[] athletes) {
    Arrays.sort(athletes);

    int pMin = 0;
    for (int i = 0; i < athletes.length - 1; i++) {
      int diff = Math.abs(athletes[i] - athletes[i + 1]);
      int minDiff = Math.abs(athletes[pMin] - athletes[pMin + 1]);
      if (diff < minDiff) pMin = i;
    }

    
  }
}