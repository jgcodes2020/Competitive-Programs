package io.github.jgcodes.dmoj.ccc.j2021;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

//J4
public class ArrangingBooks {
  public static void main(String[] args) throws Throwable {
    final char[] str;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      str = readIn.readLine().toCharArray();
    }

    int countL = 0, endM = 0;
    for (char c: str) {
      switch (c) {
        case 'L':
          countL++;
          //fall through because endM = countM + countL
        case 'M':
          endM++;
      }
    }

    int swaps = 0;
    for (int i = 0; i < countL; i++) {
      switch (str[i]) {
        case 'M' -> {
          swaps++;
          for (int j = countL; j < str.length; j++) {
            if (str[j] == 'L') {
              char temp = str[i];
              str[i] = str[j];
              str[j] = temp;
            }
          }
        }
        case 'S' -> {
          swaps++;
          for (int j = str.length - 1; j >= countL; j--) {
            if (str[j] == 'L') {
              char temp = str[i];
              str[i] = str[j];
              str[j] = temp;
            }
          }
        }
      }
    }
    for (int i = countL; i < endM; i++) {
      if (str[i] == 'S') {
        swaps++;
      }
    }
    System.out.println(swaps);
  }
}
