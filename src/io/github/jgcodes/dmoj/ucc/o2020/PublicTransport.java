package io.github.jgcodes.dmoj.ucc.o2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class PublicTransport {
  public static void main(String[] args) {
    // this is our adjacency list
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int start = 0, end = 0;
    try (BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in))) {
      // read and parse first line, ignore number of nodes since we're using adjacency list
      String[] firstLine = readIn.readLine().split("\s+");
      start = Integer.parseInt(firstLine[1]);
      end = Integer.parseInt(firstLine[2]);
      final int len = Integer.parseInt(firstLine[3]);

      for (int i = 0; i < len; i++) {
        // read and parse current edge
        String[] edge = readIn.readLine().split("\s+");
        final int a = Integer.parseInt(edge[0]), b = Integer.parseInt(edge[1]);
        // map node a to an empty list if it doesn't already have one
        graph.computeIfAbsent(a, k -> new ArrayList<>());
        // mark b as adjacent to a
        graph.get(a).add(b);
        // map node b to an empty list if it doesn't already have one
        // this will prevent null pointer exceptions
        graph.computeIfAbsent(b, k -> new ArrayList<>());
      }
    } catch (IOException e) {
      System.err.println("I/O error");
      System.exit(2);
    } catch (Exception e) {
      System.err.println("Test data botched");
      System.exit(1);
    }
    // perform BFS on the graph
    int result = breadthFirst(graph, start, end);
    // the method will return -1 if the end node is unreachable
    if (result < 0) {
      System.err.println("Goal unreachable");
      System.exit(3);
    }
    else {
      System.out.println(result);
    }
  }

  private static int breadthFirst(Map<Integer, List<Integer>> graph, int start, int end) {
    // the BFS queue
    Queue<Integer> queue = new ArrayDeque<>();
    // serves as both visited marker and backtrace
    Map<Integer, Integer> traceMap = new HashMap<>();
    // time optimization: if start and end are same, no need to change lines
    if (start == end) return 0;

    // literally BFS
    queue.add(start);
    // initialize the starting node and map to null
    // this is used in the tracing subroutine later
    traceMap.put(start, null);
    while (!queue.isEmpty()) {
      int node = queue.remove();
      //if this is the goal we can now backtrace
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
    // using integer class here allows for null values
    Integer current = goal;
    // trace back through the map until it's found
    for (; current != null; result++)
      current = traceMap.get(current);
    // return the number of line changes which is number of nodes - 1
    return --result;
  }
}
