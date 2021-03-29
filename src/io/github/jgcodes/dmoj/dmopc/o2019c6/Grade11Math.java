package io.github.jgcodes.dmoj.dmopc.o2019c6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import static java.math.BigInteger.*;

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
        String[] line = readIn.readLine().split(" ");
        s = s.or(setMask(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        System.out.println(s.mod(MODULUS));
      }
    } catch (IOException e) {
      System.err.println("i/o error, how?");
    }
  }

  private static BigInteger setMask(int from, int to) {
    if (to < from) return setMask(to, from);
    if (from < 0) throw new IndexOutOfBoundsException();
    if (to == from) return ZERO;

    byte[] bytes = new byte[(to >> 3) + 1];
    final int modFrom = (1 << (from & 0x07)) - 1, byteFrom = from >> 3;
    final int modTo = (0xFF >> (to & 0x07)), byteTo = to >> 3;
    return ZERO;
  }
}