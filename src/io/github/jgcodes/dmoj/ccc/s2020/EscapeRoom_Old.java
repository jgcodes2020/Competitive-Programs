package io.github.jgcodes.dmoj.ccc.s2020;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class EscapeRoom_Old {
  private static int m, n;
  private static int[][] room;
  private static boolean[] visited = new boolean[1000005];

  public static void main(String[] args) {
    // input
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      m = Integer.parseInt(readIn.readLine());
      n = Integer.parseInt(readIn.readLine());
      room = new int[m + 5][n + 5];

      for (int i = 0; i < m; i++) {
        /*int[] values = Arrays.stream(readIn.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        room[i] = values;*/
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println(depthFirst(room[0][0])? "yes" : "no");
  }

  private static List<Integer> findValidValues(int k) {
    List<OldIntPair> result = new ArrayList<>();
    for (int i = 2; i < sqrt(k); i++) {
      if (k % i == 0) {
        result.add(new OldIntPair(i, k / i));
        if (i != k / i)
          result.add(new OldIntPair(k / i, i));
      }
    }
    result.add(new OldIntPair(1, k));
    result.add(new OldIntPair(k, 1));

    return result
      .stream().filter(pair -> pair.x <= m && pair.y <= n)
      .map(pair -> room[pair.y - 1][pair.x - 1])
      .collect(Collectors.toUnmodifiableList());
  }

  private static boolean depthFirst(int start) {
    visited[start] = true;

    if (start == n * m) return true;

    List<Integer> next = findValidValues(start);
    boolean flag = false;
    for (int i: next) {
      if (!visited[i]) flag = depthFirst(i);
      if (flag) break;
    }

    return flag;
  }
}

class OldIntPair {
  public final int x;
  public final int y;

  OldIntPair(int x, int y) {
    this.x = x;
    this.y = y;
  }
}