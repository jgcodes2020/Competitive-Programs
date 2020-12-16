package io.github.jgcodes.uhscs;

import java.util.*;

public class FindDuplicate {
  public static void main(String[] args) {
    int[] list = new int[] {14, 19, 9, 10, 11, 12, 6, 12, 19, 1, 19, 11, 2, 4, 3, 10, 10, 9, 11, 5, 19, 5, 4, 20, 10, 12, 9, 18, 8, 20};

    //alg
    Arrays.sort(list);

    boolean flag = false;
    for (int i = 0; i < list.length; i++) {
      if (i > 0 && list[i] == list[i - 1]) {
        flag = true;
        break;
      }
    }
    System.out.println(flag);
  }
}
