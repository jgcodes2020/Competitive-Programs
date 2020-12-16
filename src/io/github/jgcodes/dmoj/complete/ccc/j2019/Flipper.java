package io.github.jgcodes.dmoj.complete.ccc.j2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Flipper {
  public static void main(String[] args) {
    // input
    int[] grid = new int[] {1, 2, 3, 4};
    char[] seq;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      seq = readIn.readLine().toCharArray();
    } catch (IOException e) {
      System.err.println("i/o error, how?");
      return;
    }

    //processing
    for (char c: seq) {
      switch (c) {
        case 'H' -> {
          swap(grid, 0, 2);
          swap(grid, 1, 3);
        }
        case 'V' -> {
          swap(grid, 0, 1);
          swap(grid, 2, 3);
        }
      }
    }
    System.out.println(grid[0] + " " + grid[1]);
    System.out.println(grid[2] + " " + grid[3]);
  }

  private static void swap(int[] array, int a, int b) {
    final int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }
}