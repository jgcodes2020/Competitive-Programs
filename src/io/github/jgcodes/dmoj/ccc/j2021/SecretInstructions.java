package io.github.jgcodes.dmoj.ccc.j2021;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

//J3
public class SecretInstructions {
  public static void main(String[] args) throws Throwable {
    List<String> lines = new ArrayList<>();
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      String line;
      while (!(line = readIn.readLine()).equals("99999")) {
        lines.add(line);
      }
    }

    String prevDir = "left ";

    for (String line: lines) {
      //sneaky ASCII magic, diff between number char and actual value is 48 (or 0x30)
      int sum = ((int) line.charAt(0) - 0x30) + ((int) line.charAt(1) - 0x30);
      if (sum != 0) {
        if (sum % 2 == 0) {
          prevDir = "right ";
        }
        else {
          prevDir = "left ";
        }
      }
      System.out.print(prevDir);
      System.out.println(line.substring(2));
    }
  }
}
