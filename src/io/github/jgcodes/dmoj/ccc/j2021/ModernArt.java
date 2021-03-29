package io.github.jgcodes.dmoj.ccc.j2021;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class ModernArt {
  // micro optimization: only compile the regex once
  private static final Pattern splitter = Pattern.compile(" ");
  static boolean[] rows, columns;

  public static void main(String[] args) throws Exception {
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      // use booleans, since 2 brushes = 0 brushes
      columns = new boolean[parseInt(readIn.readLine())];
      rows = new boolean[parseInt(readIn.readLine())];

      final int count = parseInt(readIn.readLine());
      // if we're dealing with only 1 cell,
      // the cell will always be flipped with each brush
      // so its value will be number of brushes mod 2
      if (columns.length == rows.length && columns.length == 1) {
        System.out.println(count % 2);
        return;
      }

      // count number of row and column flips, since order doesn't matter
      for (int i = 0; i < count; i++) {
        String[] tokens = splitter.split(readIn.readLine());

        switch (tokens[0]) {
          case "R" -> rows[parseInt(tokens[1]) - 1] ^= true;
          case "C" -> columns[parseInt(tokens[1]) - 1] ^= true;
        }
      }
    }

    // count number of flipped columns and flipped rows
    int countC = 0, countR = 0;
    for (boolean c: columns) if (c) countC++;
    for (boolean r: rows) if (r) countR++;
    // cool formula
    System.out.println(
      // if only considering columns, how many would be gold?
      (countC * rows.length +
        // if only considering rows, how many would be gold?
        countR * columns.length) -
        // subtract 2 for each overlap, since we counted each overlap twice
        // and we don't want to count them at all
        (countC * countR * 2)
    );
  }
}
