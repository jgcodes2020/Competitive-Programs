package io.github.jgcodes.dmoj.ccc.j2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RLE {
  public static void main(String[] args) {
    // input
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final int count = Integer.parseInt(readIn.readLine());
      for (int i = 0; i < count; i++) {
        String in = readIn.readLine() + "\0";

        StringBuilder out = new StringBuilder();
        int idx = 0;

        for (int j = 0; j < in.length(); j++) {
          if (in.charAt(j) != in.charAt(idx)) {
            out.append(j - idx).append(' ').append(in.charAt(idx)).append(' ');
            idx = j;
          }
        }

        System.out.println(out.toString().stripTrailing());
      }
    } catch (IOException e) {
      System.err.println("i/o error, how?");
    }
  }
}