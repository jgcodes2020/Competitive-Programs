package io.github.jgcodes.dmoj.ccc.s2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

public class SearchingStrings {

  public static Method setAdd;
  public static final Object dummy = new Object();

  public static void setSetAdd(Method m) {setAdd = m;}

  public static final int BASE = 30;
  public static final int BIAS = 12;

  public static void main(String[] args) throws Exception {
    // evil reflection magic
    setSetAdd(HashMap.class.getDeclaredMethod("putVal", Integer.TYPE, Object.class, Object.class, Boolean.TYPE, Boolean.TYPE));
    setAdd.setAccessible(true);

    // input
    String needle;
    String haystack;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      needle = readIn.readLine();
      haystack = readIn.readLine();

    } catch (IOException e) {
      System.err.println("i/o error, how?");
      return;
    }
    System.out.println(search(haystack, needle));
  }

  public static int search(String h, String n) throws Exception {
    //frequency map
    final Map<Integer, Integer> needleMap = n.chars()
      .boxed()
      .collect(Collector.of(
        HashMap<Integer, Integer>::new,
        (map, next) -> map.merge(next, 1, Integer::sum),
        (a, b) -> {
          a.putAll(b); return a;
        },
        Collections::unmodifiableMap
      ));

    HashMap<String, Object> hashed = new HashMap<>();
    int currentHash = hash(h.substring(0, n.length()));
    for (int i = 0; i < h.length() - (n.length() - 1); i++) {
      // update current hash
      if (i > 0) {
        currentHash -= hashTerm(h.charAt(i - 1), n.length() - 1);
        currentHash *= BASE;
        currentHash += hashTerm(h.charAt(i + n.length() - 1), 0);
      }
      // check if string is good
      if (startsWithPerm(needleMap, n.length(), h, i)) {
        // use evil reflection magic to provide the hash by ourselves
        setAdd.invoke(hashed, currentHash, h.substring(i, i + n.length()), dummy, false, true);
      }
    }
    return hashed.size();
  }

  public static boolean startsWithPerm(Map<Integer, Integer> nMap, int nLen, String h, int pos) {
    Map<Integer, Integer> n = new HashMap<>(nMap);
    for (int i = pos; i < pos + nLen; i++) {
      boolean[] flag = new boolean[1];
      n.compute((int) h.charAt(i), (k, v) -> {
        if (v == null) {
          flag[0] = true;
          return null;
        }
        final int diff = v - 1;
        if (diff == 0) return null;
        return diff;
      });
      if (flag[0]) return false;
    }
    return n.isEmpty();
  }

  public static int hash(String s) {
    int total = 0;
    for (int i = 0; i < s.length(); i++) {
      total += hashTerm(s, i);
    }
    return total;
  }

  public static int hashTerm(String s, int index) {
    return hashTerm(s.charAt(index), s.length() - index - 1);
  }

  public static int hashTerm(char c, int power) {
    return (BIAS + c) * (int) Math.pow(BASE, power);
  }
}