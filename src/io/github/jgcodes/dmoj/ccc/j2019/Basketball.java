package io.github.jgcodes.dmoj.ccc.j2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Basketball {
  public static void main(String[] args) {
    // input
    int[] apples = new int[3], bananas = new int[3];
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      for (int i = 0; i < 3; i++) {
        apples[i] = Integer.parseInt(readIn.readLine());
      }
      for (int i = 0; i < 3; i++) {
        bananas[i] = Integer.parseInt(readIn.readLine());
      }
    } catch (IOException e) {
      System.err.println("i/o error, how?");
      return;
    }
    // processing
    int tApples = apples[0] * 3 + apples[1] * 2 + apples[2];
    int tBananas = bananas[0] * 3 + bananas[1] * 2 + bananas[2];
    if (tApples < tBananas) {
      System.out.println("B");
    }
    else if (tApples == tBananas) {
      System.out.println("T");
    }
    else {
      System.out.println("A");
    }
  }
}