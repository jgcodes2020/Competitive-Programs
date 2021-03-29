package io.github.jgcodes.dmoj.ccc.j2021;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

//J2
public class SilentAuction {
  public static void main(String[] args) throws Throwable {
    String[] names; int[] bids;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final int count = parseInt(readIn.readLine());
      names = new String[count]; bids = new int[count];
      for (int i = 0; i < count; i++) {
        names[i] = readIn.readLine();
        bids[i] = parseInt(readIn.readLine());
      }
    }
    if (bids.length <= 1) {
      System.out.println(names[0]);
      return;
    }
    int maxIndex = 0;
    for (int i = 1; i < bids.length; i++) {
      if (bids[i] > bids[maxIndex]) {
        maxIndex = i;
      }
    }
    System.out.println(names[maxIndex]);
  }
}
