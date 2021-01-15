package io.github.jgcodes.dmoj.complete.dmopc.o2019c6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import static java.math.BigInteger.ONE;

public class Grade11Math {
  private static final BigInteger MODULUS = BigInteger.valueOf(1000000007);
  public static void main(String[] args) {
    // input
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      final String[] firstLine = readIn.readLine().split(" ");
      final int sLength = Integer.parseInt(firstLine[0]);
      final int m = Integer.parseInt(firstLine[1]);

      BigInteger s = new BigInteger(readIn.readLine(), 2);
      for (int i = 0; i < m; i++) {
        String line = readIn.readLine();
        int cutoff = line.indexOf(' ');
        BigInteger a = ONE.shiftLeft(sLength - Integer.parseInt(line.substring(0, cutoff)) + 1).subtract(ONE);
        BigInteger b = ONE.shiftLeft(sLength - Integer.parseInt(line.substring(cutoff + 1))).subtract(ONE);
        s = s.or(a.andNot(b));
        System.out.println(s.mod(MODULUS));
      }
    } catch (IOException e) {
      System.err.println("i/o error, how?");
    }
  }
}