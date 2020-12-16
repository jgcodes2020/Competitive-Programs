package io.github.jgcodes.dmoj.complete.ccc.j2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Decompress {
  public static void main(String[] args) {
    // input
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final int count = Integer.parseInt(readIn.readLine());
      for (int i = 0; i < count; i++) {
        String in = readIn.readLine();
        int reps = Integer.parseInt(in.substring(0, in.length() - 2));
        char letter = in.charAt(in.length() - 1);
        for (int j = 0; j < reps; j++) {
          System.out.print(letter);
          System.out.println();
        }
      }
    } catch (IOException e) {
      System.err.println("i/o error, how?");
      return;
    }
    // processing

  }
}