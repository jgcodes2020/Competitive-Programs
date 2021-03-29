package io.github.jgcodes.dmoj.ccc.j2021;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArrangingBooks_HelpFromCindy {
  public static void main(String[] args) throws Exception {
    final char[] str;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      str = readIn.readLine().toCharArray();
    }

    int lCount = 0, mCount = 0;
    int lSwapM = 0, lSwapS = 0, mSwapL = 0, mSwapS = 0;
    // count L and M (we need not worry about S, once L and M are in place so will all S)
    for (char c: str) {
      switch (c) {
        case 'L' -> lCount++;
        case 'M' -> mCount++;
      }
    }
    // count number of swap-outs for L
    for (int i = 0; i < lCount; i++) {
      switch (str[i]) {
        case 'M' -> lSwapM++;
        case 'S' -> lSwapS++;
      }
    }
    // count number of swap-outs for M
    for (int i = lCount; i < lCount + mCount; i++) {
      switch (str[i]) {
        case 'L' -> mSwapL++;
        case 'S' -> mSwapS++;
      }
    }
    //check overlap
    final int overlap = Math.min(lSwapM, mSwapL);
    //get result
    final int result = (lSwapM + lSwapS) + (mSwapL + mSwapS) - overlap;
    System.out.println(result);
  }
}
