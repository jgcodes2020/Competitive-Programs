package io.github.jgcodes.dmoj;

import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int start = 0, end = 0;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      String[] firstLine = readIn.readLine().split("\s+");
      start = Integer.parseInt(firstLine[1]);
      end = Integer.parseInt(firstLine[2]);
      final int len = Integer.parseInt(firstLine[3]);

      for (int i = 0; i < len; i++) {
        String[] edge = readIn.readLine().split("\s+");
        final int a = Integer.parseInt(edge[0]), b = Integer.parseInt(edge[1]);
        graph.computeIfAbsent(a, k -> new ArrayList<>());
        graph.get(a).add(b);
        graph.computeIfAbsent(b, k -> new ArrayList<>());
      }
    }
    catch (IOException e) {
      System.err.println("I/O error");
      System.exit(2);
    }
    catch (Exception e) {
      System.err.println("Test data botched");
      System.exit(1);
    }
    int result = breadthFirst(graph, start, end);
    if (result < 0) {
      System.err.println("Goal unreachable");
      System.exit(3);
    }
    else {
      System.out.println(result);
    }
  }

  private static int breadthFirst(Map<Integer, List<Integer>> graph, int start, int end) {
    Queue<Integer> queue = new ArrayDeque<>();
    Map<Integer, Integer> traceMap = new HashMap<>();
    // time optimization: if start and end are same, no need to change lines
    if (start == end) return 0;

    queue.add(start);
    traceMap.put(start, null);
    while (!queue.isEmpty()) {
      int node = queue.remove();

      if (node == end) {
        //backtrace through the map to find the path taken
        return traceDepth(traceMap, node);
      }
      for (int child: graph.get(node)) {
        if (!traceMap.containsKey(child)) {
          traceMap.put(child, node);
          queue.add(child);
        }
      }
    }
    // if the queue empties without finding the goal it's unreachable
    return -1;
  }
  // backtraces through the "search tree" to find the length of the path
  private static int traceDepth(Map<Integer, Integer> traceMap, int goal) {
    int result = 0;
    Integer current = goal;
    for (; current != null; result++)
      current = traceMap.get(current);
    return --result;
  }
}
