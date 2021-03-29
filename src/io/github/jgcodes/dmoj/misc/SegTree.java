package io.github.jgcodes.dmoj.misc;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class SegTree {
  private static final Pattern separator = Pattern.compile(" ");
  private static final int INF = 0x3F3F3F3F;

  private static int gcd(int a, int b) {
    int temp;
    while (b > 0) {
      temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  private static void build() {
    minTree = new int[2 * array.length];
    gcdTree = new int[2 * array.length];

    Arrays.fill(minTree, -1);
    Arrays.fill(minTree, INF);

    buildMinTree(0, 0, array.length);
  }

  private static void buildMinTree(int idx, int start, int end) {
    if (start == end) {
      minTree[idx] = start;
      return;
    }
    final int left = idx * 2, right = left + 1;
    final int mid = (start + end) / 2;
    buildMinTree(left, start, mid);
    buildMinTree(right, mid + 1, end);

    minTree[idx] = Math.min(minTree[left], minTree[right]);
  }

  private static int queryMinTree(int idx, int start, int end, int segStart, int segEnd) {
    if (end < start) throw new IndexOutOfBoundsException("wack");
    final int mid = (segStart + segEnd) / 2;
    final int left = idx * 2, right = left + 1;

    if (end < mid)
      return queryMinTree(left, start, end, segStart, segEnd);
    else if (start >= mid)
      return queryMinTree(right, start, end, segStart, segEnd);
    else {
      int a = queryMinTree(left, start, mid, segStart, mid);
      int b = queryMinTree(right, mid, end, mid, segEnd);
      return Math.min(a, b);
    }
  }

  private static int[] minTree;
  private static int[] gcdTree;
  private static int[] array;

  public static void main(String[] args) throws Exception {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      final int count;
      {
        String[] line = separator.split(br.readLine());
        count = Integer.parseInt(line[1]);
      }

      array = Arrays.stream(separator.split(br.readLine())).mapToInt(Integer::parseInt).toArray();


      for (int i = 0; i < count; i++) {

      }
    }
  }
}
