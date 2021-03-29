package io.github.jgcodes.cf.uhscco1;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class TerryAndTriangle {
  public static void main(String[] args) throws Throwable {
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final int count = parseInt(readIn.readLine());
      for (int i = 0; i < count; i++) {
        // space separate + convert to array
        int[] nums = Arrays.stream(
          readIn.readLine().split(" ")
        ).mapToInt(Integer::parseInt).toArray();
        System.out.printf("%d %d %d\n", nums[0], nums[2], nums[2]);
      }
    }
  }
}
