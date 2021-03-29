package io.github.jgcodes.dmoj.misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Binary {
  public static void main(String... args) throws Throwable {
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final int count = parseInt(readIn.readLine());
      for (int i = 0; i < count; i++) {
        System.out.println(toBitString(parseInt(readIn.readLine())));
      }
    }
  }

  public static String toBitString(int n) {
    if (n == 0) return "0000";

    StringBuilder result = new StringBuilder(35);
    final int groups = 8 - (Integer.numberOfLeadingZeros(n) / 4);
    for (int i = 0; i < groups; i++) {
      String next4bits = Integer.toBinaryString(n & 0x0F);
      next4bits = "0".repeat(4 - next4bits.length()) + next4bits;
      result.insert(0, ' ' + next4bits);
      n = n >> 4;
    }
    return result.deleteCharAt(0).toString();
  }
}


