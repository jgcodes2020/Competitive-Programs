package io.github.jgcodes.dmoj.wac;

import java.util.*;

public class Squirelloo {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String mn = sc.nextLine();

    int m = Integer.parseInt(mn.split(" ")[0]);
    int n = Integer.parseInt(mn.split(" ")[1]);


    ArrayList<Integer> array = new ArrayList<Integer>();

    String arrayraw[] = sc.nextLine().split(" ");
    for (int i = 0; i < arrayraw.length; i++) {
      array.add(Integer.parseInt(arrayraw[i]));

    }

    ArrayList<ArrayList<Integer>> maxmin = new ArrayList<ArrayList<Integer>>();

    //for each element in the array store the biggest element and
    // smallest that they have


    int max = array.get(m - 1);
    int min = array.get(m - 1);

    for (int i = m - 1; i >= 0; i--) {
      ArrayList<Integer> submaxmin = new ArrayList<>();

      if (array.get(i) > max) {
        max = array.get(i);

      }

      if (array.get(i) < min) {
        min = array.get(i);
      }

      submaxmin.add(min);
      submaxmin.add(max);

      maxmin.add(submaxmin);

    }


    for (int i = 0; i < m; i++) {
      int subtractor = sc.nextInt(); // length of the array
      int limit = sc.nextInt(); // number of test cases
      int high = maxmin.size() - 1;
      int low = 0;
      int mid = high / 2;

      arrayListLimits(maxmin, limit, subtractor, high, low, mid);

      // call on function, find the limit or when it exceeds the
      //limit
    }
  }

  static int arrayListLimits(ArrayList<ArrayList<Integer>> maxmin, int limit, int subtractor, int high, int low, int mid) {
    // if within limit
    if (Math.abs(subtractor - (maxmin.get(mid).get(0))) <= limit || Math.abs(subtractor - (maxmin.get(mid).get(1))) <= limit) {
      System.out.println(mid);
      // if next element exceeds limit
      if (Math.abs(subtractor - maxmin.get(mid + 1).get(0)) > limit || Math.abs(subtractor - (maxmin.get(mid + 1).get(1))) > limit) {
        return mid;
      }
      // if this is last element
      if (mid == maxmin.size() - 1) {
        return mid;
      }

      return arrayListLimits(maxmin, limit, subtractor, high, mid, (high + low) / 2);

    }
//
    if (Math.abs(subtractor - (maxmin.get(mid).get(0))) > limit || Math.abs(subtractor - (maxmin.get(mid).get(1))) > limit) {
      System.out.println(mid);

      if (mid == 0) {
        return mid;
      }

      return arrayListLimits(maxmin, limit, subtractor, mid, low, high / 2);
    }

    return -1;
  }
}

