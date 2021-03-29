package io.github.jgcodes.dmoj.ccc.j2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SmartFlipper {
  public static void main(String[] args) {
    boolean h = false, v = false;
    final String line;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      line = readIn.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    for (char c: line.toCharArray()) {
      switch (c) {
        case 'H' -> h = !h;
        case 'V' -> v = !v;
      }
    }
    if (h) {
      if (v) {
        System.out.println("4 3\n2 1");
      }
      else {
        System.out.println("3 4\n1 2");
      }
    }
    else {
      if (v) {
        System.out.println("2 1\n4 3");
      }
      else {
        System.out.println("1 2\n3 4");
      }
    }

  }

  private static void swap(int[] array, int a, int b) {
    final int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }
}
